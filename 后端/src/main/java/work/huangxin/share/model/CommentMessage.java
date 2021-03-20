package work.huangxin.share.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@Table(name = "`comment_message`")
public class CommentMessage {
    @Id
    @Column(name = "`comment_id`")
    private Integer commentId;

    @Column(name = "`comment_content`")
    private String commentContent;

    @Column(name = "`article_id`")
    private Integer articleId;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`create_time`")
    private Date createTime;

    private UserMessage userMessage;

    private List<ReplayMessage> replayMessageList;


}