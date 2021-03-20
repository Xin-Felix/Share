// pages/comment/comment.js

//index.js
//获取应用实例
const app = getApp()
var uploadImage = require('../../utils/uploadFile.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {
    heightConfig: 0,
    navTop: 0,
    statusBarHeight: getApp().globalData.navTop,
    inputContent: "",
    result_image_url: [],
    imgs: [],
    theme_id: -1,
    article: "",
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,
    })

    this.setData({
      article: wx.getStorageSync("priviewHtml")
    })

    if (options.theme_id != null) {
      this.setData({
        theme_id: options.theme_id
      })
    }

  },
  inputContent(e) {
    this.setData({
      inputContent: e.detail.value
    })
  },
  chooseImg: function (e) {
    var that = this;
    var imgs = this.data.imgs;
    if (imgs.length >= 9) {
      this.setData({
        lenMore: 1
      });
      setTimeout(function () {
        that.setData({
          lenMore: 0
        });
      }, 2500);
      return false;
    }
    wx.chooseImage({
      // count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        var imgs = that.data.imgs;
        // console.log(tempFilePaths + '----');
        for (var i = 0; i < tempFilePaths.length; i++) {
          if (imgs.length >= 9) {
            that.setData({
              imgs: imgs
            });
            return false;
          } else {
            imgs.push(tempFilePaths[i]);
          }
        }
        // console.log(imgs);
        that.setData({
          imgs: imgs
        });
      }
    });
  },
  // 删除图片
  deleteImg: function (e) {
    var imgs = this.data.imgs;
    var index = e.currentTarget.dataset.index;
    imgs.splice(index, 1);
    this.setData({
      imgs: imgs
    });
  },
  // 预览图片
  previewImg: function (e) {
    //获取当前图片的下标
    var index = e.currentTarget.dataset.index;
    //所有图片
    var imgs = this.data.imgs;
    wx.previewImage({
      //当前显示图片
      current: imgs[index],
      //所有图片
      urls: imgs
    })
  },
  img_upload: function (res) {
    let that = this;
    let img_url = that.data.imgs;

    let images_url = [];
    //由于图片只能一张一张地上传，所以用循环

    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    //支持多图上传
    for (var i = 0; i < img_url.length; i++) {
      //显示消息提示框
      wx.showLoading({
        title: '上传中' + (i + 1) + '/' + img_url.length,
        mask: true
      })

      //上传图片
      //你的域名下的/images/文件下的/当前年月日文件下的/图片.png
      //图片路径可自行修改
      var currentdate = year + seperator1 + month + seperator1 + strDate;
      var path = 'share/images/' + currentdate + '/' + new Date().getTime() + Math.floor(Math.random() * 150) + '.png';
      uploadImage(img_url[i], path,
        function (result) {
          console.log("======上传成功图片地址为：", result);
          wx.hideLoading();
        },
        function (result) {
          console.log("======上传失败======", result);
          wx.hideLoading()
        }
      )
      images_url.push({
        "imageUrl": path
      });

      that.setData({
        result_image_url: images_url
      })
    }
  },
  submit() {
    let that = this;



    if (this.data.inputContent.length <= 3) {
      wx.showToast({
        title: '至少三个字',
        icon: 'none',
        image: '',
        duration: 1500,
        mask: false,
      });
      return
    }

    wx.showModal({
      title: '提示',
      content: '确定么',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: (result) => {


        if (result.confirm) {
          new Promise((resolve, reject) => {
            this.img_upload()
          })

          new Promise(() => {
            wx.request({
              url: getApp().globalData.url + '/saveArticle/',

              data: {
                themeId: that.data.theme_id,
                articleContent: that.data.inputContent,
                imageUrls: that.data.result_image_url
              },
              header: {
                "authorization": wx.getStorageSync("token")
              },
              method: 'POST',
              success: (result) => {
                console.log(result.data)
              },
            });
          })

        }
      },
    });











  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

 
 
})