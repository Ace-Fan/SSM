<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<!-- 左侧导航 -->
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="layui-icon layui-icon-user" href="javascript:;">用户管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a class="layui-icon layui-icon-add-1" href="${pageContext.request.contextPath}/user/form">新增用户</a>
								</dd>
								<dd>
									<a class="layui-icon layui-icon-list" href="${pageContext.request.contextPath}/user/list">用户列表</a>
								</dd>
								</dl>
								</li>
								</ul>
				
				</div>
			</div>