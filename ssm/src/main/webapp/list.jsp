<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
			<form class="layui-form">
			<div class="layui-form-item" style="padding: 30px;">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="username" 
							placeholder="请输入姓名" autocomplete="off" class="layui-input" maxlength="5">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="phone"  placeholder="请输入手机号"
							autocomplete="off" class="layui-input" maxlength="11">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="email"
							autocomplete="off" placeholder="请输入邮箱号" class="layui-input" maxlength="20">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="address" 
							autocomplete="off" placeholder="请输入籍贯信息" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<select name="department">
  <option value="">请选择部门</option>
  <option value="">Web安全部</option>
  <option value="">Java开发部</option>
  <option value="">软件实施部</option>
  <option value="">研发部</option>
  <option value="">生产部</option>
</select>
					</div>
				</div>
				 <div class="layui-inline">
					<button class="layui-btn" lay-submit lay-filter="LAY-user-back-search">
              <i class="layui-icon layui-icon-search">搜索</i></button>
					<button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh">重置</i></button>
			</div>
			</div>
			</form>
				<table id="users" class="layui-table" lay-filter="test"></table>
				<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <a href="${pageContext.request.contextPath}/user/form" type="button" class="layui-btn  layui-btn-normal"><i class="layui-icon layui-icon-add-1">新增</i></a>
    <button type="button" class="layui-btn layui-btn-normal" id="upload"><i class="layui-icon layui-icon-upload-drag">导入</i></button>
    <button type="button" lay-submit="" class="layui-btn  layui-btn-normal" id="exportExcel"><i class="layui-icon layui-icon-download-circle">导出</i></button>
    <button type="button" class="layui-btn layui-btn-danger" id="deleteUsers" data-type="getCheckData"><i class="layui-icon layui-icon-delete">批量删除</i></button>
  </div>
</script>

				<script type="text/html" id="edit">
						<button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="detail"><i class="layui-icon layui-icon-form">查看</i></button>
						<button class="layui-btn layui-btn-warm layui-btn-sm " lay-event="edit" href="${pageContext.request.contextPath}/user/edit"><i class="layui-icon layui-icon-edit">编辑</i></button>
						<button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del"><i class="layui-icon layui-icon-delete">删除</i></button>
					</script>
			</div>
			<jsp:include page="/includes/copyright.jsp" />
		</div>
	<script type="text/javascript"  src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/js/index.js"></script>
	<script>
		layui.use([ 'layer' ], function() {
			var layer = layui.layer
			layer.msg('欢迎查看用户列表');
			
		});
	</script>
</body>

</html>