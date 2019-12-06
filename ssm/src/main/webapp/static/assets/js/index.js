layui.config({
	base:'/ssm/static/assets/js/layui_exts/'
}).use(['layer','laypage', 'table','jquery','excel','upload','carousel'],
				function(){
					var $ = layui.jquery,
					table = layui.table,
					laypage = layui.laypage,
					layer = layui.layer,
					excel = layui.excel,
					upload = layui.upload,
					carousel = layui.carousel
					
					carousel.render({
						elem: '#test1'
    ,width: '100%' //设置容器宽度
    ,arrow: 'always' //始终显示箭头
					});
					
					
					// 第一个表格
					table.render({
						elem : '#users',
						cellMinWidth : 130,
						url : '/ssm/user/PaginationUser' // 数据接口
						,
						title : '用户表',
						page : true // 开启分页
						,
						toolbar : '#toolbarDemo' // 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
						,
						totalRow : true // 开启合计行
						,
						response : {
							statusName : 'code',
							statusCode : 200,
							msgName : 'msg',
							countName : 'count',
							dataName : 'data'
						},
						cols : [ [ // 表头
						{
							type : 'checkbox',
							fixed : "left"
						}, {
							field : 'id',
							title : 'ID',
							sort : true,
							fixed : 'left'
						}, {
							field : 'username',
							title : '用户名'
						}, {
							field : 'password',
							title : '密码',
							sort : true
						}, {
							field : 'phone',
							title : '手机号'
						}, {
							field : 'email',
							title : '邮箱号',
							sort : true
						}, {
							field : 'idcard',
							title : '身份证号',
							sort : true
						}, {
							field : 'sex',
							title : '性别',
							sort : true
						},  {
							field : 'fork',
							title : '民族',
							sort : true
						},{
							field : 'address',
							title : '籍贯',
							sort : true
						},{
							field : 'department',
							title : '部门',
							sort : true
						},{
							field : 'education',
							title : '学历',
							sort : true
						}, {
							fixed : 'right',
							width : 230,
							align : 'center',
							toolbar : '#edit'
						} ] ]
						,page:true
						,id:'typeReload'
					});
					
					
					var $ = layui.$,active = {
						reload:function(){
							var username = $('#username');
							var phone = $('#phone');
							var email = $('#email');
							var address = $('#address');
							var department = $('#department');
							
							table.reload('typeReload',{
								page:{
									curr:1
								}
								,where:{
									username:username.val(),
									phone:phone.val(),
									email:email.val(),
									address:address.val(),
									department:department.val(),
								}
							},'data');
						}
					};
		
					$('#search').on('click',function(){
						var type = $(this).data('type');
						active[type] ? active[type].call(this) :'';
					});
					
					$(function () {
    // 监听上传文件的事件
    $('#LAY-excel-import-excel').change(function (e) {
      // 注意：这里直接引用 e.target.files 会导致 FileList 对象在读取之前变化，导致无法弹出文件
      var files = Object.values(e.target.files)
      uploadExcel(files)
      // 变更完清空，否则选择同一个文件不触发此事件
      e.target.value = ''
    })
    // 文件拖拽
    document.body.ondragover = function (e) {
      e.preventDefault()
    }
    document.body.ondrop = function (e) {
      e.preventDefault()
      var files = e.dataTransfer.files
      uploadExcel(files)
    }

  })
					
					function uploadExcel(files) {
  layui.use( ['excel', 'layer'], function () {
    var excel = layui.excel
    var layer = layui.layer
    try {
      excel.importExcel(files, {
        // 读取数据的同时梳理数据
        fields: {
          'address':'A',
		  'department':'B',
		  'education':'C',
		  'email':'D',
	      'fork':'E',
		  'id':'F',
		  'idcard':'G',
		  'password':'K',
		  'phone':'L',
		  'sex':'M',
		  'username':'N'
        }
      }, function (data) {
      	$.ajax({
      		url:"/ssm/user/insert",
      		type: 'post',
      		dataType:"json",
      		data:JSON.stringify(files),
      		success:function(data){
      		}
      	});
      })
      } catch (e){
      	layer.alert(e.message)
      }
      });
      }
					$('#exportExcel').on('click',function(){
					$.ajax({
						url:"/ssm/user/listData",
						dataType:'json',
						success(res){
							var data = res.data;
							data = excel.filterExportData(data,{
								address:'address',
								department:'department',
								education:'education',
								email:'email',
								fork:'fork',
								id:'id',
								idcard:'idcard',
								password:'password',
								phone:'phone',
								sex:'sex',
								username:'username'
							});
							data.unshift({address:'籍贯',department:'部门',education:'学历',email:'396989406@qq.com',fork:'汉族',id:'用户id',idcard:'身份证号',password:'密码',phone:'手机号',sex:'性别',username:'用户名'});
							excel.exportExcel(data,'用户信息列表.xlsx','xlsx');
						}
						,error(){
							layer.alert('获取数据失败，请检查环境');
						}
					});
					});
					

					// 监听行工具事件
					table.on('tool(test)', function(obj) {
						var data = obj.data // 获得当前行数据
						, layEvent = obj.event; // 获得 lay-event 对应的值
						var tr = obj.tr;
						if (layEvent === 'detail') {
							layer.open({
								type:2,
								closeBtn:2,
								title:'查看用户信息',
								area:['450px','700px'],
								shade:0.8,
								id:(new Date()).valueOf(),
								moveType:1,
								content:'/ssm/user/details?id='+data.id+"&username="+data.username+"&password="+data.password+"&phone="+data.phone+
								"&email="+data.email+"&idcard="+data.idcard+"&address="+encodeURI(encodeURI(data.address))+"&sex="+encodeURI(encodeURI(data.sex))+"&department="+encodeURI(encodeURI(data.department))+
								"&education="+encodeURI(encodeURI(data.education))+"&fork="+encodeURI(encodeURI(data.fork))
							});
						} else if (layEvent === 'del') {
							layer.confirm('真的删除行么', function(index) {
								$.ajax({
									type:"POST",
									url:"/ssm/user/delete",
									data:{'id':data.id},
									dataType:"text",
									success:function(data){
										if(data == null){
											layer.msg("删除失败",{icon:5});
										}else{
											obj.del(); // 删除对应行（tr）的DOM结构
											layer.close(index);// 向服务端发送删除指令
											layer.msg("删除成功",{icon:6,time: 2000});
											layer.closeAll();
											window.location.reload();
											Load();
										}
									},
									error:function(){
										alert(data);
									},
								});
							});
						} else if (layEvent === 'edit') {
							layer.open({
								type:2,
								closeBtn:2,
								title:'编辑用户信息',
								area:['450px','700px'],
								shade:0.8,
								id:(new Date()).valueOf(),
								moveType:1,
								content:'/ssm/user/edit?id='+data.id+"&username="+data.username+"&password="+data.password+"&phone="+data.phone+
								"&email="+data.email+"&idcard="+data.idcard+"&address="+encodeURI(encodeURI(data.address))+"&sex="+encodeURI(encodeURI(data.sex))+"&department="+encodeURI(encodeURI(data.department))+
								"&education="+encodeURI(encodeURI(data.education))+"&fork="+encodeURI(encodeURI(data.fork))
							});
						} else if (layEvent === 'download') {
							layer.confirm('真的删除行么', function(index1) {
								obj.del(); // 删除对应行（tr）的DOM结构
								layer.close(index1);
								// 向服务端发送删除指令
							});
						}
					});
					
					});
					