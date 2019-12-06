<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
	<jsp:include page="/includes/header.jsp"/>
	</head>

	<body class="layui-layout-body">

		<div class="layui-layout-admin">
			<jsp:include page="/includes/nav.jsp"/>
			<jsp:include page="/includes/menu.jsp"/>
			<div class="layui-body">
				<div class="layui-carousel" id="test1">
  <div carousel-item>
    <div>条目1</div>
    <div>条目2</div>
    <div>条目3</div>
    <div>条目4</div>
    <div>条目5</div>
  </div>
</div>
			</div>
			<jsp:include page="/includes/copyright.jsp"/>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
		<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/js/index.js"></script>
	<script>
		<script>
			layui.use(['layer', 'form'], function() {
				var layer = layui.layer,
					form = layui.form;
				layer.msg('欢迎尊敬的${user.username}登陆用户管理系统');
			});
		</script>
	</body>

</html>