const app = getApp()

Component({

  properties: {
    title: {
      type: String,
      value: '标题'
    },
    back: {
      type: Boolean,
      value: false
    },
    home: {
      type: Boolean,
      value: false
    }
  },
  data: {
    statusBarHeight: app.globalData.navTop + 'px',
    navigationBarHeight: (app.globalData.navHeight + 44) + 'px'
  },

  methods: {
    backHome: function () {
      wx.redirectTo({
        url: '/pages/index/index',
      })

    },
    back: function () {
      wx.navigateBack({
        delta: 1
      })
    },
    loading: {
      type: Boolean,
      value: false
    }
  }
})