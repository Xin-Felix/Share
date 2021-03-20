package work.huangxin.share.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
public class NoticeMessageController {
    @Resource
    private NoticeOperationService noticeOperationService;
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
    private WXMessage wxMessage;

    @GetMapping("/getNoticeMessage/{pageNumber}")
    public BaseResponse getNoticeMessage(@PathVariable Integer pageNumber, HttpServletRequest request) {

        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);


        List<NoticeMessage> list = noticeOperationService.getAllNoticeById(userId, pageNumber).getList();

        for (int i = 0; i < list.size(); i++) {
            NoticeMessage noticeMessage = list.get(i);
            switch (noticeMessage.getNoticeType()) {
                case 1://私信
                case 6:
                    list.get(i).setUserMessage(userMessageOperationService.getUserMessage(noticeMessage.getSendUserId()));
                    break;
                case 2://点赞
                    list.get(i).setLikeMessage(likeArticleService.getLikeMessage(noticeMessage.getLikeId()));
                    break;
                case 3://评论
                    list.get(i).setCommentMessage(commentService.getCommentMessage(noticeMessage.getCommentId()));
                    break;
                case 4://回复
                    ReplayMessage replayMessage = replayMessageOperationService.getReplayMessage(noticeMessage.getReplayId());
                    if (replayMessage.getReplayUserId() != null) {
                        replayMessage.setCommentUserMessage(replayMessage.getReplayUserMessage());
                    }
                    list.get(i).setReplayMessage(replayMessage);
                    break;
                case 5://系统回复
                    break;
            }
        }

        return ResponseData.success(new PageInfo<>(list));
    }

    @Transactional
    @PostMapping("/saveMiniMessage")
    @ApiOperation("私信某人")
    public BaseResponse saveMiniMessage(@RequestBody NoticeMessage noticeMessage, HttpServletRequest request) {
        Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);

        if (userId < 0) {
            return ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW);
        }

        if (CheckMessageUtil.checkMessage(noticeMessage.getContent(), wxMessage)) {
            return ResponseData.error(403, "检测到内容违规,请重新输入");
        }


        noticeMessage.setSendUserId(userId);
        noticeMessage.setNoticeType(1);
        noticeOperationService.add(noticeMessage);

        return ResponseData.success();
    }

    @Transactional
    @PostMapping("/deleteNotice/{noticeId}")
    @ApiOperation("删除消息")
    public BaseResponse deleteNotice(@PathVariable Integer noticeId, HttpServletRequest request) {
        UserMessage userMessage = CheckAllow.getUserMessage(userMessageOperationService, request);
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setNoticeId(noticeId);
        noticeMessage.setUserId(userMessage.getUserId());
        noticeOperationService.delete(noticeMessage);
        return ResponseData.success();

    }


}
