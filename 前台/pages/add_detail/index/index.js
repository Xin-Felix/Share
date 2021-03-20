var time = require('../../../utils/util.js');
var uploadImage = require('../../../utils/uploadFile');
const recorderManager = wx.getRecorderManager()
Page({
  data: {
    formats: {},
    bottom: 0,
    readOnly: false,
    placeholder: '开始输入...',
    _focus: false,
    navTop: 0,
    theme_id: -1,
    themeMessage: [],
    speech: false,
    src: ""
  },
  //预览
  priview() {
    this.editorCtx.getContents({
      success(res) {

        wx.setStorageSync("priviewHtml", res.html);

        wx.navigateTo({
          url: '/pages/add_detail/add_detail',
        });

        // console.log('内容:', res.html)
      }
    })
  },
  onLoad(options) {
    let that = this;
    this.setData({
      navTop: getApp().globalData.navTop,
      heightConfig: getApp().globalData.windowHeight,
      themeMessage: wx.getStorageSync("themeMessage")
    })

    wx.showToast({
      title: '已开启自动保存',
      icon:"none"
    })

    wx.createSelectorQuery().select('#editor').context(function (res) {
      that.editorCtx = res.context
      that.editorCtx.setContents({
        "html": wx.getStorageSync("priviewHtml")
      })
    }).exec()



    if (options.theme_id != null) {
      this.setData({
        theme_id: options.theme_id
      })
    }
  },
  formSubmit(e) {
    let that = this


    this.editorCtx.getContents({
      success(res) {

        if (res.html.length <= 13) {
          wx.showToast({
            title: '输入点东西吧',
            icon: 'none',
            image: '',
            duration: 1500,
            mask: false,
          });
          return
        }

        wx.showModal({
          title: '提示',
          content: '确定发布么？',
          showCancel: true,
          cancelText: '取消',
          cancelColor: '#000000',
          confirmText: '确定',
          confirmColor: '#3CC51F',
          success: (result) => {

            if (result.confirm) {
              wx.showLoading({
                title: "发布中",
              });

              wx.request({
                url: getApp().globalData.url + '/saveArticle/',
                data: {
                  themeId: that.data.theme_id,
                  articleContent: res.html,
                },
                header: {
                  "authorization": wx.getStorageSync("token")
                },
                method: 'POST',
                success: (result) => {
                  wx.hideLoading();
                  if (result.data.code == 200) {
                    wx.redirectTo({
                      url: '/pages/index/index'
                    });
                    wx.setStorageSync("priviewHtml", "");

                  } else {
                    wx.showModal({
                      title: '提示',
                      content: result.data.msg + '，错误码：' + result.data.code,
                      confirmText: '确定',
                      showCancel: false,
                    });
                  }
                },
              });


            }
          },
        })



      }
    })
  },
  /** 录音开始 */
  speechStart() {
    this.setData({
      speech: true
    })
    const options = {
      duration: 10000, //指定录音的时长，单位 ms
      sampleRate: 16000, //采样率
      numberOfChannels: 1, //录音通道数
      encodeBitRate: 96000, //编码码率
      format: 'mp3', //音频格式，有效值 aac/mp3
      frameSize: 50, //指定帧大小，单位 KB
    }
    //开始录音
    recorderManager.start(options);
    recorderManager.onStart(() => {
      console.log('recorder start')
    });
    //错误回调
    recorderManager.onError((res) => {
      console.log(res);
    })
  },
  stopRecord() {
    let that= this;
    this.setData({
      speech: false
    })
    recorderManager.stop();
    recorderManager.onStop((res) => {
      this.tempFilePath = res.tempFilePath;
      console.log('停止录音', res.tempFilePath)
      const {
        tempFilePath
      } = res
      wx.showLoading({
        title: '识别中',
      })
      wx.uploadFile({
        url: getApp().globalData.url + "/speechRecognition",
        filePath: res.tempFilePath,
        header: {
          "authorization": wx.getStorageSync("token")
        },
        name: 'file',
        success: function (result) {
        var m =   JSON.parse(result.data);
  
          that.editorCtx.getContents({
            success(e) {
      
              wx.createSelectorQuery().select('#editor').context(function (res) {
                that.editorCtx = res.context
                that.editorCtx.setContents({
                  "html":e.html+m.data.result[0]
                })
              }).exec()
            }
          })
        
       
          wx.hideLoading({})
        },
      })
    })
  },

  bindPickerChange(e) {
    // 选择了课程类别
    console.log(e.detail.value)
    this.setData({
      index: e.detail.value
    })

  },
  readOnlyChange() {
    this.setData({
      readOnly: !this.data.readOnly
    })
  },
  onEditorReady() {
    const that = this
    wx.createSelectorQuery().select('#editor').context(function (res) {
      that.editorCtx = res.context
    }).exec()
  },

  undo() {
    this.editorCtx.undo()
  },
  redo() {
    this.editorCtx.redo()
  },
  format(e) {
    let {
      name,
      value
    } = e.target.dataset
    if (!name) return
    // console.log('format', name, value)
    this.editorCtx.format(name, value)

  },
  onStatusChange(e) {
    const formats = e.detail
    this.setData({
      formats: formats
    })
  },
  insertDivider() {
    this.editorCtx.insertDivider({
      success: function () {
        console.log('insert divider success')
      }
    })
  },
  clear() {
    this.editorCtx.clear({
      success: function (res) {
        console.log("clear success")
      }
    })
  },
  removeFormat(e) {
    console.log(e)
    this.editorCtx.removeFormat()
  },
  insertDate() {
    const date = new Date()
    const formatDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
    this.editorCtx.insertText({
      text: formatDate
    })
  },
  input(){
    this.editorCtx.getContents({
      success(res) {
        if (res.html.length != 0) {
          wx.setStorageSync("priviewHtml", res.html);
        }

      }
    })
  },
  insertImage() {
    let that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        //把图片上传到云空间
        wx.showLoading({
          title: '正在上传图片',
        })

        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        //上传图片
        //你的域名下的/images/文件下的/当前年月日文件下的/图片.png
        //图片路径可自行修改
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        var path = getApp().globalData.imageUrl + '/share/images/' + currentdate + '/' + new Date().getTime() + Math.floor(Math.random() * 150) + '.png';
        uploadImage(res.tempFilePaths[0], path,
          function (result) {
            console.log("======上传成功图片地址为：", result);
            that.editorCtx.insertImage({
              src: result,
              width: "50%",
              success: function (e) {

                wx.hideLoading()
              }
            })
          },
          function (result) {
            console.log("======上传失败======", result);
            wx.hideLoading()
          }
        )
        //插入编辑器结束
        //云文件上传结束
      }
    })
    //选择图片结束
  }
})