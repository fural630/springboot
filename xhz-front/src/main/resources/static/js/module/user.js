var vm = new Vue({
	el : '#app',
	data : {
		q : {
			name : '',
			departmentCid : '',
			isDeleted : ''
		},
		user : {
			id : '',
			account : '',
			passWord : '',
			name : '',
			deptName : '',
			idCard : '',
			birthDay : '',
			sex : '',
			email : '',
			phone : '',
			isDeleted : 1
		},
		sexItem : constant.sexItem,
		isDeletedItem : constant.isDeletedItem,
		ruleValidate : {
			account : [ {
				required : true,
				message : '账号不能为空',
				trigger : 'blur'
			} ],
			passWord : [{
				required : true,
				message : '密码不能为空',
				trigger : 'blur'
			}],
			name : [ {
				required : true,
				message : '用户名不能为空',
				trigger : 'blur'
			} ],
			isDeleted : [ {
				required : true,
				message : '状态必须设置',
				trigger : 'change',
				type : 'number'
			} ],
		},
		showList : true,
		title : ''
	},
	methods : {
		query : function() {
			layui.table.reload('userTable', {
				where : {
					isDeleted : this.q.isDeleted === undefined ? '' : this.q.isDeleted,
					name : this.q.name,
					departmentCid : this.q.departmentCid
				}
			});
		},
		add : function() {
			vm.handleReset('userForm');
			this.title = '添加';
			this.showList = false;
			this.user = {
				id : '',
				passWord : '',
				account : '',
				name : '',
				deptName : '',
				idCard : '',
				birthDay : '',
				sex : '',
				email : '',
				phone : '',
				isDeleted : 1
			}
		},
		update : function() {
			vm.handleReset('userForm');
			var checkStatus = layui.table.checkStatus('userTable');
			var length = checkStatus.data.length;
			if (length == 0) {
				this.$message.warning('请勾选要修改的数据!');
				return;
			}
			if (length > 1) {
				this.$message.warning('请只勾选一条要修改的数据!');
				return;
			}
			this.title = '修改';
			this.showList = false;

			var userId = checkStatus.data[0].id;

			Ajax.request({
				url : "../sys/users/" + userId,
				async : true,
				type : 'GET',
				successCallback : function(r) {
					vm.user = r.data;
				}
			});

		},
		del : function () {
			var checkStatus = layui.table.checkStatus('userTable');
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
                    url: "../sys/users/deleteBatch",
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
		saveOrUpdate : function() {
			var url = '../sys/users';
			var type = vm.user.id == '' ? 'POST' : 'PATCH';
			Ajax.request({
				url : url,
				params : JSON.stringify(vm.user),
				contentType : "application/json",
				type : type,
				successCallback : function() {
					alert('操作成功', function(index) {
						vm.reload();
					});
				}
			});
		},
		reload : function() {
			this.showList = true;
			this.query();
		},
		isDeletedById : function (id, isDeleted) {
			var url = isDeleted ? "../sys/users/enable/" + id : "../sys/users/disable/" + id;
			Ajax.request({
				url: url,
				async: true,
				type: 'GET',
				successCallback: function(r) {
					alert('操作成功', function(index) {
						vm.reload();
					});
				}
			});
		},
		handleSubmit : function(name) {
			handleSubmitValidate(vm, name, function() {
				vm.saveOrUpdate();
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
		elem : '#userTable',
		url : '/sys/users/page',
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
			field : 'id',
			width : 60,
			title : 'ID',
			sort : true
		}, {
			field : 'name',
			width : 130,
			title : '姓名'
		}, {
			field : 'account',
			width : 200,
			title : '账号'
		}, {
			field : 'phone',
			width : 140,
			title : '电话'
		}, {
			field : 'email',
			width : 180,
			title : '邮箱'
		}, {
			field : 'sex',
			width : 70,
			title : '性别',
			templet : function(d) {
				return constant.transGender(d.sex)
			}
		}, {
			field : 'birthDay',
			width : 140,
			title : '出生日期',
			sort : true,
			templet : function(d) {
				return constant.transDate(d.birthDay, 'yyyy年MM月dd日')
			}
		}, {
			field : 'departmentCid',
			minWidth : 100,
			title : '所属部门'
		}, {
			field : 'isDeleted',
			width : 100,
			title : '操作',
			align : 'center',
			templet: function(d) {
				var checked = d.isDeleted === 0 ? 'checked' : '';
				return '<input ' + checked + ' type="checkbox" name="isDeleted" value="' + d.id +
					'" lay-skin="switch" lay-text="开启|禁用" lay-filter="isDeleted">'
			}
		} ] ],
		page : true
	});

	table.on('sort(userTable)', function(obj) {
		var column = obj.field;
		if (obj.field == "birthDay") {
			column = "birth_day";
		}
		table.reload('userTable', {
			initSort : obj,
			where : {
				field : column,
				order : obj.type
			}
		});
	});
	
	form.on('switch(isDeleted)', function(obj) {
		var id = this.value;
		if (obj.elem.checked) {
			confirm("确认要开启吗？", function() {
				vm.isDeletedById(id, true);
			}, function() {
				$("input[type='checkbox'][value='" + id + "']").prop("checked", false);
				form.render('checkbox');
			});
		} else {
			confirm("确认要禁用吗？", function() {
				vm.isDeletedById(id, false);
			}, function() {
				$("input[type='checkbox'][value='" + id + "']").prop("checked", true);
				form.render('checkbox');
			});
		}
	});
});