<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<head>
    <meta charset="utf-8">
    <title>我的信息</title>
    <meta charset="UTF-8">        
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="${ctxStatic}/jquery-weui/dist/lib/weui.css">
    <link rel="stylesheet" href="${ctxStatic}/jquery-weui/dist/css/jquery-weui.css"> 
    <script type="text/javascript" src="${ctxStatic}/jquery-weui/dist/lib/jquery-2.1.4.js"> </script> 
    <script type="text/javascript" src="${ctxStatic}/jquery-weui/dist/js/jquery-weui.js"> </script> 
    <style type="text/css">
	    body{background-color: #FBF9FE;}
	    .weui_label{width: 4em;}
		.container{padding: 15px;}
    </style>
    <script type="text/javascript">
    $(function(){
    	 
        $('#search').on('click', function(e){
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
        $('#btn').on('click', function(){
            $('#dialog').show();
          });
        
         $('#dialog').on('click', '.weui_btn_dialog', function(){
           $('#dialog').hide();
         });
    })
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
		    <a class="weui_btn weui_btn_primary" id="search" href="javascript:">查询</a>
		</div>
   </form>
   <div class="container">
    <a href="javascript:;" id="btn" class="weui_btn weui_btn_primary">显示弹框</a>
   </div>
   <div class="weui_dialog_alert" id="dialog" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
      <div class="weui_dialog_hd"><strong class="weui_dialog_title">警告</strong></div>
      <div class="weui_dialog_bd">弹窗内容，告知当前页面信息等</div>
      <div class="weui_dialog_ft">
        <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
      </div>
    </div>
  </div>
</body>
</html>