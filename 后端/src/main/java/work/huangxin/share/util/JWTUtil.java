package work.huangxin.share.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import work.huangxin.share.model.Audience;
import work.huangxin.share.util.common.JsonUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTUtil {

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 构建jwt
     * <p>
     * , long TTLMillis 时间说明,
     */
    public static String createJWT(Audience audience, Object o) {


        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT");


        builder.claim("user", JsonUtils.objectToJson(o));

        builder.claim("time", DateUtils.getMillis());

        builder.setIssuer(audience.getName()).setAudience(audience.getClientId()).signWith(signatureAlgorithm, signingKey);

        //添加Token过期时间,使用redis,不用内置时间
//        if (TTLMillis >= 0) {
//            long expMillis = nowMillis + TTLMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp).setNotBefore(now);
//        }

        //生成JWT
        return builder.compact();
    }


}