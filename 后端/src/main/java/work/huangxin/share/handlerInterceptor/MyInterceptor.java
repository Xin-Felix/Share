package work.huangxin.share.handlerInterceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import work.huangxin.share.model.Audience;
import work.huangxin.share.model.UserMessage;
import work.huangxin.share.model.WXSessionModel;
import work.huangxin.share.util.JWTUtil;
import work.huangxin.share.util.RedisUtil;
import work.huangxin.share.util.common.JsonUtils;
import work.huangxin.share.util.status.CodeEnum;
import work.huangxin.share.util.status.ResponseData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyInterceptor implements HandlerInterceptor {


    @Resource
    private Audience audience;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            //测试服务器支持方法
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        } else {
            final String token = authHeader;//获取 token
            try {
                if (audience == null) {//获取配置信息
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }
                final Claims claims = JWTUtil.parseJWT(token, audience.getBase64Secret());//解密token,获取token内容
                if (claims == null) {//解析失败,token有问题
                    this.returnJson(response, JsonUtils.objectToJson(ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW, "令牌出现错误")));
                    return false;
                }
                WXSessionModel user = JsonUtils.jsonToPojo(claims.get("user").toString(), WXSessionModel.class);//解析储存的user信息

                request.getSession().setAttribute("user", user);

            } catch (final Exception e) {
                this.returnJson(response, JsonUtils.objectToJson(ResponseData.out(CodeEnum.SIGNATURE_NOT_ALLOW, "出现致命错误")));
                return false;
            }
        }
        return true;
    }

    /**
     * 返回客户端数据
     */
    private void returnJson(HttpServletResponse response, String result) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(result);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
