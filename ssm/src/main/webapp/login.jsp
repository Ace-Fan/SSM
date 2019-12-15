<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <title>用户管理后台登陆</title>
            <meta charset="utf-8">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/layui/css/layui.css">
            <link href="${pageContext.request.contextPath}/static/assets/css/login.css" rel="stylesheet" type="text/css" />
        </head>

        <body>
            <form class="layui-form" action="${pageContext.request.contextPath }/user/login" method="post">
                <div class="container">
                    <div class="layui-form-mid layui-word-aux">
                        <img id="logoid" src="${pageContext.request.contextPath}/static/assets/img/rongtong.jpg" height="35">
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-block">
                            <input type="text" id="txtUsername" name="username" required lay-verify="required" placeholder="请输入您的用户名" autocomplete="off" class="layui-input" maxlength="10">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密 &nbsp;&nbsp;码</label>
                        <div class="layui-input-inline">
                            <input type="password" id="txtPassword" name="password" required lay-verify="required" placeholder="请输入您的密码" autocomplete="off" class="layui-input" maxlength="10">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">记住密码</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="close" title="记住密码">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
                        </div>
                    </div>
                    <a href="#" class="font-set">忘记密码?</a> <a href="#" class="font-set">立即注册</a>
                </div>
            </form>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
            <script>
                layui.use('layer', function() {
                    var layer = layui.layer
                    layer.msg('欢迎进入登录界面');

                    <c:if test = "${result==-1}" >
                        layer.msg("用户名或密码错误"); 
                        </c:if>
                });
            </script>
        </body>

        </html>