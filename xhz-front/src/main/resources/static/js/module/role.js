//全局树对象
var ztree;

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey :'menuId',
            pIdKey : 'parentId'
        }
    },
    view: {
        dblClickExpand: false,
        selectedMulti: false
    },
    check: {
    	enable: true
    }
};

var vm = new Vue({
	el: '#app',
	data: {
		moduleName: 'role',
		baseUrl: '/sys/roles',
		q: {
			name: ''
		},
		role: {
			roleName : '',
			remark : ''
		},
		ruleValidate: {
			roleName: [{
				required: true,
				message: '角色名称不能为空',
				trigger: 'blur'
			}],
		},
		showList: true,
		title: ''
	},
	methods: {
		query: function () {
			layui.table.reload(vm.moduleName + 'Table', {
				where: {
					name: vm.q.name
				}
			});
		},
		add: function () {
			vm.handleReset(vm.moduleName + 'Form');
			vm.getMenu();
			vm.title = '添加角色';
			vm.showList = false;
		},
		update: function () {
			vm.handleReset(vm.moduleName + 'Form');
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

			var roleId = checkStatus.data[0].roleId;

			Ajax.request({
				url: vm.baseUrl + "/" + roleId,
				async: true,
				type: 'GET',
				successCallback: function (r) {
					vm.role = r.data;
					vm.getMenu();
					vm.getRoleMenu();
					vm.title = '修改角色';
					vm.showList = false;
				}
			});

		},
		del: function () {
			var checkStatus = layui.table.checkStatus(vm.moduleName + 'Table');
			var length = checkStatus.data.length;
			if (length == 0) {
				this.$message.warning('请勾选要删除的数据!');
				return;
			}
			var ids = [];
			for (var i = 0; i < length; i++) {
				ids.push(checkStatus.data[i].roleId);
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
		saveOrUpdate: function () {
			var type = vm.role.roleId === undefined ? 'POST' : 'PATCH';
			var checkNodes = ztree.getCheckedNodes(true);
			if (checkNodes.length > 0) {
				var menuIdList = [];
				for (var i = 0; i < checkNodes.length; i++) {
					menuIdList.push(checkNodes[i].menuId);
				}
				vm.role.menuIdList = menuIdList;
			}
			Ajax.request({
				url: vm.baseUrl,
				params: JSON.stringify(vm.role),
				contentType: "application/json",
				type: type,
				successCallback: function () {
					alert('操作成功', function (index) {
						vm.reload();
					});
				}
			});
		},
		reload: function () {
			vm.showList = true;
			vm.query();
		},
		back: function () {
			vm.showList = true;
		},
		getMenu : function () {
			Ajax.request({
				url: '/sys/roleMenus/',
				type: 'GET',
				successCallback: function (r) {
					ztree = $.fn.zTree.init($("#tree"), setting, r.data);
					var nodes = ztree.getNodesByParam("type", "0", null);
					if (nodes.length > 0) {
						for (var i = 0; i <= nodes.length; i++) {
							ztree.expandNode(nodes[i], true, false, true);
						}
					}
				}
			});
		},
		getRoleMenu : function () {
			Ajax.request({
				url: '/sys/roleMenus/' + vm.role.roleId, 
				async: true,
				type: 'GET',
				successCallback: function (r) {
					var menuIds = r.data;
					if (menuIds.length > 0) {
						for (var i = 0; i < menuIds.length; i++) {
							var node = ztree.getNodeByParam("menuId", menuIds[i]);
							ztree.checkNode(node, true);
						}
					}
				}
			});
		},
		handleSubmit: function (name) {
			handleSubmitValidate(vm, name, function () {
				vm.saveOrUpdate();
			});
		},
		handleReset: function (name) {
			handleResetForm(vm, name);
		}
	}
});

layui.use('table', function () {
	var table = layui.table;
	var $ = layui.$;
	var form = layui.form;

	table.render({
		elem: '#' + vm.moduleName + 'Table',
		url: vm.baseUrl + '/page',
		autoSort: false,
		parseData: function (res) {
			return {
				"code": res.code,
				"count": res.data.total,
				"data": res.data.list
			};
		},
		cols: [
			[{
				type: 'checkbox'
			}, {
				field: 'roleName',
				title: '角色名称'
			}, {
				field: 'remark',
				title: '备注'
			}, {
				field: 'createUser',
				width: 200,
				title: '创建人'
			}, {
				field: 'createTime',
				width: 180,
				title: '创建时间',
				sort: true
			}]
		],
		page: true
	});

	table.on('sort(roleTable)', function (obj) {
		var column = obj.field;
		if (obj.field == "createTime") {
			column = "CREATE_TIME";
		}
		table.reload(vm.moduleName + 'Table', {
			initSort: obj,
			where: {
				field: column,
				order: obj.type
			}
		});
	});

});

var nodes = [{
    id: 1,
    pId: 0,
    name: "广西新豪智云技术股份有限公司",
    open: true
},
{
    id: 11,
    pId: 1,
    name: "父节点11 - 折叠"
},
{
    id: 111,
    pId: 11,
    name: "叶子节点111"
},
{
    id: 112,
    pId: 11,
    name: "叶子节点112"
},
{
    id: 113,
    pId: 11,
    name: "叶子节点113"
},
{
    id: 114,
    pId: 11,
    name: "叶子节点114"
},
{
    id: 12,
    pId: 1,
    name: "父节点12 - 折叠"
},
{
    id: 121,
    pId: 12,
    name: "叶子节点121"
},
{
    id: 122,
    pId: 12,
    name: "叶子节点122"
},
{
    id: 123,
    pId: 12,
    name: "叶子节点123"
},
{
    id: 124,
    pId: 12,
    name: "叶子节点124"
},
{
    id: 13,
    pId: 1,
    name: "父节点13 - 没有子节点",
    isParent: true
},
{
    id: 2,
    pId: 0,
    name: "父节点2 - 折叠"
},
{
    id: 21,
    pId: 2,
    name: "父节点21 - 展开",
    open: true
},
{
    id: 211,
    pId: 21,
    name: "叶子节点211"
},
{
    id: 212,
    pId: 21,
    name: "叶子节点212"
},
{
    id: 213,
    pId: 21,
    name: "叶子节点213"
},
{
    id: 214,
    pId: 21,
    name: "叶子节点214"
},
{
    id: 22,
    pId: 2,
    name: "父节点22 - 折叠"
},
{
    id: 221,
    pId: 22,
    name: "叶子节点221"
},
{
    id: 222,
    pId: 22,
    name: "叶子节点222"
},
{
    id: 223,
    pId: 22,
    name: "叶子节点223"
},
{
    id: 224,
    pId: 22,
    name: "叶子节点224"
},
{
    id: 23,
    pId: 2,
    name: "父节点23 - 折叠"
},
{
    id: 231,
    pId: 23,
    name: "叶子节点231"
},
{
    id: 232,
    pId: 23,
    name: "叶子节点232"
},
{
    id: 233,
    pId: 23,
    name: "叶子节点233"
},
{
    id: 234,
    pId: 23,
    name: "叶子节点234"
},
{
    id: 3,
    pId: 0,
    name: "父节点3 - 没有子节点",
    isParent: true
},
{
    id: 31,
    pId: 3,
    name: "父节点3 - 31"
},
{
    id: 32,
    pId: 3,
    name: "父节点3 - 32"
},
{
    id: 33,
    pId: 3,
    name: "父节点3 - 33"
}, {
    id: 34,
    pId: 3,
    name: "父节点3 - 34"
},
{
    id: 35,
    pId: 3,
    name: "父节点3 - 35"
},
{
    id: 36,
    pId: 3,
    name: "父节点3 - 36"
}
];