package work.huangxin.share.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "`user_message`")
public class UserMessage {
    @Id
    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`user_nickname`")
    private String userNickname;

    @Column(name = "`user_gender`")
    private Byte userGender;

    @Column(name = "`user_token`")
    private String userToken;

    @Column(name = "`user_avatar`")
    private String userAvatar;

    @Column(name = "`user_other`")
    private String userOther;

    @Column(name = "`user_city`")
    private String userCity;

    @Column(name = "`user_age`")
    private String userAge;

    @Column(name = "`user_province`")
    private String userProvince;

    @Column(name = "`user_country`")
    private String userCountry;

    @Column(name = "`user_motto`")
    private String userMotto;

    @Column(name = "`user_phone`")
    private String userPhone;

    @Column(name = "`create_time`")
    private Date createTime;

    private String userAllow;

    private String userAdmin;


}