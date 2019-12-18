<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<input type="text" class="layui-input" maxlength="10"
						autocomplete="off" value="<%=username%>" name="username"
						lay-verify="required" placeholder="请输入用户名" />
				</div>
				<label class="layui-form-label layui-required">密码</label>
				<div class="layui-input-inline">
					<input type="password" class="layui-input" id="password"
						maxlength="10" autocomplete="off" name="password"
						lay-verify="required" placeholder="请输入密码" />
				</div>
				<!-- <div class="layui-input-inline" style="padding-left: 170px;">
                <button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" id="change2">
                    显示密码
                </button>
            </div> -->
				<%
					String phone = request.getParameter("phone");
				%>
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-inline">
					<input type="tel" required name="phone" maxlength="11"
						autocomplete="off" value="<%=phone%>" placeholder="请输入手机号"
						lay-verify="required|phone" class="layui-input">
				</div>
				<%
					String email = request.getParameter("email");
				%>
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="email" class="layui-input" maxlength="20"
						autocomplete="off" value="<%=email%>" required name="email"
						lay-verify="required" placeholder="请输入邮箱号" />
				</div>
				<%
					String idcard = request.getParameter("idcard");
				%>
				<label class="layui-form-label">身份证</label>
				<div class="layui-input-inline">
					<input type="text" type="text" value="<%=idcard%>" maxlength="18"
						required name="idcard" lay-verify="identity" placeholder="请输入身份证号"
						autocomplete="off" class="layui-input">
				</div>
				<%
					String address = request.getParameter("address");
					address = URLDecoder.decode(address, "UTF-8");
				%>
				<label class="layui-form-label layui-required">籍贯</label>
				<div class=" layui-input-inline">
					<input type="text" name="address" autocomplete="off" maxlength="10"
						value="<%=address%>" placeholder="请输入籍贯" class="layui-input">
				</div>
				<%
					String sex = request.getParameter("sex");
					sex = URLDecoder.decode(sex, "UTF-8");
				%>
				<label class="layui-form-label">性别</label>
				<div class="layui-input-inline">
					<select name="sex" lay-verify="required">
						<option value="<%=sex%>"><%=sex%></option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<%
					String department = request.getParameter("department");
					department = URLDecoder.decode(department, "UTF-8");
				%>
				<label class="layui-form-label layui-required">职务</label>
				<div class="layui-input-inline">
					<select name="department" lay-verify="required">
						<option value="<%=department%>"><%=department%></option>
						<option value="Web安全部">Web安全部</option>
						<option value="Java开发部">Java开发部</option>
						<option value="软件实施部">软件实施部</option>
						<option value="研发部">研发部</option>
						<option value="生产部">生产部</option>
					</select>
				</div>
				<%
					String education = request.getParameter("education");
					education = URLDecoder.decode(education, "UTF-8");
				%>
				<label class="layui-form-label layui-required">学历</label>
				<div class=" layui-input-inline">
					<select name="education" lay-verify="required">
						<option value="<%=education%>"><%=education%></option>
						<option value="高中">高中</option>
						<option value="大专">大专</option>
						<option value="本科">本科</option>
						<option value="研究生">研究生</option>
						<option value="硕士">硕士</option>
					</select>
				</div>
				<%
					String fork = request.getParameter("fork");
					fork = URLDecoder.decode(fork, "UTF-8");
				%>
				<label class="layui-form-label layui-required">民族</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" autocomplete="off"
						maxlength="2" value="<%=fork%>" required name="fork"
						lay-verify="required" placeholder="请输入民族" />
				</div>
			</div>
			<div class="layui-input-block">
				<button type="button" lay-submit="" class="layui-btn"
					lay-filter="demo1">
					<i class="layui-icon layui-icon-edit">立即修改</i>
				</button>
				<button type="reset" class="layui-btn layui-btn-warm">
					<i class="layui-icon layui-icon-refresh-1">重置</i>
				</button>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/js/index.js"></script>
</body>

</html>