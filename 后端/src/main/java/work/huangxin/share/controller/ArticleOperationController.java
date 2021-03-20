package work.huangxin.share.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ResponseMessage;
import work.huangxin.share.model.*;
import work.huangxin.share.service.*;
import work.huangxin.share.util.CheckAllow;
import work.huangxin.share.util.CheckMessageUtil;
import work.huangxin.share.util.status.BaseResponse;
import work.huangxin.share.util.status.CodeEnum;
import work.huangxin.share.util.status.ResponseData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wx")
public class ArticleOperationController {

    @Resource
    private ArticleOperationService articleOperationService;
    @Resource
    private UserMessageOperationService userMessageOperationService;
    @Resource
    private CommentService commentService;
    @Resource
    private ReplayMessageOperationService replayMessageOperationService;
    @Resource
    private LikeArticleService likeArticleService;
    @Resource
    private NoticeOperationService noticeOperationService;
    @Resource
    private WXMessage wxMessage;

    @PostMapping("/saveArticle")
    @Transactional
    @ApiOperation(value = "保存文章")
    public BaseResponse saveArticle(@RequestBody ArticleMessage articleMessage, HttpServletRequest request) {
        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);

        if (userId < 0) {
            return ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW);
        }
        if (CheckMessageUtil.checkMessage(articleMessage.getArticleContent(), wxMessage)) {
            return ResponseData.error(403, "检测到内容违规,请重新输入");
        }
        articleMessage.setUserId(userId);
        articleOperationService.add(articleMessage);
        return ResponseData.success();
    }


    @PostMapping("/deleteArticle/{articleId}")
    @Transactional
    @ApiOperation(value = "删除文章")
    public BaseResponse deleteArticle(@PathVariable Integer articleId, HttpServletRequest request) {
        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);

        if (userId < 0) {
            return ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW);
        }
        ArticleMessage articleMessage = new ArticleMessage();

        articleMessage.setArticleId(articleId);
        articleMessage.setUserId(userId);
        articleOperationService.delete(articleMessage);

        CommentMessage commentMessage = new CommentMessage();
        commentMessage.setArticleId(articleId);
        List<CommentMessage> list = commentService.findList(commentMessage);

        ReplayMessage replayMessage = new ReplayMessage();
        for (int i = 0; i < list.size(); i++) {
            replayMessage.setCommentId(list.get(i).getCommentId());
            replayMessageOperationService.delete(replayMessage);
        }
        commentService.delete(commentMessage);

        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setArticleId(articleId);
        noticeOperationService.delete(noticeMessage);

        return ResponseData.success();
    }

    @PostMapping("/deleteComment/{commentId}")
    @Transactional
    @ApiOperation(value = "删除评论")
    public BaseResponse deleteComment(@PathVariable Integer commentId, HttpServletRequest request) {
        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);
        CommentMessage commentMessage = new CommentMessage();
        commentMessage.setCommentId(commentId);
        commentMessage.setUserId(userId);

        commentService.delete(commentMessage);

        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setCommentId(commentId);

        noticeOperationService.delete(noticeMessage);

        ReplayMessage replayMessage = new ReplayMessage();
        replayMessage.setCommentId(commentId);

        List<ReplayMessage> list = replayMessageOperationService.findList(replayMessage);

        replayMessageOperationService.delete(replayMessage);

        for (int i = 0; i < list.size(); i++) {
            NoticeMessage noticeMessage1 = new NoticeMessage();
            noticeMessage1.setReplayId(list.get(i).getReplayId());
            noticeOperationService.delete(noticeMessage1);
        }


        return ResponseData.success();
    }

    @PostMapping("/deleteReplay/{replayId}")
    @Transactional
    @ApiOperation(value = "删除回复")
    public BaseResponse deleteReplay(@PathVariable Integer replayId, HttpServletRequest request) {
        WXSessionModel user = (WXSessionModel) request.getSession().getAttribute("user");

        ReplayMessage replayMessage = new ReplayMessage();
        replayMessage.setReplayId(replayId);
        replayMessage.setUserId(user.getUserId());
        replayMessageOperationService.deleteById(replayMessage);

        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setReplayId(replayId);
        noticeOperationService.delete(noticeMessage);

        return ResponseData.success();
    }

    @GetMapping("/getLikeArticle/{articleId}")
    @Transactional
    @ApiOperation(value = "检测文章是否被喜欢")
    public BaseResponse getLikeArticle(@PathVariable Integer articleId, HttpServletRequest request) {
        WXSessionModel user = (WXSessionModel) request.getSession().getAttribute("user");
        LikeMessage likeMessage = new LikeMessage();
        likeMessage.setArticleId(articleId);
        likeMessage.setUserId(user.getUserId());

        Integer count = likeArticleService.findCount(likeMessage);
        if (count == 0) {
            return ResponseData.success(false);
        }
        return ResponseData.success(true);
    }

    @GetMapping("/likeArticle/{articleId}")
    @Transactional
    @ApiOperation(value = "喜欢文章")
    public BaseResponse LikeArticle(@PathVariable Integer articleId, HttpServletRequest request) {
        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);

        if (userId < 0) {
            return ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW);
        }

        LikeMessage likeMessage = new LikeMessage();

        likeMessage.setArticleId(articleId);
        likeMessage.setUserId(userId);



        List<LikeMessage> list = likeArticleService.findList(likeMessage);
        if (list.size() == 0) {
            likeArticleService.add(likeMessage);

            LikeMessage likeMessage1 = likeArticleService.findList(likeMessage).get(0);

            ArticleMessage newArticleById = articleOperationService.getNewArticleById(articleId);

            NoticeMessage noticeMessage = new NoticeMessage();

            noticeMessage.setArticleId(articleId);

            noticeMessage.setNoticeType(2);
            noticeMessage.setUserId(newArticleById.getUserId());
            noticeMessage.setLikeId(likeMessage1.getLikeId());

            List<NoticeMessage> noticeMessages = noticeOperationService.findList(noticeMessage);

            if (noticeMessages.size() == 0 && !newArticleById.getUserId().equals(userId)) {

                noticeMessage.setLikeId(likeMessage1.getLikeId());
                noticeOperationService.add(noticeMessage);
            }
        } else {
            ArticleMessage newArticleById = articleOperationService.getNewArticleById(articleId);

            NoticeMessage noticeMessage = new NoticeMessage();

            noticeMessage.setArticleId(articleId);

            noticeMessage.setNoticeType(2);
            noticeMessage.setUserId(newArticleById.getUserId());
            noticeMessage.setLikeId(list.get(0).getLikeId());

            likeArticleService.delete(likeMessage);
            noticeOperationService.delete(noticeMessage);
        }

        return ResponseData.success();
    }

    @PostMapping("/checkArticle/{articleId}")
    @Transactional
    @ApiOperation("举报文章")
    public BaseResponse checkArticle(@PathVariable Integer articleId, HttpServletRequest request) {
        UserMessage userMessage = new UserMessage();
        userMessage.setUserAdmin("2");

        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);

        if (userId < 0) {
            return ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW);
        }

        List<UserMessage> list = userMessageOperationService.findList(userMessage);

        for (int i = 0; i < list.size(); i++) {
            NoticeMessage noticeMessage = new NoticeMessage();
            noticeMessage.setContent("我举报这篇文章,查看详情");
            noticeMessage.setNoticeType(6);
            noticeMessage.setArticleId(articleId);
            noticeMessage.setSendUserId(userId);
            noticeMessage.setUserId(list.get(i).getUserId());
            noticeOperationService.add(noticeMessage);
        }
        return ResponseData.success();
    }


}
