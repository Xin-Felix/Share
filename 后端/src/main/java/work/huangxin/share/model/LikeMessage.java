package work.huangxin.share.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "`like_message`")
public class LikeMessage {
    @Id
    @Column(name = "`like_id`")
    private Integer likeId;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`article_id`")
    private Integer articleId;

    @Column(name = "`create_time`")
    private Date createTime;

    private UserMessage userMessage;


}