<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/wx/wxMenu/">微信菜单列表</a></li>
		<shiro:hasPermission name="wx:wxMenu:edit"><li><a href="${ctx}/wx/wxMenu/form">微信菜单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxMenu" action="${ctx}/wx/wxMenu/createMenu" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="创建菜单"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>父级菜单</th>
				<th>菜单标题</th>
				<th>菜单类型</th>
				<th>菜单键值</th>
				<th>网页链接</th>
				<th>是否显示</th>
				<th>备注</th>
				<shiro:hasPermission name="wx:wxMenu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxMenu">
			<tr>
			    <td>
					${fns:getWxParentName(wxMenu.parentId)}
				</td>
				<td>
					${wxMenu.menuName}
				</td>
				<td><a href="${ctx}/wx/wxMenu/form?id=${wxMenu.id}">
					${fns:getDictLabel(wxMenu.menuType, 'menu_type', '')}
				</a></td>
				<td>
					${wxMenu.menuKey}
				</td>
				<td>
					${wxMenu.menuUrl}
				</td>
				<td>
					${fns:getDictLabel(wxMenu.isShow, 'show_hide', '')}
				</td>
				<td>
					${wxMenu.remarks}
				</td>
				<shiro:hasPermission name="wx:wxMenu:edit"><td>
    				<a href="${ctx}/wx/wxMenu/form?id=${wxMenu.id}">修改</a>
					<a href="${ctx}/wx/wxMenu/delete?id=${wxMenu.id}" onclick="return confirmx('确认要删除该微信菜单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>