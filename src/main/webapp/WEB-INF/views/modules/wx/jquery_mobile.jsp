<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<head>
    <meta charset="utf-8">
    <title>我的信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${ctxStatic}/jquery-mobile/jquery.mobile-1.4.5.css">
	<script src="${ctxStatic}/jquery-mobile/jquery.js"></script>
	<script src="${ctxStatic}/jquery-mobile/jquery.mobile-1.4.5.js"></script>
</head>
<body>
	  <form method="post" action="demo_form.php">
		<div class="ui-field-contain">
			<label for="fullname">昵称:</label>
			<input type="text" name="fullname" id="fullname" value="${wxMpUser.nickname}">
			
			<label for="bday">生日:</label>
			<input type="date" name="bday" id="bday">
			
			<label for="email">E-mail:</label>
			<input type="email" name="email" id="email" placeholder="你的电子邮箱..">
			<label for="fullname">头像</label>
			<img alt="我的头像" src="${wxMpUser.headImgUrl}">
			<label for="day">出行时间</label>
	        <select name="day" id="day" data-native-menu="false">
	         <option value="mon">星期一</option>
	         <option value="tue">星期二</option>
	         <option value="wed">星期三</option>
	         <option value="thu">星期四</option>
	         <option value="fri">星期五</option>
	         <option value="sat">星期六</option>
	         <option value="sun">星期日</option>
	        </select>
		</div>
		
	 </form>
</body>
</html>