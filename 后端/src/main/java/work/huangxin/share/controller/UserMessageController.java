package work.huangxin.share.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import work.huangxin.share.model.UserMessage;
import work.huangxin.share.model.WXMessage;
import work.huangxin.share.model.WXSessionModel;
import work.huangxin.share.service.ArticleOperationService;
import work.huangxin.share.service.UserMessageOperationService;
import work.huangxin.share.util.CheckMessageUtil;
import work.huangxin.share.util.status.BaseResponse;
import work.huangxin.share.util.status.CodeEnum;
import work.huangxin.share.util.status.ResponseData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/wx")
public class UserMessageController {

    @Resource
    private UserMessageOperationService userMessageOperationService;
    @Resource
    private ArticleOperationService articleOperationService;
    @Resource
    private WXMessage wxMessage;

    /**
     * 获取用户资料
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserMessage/{id}")
    @ApiOperation(value = "获取用户资料")
    public BaseResponse getUserMessage(@PathVariable Integer id, HttpServletRequest request) {
        UserMessage userMessage = null;

        WXSessionModel wxSessionModel = (WXSessionModel) request.getSession().getAttribute("user");

        if (id == -1 || wxSessionModel.getUserId() == id) {
            //获取自己
            userMessage = userMessageOperationService.getById(wxSessionModel.getUserId());
        } else {
            userMessage = userMessageOperationService.getById(id);
            //获取他人
            userMessage.setUserPhone(null);
            userMessage.setUserToken(null);
            userMessage.setUserId(null);
        }
        return ResponseData.out(CodeEnum.SUCCESS, userMessage);
    }

    /**
     * 更改或者添加用户资料
     */
    @PostMapping("/changeUserMessage")
    @Transactional
    @ApiOperation(value = "更改用户资料")
    public BaseResponse changeUserMessage(@RequestBody UserMessage userMessage) {

        if (CheckMessageUtil.checkMessage(userMessage.getUserMotto(), wxMessage)) {
            return ResponseData.error(403, "检测到内容违规,请重新输入");
        }


        userMessageOperationService.update(userMessage);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @GetMapping("/getUserArticle/{userId}/{pageNumber}")
    @ApiOperation(value = "获取用户所有文章")
    public BaseResponse getUserArticle(@PathVariable Integer userId, @PathVariable Integer pageNumber, HttpServletRequest request) {
        if (userId == -1) {
            WXSessionModel wxSessionModel = (WXSessionModel) request.getSession().getAttribute("user");
            return ResponseData.success(articleOperationService.getNewArticle(pageNumber, null, null, wxSessionModel.getUserId()));
        } else {
            return ResponseData.success(articleOperationService.getNewArticle(pageNumber, null, null, userId));
        }
    }

    @GetMapping("/getUserId")
    @ApiOperation("获取用户ID")
    public static BaseResponse getUserId(HttpServletRequest request) {

        WXSessionModel wxSessionModel = (WXSessionModel) request.getSession().getAttribute("user");

        return ResponseData.success(wxSessionModel.getUserId());
    }


}
