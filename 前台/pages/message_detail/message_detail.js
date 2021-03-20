// pages/me/me.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    heightConfig: 0,
    navTop: 0,
    article: {},
    isLike: false,
    replayMessage: {
      commentId: "-1",
      userId: "",
      nickName: '',
      replayUserId: '',
      replayContent: ""
    },

    commentMessage: {
      commentContent: "",
      articleId: ""
    },
    userId: "",
    isReplay: false,
    isShow: false,
    comment: [],

    new_comment_end: false, //是否加载完成
    new_comment_page: 1, //当前page页数
  },

  newCommentLoadMessage() {

    this.loadComment(++this.data.new_comment_page, this.data.article.articleId);
  },
  loadComment(index, article) {

    if (this.data.new_comment_end) {
      return;
    }
    let that = this;
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: getApp().globalData.url + '/api/getCommentById/' + article + '/' + index,
      method: 'GET',
      success: (result) => {
        wx.hideLoading({})
        if (that.data.new_comment_page > result.data.data.pages) {
          that.setData({
            new_comment_end: true
          })
          return
        }
        if (that.data.new_comment_page == 1) {
          that.setData({
            comment: result.data.data.list
          })
        } else {
          that.setData({
            comment: that.data.comment.concat(result.data.data.list)
          })
        }

      },
    });
  },
  sendReplay() {
    let that = this;
    var result = this.checkLogin();
    if (!result) {
      wx.showModal({
        title: '提示',
        content: '未登录!',
        showCancel: true,
        cancelText: '取消',
        cancelColor: '#000000',
        confirmText: '去登陆',
        confirmColor: '#3CC51F',
        success: (result) => {
          if (result.confirm) {
            wx.redirectTo({
              url: '/pages/index/index',
            });
          }
        },
      });
      return
    }

    if (that.data.replayMessage.replayContent.length == 0) {
      wx.showToast({
        title: '至少输入一个字',
        icon: 'none',
      });
      return
    }


    that.setData({
      isReplay: false
    })
    wx.showLoading({
      title: '发布中',
    })

    wx.request({
      url: getApp().globalData.url + '/saveReplay',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      data: that.data.replayMessage,
      method: 'POST',
      success: (result) => {
        if (result.data.code == 200) {
          wx.showToast({
            title: '回复成功',
            icon: 'none',
          });
          that.setData({
            'replayMessage.replayContent': "",
          })
          that.loadComment(1, that.data.commentMessage.articleId)
          console.log("123")
        } else {
          wx.showModal({
            title: '提示',
            content: result.data.msg + '，错误码：' + result.data.code,
            confirmText: '确定',
            showCancel: false,
          });
        }
        wx.hideLoading({})

      }
    });




  },
  //喜欢文章
  likeArticle() {

    let that = this;
    var result = this.checkLogin();
    if (!result) {
      wx.showModal({
        title: '提示',
        content: '未登录!',
        showCancel: true,
        cancelText: '取消',
        cancelColor: '#000000',
        confirmText: '去登陆',
        confirmColor: '#3CC51F',
        success: (result) => {
          if (result.confirm) {
            wx.redirectTo({
              url: '/pages/index/index',
            });
          }
        },
      });
      return
    }



    if (wx.getStorageSync("token") == "" || wx.getStorageSync("token") == null) {
      return
    }

    this.setData({
      isLike: !this.data.isLike
    })
    wx.request({
      url: getApp().globalData.url + '/likeArticle/' + this.data.article.articleId,
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      success: (result) => {
        if (result.data.code != 200) {
          wx.showModal({
            title: '提示',
            content: result.data.msg + '，错误码：' + result.data.code,
            confirmText: '确定',
            showCancel: false,
          });
        }
      }
    });
  },
  complainArticle(e) {
    let that = this;
    var result = this.checkLogin();
    if (!result) {

      wx.showModal({
        title: '提示',
        content: '未登录!',
        showCancel: true,
        cancelText: '取消',
        cancelColor: '#000000',
        confirmText: '去登陆',
        confirmColor: '#3CC51F',
        success: (result) => {
          if (result.confirm) {
            wx.redirectTo({
              url: '/pages/index/index',
            });
          }
        },
      });
      return
    }
    wx.showModal({
      title: '提示',
      content: '是否举报该文章?',
      success(res) {
        if (res.confirm) {

          wx.request({
            url: getApp().globalData.url + '/checkArticle/' + that.data.article.articleId,
            header: {
              "authorization": wx.getStorageSync("token")
            },
            method: 'POST',
            success: (result) => {
              if (result.data.code == 200) {
                wx.showToast({
                  title: '举报成功',
                })
              } else {
                wx.showToast({
                  title: '举报失败',
                })
              }
            }
          });
        }
      }
    })

  },
  inputReplay(e) {
    this.setData({
      'replayMessage.replayContent': e.detail.value
    })
  },
  //删除文章
  deleteArticle() {

    wx.showModal({
      title: '提示',
      content: '是否删除，无法撤销',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: (result) => {
        if (result.confirm) {
          wx.showLoading({
            title: "正在删除",
          });

          wx.request({
            url: getApp().globalData.url + '/deleteArticle/' + this.data.article.articleId,
            method: 'POST',
            header: {
              "authorization": wx.getStorageSync("token")
            },
            success: (result) => {
              wx.hideLoading();
              if (result.data.code == 200) {
                wx.showModal({
                  title: '提示',
                  content: '删除成功',
                  showCancel: false,
                  confirmText: '确定',
                  confirmColor: '#3CC51F',
                  success: (result) => {
                    if (result.confirm) {
                      wx.redirectTo({
                        url: "/pages/index/index"
                      })
                    }
                  },
                });
              } else {
                wx.showToast({
                  title: '失败',
                });
              }
            },
          });
     
        }
      },

    });

  },
  previewImage: function (e) {
    let that = this
    let arr = [];
    let reg = /(?<=(src="))[^"]*?(?=")/ig
    let allSrc = that.data.article.articleContent.match(reg)
    for (let i = 0; i < allSrc.length; i++) {
      arr.push(allSrc[i])
    }
    wx.previewImage({
      current: arr, // 当前显示图片的http链接  
      urls: arr // 需要预览的图片http链接列表  
    })
  },
  replayPop(e) {
    this.setData({
      isReplay: !this.data.isReplay,
      'replayMessage.commentId': e.currentTarget.dataset.commentid,
      'replayMessage.userId': e.currentTarget.dataset.userid,
      'replayMessage.nickName': e.currentTarget.dataset.nickname,
      'replayMessage.replayUserId': e.currentTarget.dataset.replayid
    })
    // console.log(e.currentTarget.dataset)
  },

  //删除评论或者回复
  deleteComment(e) {
    let that = this;

    if (this.data.userId == e.currentTarget.dataset.userid) {
      wx.showModal({
        title: '提示',
        content: ('是否删除这条' + (e.currentTarget.dataset.replayid == null ? '评论' : '回复')),
        showCancel: true,
        cancelText: '取消',
        cancelColor: '#000000',
        confirmText: '确定',
        confirmColor: '#3CC51F',
        success: (result) => {
          if (result.confirm) {
            wx.request({
              url: getApp().globalData.url + (e.currentTarget.dataset.replayid == null ? '/deleteComment/' + e.currentTarget.dataset.commentid : '/deleteReplay/' + e.currentTarget.dataset.replayid),
              header: {
                "authorization": wx.getStorageSync("token")
              },
              method: 'POST',
              success: (result) => {
           
                if (result.data.code == 200) {
                  wx.showToast({
                    title: '删除成功',
                    icon: 'none',
                  });
                  that.loadComment(1, that.data.article.articleId)
                }
              },
            });
          }
        },
      });
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,

    })
    let that = this;



    wx.request({
      url: getApp().globalData.url + '/getLikeArticle/' + options.article_id,
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      success: (result) => {
        that.setData({
          isLike: result.data.data
        })
      },
    });


    wx.request({
      url: getApp().globalData.url + '/api/getNewArticleById/' + options.article_id,
      method: 'GET',
      success: (result) => {
        that.setData({
          article: result.data.data,
          'commentMessage.articleId': options.article_id,
        })
      },
    });
    this.loadComment(1, options.article_id)

    var userId = wx.getStorageSync("userId");
    if (userId == "" || userId == null || userId.length > 5) {
      wx.request({
        url: getApp().globalData.url + '/getUserId',
        header: {
          "authorization": wx.getStorageSync("token")
        },
        method: 'GET',
        success: (result) => {
          wx.setStorageSync("userId", result.data.data)
          that.setData({
            userId: result.data.data
          })
        },
      });
      return
    }
    that.setData({
      userId: userId
    })

  },

  //查看用户信息
  check_user_message(e) {
    var result = this.checkLogin();
    if (result) {
      wx.navigateTo({
        url: '/pages/me/me?id=' + e.currentTarget.id
      });
    } else {
      wx.redirectTo({
        url: '/pages/index/index',
      });
    }
  },
  pop() {
    this.setData({
      isShow: !this.data.isShow
    })
  },
  theme(e) {
    if (e.currentTarget.id == "-1") {
      return
    }
    wx.navigateTo({
      url: '/pages/theme/theme?theme_id=' + e.currentTarget.id,
    });

  },

  //发表评论
  sendComment() {
    let that = this;
    var result = this.checkLogin();
    if (!result) {

      wx.showModal({
        title: '提示',
        content: '未登录!',
        showCancel: true,
        cancelText: '取消',
        cancelColor: '#000000',
        confirmText: '去登陆',
        confirmColor: '#3CC51F',
        success: (result) => {
          if (result.confirm) {
            wx.redirectTo({
              url: '/pages/index/index',
            });
          }
        },
      });
      return
    }

    if (that.data.commentMessage.commentContent.length == 0) {
      wx.showToast({
        title: '至少输入一个字',
        icon: 'none',
      });
      return
    }

    that.setData({
      isShow: false
    })
    wx.showLoading({
      title: '发布中',
    })


    wx.request({
      url: getApp().globalData.url + '/saveComment',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      data: that.data.commentMessage,
      method: 'POST',
      success: (result) => {
        wx.hideLoading({})
        if (result.data.code == 200) {
          wx.showToast({
            title: '评论成功',
            icon: 'none',
          });
          that.loadComment(1, that.data.commentMessage.articleId)
          that.setData({
            'commentMessage.commentContent': "",
          })
        } else {
          wx.showModal({
            title: '提示',
            content: result.data.msg + '，错误码：' + result.data.code,
            confirmText: '确定',
            showCancel: false,
          });
        }
   
     
      }
    });
  },
  inputComment(e) {
    this.setData({
      'commentMessage.commentContent': e.detail.value
    })
  },

  checkLogin() { //检查登录
    var userInfo = wx.getStorageSync("userInfo");
    var token = wx.getStorageSync("token");
    if (userInfo == "" || userInfo == null || token == "" || token == null) {
      this.setData({
        userInfo: null
      })
      return false;
    } else {
      this.setData({
        userInfo: userInfo,
      })
      return true
    }
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})