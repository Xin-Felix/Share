const app = getApp()

const api = app.globalData.api
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShow: false,
    heightConfig: 0,
    navTop: 0,
    new_article: [], //最新的几篇文章
    new_end: false, //是否加载完成
    new_page: 1, //当前page页数
    themeMessage: [], //主题信息
    motto: "",
    isReplay: false,
    notice: {
      userId: "",
      content: ""
    },
    userInfo: {
      "userId": 1,
      "userNickname": "",
      "userGender": 1,
      "userToken": "",
      "userAvatar": "/image/user.png",
      "userOther": "",
      "userCity": "",
      "userAge": "",
      "userProvince": "",
      "userCountry": "",
      "userMotto": "这个人很懒,什么也没有留下",
      "userPhone": "",
      "createTime": "2020-04-29 12:37"
    },
  },
  inputReplay(e) {
    this.setData({
      'notice.content': e.detail.value
    })

  },
  replayPop(e) {
    this.setData({
      isReplay: !this.data.isReplay,
    })
  },


  sendReplay() {
    let that = this;




    if (that.data.notice.content.length == 0) {

      wx.showToast({
        title: '至少输入一个字',
      })
      return

    }


    wx.showModal({
      title: "提示",
      content: "是否私信",
      success: (res) => {
        if (res.confirm) {


          that.setData({
            isReplay: false,
          })

          wx.showLoading({
            title: '发送中',
          })

          wx.request({
            url: getApp().globalData.url + '/saveMiniMessage',
            header: {
              "authorization": wx.getStorageSync("token")
            },
            data: that.data.notice,
            method: 'post',
            success: (result) => {
              if (result.data.code == 200) {
            wx.showToast({
              title: '成功',
            })
              } else {
                wx.showModal({
                  title: '提示',
                  content: result.data.msg + '，错误码：' + result.data.code,
                  confirmText: '确定',
                  showCancel: false,
                });
              }
              that.setData({
                'notice.content': ""
              })
              wx.hideLoading({})
            }
          });
        }

      }
    })

  },
  //跳转文章
  message_detail(e) {
    wx.navigateTo({
      url: '/pages/message_detail/message_detail?article_id=' + e.currentTarget.id,
    });
  },
  getPhoneNumber: function (e) {
    var that = this;
    if (e.detail.encryptedData == undefined) {
      wx.showToast({
        title: '取消授权',
        icon: "none"
      })
      return;
    }
    wx.request({
      url: getApp().globalData.url + '/getPhoneNumber/',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      data: JSON.stringify({
        encryptedData: e.detail.encryptedData,
        sessionId: "",
        iv: e.detail.iv
      }),
      method: 'post',
      success: (result) => {
        if (result.statusCode != 200) {
          wx.showModal({
            title: '提示',
            content: '服务器连接失败',
          })
          return;
        }
        if (result.data.code != 200) {
          wx.showModal({
            title: '提示',
            content: '绑定失败，请退出登录重试',
          })
          return;
        }
        that.setData({
          'userInfo.userPhone': result.data.data.phoneNumber
        })
        wx.showToast({
          title: '绑定成功',
        })
        wx.setStorageSync('userPhone', result.data.data.phoneNumber)
      }
    });
  },
  getUserInfo: function (e) {



    let that = this;

    if (e.detail.userInfo == null) {
      wx.showToast({
        title: '取消',
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
          title: '同步中~',
        })
        // 获取登录的临时凭证
        var code = res.code;
        // 调用后端，获取微信的session_key, secret

        wx.request({
          url: getApp().globalData.url + "/Login?code=" + code,
          method: "POST",
          data: listMessage,
          success: function (result) {
            if (result.statusCode != 200) {
              wx.showModal({
                title: '提示',
                content: '服务器连接失败',
              })
              return;
            }
            if (result.data.code == 200) {
              wx.showToast({
                title: '同步成功',
              })
            } else {
              wx.showModal({
                title: '失败',
                content: '出现错误,请联系管理员',
              })
              return
            }
            console.log(result)
            wx.setStorageSync("token", result.data.data);
            wx.setStorageSync("userInfo", e.detail.userInfo);
            that.setData({
              isShow: false,
            })
            wx.hideLoading()
            that.onLoad({
              id: -1
            })
          }
        })

      }
    })

  },
  exit() {
    wx.showModal({
      title: '提示',
      content: '是否退出登录',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: (result) => {
        if (result.confirm) {
          wx.clearStorageSync();
          wx.redirectTo({
            url: '/pages/index/index',
          });

        }
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,
      themeMessage: wx.getStorageSync("themeMessage"),
      'notice.userId': options.id
    })




    this.loadMessage(options.id, this.data.new_page);
    wx.request({
      url: getApp().globalData.url + '/getUserMessage/' + options.id,
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      responseType: 'text',
      success: (result) => {
        if (result.data.code == 200) {
          that.setData({
            userInfo: result.data.data,
            motto: result.data.data.userMotto
          })
        } else {
          wx.showModal({
            title: '提示',
            content: '获取失败,请检查是否登录',
            showCancel: false,
            confirmText: '确定',
          });
        }
      }

    });



  },

  newArticelLoadMessage() {
    this.loadMessage(this.data.userInfo.userId,++this.data.new_page)
  },
  loadMessage(uesrId, index) {




      let that = this;
      /**
       * 获取最新的几篇文章
       */
      if (this.data.new_end) {
        return;
      }
      wx.showLoading({
        title: '加载中',
      })
      wx.request({
        url: getApp().globalData.url + '/getUserArticle/' + uesrId + "/" + index,
        method: 'GET',
        header: {
          "authorization": wx.getStorageSync("token")
        },
        success: (result) => {
  
          wx.hideLoading({
          })
          if (that.data.new_page > result.data.data.pages) {
            that.setData({
              new_end: true
            })
            return
          }
  
          if(that.data.new_page ==1){
            that.setData({
              new_article: result.data.data.list
            })
          }else{
            that.setData({
              new_article: that.data.new_article.concat(result.data.data.list)
            })
          }
       
        },
      });

 
  },
  changeMessage() {
    this.setData({
      isShow: !this.data.isShow
    })
  },

  inputAge(e) {
    this.setData({
      'userInfo.userAge': e.detail.value
    })
  },
  inputOther(e) {
    this.setData({
      'userInfo.userOther': e.detail.value
    })
  },
  inputMotto(e) {
    this.setData({
      motto: e.detail.value
    })
  },
  saveUserMessage() {
    let that = this;
    var list = {
      "userId": this.data.userInfo.userId,
      "userOther": this.data.userInfo.userOther,
      "userAge": this.data.userInfo.userAge,
      "userMotto": that.data.motto
    }




    wx.request({
      url: getApp().globalData.url + '/changeUserMessage',
      data: list,
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'POST',
      success: (result) => {
        if (result.data.code == 200) {
          that.setData({
            isShow: false,
          })
          wx.showToast({
            title: '保存成功',
          })
          that.setData({
            'userInfo.userMotto': that.data.motto
          })
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