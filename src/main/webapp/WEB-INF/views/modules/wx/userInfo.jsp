<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<head>
    <title>我的信息</title>
    <meta charset="UTF-8">        
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">         
    <link rel="stylesheet" href="${ctxStatic}/weui/dist/style/weui.min.css"/>    
	<script src="${ctxStatic}/weui/dist/example/zepto.min.js"></script>
    <script src="${ctxStatic}/weui/dist/example/router.min.js"></script>
    <script src="${ctxStatic}/weui/dist/example/example.js"></script>
    <script src="${ctxStatic}/weui/dist/example/weui.js"></script>
    <script src="${ctxStatic}/weui/dist/example/jweixin-1.0.0.js" ></script>
    <style type="text/css">
    body{
	  background-color: #FBF9FE;
	}
	.weui_label{
	  width: 4em;
	} 
    </style>
    <script>
		wx.config({
	      debug: false,
	      appId: '${jsapi.appid}',
	      timestamp: '${jsapi.timestamp}',
	      nonceStr: '${jsapi.nonceStr}',
	      signature: '${jsapi.signature}',
	      jsApiList: [
	        'checkJsApi',
	        'onMenuShareTimeline',
	        'onMenuShareAppMessage',
	        'onMenuShareQQ',
	        'onMenuShareWeibo',
	        'onMenuShareQZone',
	        'chooseImage',
	        'previewImage',
	        'uploadImage',
	        'downloadImage'
			      ]
			  });
    </script>
     
    <script type="text/javascript">
    $(function(){
        $('#button').on('click', function(e){
            var pairs = $('form').serialize().split(/&/gi);
            var data = {};
            pairs.forEach(function(pair) {
                pair = pair.split('=');
                data[pair[0]] = decodeURIComponent(pair[1] || '');
            });
            if(!data.name){
                $.weui.topTips('请输入姓名');
                return;
            }
            if(!data.idcard || !/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(data.idcard)){
                $.weui.topTips('请输入正确的身份证号码');
                return;
            }
        })
        
    })
    wx.ready(function () {
    	 // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
    	  document.querySelector('#checkJsApi').onclick = function () {
    	    wx.checkJsApi({
    	      jsApiList: [
    	        'getNetworkType',
    	        'previewImage'
    	      ],
    	      success: function (res) {
    	        alert(JSON.stringify(res));
    	      }
    	    });
    	  };
    	// 2. 分享接口
    	  // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
    	  document.querySelector('#onMenuShareAppMessage').onclick = function () {
    	    wx.onMenuShareAppMessage({
    	      title: '互联网之子',
    	      desc: '在长大的过程中，我才慢慢发现，我身边的所有事，别人跟我说的所有事，那些所谓本来如此，注定如此的事，它们其实没有非得如此，事情是可以改变的。更重要的是，有些事既然错了，那就该做出改变。',
    	      link: 'http://movie.douban.com/subject/25785114/',
    	      imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg',
    	      trigger: function (res) {
    	        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
    	        alert('用户点击发送给朋友');
    	      },
    	      success: function (res) {
    	        alert('已分享');
    	      },
    	      cancel: function (res) {
    	        alert('已取消');
    	      },
    	      fail: function (res) {
    	        alert(JSON.stringify(res));
    	      }
    	    });
    	    alert('已注册获取“发送给朋友”状态事件');
    	  };

    	  // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
    	  document.querySelector('#onMenuShareTimeline').onclick = function () {
    	    wx.onMenuShareTimeline({
    	      title: '互联网之子',
    	      link: 'http://movie.douban.com/subject/25785114/',
    	      imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg',
    	      trigger: function (res) {
    	        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
    	        alert('用户点击分享到朋友圈');
    	      },
    	      success: function (res) {
    	        alert('已分享');
    	      },
    	      cancel: function (res) {
    	        alert('已取消');
    	      },
    	      fail: function (res) {
    	        alert(JSON.stringify(res));
    	      }
    	    });
    	    alert('已注册获取“分享到朋友圈”状态事件');
    	  };

    	  // 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
    	  document.querySelector('#onMenuShareQQ').onclick = function () {
    	    wx.onMenuShareQQ({
    	      title: '互联网之子',
    	      desc: '在长大的过程中，我才慢慢发现，我身边的所有事，别人跟我说的所有事，那些所谓本来如此，注定如此的事，它们其实没有非得如此，事情是可以改变的。更重要的是，有些事既然错了，那就该做出改变。',
    	      link: 'http://movie.douban.com/subject/25785114/',
    	      imgUrl: 'http://img3.douban.com/view/movie_poster_cover/spst/public/p2166127561.jpg',
    	      trigger: function (res) {
    	        alert('用户点击分享到QQ');
    	      },
    	      complete: function (res) {
    	        alert(JSON.stringify(res));
    	      },
    	      success: function (res) {
    	        alert('已分享');
    	      },
    	      cancel: function (res) {
    	        alert('已取消');
    	      },
    	      fail: function (res) {
    	        alert(JSON.stringify(res));
    	      }
    	    });
    	    alert('已注册获取“分享到 QQ”状态事件');
    	  };
    	  
    	  // 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
    	  document.querySelector('#onMenuShareWeibo').onclick = function () {
    	    wx.onMenuShareWeibo({
    	      title: '互联网之子',
    	      desc: '在长大的过程中，我才慢慢发现，我身边的所有事，别人跟我说的所有事，那些所谓本来如此，注定如此的事，它们其实没有非得如此，事情是可以改变的。更重要的是，有些事既然错了，那就该做出改变。',
    	      link: 'http://movie.douban.com/subject/25785114/',
    	      imgUrl: 'http://img3.douban.com/view/movie_poster_cover/spst/public/p2166127561.jpg',
    	      trigger: function (res) {
    	        alert('用户点击分享到微博');
    	      },
    	      complete: function (res) {
    	        alert(JSON.stringify(res));
    	      },
    	      success: function (res) {
    	        alert('已分享');
    	      },
    	      cancel: function (res) {
    	        alert('已取消');
    	      },
    	      fail: function (res) {
    	        alert(JSON.stringify(res));
    	      }
    	    });
    	    alert('已注册获取“分享到微博”状态事件');
    	  };

    	  // 2.5 监听“分享到QZone”按钮点击、自定义分享内容及分享接口
    	  document.querySelector('#onMenuShareQZone').onclick = function () {
    	    wx.onMenuShareQZone({
    	      title: '互联网之子',
    	      desc: '在长大的过程中，我才慢慢发现，我身边的所有事，别人跟我说的所有事，那些所谓本来如此，注定如此的事，它们其实没有非得如此，事情是可以改变的。更重要的是，有些事既然错了，那就该做出改变。',
    	      link: 'http://movie.douban.com/subject/25785114/',
    	      imgUrl: 'http://img3.douban.com/view/movie_poster_cover/spst/public/p2166127561.jpg',
    	      trigger: function (res) {
    	        alert('用户点击分享到QZone');
    	      },
    	      complete: function (res) {
    	        alert(JSON.stringify(res));
    	      },
    	      success: function (res) {
    	        alert('已分享');
    	      },
    	      cancel: function (res) {
    	        alert('已取消');
    	      },
    	      fail: function (res) {
    	        alert(JSON.stringify(res));
    	      }
    	    });
    	    alert('已注册获取“分享到QZone”状态事件');
    	  };
    	  // 5 图片接口
    	  // 5.1 拍照、本地选图
    	  var images = {
    	    localId: [],
    	    serverId: []
    	  };
    	  document.querySelector('#chooseImage').onclick = function () {
    	    wx.chooseImage({
    	      success: function (res) {
    	        images.localId = res.localIds;
    	        alert('已选择 ' + res.localIds.length + '张图片');
    	        $('#pic').attr('src',res.localIds);
    	      }
    	    });
    	  };

    	  // 5.2 图片预览
    	  document.querySelector('#previewImage').onclick = function () {
    	    wx.previewImage({
    	      current: 'http://img3.douban.com/view/photo/photo/public/p2152134700.jpg',
    	      urls: [
    	        'http://img3.douban.com/view/photo/photo/public/p2152117150.jpg',
    	        'http://img5.douban.com/view/photo/photo/public/p1353993776.jpg',
    	        'http://img3.douban.com/view/photo/photo/public/p2152134700.jpg'
    	      ]
    	    });
    	  };

    	  // 5.3 上传图片
    	  document.querySelector('#uploadImage').onclick = function () {
    	    if (images.localId.length == 0) {
    	      alert('请先使用 chooseImage 接口选择图片');
    	      return;
    	    }
    	    var i = 0, length = images.localId.length;
    	    images.serverId = [];
    	    function upload() {
    	      wx.uploadImage({
    	        localId: images.localId[i],
    	        isShowProgressTips: 1,
    	        success: function (res) {
    	          i++;
    	          //返回图片的服务器端ID
    	          images.serverId.push(res.serverId);
    	          if (i < length) {
    	            upload();
    	          }
    	        },
    	        fail: function (res) {
    	          alert(JSON.stringify(res));
    	        }
    	      });
    	    }
    	    upload();
    	  };

    	  // 5.4 下载图片
    	  document.querySelector('#downloadImage').onclick = function () {
    	    if (images.serverId.length === 0) {
    	      alert('请先使用 uploadImage 上传图片');
    	      return;
    	    }
    	    var i = 0, length = images.serverId.length;
    	    images.localId = [];
    	    function download() {
    	      wx.downloadImage({
    	        serverId: images.serverId[i],
    	        success: function (res) {
    	          i++;
    	          alert('已下载：' + i + '/' + length);
    	          images.localId.push(res.localId);
    	          if (i < length) {
    	            download();
    	          }
    	        }
    	      });
    	    }
    	    download();
    	  };
    });
   </script>
</head>
<body>
	  <form>
		<div class="weui_cells_title">制卡进度查询</div>
		<div class="weui_cells weui_cells_form">
		    <div class="weui_cell">
		        <div class="weui_cell_hd">
		            <label class="weui_label">姓名</label>
		        </div>
		        <div class="weui_cell_bd weui_cell_primary">
		            <input class="weui_input" name="name" type="text" placeholder="请在此输入姓名" />
		        </div>
		    </div>
		    <div class="weui_cell">
		        <div class="weui_cell_hd">
		            <label class="weui_label">身份证</label>
		        </div>
		        <div class="weui_cell_bd weui_cell_primary">
		            <input class="weui_input" name="idcard" type="text" placeholder="请在此输入身份证号" />
		        </div>
		    </div>
		</div>
		<p class="weui_cells_tips">提示:目前只能查询金融社保卡制卡进度</p>
		<div class="weui_btn_area">
		    <a class="weui_btn weui_btn_primary" id="button" href="javascript:">查询</a>
		</div>
      </form>
      <h3 id="menu-basic">基础接口</h3>
      <span class="desc">判断当前客户端是否支持指定JS接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="checkJsApi">checkJsApi</a>
      </div>
      <h3 id="menu-share">分享接口</h3>
      <span class="desc">获取“分享到朋友圈”按钮点击状态及自定义分享内容接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="onMenuShareTimeline" href="javascript:">onMenuShareTimeline</a>
      </div>
      <span class="desc">获取“分享给朋友”按钮点击状态及自定义分享内容接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="onMenuShareAppMessage" href="javascript:">onMenuShareAppMessage</a>
      </div>
      <span class="desc">获取“分享到QQ”按钮点击状态及自定义分享内容接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="onMenuShareQQ" href="javascript:">onMenuShareQQ</a>
      </div>
      <span class="desc">获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="onMenuShareWeibo" href="javascript:">onMenuShareWeibo</a>
      </div>
      <span class="desc">获取“分享到QZone”按钮点击状态及自定义分享内容接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="onMenuShareQZone" href="javascript:">onMenuShareQZone</a>
      </div>
      <h3 id="menu-image">图像接口</h3>
      <span class="desc">拍照或从手机相册中选图接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="chooseImage" href="javascript:">chooseImage</a>
      </div>
      <span class="desc">预览图片接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="previewImage" href="javascript:">previewImage</a>
      </div>
      <span class="desc">上传图片接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="uploadImage" href="javascript:">uploadImage</a>
      </div>
      <span class="desc">下载图片接口</span>
      <div class="weui_btn_area">
      <a class="weui_btn weui_btn_primary" id="downloadImage" href="javascript:">downloadImage</a>
      </div>
      <div class="weui_uploader_bd">
          <ul class="weui_uploader_files">
		      <li class="weui_uploader_file">
		      <img alt="" src="" id="pic">
		      </li>
          </ul>
      </div>
</body>
</html>