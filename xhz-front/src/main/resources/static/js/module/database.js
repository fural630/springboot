var vm = new Vue({
	el : '#app',
	data : {
		moduleName : 'database',
		baseUrl : '/develop/databases',
		q : {
			name : '',
			url : '',
			dbType : ''
		},
		database : {
			id : '',
			name : '',
			url : '',
			userName : '',
			passWord : '',
			dbType : ''
		},
		dbTypeItem : constant.dbTypeItem,
		ruleValidate : {
			name : [ {
				required : true,
				message : '数据源名称不能为空',
				trigger : 'blur'
			} ],
			url : [{
				required : true,
				message : '链接地址不能为空',
				trigger : 'blur'
			}],
			userName : [ {
				required : true,
				message : '用户名不能为空',
				trigger : 'blur'
			} ],
			passWord : [ {
				required : true,
				message : '密码不能为空',
				trigger : 'blur'
			} ],
			dbType : [ {
				required : true,
				message : '数据库类型必须设置',
				trigger : 'change',
				type : 'string'
			} ],
		},
		title : ''
	},
	methods : {
		query : function() {
			layui.table.reload(vm.moduleName + 'Table', {
				where : {
					dbType : vm.q.dbType === undefined ? '' : vm.q.dbType,
					name : vm.q.name,
					url : vm.q.url
				}
			});
		},
		add : function() {
			vm.title = '添加数据源';
			vm.database = {
				id : '',
				name : '',
				url : '',
				userName : '',
				passWord : '',
				dbType : ''
			},
			vm.openDialog();
		},
		update : function() {
			var checkStatus = layui.table.checkStatus(vm.moduleName + 'Table');
			var length = checkStatus.data.length;
			if (length == 0) {
				this.$message.warning('请勾选要修改的数据!');
				return;
			}
			if (length > 1) {
				this.$message.warning('请只勾选一条要修改的数据!');
				return;
			}
			this.title = '修改数据源';

			var id = checkStatus.data[0].id;

			Ajax.request({
				url : vm.baseUrl + "/" +id,
				async : true,
				type : 'GET',
				successCallback : function(r) {
					vm.database = r.data;
					vm.openDialog();
				}
			});

		},
		del : function () {
			var checkStatus = layui.table.checkStatus(vm.moduleName + 'Table');
			var length = checkStatus.data.length;
			if (length == 0) {
				this.$message.warning('请勾选要删除的数据!');
				return;
			}
			var ids = [];
			for (var i = 0 ; i < length; i++) {
				ids.push(checkStatus.data[i].id);
			}
			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: vm.baseUrl + "/deleteBatch",
                    params: JSON.stringify(ids),
                    contentType: "application/json",
                    type: 'POST',
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
		},
		saveOrUpdate : function(layerIndex) {
			var url = vm.baseUrl;
			var type = vm.database.id == '' ? 'POST' : 'PATCH';
			Ajax.request({
				url : url,
				params : JSON.stringify(vm.database),
				contentType : "application/json",
				type : type,
				successCallback : function() {
					layer.close(layerIndex);
					alert('操作成功', function(index) {
						vm.reload();
					});
				}
			});
		},
		openDialog: function() {
			openWindow({
				title: vm.title,
				area: ['720px'],
				content: jQuery("#" + vm.moduleName + "Dialog"),
				btn: ['保存', '取消'],
				yes: function(index) {
					vm.handleSubmit(vm.moduleName + 'Form', index);
				},
				end: function() {
					vm.handleReset(vm.moduleName + 'Form');
				}
			});
		},
		connectTest : function (row) {
			alert(row.name);
		},
		reload : function() {
			vm.query();
		},
		handleSubmit : function(name, layerIndex) {
			handleSubmitValidate(vm, name, function() {
				vm.saveOrUpdate(layerIndex);
			});
		},
		handleReset : function(name) {
			handleResetForm(vm, name);
		}
	}
});

layui.use('table', function() {
	var table = layui.table;
	var $ = layui.$;
	var form = layui.form;

	table.render({
		elem : '#' + vm.moduleName + 'Table',
		url : vm.baseUrl + '/page',
		autoSort : false,
		parseData : function(res) {
			return {
				"code" : res.code,
				"count" : res.data.total,
				"data" : res.data.list
			};
		},
		cols : [ [ {
			type : 'checkbox'
		}, {
			field : 'name',
			width : 200,
			title : '数据源名称'
		}, {
			field : 'url',
			width : 300,
			title : '链接地址'
		}, {
			field : 'userName',
			width : 140,
			title : '用户名'
		}, {
			field : 'lastTestTime',
			width : 150,
			title : '最近测试连接时间'
		}, {
			field : 'dbType',
			width : 110,
			title : '数据库类型',
			align : 'center',
			templet: function(d) {
				if (d.dbType === '0') {
					return '<span class="layui-badge layui-bg-orange">Mysql</span>';
				}
				if (d.dbType === '1') {
					return '<span class="layui-badge layui-bg-blue">Oracle</span>';
				}
			}
		}, {
			width : 90,
			title : '操作',
			align : 'center',
			templet: function(d) {
				return '<a class="layui-btn layui-btn-xs" lay-event="connect">链接测试</a>';
			}
		}] ],
		page : true
	});
	
	table.on('tool(' + vm.moduleName + 'Table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
		if (layEvent === 'connect'){
			vm.connectTest(data);
		}
	});
});