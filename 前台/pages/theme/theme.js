// pages/me/me.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    heightConfig: 0,
    navTop: 0,
    theme_id: -1,
    themeMessage: [],
    articleMessage: [],
    new_article: [], //最新的几篇文章
    new_end: false, //是否加载完成
    new_page: 1, //当前page页数
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,
      theme_id: options.theme_id,
      themeMessage: wx.getStorageSync("themeMessage")
    })
    this.loadMessage(options.theme_id, this.data.new_page)

  },

  newArticleLoadMessage() {
wx.showLoading({
  title: '加载中',
})

    this.loadMessage(this.data.theme_id, ++this.data.new_page);
    wx.hideLoading({  })
  },

  loadMessage(theme_id, index) {
    let that = this;

    if (this.data.new_end) {
      return;
    }


    wx.request({
      url: getApp().globalData.url + '/api/getNewArticleByThemeId/' + theme_id + "/" + index,
      method: 'GET',
      success: (result) => {
        if (that.data.new_page > result.data.data.pages) {
          that.setData({
            new_end: true
          })
          return
        }

        if (that.data.new_page == 1) {
          that.setData({
            new_article: result.data.data.list
          })
        } else {
          that.setData({
            new_article: that.data.new_article.concat(result.data.data.list)
          })
        }

      },

    });
  },

  //查看用户信息
  check_user_message(e) {
    wx.navigateTo({
      url: '/pages/me/me?id=' + e.currentTarget.id
    });
  },
  /**
   * 
   * @param {跳转帖子详情} e 
   */
  message_detail(e) {
    wx.navigateTo({
      url: '/pages/message_detail/message_detail?article_id=' + e.currentTarget.id,
    });
  },

})