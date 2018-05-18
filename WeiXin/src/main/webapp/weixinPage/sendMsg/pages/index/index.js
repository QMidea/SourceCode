//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Hello World',
    userInfo: {}
  },
  onLoad: function () {
    console.log('onLoad')
  },
  //
  sendMsg: function(e){
    // 获取表单id
    var formId = e.detail.formId;
    var that = this;
    wx.login({
      success: function(res) {
        that.getOpenId(res.code, formId);
      }
    });
    
  },
  //获取openid
  getOpenId: function (code, formId){
    var that = this;
    wx.request({ 
      url: 'http://www.qianare.xyz:443/WeiXin/getOpenId?code=' + code, 
        method: 'POST',
        header: {
           'content-type': 'application/x-www-form-urlencoded'
        },
        success: function(res) {
           var openId = res.data;
           that.sendMessage(openId, formId);
        }
    })
  },
  //发送模板消息
  sendMessage: function (openId, formId) {
    var that = this;
    wx.request({
      url: 'http://www.qianare.xyz:443/WeiXin/sendMessage',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: { 'openId': openId, 'formId': formId},
      success: function (res) {
      }
    })
  }
})