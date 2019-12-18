<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/includes/header.jsp" />
</head>

<body class="layui-layout-body">
	<!-- 内容主体区域 -->
	<div class="layui-main">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<%
					String id = request.getParameter("id");
				%>
				<label class="layui-form-label">用户ID</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="id"
						lay-verify="required" disabled="disabled" autocomplete="off"
						value="<%=id%>" />
				</div>
				<%
					String username = request.getParameter("username");
				%>
				<label class="layui-form-label layui-required">用户名</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" autocomplete="off"
						value="<%=username%>" disabled="disabled" name="username"
						lay-verify="required" />
				</div>
				<%
					String password = request.getParameter("password");
				%>
				<label class="layui-form-label layui-required">密码</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" autocomplete="off"
						value="<%=password%>" disabled="disabled" name="password"
						lay-verify="required" />
				</div>
			</div>

			<div class="layui-form-item">
				<%
					String phone = request.getParameter("phone");
				%>
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-inline">
					<input type="tel" required name="phone" autocomplete="off"
						disabled="disabled" value="<%=phone%>" lay-verify="required|phone"
						class="layui-input">
				</div>
				<%
					String email = request.getParameter("email");
				%>
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="email" class="layui-input" autocomplete="off"
						disabled="disabled" value="<%=email%>" required name="email"
						lay-verify="required" />
				</div>
				<%
					String idcard = request.getParameter("idcard");
				%>
				<label class="layui-form-label">身份证</label>
				<div class="layui-input-inline">
					<input type="text" type="text" value="<%=idcard%>"
						disabled="disabled" required name="idcard" lay-verify="identity"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<%
					String address = request.getParameter("address");
					address = URLDecoder.decode(address, "UTF-8");
				%>
				<label class="layui-form-label layui-required">籍贯</label>
				<div class=" layui-input-inline">
					<input type="text" name="address" autocomplete="off"
						disabled="disabled" value="<%=address%>" class="layui-input">
				</div>
				<%
					String sex = request.getParameter("sex");
					sex = URLDecoder.decode(sex, "UTF-8");
				%>
				<label class="layui-form-label">性别</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" disabled="disabled"
						autocomplete="off" value="<%=sex%>" required name="sex"
						lay-verify="required" />
				</div>
				<%
					String department = request.getParameter("department");
					department = URLDecoder.decode(department, "UTF-8");
				%>
				<label class="layui-form-label layui-required">职务</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" disabled="disabled"
						autocomplete="off" value="<%=department%>" required
						name="department" lay-verify="required" />
				</div>
			</div>
			<div class="layui-form-item">
				<%
					String education = request.getParameter("education");
					education = URLDecoder.decode(education, "UTF-8");
				%>
				<label class="layui-form-label layui-required">学历</label>
				<div class=" layui-input-inline">
					<input type="text" name="education" autocomplete="off"
						disabled="disabled" value="<%=education%>" class="layui-input">
				</div>
				<%
					String fork = request.getParameter("fork");
					fork = URLDecoder.decode(fork, "UTF-8");
				%>
				<label class="layui-form-label layui-required">民族</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" disabled="disabled"
						autocomplete="off" value="<%=fork%>" required name="fork"
						lay-verify="required" />
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
</body>

</html>