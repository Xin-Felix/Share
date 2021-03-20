package work.huangxin.share.service;

import org.springframework.stereotype.Service;
import work.huangxin.share.basemapper.SameService;
import work.huangxin.share.mapper.UserMessageMapper;
import work.huangxin.share.model.UserMessage;

import javax.annotation.Resource;

@Service
public class UserMessageOperationService extends SameService<UserMessage> {


    @Resource
    private UserMessageMapper userMessageMapper;


    public UserMessage getUserMessage(Integer userId){
        return userMessageMapper.getUserMessage(userId);
    }



}
