// pages/welcome/welcome.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    heightConfig: 0,
    widthConfig: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      widthConfig: getApp().globalData.windowWidth,
      heightConfig: getApp().globalData.windowHeight,
    })

    setTimeout(() => {
      wx.redirectTo({
        url: '/pages/index/index'
      });
    }, 3000);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },
 
})