# Share 
社区|文章|帖子|评论回复|表白墙|微信小程序|论坛


此版本未上线只有演示视频   [哔哩哔哩地址](https://www.bilibili.com/video/BV1q541157te/)https://www.bilibili.com/video/BV1q541157te/

## 另一个小程序地址 https://github.com/Xin-Felix/Mango

#### 此版本介绍 
此版本有富文本编辑器可以写文章(可以像word一样),语音识别(百度语音识别api[用处不是很大]),评论回复,个人主页,通知页.采用左右滑来显示页面,常用的功能,相比[另一个小程序](https://github.com/Xin-Felix/Mango)简化了很多不必要的功能,重构了代码,优化界面,代码可以二次开发

## 使用介绍
请先看[另一个小程序](https://github.com/Xin-Felix/Mango)介绍,因为两个程序大部分文档相似

1.下载代码,分别导入微信开发工具以及带maven的Java开发工具,数据库使用MySQL5.7(推荐此版本以上)

2.推荐使用Idea,navicate,vscode,微信开发者工具等开发工具

3.后端修改:1.打开application.yml(后端代码里找),分别修改登录需要的appid和secret,oss地址,数据库链接,其他配置可以忽略,不懂不要修改,语音识别需要修改controller里的speechController里的相关配置,需要申请百度语音api(如果没必要可以忽略, 因为作用也不大),

4.小程序修改:1.打开util里的config.js修改oss的配置信息,app.js修改oss地址以及服务器地址,[oss直通购买,几块钱](https://www.aliyun.com/minisite/goods?userCode=ztot6i1n),其他的需要自己diy就行,这个小程序仍然没有后台管理,所以需要直接操作数据库,可以自己后期加上

## 注意
这次的程序耦合度相对[另一个小程序](https://github.com/Xin-Felix/Mango)耦合度较低,很多直接修改配置即可,不需要进代码修改,代码也有注释可以看的懂

# 喜欢给个star,谢谢 

## QQ/WeChat 1538933906  有问题联系我




