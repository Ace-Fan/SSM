<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
    <head>
<jsp:include page="/includes/header.jsp"/>
    </head>
    <body>
    	<div class="layui-main">
    	<form class="layui-form layui-form-pane" action="" method="">
					<div class="layui-form-item">
					<%String id = request.getParameter("id");%>
					<input type="hidden" name="id" value="<%=id%>" class="layui-input">
					<label class="layui-form-label">用户ID</label>
						<div class="layui-input-inline">
							<input type="text" disabled="disabled" class="layui-input" name="id" lay-verify="required"
								autocomplete="off" value="<%=id%>" />
						</div>
											</div>
											<div class="layui-form-item">
					<%String username = request.getParameter("username");%>
						<label class="layui-form-label layui-required">用户名</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" disabled="disabled" autocomplete="off" value="<%=username%>" name="username" lay-verify="required"/>
						</div>
						</div>
						<div class="layui-form-item">
					<%String password = request.getParameter("password");%>
						<label class="layui-form-label">密码</label>
						<div class="layui-input-inline">
							<input type="text" disabled="disabled"  autocomplete="off" value="<%=password%>"  name="password" lay-verify="pass" autocomplete="off" class="layui-input">
						</div>
						</div>

					<div class="layui-form-item">
						<%String phone = request.getParameter("phone");%>
												<label class="layui-form-label">手机号</label>
      <div class="layui-input-inline">
        <input class="layui-input" disabled="disabled" type="tel" required name="phone"  autocomplete="off" value="<%=phone%>" ">
      </div>
      </div>
      <div class="layui-form-item">
                        <%String email = request.getParameter("email");%>
						<label class="layui-form-label">邮箱</label>
						<div class="layui-input-inline">
							<input type="email" disabled="disabled" class="layui-input" autocomplete="off" value="<%=email%>"  required name="email" lay-verify="required"/>
						</div>
						</div>
						<div class="layui-form-item">
						<%String idcard = request.getParameter("idcard");%>
								<label class="layui-form-label">身份证</label>
						<div class="layui-input-inline">
							<input type="text" type="text" value="<%=idcard%>" required name="idcard" lay-verify="identity"
								disabled="disabled" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<%String address = request.getParameter("address");
							address = URLDecoder.decode(address,"UTF-8");%>
						<label class="layui-form-label layui-required">籍贯</label>
						<div class=" layui-input-inline">
							<input type="text" name="address"  autocomplete="off" value="<%=address%>" 
								disabled="disabled" class="layui-input">
						</div>
						</div>
						<div class="layui-form-item">
						<%String sex = request.getParameter("sex");
							sex = URLDecoder.decode(sex,"UTF-8");%>
						<label class="layui-form-label">性别</label>
						<div class="layui-input-inline">
							<input type="text"  class="layui-input" autocomplete="off" value="<%=sex%>"  required name="sex" lay-verify="required"
								disabled="disabled" />
						</div>
						</div>
						<div class="layui-form-item">
						<%String department = request.getParameter("department");
							department = URLDecoder.decode(department,"UTF-8");%>
						<label class="layui-form-label layui-required">职务</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" autocomplete="off" value="<%=department%>"  required name="department" lay-verify="required"
								disabled="disabled" />
						</div>
					</div>
					<div class="layui-form-item">
						<%String education = request.getParameter("education");
							education = URLDecoder.decode(education,"UTF-8");%>
					<label class="layui-form-label layui-required">学历</label>
						<div class=" layui-input-inline">
							<input type="text" name="education" autocomplete="off" value="<%=education%>" 
								disabled="disabled" class="layui-input">
						</div>
						</div>
						<div class="layui-form-item">
						<%String fork = request.getParameter("fork");
							fork = URLDecoder.decode(fork,"UTF-8");%>
						<label class="layui-form-label layui-required">民族</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" autocomplete="off" value="<%=fork %>" required name="fork" lay-verify="required"
							disabled="disabled"  />
					</div>
    </div>
				</form>
				</div>
    		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
 	</body>
</html>