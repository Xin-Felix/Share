Page({

  /**
   * 页面的初始数据
   */
  data: {
    loadShow: false,
    isShow: false,
    navTop: 0,
    topCa: ["推荐", "主页", "消息"],
    swiperMessage: [],
    currentIndex: 1,
    heightConfig: 0,
    widthConfig: 0,
    userInfo: null, //用户信息
    userId: "", //用户id
    new_article: [], //最新的几篇文章
    new_end: false, //是否加载完成
    new_page: 1, //当前page页数
    themeMessage: [], //主题信息

    //精选文章
    new_likeArticle: [], //最新的几篇文章
    new_likeArticle_end: false, //是否加载完成
    new_likeArticle_page: 1, //当前page页数
    //刷新栏目
    hideHeader: true,
    refreshTime: '', // 刷新的时间 
    /**
     * 下拉
     */

    height: 0, // scroll-wrap 的高度，这个高度是固定的
    inner_height: 0, // inner-wrap 的高度，这个高度是动态的
    scroll_top: 0, // 滚动到位置。
    start_scroll: 0, // 滚动前的位置。
    touch_down: 0, // 触摸时候的 Y 的位置

    /**
     * 新消息
     */
    new_notice: [],
    new_notice_end: false, //是否加载完成
    new_notice_page: 1, //当前page页数
    notice_type: ["私信", "喜欢", "评论", "回复", "系统"],
    notice_type_message: ["", "我喜欢你的这篇文章,点击查看~", "", "", ""]
  },
  checkUserNoticeMessage(e) {
    wx.showModal({
      title: e.currentTarget.id,
      confirmText: "去看看",
      cancelText: "取消",
      success: res => {
        if (res.confirm) {
          wx.navigateTo({
            url: '/pages/me/me?id=' + e.currentTarget.dataset.userid,
          })
        }
      }
    })
  },
  deleteNotice(e) {
    let that = this;
    wx.showModal({
      title: "提示",
      content: "是否删除这条消息",
      confirmText: "删除",
      success: res => {

        if (res.confirm) {
          wx.showLoading({
            title: '删除中~',
          })
          wx.request({
            url: getApp().globalData.url + '/deleteNotice/' + e.currentTarget.id,
            header: {
              "authorization": wx.getStorageSync("token")
            },
            method: 'POST',
            success: (result) => {
              if (result.data.code == 200) {
                wx.showToast({
                  title: '删除成功',
                })
                that.onLoad();
              } else {
                wx.showModal({
                  title: '提示',
                  content: result.data.msg + '，错误码：' + result.data.code,
                  confirmText: '确定',
                  showCancel: false,
                })

              }

            }
          });
        }
      }
    })
  },


  previewImage: function (e) {
    let that = this
    let arr = [];
    let reg = /(?<=(src="))[^"]*?(?=")/ig
    let allSrc = that.data.article.match(reg)
    for (let i = 0; i < allSrc.length; i++) {
      arr.push(allSrc[i])
    }
    wx.previewImage({
      current: arr, // 当前显示图片的http链接  
      urls: arr // 需要预览的图片http链接列表  
    })
  },
  //点击轮播图
  touchImage(e) {
    var swiper = this.data.swiperMessage[e.currentTarget.id];
    if (swiper.swiperType == 1) {
      wx.showModal({
        title: '提示',
        content: swiper.intro,
        showCancel: false,
        cancelColor: '#000000',
        confirmText: '确定',
      });
    } else if (swiper.swiperType == 2) {
      this.check_user_message({"currentTarget":{"id":swiper.userId}})
    }else if(swiper.swiperType == 3){
      this.message_detail({"currentTarget":{"id":swiper.articleId}})
    }
  },

  getUserInfo: function (e) {
    let that = this;

    if (e.detail.userInfo == null) {
      wx.showToast({
        title: '取消登录',
        icon: "none"
      })
      return;
    }
    var listMessage = {
      userNickname: e.detail.userInfo.nickName,
      userGender: e.detail.userInfo.gender,
      userAvatar: e.detail.userInfo.avatarUrl,
      userCity: e.detail.userInfo.city,
      userProvince: e.detail.userInfo.province,
      userCountry: e.detail.userInfo.country,
    }

    wx.login({
      success: function (res) {
        wx.showLoading({
          title: '登陆中~',
        })
        // 获取登录的临时凭证
        var code = res.code;
        // 调用后端，获取微信的session_key, secret
        wx.request({
          url: getApp().globalData.url + "/Login?code=" + code,
          method: "POST",
          data: listMessage,
          success: function (result) {
            if (result.data.code == 200) {
              wx.showToast({
                title: '登陆成功',
              })
              that.onLoad()
            } else {
              wx.showModal({
                title: '失败',
                content: '出现错误,请联系管理员',
              })
              return
            }
            wx.setStorageSync("token", result.data.data);
            wx.setStorageSync("userInfo", e.detail.userInfo);
            that.setData({
              isShow: true,
              userInfo: e.detail.userInfo
            })
          }
        })
      }
    })
    wx.hideLoading()
  },
  /**
   * 暂不登录
   */
  exit() {
    this.setData({
      isShow: true
    })
  },
  message_detail(e) {
    wx.navigateTo({
      url: '/pages/message_detail/message_detail?article_id=' + e.currentTarget.id,
    });
  },
  swiperChange(e) {
    this.setData({
      currentIndex: e.detail.current
    })
  },
  tapChange(e) {
    this.setData({
      currentIndex: e.currentTarget.id
    })
  },
  comment() {
    wx.navigateTo({
      url: '/pages/comment/comment'
    });
  },
  to_me() {
    var result = this.checkLogin();
    if (result) {
      wx.navigateTo({
        url: '/pages/me/me?id=' + -1
      });
    }
  },
  //查看用户信息
  check_user_message(e) {
    console.log(e)
    var result = this.checkLogin();
    if (result) {
      wx.navigateTo({
        url: '/pages/me/me?id=' + e.currentTarget.id
      });
    }
  },
  add() {
    var result = this.checkLogin();
    if (result) {
      wx.navigateTo({
        url: '/pages/add/add',
      });
    }
  },
  theme(e) {
    if (e.currentTarget.id == "-1") {
      return
    }
    wx.navigateTo({
      url: '/pages/theme/theme?theme_id=' + e.currentTarget.id,
    });

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    this.checkLogin();
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,
      widthConfig: getApp().globalData.windowWidth,
    })
    let that = this;
    this.createSelectorQuery().select('#scroll-wrap').boundingClientRect(res => {
      that.setData({
        height: res.height
      })
    }).exec()
    /**
     * 获取主题
     */
    wx.request({
      url: getApp().globalData.url + '/api/getAllTheme',
      method: 'GET',
      success: (result) => {
        that.setData({
          themeMessage: result.data.data
        })
        wx.setStorageSync("themeMessage", result.data.data);
      },
    });

    /**
     * 获取轮播图
     */
    wx.request({
      url: getApp().globalData.url + '/api/getSwiperMessage',
      method: 'GET',
      success: (result) => {
        that.setData({
          swiperMessage: result.data.data
        })
      },
    });
    this.setData({
      new_end: false,
      new_page: 1,
      new_likeArticle_end: false,
      new_likeArticle_page: 1,
      // new_article: [],
      // new_notice: [],
      new_notice_end: false, //是否加载完成
      new_notice_page: 1, //当前page页数
    })

    this.getNewArticle(1) //获取最新的文章
    this.loadNotice(1) //获取最新的消息
    this.getAllLikeArticle(1) //获取精选文章
    this.setData({
      widthConfig: getApp().globalData.windowWidth,
      heightConfig: getApp().globalData.windowHeight,
    })

  },

  newNoticeLoadMessage() {

    wx.showLoading({
      title: '加载中',
    })
    this.loadNotice(++this.data.new_notice_page)
    wx.hideLoading({})
  },
  newArticelLoadMessage() {
    wx.showLoading({
      title: '加载中',
    })
    this.getNewArticle(++this.data.new_page)
    wx.hideLoading({})
  },
  flush() {
    this.onLoad();
    var self = this;

    self.setData({
      refreshTime: self.getTime(),
      hideHeader: false
    })
    setTimeout(function () {
      self.setData({
        hideHeader: true
      })
    }, 1000);

  },
  getTime() { //获取时间
    var date = new Date();

    // var year = date.getFullYear();
    // var month = date.getMonth();
    // var day = date.getDate();

    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();

    //这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
    if (hour < 10) {
      hour = '0' + hour;
    }
    if (minute < 10) {
      minute = '0' + minute;
    }
    if (second < 10) {
      second = '0' + second;
    }


    var x = date.getDay(); //获取星期


    return hour + ':' + minute + ':' + second

  },

  //加载新消息
  loadNotice(index) {


    if (this.data.new_notice_end) {
      return;
    }

    let that = this;
    wx.request({
      url: getApp().globalData.url + '/getNoticeMessage/' + index,
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      responseType: 'text',
      success: (result) => {



        if (that.data.new_notice_page == 1) {
          that.setData({
            new_notice: result.data.data.list
          })
        } else {
          that.setData({
            new_notice: that.data.new_notice.concat(result.data.data.list)
          })
        }
        if (that.data.new_notice_page >= result.data.data.pages) {
          that.setData({
            new_notice_end: true
          })
          return
        }


      }
    });
  },
  likeArticle() {
    wx.showLoading({
      title: '加载中',
    })
    this.getAllLikeArticle(++this.data.new_likeArticle_page)
    wx.hideLoading({})
  },
  getAllLikeArticle(index) {
    let that = this;
    /**
     * 获取最新的几篇文章
     */
    if (this.data.new_likeArticle_end) {
      return;
    }

    wx.request({
      url: getApp().globalData.url + '/api/getAllLikeArticle/' + index,
      method: 'GET',
      success: (result) => {


        if (that.data.new_likeArticle_page > result.data.data.pages) {
          that.setData({
            new_likeArticle_end: true
          })
          return
        }

        if (that.data.new_likeArticle_page == 1) {
          that.setData({
            new_likeArticle: result.data.data.list
          })
        } else {
          that.setData({
            new_likeArticle: that.data.new_likeArticle.concat(result.data.data.list)
          })
        }

      },
    });
  },

  getNewArticle(index) {
    let that = this;
    /**
     * 获取最新的几篇文章
     */
    if (this.data.new_end) {
      return;
    }

    wx.request({
      url: getApp().globalData.url + '/api/getNewArticle/' + index,
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



  checkLogin() { //检查登录
    var userInfo = wx.getStorageSync("userInfo");
    var token = wx.getStorageSync("token");
    if (userInfo == "" || userInfo == null || token == "" || token == null) {
      this.setData({
        isShow: false,
        userInfo: null
      })
      return false;
    } else {
      this.setData({
        userInfo: userInfo,
        isShow: true
      })
      return true
    }
  },




  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})