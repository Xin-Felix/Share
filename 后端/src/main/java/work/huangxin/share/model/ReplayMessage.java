package work.huangxin.share.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "`replay_message`")
public class ReplayMessage {
    @Id
    @Column(name = "`replay_id`")
    private Integer replayId;

    @Column(name = "`comment_id`")
    private Integer commentId;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`replay_content`")
    private String replayContent;

    @Column(name = "`create_time`")
    private Date createTime;

    private Integer replayUserId;

    private UserMessage replayUserMessage;

    private UserMessage commentUserMessage;


}