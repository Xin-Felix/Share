package work.huangxin.share.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wx") //接收application.wx
public class WXMessage {


    private String wxId;


    private String wxSecret;
}
