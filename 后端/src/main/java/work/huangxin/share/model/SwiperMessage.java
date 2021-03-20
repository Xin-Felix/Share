package work.huangxin.share.model;

import javax.persistence.*;

@Table(name = "`swiper_message`")
public class SwiperMessage {
    @Id
    @Column(name = "`swiper_id`")
    private Integer swiperId;

    @Column(name = "`swiper_url`")
    private String swiperUrl;

    @Column(name = "`swiper_type`")
    private String swiperType;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`article_id`")
    private Integer articleId;

    @Column(name = "`intro`")
    private String intro;

    @Column(name = "`web_url`")
    private String webUrl;

    /**
     * @return swiper_id
     */
    public Integer getSwiperId() {
        return swiperId;
    }

    /**
     * @param swiperId
     */
    public void setSwiperId(Integer swiperId) {
        this.swiperId = swiperId;
    }

    /**
     * @return swiper_url
     */
    public String getSwiperUrl() {
        return swiperUrl;
    }

    /**
     * @param swiperUrl
     */
    public void setSwiperUrl(String swiperUrl) {
        this.swiperUrl = swiperUrl;
    }

    /**
     * @return swiper_type
     */
    public String getSwiperType() {
        return swiperType;
    }

    /**
     * @param swiperType
     */
    public void setSwiperType(String swiperType) {
        this.swiperType = swiperType;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return article_id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * @return intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * @param intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * @return web_url
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * @param webUrl
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}