package work.huangxin.share.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "`notice_message`")
public class NoticeMessage {
    @Id
    @Column(name = "`notice_id`")
    private Integer noticeId;

    @Column(name = "`notice_type`")
    private Integer noticeType;

    @Column(name = "`user_id`")
    private Integer userId;//1

    @Column(name = "`replay_id`")
    private Integer replayId;//2

    @Column(name = "`like_id`")
    private Integer likeId;//3

    @Column(name = "`comment_id`")
    private Integer commentId;//4

    @Column(name = "`article_id`")
    private Integer articleId;//2/3/4

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`content`")
    private String content;//1

    @Column(name = "`send_user_id`")
    private Integer sendUserId;

    private ReplayMessage replayMessage;

    private CommentMessage commentMessage;

    private UserMessage userMessage;

    private LikeMessage likeMessage;

}