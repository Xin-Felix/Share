package work.huangxin.share.service;

import org.springframework.stereotype.Service;
import work.huangxin.share.basemapper.SameService;
import work.huangxin.share.mapper.ThemeMessageMapper;
import work.huangxin.share.model.ThemeMessage;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThemeMessageOperationService extends SameService<ThemeMessage> {
    @Resource
    private ThemeMessageMapper themeMessageMapper;


    /**
     * 获取所有主题
     *
     * @param use
     * @return
     */
    public List<ThemeMessage> getAllTheme(Integer use) {
        return themeMessageMapper.getAllTheme(use);
    }
}
