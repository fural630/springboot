var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey : "id",
			pIdKey : "parentId",
            rootPId: 0
        },
        key: {
            url: "nourl"
        }
    },
    callback: {
		onClick: zTreeOnClick
	},
	view: {
		selectedMulti: false
	}
};
var ztree;

var agencyPng = '../plugins/ztree/css/metroStyle/img/agency.png';
var department = '../plugins/ztree/css/metroStyle/img/department.png';

function zTreeOnClick (event, treeId, treeNode) {
	var title = vm.title;
	if (title == '') {
		return;
	}
	if (title == '修改') {
		vm.update();
	}
	if (title == '添加') {
		vm.add();
	}
}

var vm = new Vue({
	el : '#app',
	data : {
		q : {
			qyid : '',
			type : '',
			bmmc : '',
			bmdm : '',
			bmlx : '',
			sjdwmc : '',
			sjdwdm : ''
		},
		zzbm : {
			qyid : '',
			bmmc : '',
			sjdwmc : '',
			bmlx : '',
			bmjc : '',
			lxdz : '',
			lxdh : '',
			jkyh : '',
			jkyhzh : '',
			fydw1 : '',
			fydw2 : '',
			dfbm : '',
			yb : ''
		},
		zzlxList : [
			{label : '单位', value : 1},
			{label : '部门', value : 2}
		],
		typeList : [
			{ label : '省级', value : 1 },
			{ label : '市级', value : 2 },
			{ label : '区县', value : 3 }
		],
		cityList : constant.cityList,
		ruleValidate : {
			bmmc : [ {
				required : true,
				message : '组织名称不能为空'
			} ],
			qyid : [ {
				required : true,
				message : '管辖区域不能为空'
			} ],
			bmlx : [ {
				required : true,
				message : '部门类型必须设置',
				type : 'number'
			} ],
		},
		showList : true,
		title : ''
	},
	methods : {
		query : function () {
			layui.table.reload('zzbmTable', {
				where : {
					qyid : this.q.qyid === undefined ? '' : this.q.qyid,
					type : this.q.type === undefined ? '' : this.q.type,
					bmmc : this.q.bmmc,
					bmdm : this.q.bmdm,
					bmlx : this.q.bmlx === undefined ? '' : this.q.bmlx,
					sjdwmc : this.q.sjdwmc,
					sjdwdm : this.q.sjdwdm
				}
			});
		},
		reloadTree : function () {
			Ajax.request({
                url: '../sys/zzbm/list',
                async: true,
                successCallback: function (data) {
                	var zNodes = [];
                	var list = data.list;
                	for (var i = 0; i < list.length; i++) {
                		var icon = list[i].bmlx == '1' ? agencyPng : department;
                		var zNode = {
                			id : list[i].zzid,
                			parentId : list[i].sjid,
                			name : list[i].bmmc,
                			bmlx : list[i].bmlx,
                			icon : icon
                		};
                		zNodes.push(zNode);
                	}
                    ztree = $.fn.zTree.init($("#zzbmTree"), setting, zNodes);
                }
            });
		},
		expand : function () {
			ztree.expandAll(true);
		},
		add : function() {
			this.title = '添加';
			this.showList = false;
			var nodes = ztree.getSelectedNodes();
			var sjdwmc = '';
			var sjid = '0';
			if (nodes.length > 0) {
				sjdwmc = nodes[0].name;
				sjid = nodes[0].id;
			}
			this.zzbm = {
				qyid : '',
				bmmc : '',
				sjdwmc : sjdwmc,
				sjid : sjid,
				bmlx : '',
				bmjc : '',
				lxdz : '',
				lxdh : '',
				jkyh : '',
				jkyhzh : '',
				fydw1 : '',
				fydw2 : '',
				dfbm : '',
				yb : ''
			}
		},
		update : function() {
			var nodes = ztree.getSelectedNodes();
			if (nodes.length == 0) {
				iview.Message.warning('请选择要修改的数据!');
				return;
			}
			this.title = '修改';
			this.showList = false;
			var id = nodes[0].id;
			Ajax.request({
                url: "../sys/zzbm/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.zzbm = r.zzbm;
                }
            });
			
		},
		del : function () {
			var nodes = ztree.getSelectedNodes();
			if (nodes.length == 0) {
				iview.Message.warning('请选择要删除的数据!');
				return;
			}
			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/zzbm/delete/" + nodes[0].id,
                    successCallback: function () {
                        alert('操作成功', function (index) {
                        	var node = ztree.getSelectedNodes()[0];
                        	ztree.removeNode(node);
                        });
                    }
                });
            });
		},
		saveOrUpdate : function() {
			var url = this.zzbm.zzid == undefined ? "../sys/zzbm/save"
					: "../sys/zzbm/update";
			Ajax.request({
				url : url,
				params : JSON.stringify(vm.zzbm),
				contentType : "application/json",
				type : 'POST',
				successCallback : function(data) {
					alert('操作成功', function(index) {
						var icon = vm.zzbm.bmlx == '1' ? agencyPng : department;
						if (vm.zzbm.zzid == undefined) { //添加
							var parentNode = ztree.getSelectedNodes()[0];
							var newNode = {
								id : data.id,
								parentId : parentNode.id,
								name : vm.zzbm.bmmc,
								icon : icon
							};
							ztree.addNodes(parentNode, newNode);
						} else {
							var newNode = ztree.getSelectedNodes()[0];
							newNode.name = vm.zzbm.bmmc;
							newNode.icon = icon;
							ztree.updateNode(newNode);
						}
					});
				}
			});
		},
		reload : function() {
//			this.showList = false;
//			this.reloadTree();
		},
		handleSubmit : function(name) {
			handleSubmitValidate(vm, name, function() {
				vm.saveOrUpdate();
			});
		},
		handleReset : function(name) {
			this.$refs[name].resetFields();
		}
	},
	created : function () {
		this.reloadTree();
	}
});

layui.use('table', function() {
	var table = layui.table;
	var $ = layui.$;

	table.render({
		elem : '#zzbmTable',
		url : '/sys/zzbm/page',
		autoSort : false,
		parseData : function(res) {
			return {
				"code" : res.code,
				"count" : res.page.total,
				"data" : res.page.list
			};
		},
		cols : [ [ 
		{
			field : 'qyid',
			width : 80,
			title : '区域ID'
		}, {
			field : 'regionName',
			title : '管辖区域'
		}, {
			field : 'agencyCode',
			width : 110,
			title : '区域代码'
		}, {
			field : 'type',
			width : 90,
			title : '区域类型',
			templet : function (d) {
				if (d.type == '1') {
					return '<button class="layui-btn layui-btn-danger layui-btn-xs">省级</button>'
				} else if (d.type == '2') {
					return '<button class="layui-btn layui-btn-xs">市级</button>'
				} else if (d.type == '3') {
					return '<button class="layui-btn layui-btn-xs" style="background-color:#c2c2c2">区县级</button>'
				}
				return '';
			}
		}, {
			field : 'bmmc',
			title : '组织名称'
		}, {
			field : 'bmdm',
			width : 120,
			title : '组织代码'
		}, {
			field : 'bmlx',
			width : 90,
			title : '组织类型',
			templet : function (d) {
				if (d.bmlx == '1') {
					return '<button class="layui-btn layui-btn-normal layui-btn-xs">单位</button>'
				} else if (d.bmlx == '2') {
					return '<button class="layui-btn layui-btn-warm layui-btn-xs">部门</button>'
				}
				return '';
			}
		}, {
			field : 'sjdwmc',
			title : '上级组织名称'
		}, {
			field : 'sjdwdm',
			width : 140,
			title : '上级组织代码'
		}, {
			field : 'bmjc',
			minWidth : 80,
			title : '制文简称'
		}, {
			field : 'seq',
			title : '序列'
		},
		] ],
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