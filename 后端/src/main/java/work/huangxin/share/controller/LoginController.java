package work.huangxin.share.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.huangxin.share.model.*;
import work.huangxin.share.service.UserMessageOperationService;
import work.huangxin.share.util.JWTUtil;
import work.huangxin.share.util.WxGetPhoneUtils;
import work.huangxin.share.util.common.HttpClientUtil;
import work.huangxin.share.util.common.JsonUtils;
import work.huangxin.share.util.status.BaseResponse;
import work.huangxin.share.util.status.CodeEnum;
import work.huangxin.share.util.status.ResponseData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/wx")
public class LoginController {

    @Resource
    private UserMessageOperationService userMessageOperationService;
    @Resource
    private WXMessage wxMessage;
    @Resource
    private Audience audience;

    /**
     * 绑定手机号
     *
     * @param decodePhoneMessage
     * @return
     */
    @PostMapping("/getPhoneNumber")
    @Transactional
    @ApiOperation(value = "绑定手机号")
    public BaseResponse getPhone(@RequestBody DecodePhoneMessage decodePhoneMessage, HttpServletRequest request) {

        WXSessionModel user = (WXSessionModel) request.getSession().getAttribute("user");
        UserMessage userMessage = new UserMessage();
        PhoneMessage phoneMessage;

        try {
            phoneMessage = JsonUtils.jsonToPojo(WxGetPhoneUtils.wxDecrypt(decodePhoneMessage.getEncryptedData(), user.getSession_key(), decodePhoneMessage.getIv()), PhoneMessage.class);
            userMessage.setUserToken(user.getOpenid());
            userMessage.setUserId(user.getUserId());
            userMessage.setUserPhone(phoneMessage.getPhoneNumber());
            userMessageOperationService.update(userMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.out(CodeEnum.FAIL, "获取失败");
        }
        return ResponseData.success(phoneMessage);
    }

    /**
     * 登录功能
     */
    @PostMapping("/Login")
    @Transactional
    @ApiOperation(value = "登录")
    public BaseResponse toLogin(String code, @RequestBody UserMessage userMessage) {
        WXSessionModel wxSessionModel = null;
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", wxMessage.getWxId());
        param.put("secret", wxMessage.getWxSecret());
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String wxResult = HttpClientUtil.doGet(url, param);
        wxSessionModel = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
        String openid = wxSessionModel.getOpenid();

        UserMessage tem = new UserMessage();

        tem.setUserToken(openid);

        List<UserMessage> list = userMessageOperationService.findList(tem);
        if (list.size() == 0) {
            userMessage.setUserMotto("这个人很懒,什么也没有留下");
            userMessage.setUserToken(openid);
            userMessageOperationService.add(userMessage);

            tem = new UserMessage();
            tem.setUserToken(openid);

            wxSessionModel.setUserId(userMessageOperationService.findList(tem).get(0).getUserId());

            return ResponseData.success(JWTUtil.createJWT(audience, wxSessionModel));
        }

        tem = new UserMessage();
        tem.setUserToken(openid);

        wxSessionModel.setUserId(userMessageOperationService.findList(tem).get(0).getUserId());

        userMessage.setUserId(list.get(0).getUserId());

        userMessageOperationService.update(userMessage);

        return ResponseData.success(JWTUtil.createJWT(audience, wxSessionModel));
    }
}
