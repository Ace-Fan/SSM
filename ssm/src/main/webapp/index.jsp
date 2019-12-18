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
			<div class="layui-carousel" id="test1">
				<div carousel-item>
					<div>
						<img
							src="${pageContext.request.contextPath}/static/assets/img/rongtong.jpg">
					</div>
					<div>
						<img
							src="${pageContext.request.contextPath}/static/assets/img/security.jpg">
					</div>
					<div>
						<img
							src="${pageContext.request.contextPath}/static/assets/img/mountain.jpg">
					</div>
					<div>
						<img
							src="${pageContext.request.contextPath}/static/assets/img/circle.jpg">
					</div>
					<div>
						<img
							src="${pageContext.request.contextPath}/static/assets/img/bridge.jpg">
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-xs12 layui-col-md8">
					<div class="layui-collapse" lay-filter="test">
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">layui 更适合哪些开发者？</h2>
							<div class="layui-colla-content">
								<p>
									在前端技术快速变革的今天，layui
									仍然坚持语义化的组织模式，甚至于模块理念都是采用类AMD组织形式，并非是有意与时代背道而驰。layui
									认为以jQuery为核心的开发方式还没有到完全消亡的时候，而早期市面上基于jQuery的UI都普通做得差强人意，所以需要有一个新的UI去重新为这一领域注入活力，并采用一些更科学的架构方式。
									<br>
									<br> 因此准确地说，layui 更多是面向那些追求开发简单的前端工程师们，以及所有层次的服务端程序员。
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-xs6">
					<div class="layui-card">
						<div class="layui-card-header">Spring</div>
						<div class="layui-card-body">
							Spring就像是整个项目中装配bean的大工厂，在配置文件中可以指定使用特定的参数去调用实体类的构造方法来实例化对象。 <br>
							Spring的核心思想是IoC（控制反转），即不再需要程序员去显式地new一个对象，而是让Spring框架帮你来完成这一切。
						</div>
					</div>
				</div>
				<div class="layui-col-xs6">
					<div class="layui-card">
						<div class="layui-card-header">SpringMVC</div>
						<div class="layui-card-body">
							SpringMVC在项目中拦截用户请求，它的核心Servlet即DispatcherServlet承担中介或是前台这样的职责， <br>
							将用户请求通过HandlerMapping去匹配Controller，Controller就是具体对应请求所执行的操作。SpringMVC相当于SSH框架中struts。
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/includes/copyright.jsp" />
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/layui/layui.all.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/assets/js/index.js"></script>
	<script>
		layui.use([ 'layer', 'element' ], function() {
			var layer = layui.layer;
			var element = layui.element;
			layer.msg('欢迎尊敬的${user.username}登陆用户管理系统');

			//监听折叠
			element.on('collapse(test)', function(data) {
				layer.msg('展开状态：' + data.show);
			});
		});
	</script>
</body>

</html>