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
							<input type="text" class="layui-input" name="id" lay-verify="required"
								autocomplete="off" value="<%=id%>" placeholder="自定义用户编号" />
						</div>
											</div>
											<div class="layui-form-item">
					<%String username = request.getParameter("username");%>
						<label class="layui-form-label layui-required">用户名</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" autocomplete="off" value="<%=username%>" name="username" lay-verify="required"
								placeholder="请输入用户名" />
						</div>
						</div>
						<div class="layui-form-item">
					<%String password = request.getParameter("password");%>
						<label class="layui-form-label">密码</label>
						<div class="layui-input-inline">
							<input type="password" autocomplete="off" value="<%=password%>"  name="password" lay-verify="pass"
								placeholder="请输入密码" autocomplete="off" class="layui-input">
						</div>
						</div>

					<div class="layui-form-item">
						<%String phone = request.getParameter("phone");%>
												<label class="layui-form-label">手机号</label>
      <div class="layui-input-inline">
        <input type="tel" required name="phone"  autocomplete="off" value="<%=phone%>"  placeholder="请输入手机号" lay-verify="required|phone" class="layui-input">
      </div>
      </div>
      <div class="layui-form-item">
                        <%String email = request.getParameter("email");%>
						<label class="layui-form-label">邮箱</label>
						<div class="layui-input-inline">
							<input type="email" class="layui-input" autocomplete="off" value="<%=email%>"  required name="email" lay-verify="required"
								placeholder="请输入邮箱号" />
						</div>
						</div>
						<div class="layui-form-item">
						<%String idcard = request.getParameter("idcard");%>
								<label class="layui-form-label">身份证</label>
						<div class="layui-input-inline">
							<input type="text" type="text" value="<%=idcard%>" required name="idcard" lay-verify="identity"
								placeholder="请输入身份证号" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<%String address = request.getParameter("address");
							address = URLDecoder.decode(address,"UTF-8");%>
						<label class="layui-form-label layui-required">籍贯</label>
						<div class=" layui-input-inline">
							<input type="text" name="address"  autocomplete="off" value="<%=address%>" 
								placeholder="请输入籍贯" class="layui-input">
						</div>
						</div>
						<div class="layui-form-item">
						<%String sex = request.getParameter("sex");
							sex = URLDecoder.decode(sex,"UTF-8");%>
						<label class="layui-form-label">性别</label>
						<div class="layui-input-inline">
							<input type="text"  class="layui-input" autocomplete="off" value="<%=sex%>"  required name="sex" lay-verify="required"
								placeholder="请输入性别" />
						</div>
						</div>
						<div class="layui-form-item">
						<%String department = request.getParameter("department");
							department = URLDecoder.decode(department,"UTF-8");%>
						<label class="layui-form-label layui-required">职务</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" autocomplete="off" value="<%=department%>"  required name="department" lay-verify="required"
								placeholder="请输入职务" />
						</div>
					</div>
					<div class="layui-form-item">
						<%String education = request.getParameter("education");
							education = URLDecoder.decode(education,"UTF-8");%>
					<label class="layui-form-label layui-required">学历</label>
						<div class=" layui-input-inline">
							<input type="text" name="education" autocomplete="off" value="<%=education%>" 
								placeholder="请输入学历" class="layui-input">
						</div>
						</div>
						<div class="layui-form-item">
						<%String fork = request.getParameter("fork");
							fork = URLDecoder.decode(fork,"UTF-8");%>
						<label class="layui-form-label layui-required">民族</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" autocomplete="off" value="<%=fork %>" required name="fork" lay-verify="required"
								placeholder="请输入民族" />
					</div>
    </div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button type="button" lay-submit=""  class="layui-btn"
								lay-filter="demo1">立即修改</button>
								<button type="reset" class="layui-btn layui-btn-warm">重置</button>
						</div>
					</div>
				</form>
				</div>
    		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
    			<script type="text/javascript">
    				layui.use('element',function(){
    					var element = layui.element;
    				});
    				layui.use(['form','jquery'],function(){
    					var form = layui.form;
    					var $ = layui.jquery;
    				form.on('submit(demo1)',function(data){
						$.ajax({
							url:"/ssm/user/update",
							type:"post",
							dataType:"json",
							data:{
								'id':data.field.id,
								'username':data.field.username,
								'password':data.field.password,
								'phone':data.field.phone,
								'email':data.field.email,
								'idcard':data.field.idcard,
								'address':data.field.address,
								'sex':data.field.sex,
								'department':data.field.department,
								'education':data.field.education,
								'fork':data.field.fork
							},
							success:function(res){
								var json=JSON.parse(JSON.stringify(res));
								if(json.result =="1"){
									layer.msg("编辑成功！",{icon:6});
									parent.a();
								}else{
									layer.msg("编辑失败",{icon:5});
								}
							}
						});
					});
					return false;
					});
    			</script>
 	</body>
</html>