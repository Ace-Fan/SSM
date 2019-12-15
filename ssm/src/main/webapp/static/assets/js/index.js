layui.config({
    base: '/ssm/static/assets/js/layui_exts/'
}).use(['layer', 'laypage', 'table', 'excel', 'upload', 'carousel', 'form'],
    function() {
        var $ = layui.$,
            table = layui.table,
            laypage = layui.laypage,
            layer = layui.layer,
            excel = layui.excel,
            upload = layui.upload,
            form = layui.form,
            carousel = layui.carousel
        carousel.render({
            elem: '#test1',
            width: '100%' //设置容器宽度
                ,
            arrow: 'always' //始终显示箭头
        });
        // 第一个表格
        var tableIns = table.render({
            elem: '#users',
            cellMinWidth: 130,
            url: '/ssm/user/listData' // 数据接口
                ,
            title: '用户表',
            page: true // 开启分页
                ,
            toolbar: '#toolbarDemo' // 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                ,
            totalRow: true // 开启合计行
                ,
            id: 'idTest',
            done: function(res, curr, count) {
                bindTableToolbarFunction();
            },
            cols: [
                [ // 表头
                    {
                        type: 'checkbox',
                        fixed: "left"
                    }, {
                        field: 'id',
                        width: 130,
                        title: 'ID',
                        sort: true,
                        fixed: 'left'
                    }, {
                        field: 'username',
                        title: '用户名'
                    }, {
                        field: 'password',
                        title: '密码',
                        sort: true
                    }, {
                        field: 'phone',
                        title: '手机号'
                    }, {
                        field: 'email',
                        title: '邮箱号',
                        sort: true
                    }, {
                        field: 'idcard',
                        title: '身份证号',
                        sort: true
                    }, {
                        field: 'sex',
                        title: '性别',
                        sort: true
                    }, {
                        field: 'fork',
                        title: '民族',
                        sort: true
                    }, {
                        field: 'address',
                        title: '籍贯',
                        sort: true
                    }, {
                        field: 'department',
                        title: '部门',
                        sort: true
                    }, {
                        field: 'education',
                        title: '学历',
                        sort: true
                    }, {
                        fixed: 'right',
                        width: 300,
                        align: 'center',
                        toolbar: '#edit'
                    }
                ]
            ]
        });

        //点击监听器
        function bindTableToolbarFunction() {

            //导入
            upload.render({
                elem: '#upload',
                url: '/ssm/excel/upload',
                accept: 'file',
                exts: 'xls|xlsx',
                done: function(json) {
                    if (json.code == 0) {
                        layer.msg("导入失败", {
                            icon: 5
                        });
                    } else {
                        layer.msg("导入成功", {
                            icon: 6
                        });
                        tableIns.reload('', {});
                    }
                }
            });

            //导出Excel
            $('#exportExcel').on('click', function() {
                layer.confirm('是否导出此表格为EXCEL到本地?', {
                    icon: 3,
                    title: '温馨提示'
                }, function(index) {
                    window.location.href = "/ssm/excel/ExcelOut";
                    layer.close(index);
                    layer.msg("Excel导出下载中，请稍等......", {
                        icon: 6
                    });
                });
                return false;
            });

            //导出Pdf
            $('#exportPdf').on('click', function() {
                layer.confirm('是否导出此表格为PDF到本地?', {
                    icon: 3,
                    title: '温馨提示'
                }, function(index) {
                    window.location.href = "/ssm/pdf/pdfExport";
                    layer.close(index);
                    layer.msg("Pdf导出下载中，请稍等......", {
                        icon: 6
                    });
                });
                return false;
            });

            //批量删除
            $('#deleteUsers').on('click', function() {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });

        }

        /*$(function () {
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
      }*/

        /*//搜索
        $('#search').on('click', function(data){
        	var field = data.field;
        tableIns.reload({
         url:'/ssm/user/search'
        ,page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where:field
        });
        });*/
        //监听表单提交
        form.on('submit(formDemo)', function(data) {
            // ajax方式添加用户
            $.ajax({
                url: "/ssm/user/insert",
                type: "post",
                dataType: 'json',
                data: data.field,
                success: function(res) {
                    var json = JSON.parse(JSON.stringify(res));
                    if (json.result == 1) {
                        layer.msg("新增成功！", {
                            icon: 6
                        });
                        location.href = "/ssm/user/list";
                    } else {
                        layer.msg("新增失败", {
                            icon: 5
                        });
                    }
                },
                error: function() {
                    console.log("ajax error");
                }
            });
            // 阻止表单跳转
            return false;
        });
        //监听编辑
        form.on('submit(demo1)', function(data) {
            var index = parent.layer.getFrameIndex(window.name);
            $.ajax({
                url: "/ssm/user/update",
                type: "post",
                dataType: "json",
                data: {
                    'id': data.field.id,
                    'username': data.field.username,
                    'password': data.field.password,
                    'phone': data.field.phone,
                    'email': data.field.email,
                    'idcard': data.field.idcard,
                    'address': data.field.address,
                    'sex': data.field.sex,
                    'department': data.field.department,
                    'education': data.field.education,
                    'fork': data.field.fork
                },
                success: function(res) {
                    var json = JSON.parse(JSON.stringify(res));
                    if (json.result == "1") {
                        parent.layer.close(index);
                        parent.layer.msg("编辑成功！", {
                            icon: 6
                        });
                        parent.layui.table.reload('idTest', {});
                    } else {
                        layer.msg("编辑失败", {
                            icon: 5
                        });
                    }
                }
            });
        });
        //监听搜索
        form.on('submit(LAY-user-back-search)', function(data) {
            var field = data.field;
            lay
            //执行重载
            tableIns.reload({
                url: '/ssm/user/search',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: field
            });
            return false;
        });

        //批量删除绑定事件
        var $ = layui.$,
            active = {
                getCheckData: function() {
                    var checkStatus = table.checkStatus('idTest'),
                        data = checkStatus.data; //获取选中的数据
                    if (data == "") {
                        layer.msg('请至少选择其中一项', {
                            icon: 2
                        });
                        return;
                    }
                    var ids = ""; //存入ID
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) { //将获取的选中行数据进行遍历
                            ids += data[i].id + ","; //将ID存入数组
                        }
                    }
                    console.log(ids);
                    layer.confirm('确定要删除所选?', {
                        icon: 7,
                        title: '删除提示'
                    }, function(index) {
                        $.ajax({
                            type: "POST",
                            url: "/ssm/user/deleteUsers",
                            data: {
                                'ids': ids
                            },
                            success: function(result) {
                                if (result == 1) {
                                    layer.close(index);
                                    layer.msg("删除成功", {
                                        icon: 6
                                    });
                                    table.reload('idTest', {});
                                } else {
                                    layer.msg("删除失败", {
                                        icon: 5
                                    });
                                }
                            }
                        })
                        layer.close(index);
                    })
                }
            };


        /*$.ajax({
        	url:"/ssm/user/listData",
        	dataType:'json',
        	success(res){
        		var data = res.data;
        		data = excel.filterExportData(data,{
        			id:'id',
        			username:'username',
        			password:'password',
        			phone:'phone',
        			email:'email',
        			idcard:'idcard',
        			address:'address',
        			sex:'sex',
        			department:'department',
        			education:'education',	
        			fork:'fork',
        		});
        		data.unshift({id:'用户id',username:'用户名',password:'密码',phone:'手机号',email:'邮箱号',idcard:'身份证号',address:'籍贯',sex:'性别',department:'部门',education:'学历',fork:'汉族'});
        		layer.msg("导出成功",{icon:6});
        		excel.exportExcel(data,'用户信息列表.xlsx','xlsx');
        	}
        	,error(){
        		layer.alert('获取数据失败，请检查环境');
        	}
        });*/

        //监听表格复选框选择
        table.on('checkbox(test)', function(obj) {
            console.log(obj)
        });

        // 监听行工具事件
        table.on('tool(test)', function(obj) {
            var data = obj.data // 获得当前行数据
                ,
                layEvent = obj.event; // 获得 lay-event 对应的值
            var tr = obj.tr;
            if (layEvent === 'detail') {
                layer.open({
                    type: 2,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1,
                    anim: 5,
                    title: '查看用户信息',
                    area: ['440px', '670px'],
                    shadeClose: true, //开启遮罩关闭
                    content: '/ssm/user/details?id=' + data.id + "&username=" + data.username + "&password=" + data.password + "&phone=" + data.phone +
                        "&email=" + data.email + "&idcard=" + data.idcard + "&address=" + encodeURI(encodeURI(data.address)) + "&sex=" + encodeURI(encodeURI(data.sex)) + "&department=" + encodeURI(encodeURI(data.department)) +
                        "&education=" + encodeURI(encodeURI(data.education)) + "&fork=" + encodeURI(encodeURI(data.fork))
                });
            } else if (layEvent === 'del') {
                layer.confirm('真的删除这一行吗？', {
                    icon: 7,
                    title: '删除提示'
                }, function(index) {
                    $.ajax({
                        type: "POST",
                        url: "/ssm/user/delete",
                        data: {
                            'id': data.id
                        },
                        dataType: "text",
                        success: function(data) {
                            if (data == null) {
                                layer.msg("删除失败", {
                                    icon: 5
                                });
                            } else {
                                obj.del(); // 删除对应行（tr）的DOM结构
                                layer.close(index); // 向服务端发送删除指令
                                layer.msg("删除成功", {
                                    icon: 6,
                                    time: 2000
                                });
                                table.reload('idTest', {});
                            }
                        },
                        error: function() {
                            alert(data);
                        },
                    });
                });
            } else if (layEvent === 'edit') {
                layer.open({
                    type: 2,
                    skin: 'layui-layer-demo', //样式类名
                    title: '编辑用户信息',
                    closeBtn: 1, //不显示关闭按钮
                    anim: 5,
                    area: ['440px', '690px'],
                    shadeClose: true, //开启遮罩关闭
                    content: '/ssm/user/edit?id=' + data.id + "&username=" + data.username + "&password=" + data.password + "&phone=" + data.phone +
                        "&email=" + data.email + "&idcard=" + data.idcard + "&address=" + encodeURI(encodeURI(data.address)) + "&sex=" + encodeURI(encodeURI(data.sex)) + "&department=" + encodeURI(encodeURI(data.department)) +
                        "&education=" + encodeURI(encodeURI(data.education)) + "&fork=" + encodeURI(encodeURI(data.fork))
                })
            }
        });

    });