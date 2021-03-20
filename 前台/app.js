
App({
  globalData: {
    navHeight: "",
    navTop: "",
    windowHeight: "",
    windowWidth: "",
    // url: "http://127.0.0.1:8081/wx",
    url: "http://127.0.0.1:8081/wx",//服务器地址需要带 /wx
    imageUrl: "https://*.oss-accelerate.aliyuncs.com/"//修改成oss地址,不然无法查看图片
  },
  onLaunch: function () {
    // wx.setEnableDebug({
    //   enableDebug: true,
    // })


    let menuButtonObject = wx.getMenuButtonBoundingClientRect();
    wx.getSystemInfo({
      success: res => {
        let statusBarHeight = res.statusBarHeight,
          navTop = menuButtonObject.top, //胶囊按钮与顶部的距离
          navHeight = statusBarHeight + menuButtonObject.height + (menuButtonObject.top - statusBarHeight) * 2; //导航高度
        this.globalData.navHeight = navHeight;
        this.globalData.navTop = navTop;
        this.globalData.windowHeight = res.windowHeight;
        this.globalData.windowWidth = res.windowWidth
      },
      fail(err) {
        console.log(err);
      }
    })

  },

 checkMessage(contents) {
    wx.request({
      url: getApp().globalData.url + '/getAccessToken/',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'POST',
      success: (result) => {
        wx.request({
          url: 'https://api.weixin.qq.com/wxa/msg_sec_check?access_token=' + result.data.data,
          data: {
            content: contents
          },
          method: "post",
          success: function (e) {
            if (e.data.errcode != 0) {
              wx.showModal({
                title: "警告",
                content: "内容包含违规信息",
                showCancel: false,
                confirmText: "重新编辑"
              })
              return true;
            }
          }
        })
      }
    });
    return false;

  }


})
