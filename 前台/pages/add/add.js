// pages/comment/comment.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    heightConfig: 0,
    navTop: 0,
    themeMessage: [],
    statusBarHeight: getApp().globalData.navTop,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,
      themeMessage: wx.getStorageSync("themeMessage")
    })

  },

  add_detail (e) {
    wx.navigateTo({
      url: '/pages/add_detail/index/index?theme_id=' + e.currentTarget.id,
    });
  },
 
 
})