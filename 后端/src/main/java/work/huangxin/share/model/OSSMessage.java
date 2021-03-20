package work.huangxin.share.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "oss") //接收application.oss
public class OSSMessage {


    private String ossId;


    private String ossSecret;

}
