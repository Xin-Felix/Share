package work.huangxin.share.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import work.huangxin.share.model.ReplayMessage;

import java.util.List;

public interface ReplayMessageMapper extends Mapper<ReplayMessage> {


    List<ReplayMessage> getReplayContent(@Param("commentId") Integer commentId);


    ReplayMessage getReplayMessage(@Param("replayId") Integer replayId);
}