<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@page pageEncoding="UTF-8"%>
        <div class="layui-header">
            <div class="layui-logo">用户信息管理</div>
            <!--头部-->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/user/index" class="layui-icon layui-icon-home" href="#">主页</a>
                </li>
                <li class="layui-nav-item">
                    <a class="layui-icon layui-icon-template" href="${pageContext.request.contextPath}/user/list">用户信息管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="#">其他</a>
                </li>

            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/static/assets/img/rongtong.jpg" class="layui-nav-img"> ${user.username}
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="#">个人资料</a>
                        </dd>
                        <dd>
                            <a href="#">安全设置</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/loginout">注销</a></li>
            </ul>
        </div>