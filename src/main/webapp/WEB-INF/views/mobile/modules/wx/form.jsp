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
    <script type="text/javascript">
      $(function(){
    	  $('#validateCode').click(function(){
    		  $('#validateCode').attr('src','${local}/servlet/validateCodeServlet?'+new Date().getTime());
    	  });
      });
    </script>
</head>
<body ontouchstart>


    <header class='demos-header'>
      <h1 class="demos-title">表单</h1>
    </header>

    
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">qq</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="tel" placeholder="请输入qq号">
        </div>
      </div>
      <div class="weui_cell weui_vcode">
        <div class="weui_cell_hd"><label class="weui_label">验证码</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="number" placeholder="请输入验证码">
        </div>
        <div class="weui_cell_ft">
          <img src="${local}/servlet/validateCodeServlet" id="validateCode"/>
        </div>
      </div>
      <div class="weui_cell weui_cell_switch">
        <div class="weui_cell_hd weui_cell_primary">接受通知</div>
        <div class="weui_cell_ft">
          <input class="weui_switch" type="checkbox">
        </div>
      </div>
      <div class="weui_cell">
        <div class="weui_cell_hd"><label for="" class="weui_label">日期</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="date" value="">
        </div>
      </div>
      <div class="weui_cell">
        <div class="weui_cell_hd"><label for="" class="weui_label">时间</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="datetime-local" value="" placeholder="">
        </div>
      </div>
      <div class="weui_cell weui_cell_select">
        <div class="weui_cell_bd weui_cell_primary">
          <select class="weui_select" name="select1">
            <option selected="" value="0">选择</option>
            <option value="1">微信号</option>
            <option value="2">QQ号</option>
            <option value="3">Email</option>
          </select>
        </div>
      </div>
    </div>
    <div class="weui_cells_title">文本域</div>
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <textarea class="weui_textarea" placeholder="请输入评论" rows="3"></textarea>
          <div class="weui_textarea_counter"><span>0</span>/200</div>
        </div>
      </div>
    </div>

    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_uploader">
            <div class="weui_uploader_hd weui_cell">
              <div class="weui_cell_bd weui_cell_primary">图片上传</div>
              <div class="weui_cell_ft">0/2</div>
            </div>
            <div class="weui_uploader_bd">
              <ul class="weui_uploader_files">
                <li class="weui_uploader_file" style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
                <li class="weui_uploader_file" style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
                <li class="weui_uploader_file" style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
                <li class="weui_uploader_file weui_uploader_status" style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)">
                  <div class="weui_uploader_status_content">
                    <i class="weui_icon_warn"></i>
                  </div>
                </li>
                <li class="weui_uploader_file weui_uploader_status" style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)">
                  <div class="weui_uploader_status_content">50%</div>
                </li>
              </ul>
              <div class="weui_uploader_input_wrp">
                <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple="">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <h2 class="demos-second-title">表单校验</h2>
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell weui_cell_warn">
        <div class="weui_cell_hd"><label class="weui_label">qq</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="tel" placeholder="请输入qq号">
        </div>
      </div>
      <div class="weui_cell weui_vcode weui_cell_warn">
        <div class="weui_cell_hd"><label class="weui_label">验证码</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="number" placeholder="请输入验证码">
        </div>
        <div class="weui_cell_ft">
          <i class="weui_icon_warn"></i>
          <img src="${local}/servlet/validateCodeServlet" id="validateCode"/>
        </div>
      </div>
    </div>
  </body>
</html>