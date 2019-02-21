var vm = new Vue({
	el: '#app',
	data: {
		moduleName: 'menu',
		baseUrl: '/sys/menus',
		q: {
			name: '',
			url: ''
		},
		title: '',
		showList: true,
		iconLoading: false,
		iconShow: '',
		menu: {
			menuId: '',
			type: '',
			name: '',
			parentName: '',
			parentId: '',
			url: '',
			perms: '',
			orderNum: 0,
			icon: '',
			isDeleted: 0
		},
		permsMenu: {
			parentId: '',
			parentName: '',
			url: '',
			permsMenus: []
		},
		ruleValidate: {
			name: [{
				required: true,
				message: '菜单名称不能为空',
				trigger: 'blur'
			}],
			url: [{
				required: true,
				message: '菜单url不能为空',
				trigger: 'blur'
			}],
			icon: [{
				required: true,
				message: '请选择一个图标',
				trigger: 'blur'
			}]
		},
	},
	methods: {
		query: function () {
			treeGrid.reload(vm.moduleName + 'Table', {
				where: {
					name: vm.q.name,
					url: vm.q.url
				}
			});
		},
		add: function () {
			var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
			var type = '0';
			var parentId = '0';
			var parentName = '一级目录';
			if (selectedData.menuId != undefined) {
				type = parseInt(selectedData.type) + 1;
				parentId = selectedData.menuId;
				parentName = selectedData.name;
			}
			if (type == 3) {
				vm.$message.warning("按钮下不允许添加节点，请选择其他层级！");
				return;
			}
			this.title = '添加菜单';
			vm.menu = {
				menuId: '',
				type: type,
				name: '',
				parentName: parentName,
				parentId: parentId,
				url: '',
				perms: '',
				orderNum: 0,
				icon: '',
				isDeleted: 0
			};
			this.openDialog();
		},
		update: function () {
			var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
			if (selectedData.menuId == undefined) {
				vm.$message.warning("请选择要修改的数据！");
				return;
			}
			this.title = '修改菜单';
			var menuId = selectedData.menuId;

			Ajax.request({
				url: vm.baseUrl + "/" + menuId,
				async: true,
				type: 'GET',
				successCallback: function (r) {
					vm.menu = r.data;
					if (vm.menu.parentName == null) {
						vm.menu.parentName = "一级菜单";
					}
					vm.openDialog();
				}
			});
		},
		del: function () {
			var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
			if (selectedData.menuId == undefined) {
				vm.$message.warning("请选择要删除的数据！");
				return;
			}
			confirm("确认要删除吗，删除后子节点都将被删除？", function () {
				var menuId = selectedData.menuId;
				Ajax.request({
					url: vm.baseUrl + "/" + menuId,
					type: 'DELETE',
					successCallback: function () {
						alert('操作成功', function (index) {
							vm.reload();
						});
					}
				});
			});
		},
		saveOrUpdate: function (layerIndex) {
			var url = vm.baseUrl;
			if (vm.showList) {
				var type = vm.menu.menuId == '' ? 'POST' : 'PATCH';
				Ajax.request({
					url: url,
					params: JSON.stringify(vm.menu),
					contentType: "application/json",
					type: type,
					successCallback: function () {
						layer.close(layerIndex);
						alert('操作成功', function (index) {
							vm.reload();
						});
					}
				});
			} else {
				Ajax.request({
					url: url,
					params: JSON.stringify(vm.permsMenu.permsMenus),
					contentType: "application/json",
					type: 'PUT',
					successCallback: function () {
						alert('操作成功', function (index) {
							vm.back();
							vm.reload();
						});
					}
				});
			}
		},
		openDialog: function () {
			openWindow({
				title: this.title,
				area: ['600px', '530px'],
				content: jQuery("#" + vm.moduleName + "Dialog"),
				btn: ['保存', '取消'],
				yes: function (index) {
					vm.handleSubmit(vm.moduleName + 'Form', index);
				},
				end: function () {
					vm.handleReset(vm.moduleName + 'Form');
				}
			});
		},
		isDeletedMenuById: function (menuId, isDeleted) {
			var url = isDeleted ? vm.baseUrl + "/enable/" + menuId : vm.baseUrl + "/disable/" + menuId;
			Ajax.request({
				url: url,
				async: true,
				type: 'GET',
				successCallback: function (r) {
					alert('操作成功', function (index) {
						vm.reload();
					});
				}
			});
		},
		managePerms: function () {
			var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
			if (selectedData.menuId == undefined) {
				vm.$message.warning("请选择要处理的数据！");
				return;
			}
			if (selectedData.type != "1") {
				vm.$message.warning("请选择一个菜单类型的数据！");
				return;
			}
			vm.permsMenu.parentId = selectedData.menuId;
			vm.permsMenu.parentName = selectedData.name;
			vm.permsMenu.url = selectedData.url;
			vm.permsMenu.permsMenus = [];
			vm.showList = false;
			Ajax.request({
				url: vm.baseUrl + "/child/" + selectedData.menuId,
				async: true,
				type: 'GET',
				successCallback: function (r) {
					if (r.code == 0) {
						vm.permsMenu.permsMenus = r.data;
					}
				}
			});
		},
		addPermsMenu: function () {
			vm.permsMenu.permsMenus.push({
				parentId: vm.permsMenu.parentId
			});
		},
		autoBuildPermsMenu: function () {
			var url = vm.permsMenu.url;
			var perms = url.replace('.html', '').replace(/\//g, ':').substring(1) + ':';
			var permsTemplate = [{
				name: '新增',
				perms: perms + 'insert',
				orderNum: 0,
				parentId: vm.permsMenu.parentId
			}, {
				name: '删除',
				perms: perms + 'delete',
				orderNum: 1,
				parentId: vm.permsMenu.parentId
			}, {
				name: '批量删除',
				perms: perms + 'deleteBatch',
				orderNum: 2,
				parentId: vm.permsMenu.parentId
			}, {
				name: '修改',
				perms: perms + 'update',
				orderNum: 3,
				parentId: vm.permsMenu.parentId
			}, {
				name: '查询',
				perms: perms + 'info',
				orderNum: 4,
				parentId: vm.permsMenu.parentId
			}, {
				name: '分页查询',
				perms: perms + 'page',
				orderNum: 5,
				parentId: vm.permsMenu.parentId
			}]
			vm.permsMenu.permsMenus = vm.permsMenu.permsMenus.concat(permsTemplate);
		},
		back: function () {
			vm.showList = true;
		},
		removePermsMenu: function (item) {
			var index = vm.permsMenu.permsMenus.indexOf(item)
			if (index !== -1) {
				vm.permsMenu.permsMenus.splice(index, 1)
			}
		},
		handleSubmit: function (name, layerIndex) {
			handleSubmitValidate(vm, name, function () {
				vm.saveOrUpdate(layerIndex);
			});
		},
		handleReset: function (name) {
			handleResetForm(vm, name);
		},
		reload: function () {
			vm.query();
		}
	},
	watch: {
		'menu.icon': function () {
			vm.iconLoading = true;
			window.setTimeout(function () {
				vm.iconLoading = false;
				vm.iconShow = vm.menu.icon;
			}, 500);
		}
	}
});

var treeGrid = null;

layui.config({
	base: '/plugins/layui/extend/'
}).extend({
	treeGrid: 'treeGrid'
}).use(['jquery', 'treeGrid', 'layer'], function () {
	var $ = layui.jquery;
	treeGrid = layui.treeGrid; //很重要
	var layer = layui.layer;
	var form = layui.form;
	var ptable = treeGrid.render({
		id: vm.moduleName + "Table",
		elem: '#' + vm.moduleName + 'Table',
		idField: 'menuId',
		url: vm.baseUrl,
		method: 'GET',
		cellMinWidth: 100,
		loading: false,
		treeId: 'menuId', //树形id字段名称
		treeUpId: 'parentId', //树形父id字段名称
		treeShowName: 'name', //以树形式显示的字段
		iconOpen: false, //是否显示图标【默认显示】
		isOpenDefault: false, //节点默认是展开还是折叠【默认展开】
		page: false,
		cols: [
			[{
				type: 'radio'
			}, {
				field: 'name',
				width: 200,
				title: '名称',
				templet: function (d) {
					if (d.icon != null) {
						return '&nbsp;&nbsp;<i class="' + d.icon + ' fa-lg"></i>&nbsp;&nbsp;' + d.name;
					}
					return d.name;
				}
			}, {
				field: 'parentName',
				width: 100,
				title: '上级菜单',
				templet: function (d) {
					if (d.parentName == null) {
						return "一级菜单";
					} else {
						return d.parentName;
					}
				}
			}, {
				field: 'type',
				width: 80,
				title: '类型',
				align: 'center',
				templet: function (d) {
					if (d.type === "0") {
						return '<span class="layui-badge layui-bg-green">目录</span>';
					}
					if (d.type === "1") {
						return '<span class="layui-badge layui-bg-blue">菜单</span>';
					}
					if (d.type === "2") {
						return '<span class="layui-badge layui-bg-orange">按钮</span>';
					}
				}
			}, {
				field: 'orderNum',
				width: 60,
				title: '排序',
				align: 'center'
			}, {
				field: 'url',
				title: '菜单URL'
			}, {
				field: 'perms',
				title: '授权标识'
			}, {
				field: 'isDeleted',
				title: '操作',
				width: 90,
				templet: function (d) {
					var checked = d.isDeleted === '0' ? 'checked' : '';
					return '<input ' + checked + ' type="checkbox" name="isDeleted" value="' + d.menuId +
						'" lay-skin="switch" lay-text="开启|禁用" lay-filter="isDeleted">'
				}
			}]
		]
	});

	form.on('switch(isDeleted)', function (obj) {
		var menuId = this.value;
		if (obj.elem.checked) {
			confirm("确认要开启吗，开启后子节点都将被开启？", function () {
				vm.isDeletedMenuById(menuId, true);
			}, function () {
				$("input[type='checkbox'][value='" + menuId + "']").prop("checked", false);
				form.render('checkbox');
			});
		} else {
			confirm("确认要禁用吗，禁用后子节点都将被禁用？", function () {
				vm.isDeletedMenuById(menuId, false);
			}, function () {
				$("input[type='checkbox'][value='" + menuId + "']").prop("checked", true);
				form.render('checkbox');
			});
		}
	});
});