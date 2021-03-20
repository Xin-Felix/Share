package work.huangxin.share.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import work.huangxin.share.model.ArticleMessage;

import java.util.List;

public interface ArticleMessageMapper extends Mapper<ArticleMessage> {

    /**
     * 获取最新的文章
     *
     * @return
     */
    List<ArticleMessage> getNewArticle(@Param("articleId") Integer articleId, @Param("themeId") Integer themeId, @Param("userId") Integer userId);

    /**
     * 通过id获取文章
     */

    ArticleMessage getNewArticleById(@Param("articleId") Integer articleId);

    /**
     * 获取精选文章
     * @return
     */
    List<ArticleMessage> getAllLikeArticle();
}