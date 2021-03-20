package work.huangxin.share.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import work.huangxin.share.model.AccessToken;
import work.huangxin.share.model.CheckMessage;
import work.huangxin.share.model.WXMessage;
import work.huangxin.share.util.common.HttpClientUtil;
import work.huangxin.share.util.common.JsonUtils;

import java.nio.charset.Charset;

public class CheckMessageUtil {

    public static Boolean checkMessage(String content, WXMessage wxMessage) {


        String s = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxMessage.getWxId() + "&secret=" + wxMessage.getWxSecret());
        AccessToken accessToken = JsonUtils.jsonToPojo(s, AccessToken.class);

        String token = accessToken.getAccess_token();

        String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + token;

        String param = "{\"content\":\"" + content + "\"}";
        //创建client和post对象
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        //json形式
        post.addHeader("content-type", "image");
        //json字符串以实体的实行放到post中
        post.setEntity(new StringEntity(param, Charset.forName("utf-8")));
        HttpResponse response = null;
        try {
            //获得response对象
            response = client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            System.out.println("请求返回不正确");
        }
        String result = "";
        try {
            //获得字符串形式的结果
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        CheckMessage checkMessage = JsonUtils.jsonToPojo(result, CheckMessage.class);
        if (checkMessage.getErrcode().equals(87014)) {
            return true;
        } else {
            return false;
        }
    }


}
