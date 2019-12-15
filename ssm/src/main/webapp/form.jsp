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
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                        <legend style="text-align: center;" class="layui-icon layui-icon-add-1">企业用户信息添加</legend>
                    </fieldset>
                    <div class="layui-card">
                        <div class="layui-card-header"><i class="layui-icon layui-icon-file-b">基本信息</i></div>
                        <div class="layui-card-body layui-main">
                            <form class="layui-form layui-form-pane">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户ID</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" name="id" lay-verify="required" placeholder="自定义用户编号" maxlength="5" />
                                    </div>
                                    <label class="layui-form-label layui-required">用户名</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" required name="username" lay-verify="required" placeholder="请输入用户名" maxlength="10" />
                                    </div>
                                    <label class="layui-form-label">密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" required name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input" maxlength="8">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">手机号</label>
                                    <div class="layui-input-inline">
                                        <input type="tel" maxlength="11" required name="phone" placeholder="请输入手机号" lay-verify="required|phone" autocomplete="off" class="layui-input">
                                    </div>
                                    <label class="layui-form-label">邮箱</label>
                                    <div class="layui-input-inline">
                                        <input type="email" class="layui-input" required name="email" lay-verify="required" placeholder="请输入邮箱号" maxlength="20" />
                                    </div>
                                    <label class="layui-form-label">身份证</label>
                                    <div class="layui-input-inline">
                                        <input type="text" required name="idcard" lay-verify="identity" placeholder="请输入身份证号" autocomplete="off" class="layui-input" maxlength="18">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label layui-required">籍贯</label>
                                    <div class=" layui-input-inline">
                                        <input type="text" name="address" required autocomplete="off" placeholder="请输入籍贯" class="layui-input" maxlength="10">
                                    </div>
                                    <label class="layui-form-label">性别</label>
                                    <div class="layui-input-inline">
                                        <select name="sex" lay-verify="required">
                                    <option value="">请选择性别</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                                    </div>
                                    <label class="layui-form-label layui-required">职务</label>
                                    <div class="layui-input-inline">
                                        <select name="department" lay-search>
                                <option value="">请选择部门</option>
                                <option value="Web安全部">Web安全部</option>
                                <option value="Java开发部">Java开发部</option>
                                <option value="软件实施部">软件实施部</option>
                                <option value="研发部">研发部</option>
                                <option value="生产部">生产部</option>
                            </select>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label layui-required">学历</label>
                                    <div class=" layui-input-inline">
                                        <select name="education" lay-verify="required">
                                    <option value="">请选择学历</option>
                                    <option value="高中">高中</option>
                                    <option value="大专">大专</option>
                                    <option value="本科">本科</option>
                                    <option value="研究生">研究生</option>
                                    <option value="硕士">硕士</option>
                                </select>
                                    </div>
                                    <label class="layui-form-label layui-required">民族</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" maxlength="2" required name="fork" lay-verify="required" placeholder="请输入民族" />
                                    </div>
                                </div>
                                <div style="padding-left: 300px;">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit lay-filter="formDemo"><i
                                        class="layui-icon layui-icon-release">立即提交</i></button>
                                        <button type="reset" class="layui-btn layui-btn-warm"><i
                                        class="layui-icon layui-icon-refresh-1">重置</i></button>
                                        <button type="reset" class="layui-btn layui-btn-normal" onclick="history.go(-1)"><i
                                        class="layui-icon layui-icon-return">返回</i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="/includes/copyright.jsp" />
            </div>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/js/index.js"></script>
            <script>
                layui.use('layer', function() {
                    var layer = layui.layer;
                    layer.msg('欢迎进入用户添加');
                    // 添加用户表单提交
                });
            </script>
        </body>

        </html>