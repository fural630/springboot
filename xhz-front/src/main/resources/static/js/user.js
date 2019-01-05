var vm = new Vue({
	el : '#app',
	data : {
		q : {
			name : '',
			departmentCid : '',
			status : ''
		},
		user : {
			account : '',
			name : '',
			deptName : '',
			idCard : '',
			birthDay : '',
			sex : '',
			email : '',
			phone : ''
		},
		sexList : constant.sexList,
		statusList : constant.userStatusList,
		ruleValidate : {
			account : [ {
				required : true,
				message : '账号不能为空',
				trigger : 'blur'
			} ],
			name : [ {
				required : true,
				message : '用户名不能为空',
				trigger : 'blur'
			} ],
			status : [ {
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
					status : this.q.status === undefined ? '' : this.q.status,
					name : this.q.name,
					departmentCid : this.q.departmentCid
				}
			});
		},
		add : function() {
			this.title = '添加';
			this.showList = false;
			this.user = {
				account : '',
				name : '',
				deptName : '',
				idCard : '',
				birthDay : '',
				sex : '',
				email : '',
				phone : ''
			}
		},
		update : function() {
			var checkStatus = layui.table.checkStatus('userTable');
			var length = checkStatus.data.length;
			if (length == 0) {
				iview.Message.warning('请勾选要修改的数据!');
				return;
			}
			if (length > 1) {
				iview.Message.warning('请只勾选一条要修改的数据!');
				return;
			}
			this.title = '修改';
			this.showList = false;

			var userId = checkStatus.data[0].id;

			Ajax.request({
				url : "../sys/user/info/" + userId,
				async : true,
				successCallback : function(r) {
					vm.user = r.user;
				}
			});

		},
		del : function () {
			var checkStatus = layui.table.checkStatus('userTable');
			var length = checkStatus.data.length;
			if (length == 0) {
				iview.Message.warning('请勾选要删除的数据!');
				return;
			}
			var ids = [];
			for (var i = 0 ; i < length; i++) {
				ids.push(checkStatus.data[i].id);
			}
			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/user/deleteBatchByIds",
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
			var url = this.user.id == undefined ? "../sys/user/save"
					: "../sys/user/update";
			var type = this.user.id == undefined ? "POST"
					: "PUT";
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
		handleSubmit : function(name) {
			handleSubmitValidate(vm, name, function() {
				vm.saveOrUpdate();
			});
		},
		handleReset : function(name) {
			this.$refs[name].resetFields();
		}
	}
});

layui.use('table', function() {
	var table = layui.table;
	var $ = layui.$;

	table.render({
		elem : '#userTable',
		url : '/sys/user/page',
		autoSort : false,
		parseData : function(res) {
			return {
				"code" : res.code,
				"count" : res.page.total,
				"data" : res.page.list
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
			width : 80,
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
				return transDate(d.birthDay, 'yyyy年MM月dd日')
			}
		}, {
			field : 'departmentCid',
			minWidth : 100,
			title : '所属部门'
		}, {
			field : 'status',
			width : 80,
			title : '状态',
			sort : true,
			templet : function(d) {
				return constant.transUserStatus(d.status)
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
});