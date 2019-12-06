<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/includes/header.jsp" />
</head>

<body class="layui-layout-body">

	<div class="layui-layout-admin">
		<jsp:include page="/includes/nav.jsp" />
		<jsp:include page="/includes/menu.jsp" />
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 50px;">
				<legend style="text-align: center;"
					class="layui-icon layui-icon-table">企业用户信息列表</legend>
			</fieldset>
			<form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/user/search" method="post">
			<div class="layui-form-item" style="padding: 30px;">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="username" id="username" lay-verify="title"
							placeholder="请输入姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="tel" name="phone" id="phone"
							lay-verify="required|phone" placeholder="请输入手机号"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="email" id="email" lay-verify="email"
							autocomplete="off" placeholder="请输入邮箱号" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="address" name="address" id="address" lay-verify="address"
							autocomplete="off" placeholder="请输入籍贯信息" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="department" name="department" id="department" lay-verify="department"
							autocomplete="off" placeholder="请输入所在部门" class="layui-input">
					</div>
				</div>
				<div>
					<button type="submit" lay-submit=""
						class="layui-btn layui-btn-normal layui-icon layui-icon-search" data-type="reload" id="search">
						搜索</button>
					<button type="reset"
						class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">
						重置</button>
				</div>
			</div>
			</form>

				<table id="users" class="layui-hide" lay-filter="test"></table>
				<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <a href="${pageContext.request.contextPath}/user/form" type="button" class="layui-btn  layui-btn-normal layui-icon layui-icon-add-1" lay-event="getCheckData">新增</a>
    <button type="button" class="layui-btn layui-btn-normal layui-icon layui-icon-upload-drag" id="LAY-excel-import-excel" multiple="multiple">导入</button>
    <a type="button" lay-submit="" class="layui-btn  layui-btn-normal layui-icon layui-icon-download-circle" id="exportExcel"  lay-event="download">导出</a>
    <a href="#" type="button" class="layui-btn  layui-btn-danger layui-icon layui-icon-delete" id="exportExcel" lay-filter="remove">批量删除</a>
  </div>
</script>

				<script type="text/html" id="edit">
						<a class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-form" lay-event="detail">查看</a>
						<a class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-edit" lay-event="edit">编辑</a>
						<a class="layui-btn layui-btn-danger layui-btn-sm layui-icon layui-icon-delete" lay-event="del">删除</a>
					</script>
			</div>
			<jsp:include page="/includes/copyright.jsp" />
		</div>
	</div>
	<script type="text/javascript"  src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/js/index.js"></script>
	<script>
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer, form = layui.form;
			layer.msg('欢迎查看用户列表');
		});
	</script>
</body>

</html>