package work.huangxin.share.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import work.huangxin.share.model.ThemeMessage;

import java.util.List;

public interface ThemeMessageMapper extends Mapper<ThemeMessage> {

    List<ThemeMessage> getAllTheme(@Param("use") Integer use);
}