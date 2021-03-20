package work.huangxin.share.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import work.huangxin.share.model.CommentMessage;
import work.huangxin.share.model.ReplayMessage;
import work.huangxin.share.model.ThemeMessage;
import work.huangxin.share.model.WXSessionModel;
import work.huangxin.share.service.ArticleOperationService;
import work.huangxin.share.service.CommentService;
import work.huangxin.share.service.ReplayMessageOperationService;
import work.huangxin.share.service.ThemeMessageOperationService;
import work.huangxin.share.util.status.BaseResponse;
import work.huangxin.share.util.status.ResponseData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wx/api")
public class GlobalDataController {

    @Resource
    private ArticleOperationService articleOperationService;
    @Resource
    private ThemeMessageOperationService themeMessageOperationService;

    @Resource
    private CommentService commentService;
    @Resource
    private ReplayMessageOperationService replayMessageOperationService;


    @GetMapping("/getNewArticle/{pageNumber}")
    @ApiOperation("获取最新文章")
    public BaseResponse getNewArticle(@PathVariable Integer pageNumber) {
        return ResponseData.success(articleOperationService.getNewArticle(pageNumber, null, null, null));
    }

    @GetMapping("/getNewArticleById/{articleId}")
    @ApiOperation("通过id获取文章")
    public BaseResponse getNewArticleById(@PathVariable Integer articleId) {
        return ResponseData.success(articleOperationService.getNewArticleById(articleId));
    }

    @GetMapping("/getCommentById/{articleId}/{pageNumber}")
    @ApiOperation("获取评论以及回复")
    public BaseResponse getCommentById(@PathVariable Integer articleId, @PathVariable Integer pageNumber) {
        List<CommentMessage> list = commentService.getCommentById(pageNumber, articleId).getList();
        int number = list.size();
        for (int i = 0; i < number; i++) {
            list.get(i).setReplayMessageList(replayMessageOperationService.getReplayContent(list.get(i).getCommentId()));
        }

        PageInfo<CommentMessage> of = PageInfo.of(list);
        return ResponseData.success(of);
    }

    @GetMapping("/getAllTheme")
    @ApiOperation("获取主题信息")
    public BaseResponse getAllTheme() {
        return ResponseData.success(themeMessageOperationService.getAllTheme(1));
    }

    @GetMapping("/getNewArticleByThemeId/{themeId}/{pageNumber}")
    @ApiOperation("通过主题获取文章")
    public BaseResponse getNewArticleByThemeId(@PathVariable Integer pageNumber, @PathVariable Integer themeId) {
        return ResponseData.success(articleOperationService.getNewArticle(pageNumber, null, themeId, null));
    }

    @GetMapping("/getAllLikeArticle/{pageNumber}")
    @ApiOperation("或者精选文章")
    public BaseResponse getAllLikeArticle(@PathVariable Integer pageNumber) {
        return ResponseData.success(articleOperationService.getAllLikeArticle(pageNumber));
    }


}
