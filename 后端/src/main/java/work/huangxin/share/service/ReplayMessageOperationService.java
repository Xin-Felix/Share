package work.huangxin.share.service;

import org.springframework.stereotype.Service;
import work.huangxin.share.basemapper.SameService;
import work.huangxin.share.mapper.ReplayMessageMapper;
import work.huangxin.share.model.ReplayMessage;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReplayMessageOperationService extends SameService<ReplayMessage> {

    @Resource
    private ReplayMessageMapper replayMessageMapper;


    public List<ReplayMessage> getReplayContent(Integer commentId) {

        return replayMessageMapper.getReplayContent(commentId);

    }

    /**
     * 获取回复的人
     *
     * @param replayId
     * @return
     */
    public ReplayMessage getReplayMessage(Integer replayId) {
        return replayMessageMapper.getReplayMessage(replayId);
    }
}
