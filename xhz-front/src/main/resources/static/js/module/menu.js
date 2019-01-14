var vm = new Vue({
	el: '#app',
	data: {
		q: {
			name: '',
			url: ''
		},
		title : '',
		iconLoading : false,
		iconShow : '',
		menu : {
			menuId : '',
			type :'',
			name : '',
			parentName : '',
			parentId : '',
			url : '',
			perms : '',
			orderNum : 0,
			icon : '',
			isDeleted : ''
		},
		ruleValidate: {
            name: [
                {required: true, message: '菜单名称不能为空', trigger: 'blur'}
            ],
            url: [
                {required: true, message: '菜单url不能为空', trigger: 'blur'}
            ],
            parentName: [
                {required: true, message: '上级菜单不能为空', trigger: 'blur'}
            ],
            icon: [
                {required: true, message: '请选择一个图标', trigger: 'blur'}
            ]
        }
	},
	methods: {
		query : function() {

		},
		add : function () {
			var selectedData = treeGrid.radioStatus("menuTable");
			var type = 0;
			var parentId = '';
			var parentName = '一级目录';
			if (selectedData.menuId != undefined) {
				type = selectedData.type + 1;
				parentId = selectedData.parentId;
				parentName = selectedData.name;
			}
			if (type == 3) {
				vm.$message.warning("按钮下不允许添加节点，请选择其他层级！");
				return;
			}
			this.title = '添加菜单';
			vm.menu = {
				menuId : '',
				type : type,
				name : '',
				parentName : parentName,
				parentId : parentId,
				url : '',
				perms : '',
				orderNum : 0,
				icon : '',
				isDeleted : 0
			};
			this.openDialog();
		},
		update : function () {
			var selectedData = treeGrid.radioStatus("menuTable");
			if (selectedData.menuId == undefined) {
				vm.$message.warning("请选择要修改的数据！");
				return;
			}
			this.title = '修改菜单';
			var menuId = selectedData.menuId;
			
			Ajax.request({
				url : "../sys/menu/" + menuId,
				async : true,
				type : 'GET',
				successCallback : function(r) {
					vm.menu = r.menu;
					if (r.menu.parentName == null) {
						vm.menu.parentName = "一级菜单";
					}
					vm.openDialog();
				}
			});
			
		},
		del : function () {
			layer.alert('偶吧深蓝style', {
			    skin: 'layui-layer-lan'
			    ,closeBtn: 0
			    ,anim: 4 //动画类型
			    ,btn: ['保存','取消']
			});
		},
		saveOrUpdate : function (layerIndex) {
			var url = '../sys/menu';
			var type = vm.menu.menuId == '' ? 'POST' : 'PATCH';
			Ajax.request({
				url : url,
				params : JSON.stringify(vm.menu),
				contentType : "application/json",
				type : type,
				successCallback : function() {
					layer.close(layerIndex);
					alert('操作成功', function(index) {
//						vm.reload();
					});
				}
			});
			
		},
		openDialog : function () {
			openWindow({
				title: this.title,
				area: ['600px', '570px'],
				content: jQuery("#menuDialog"),
				btn: ['保存','取消'],
				yes: function(index) {
					vm.handleSubmit('menuForm', index);
				},
				end : function () {
					vm.handleReset('menuForm');
				}
			});
		},
		handleSubmit : function(name, layerIndex) {
			handleSubmitValidate(vm, name, function() {
				vm.saveOrUpdate(layerIndex);
			});
		},
		handleReset: function(name) {
			handleResetForm(vm, name);
		}
	},
	watch : {
		'menu.icon' : function () {
			vm.iconLoading = true;
			window.setTimeout(function () {
				vm.iconLoading = false;
				vm.iconShow = vm.menu.icon;
            }, 500);
		}
	}
});

var	treeGrid = null;

layui.config({
	base: '/plugins/layui/extend/'
}).extend({
	treeGrid: 'treeGrid'
}).use(['jquery', 'treeGrid', 'layer'], function() {
	var $ = layui.jquery;
	treeGrid = layui.treeGrid; //很重要
	var layer = layui.layer;
	var ptable = treeGrid.render({
		id: "menuTable",
		elem: '#menuTable',
		idField: 'menuId',
		url: '../sys/menu',
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
				field: 'menuId',
				width: 100,
				title: '编号'
			}, {
				field: 'name',
				width: 200,
				title: '名称',
				templet: function(d) {
					if (d.icon != null) {
						return '&nbsp;&nbsp;<i class="' + d.icon + ' fa-lg"></i>&nbsp;&nbsp;' + d.name;
					}
					return d.name;
				}
			}, {
				field: 'parentName',
				width: 100,
				title: '上级菜单'
			}, {
				field: 'type',
				width: 80,
				title: '类型',
				align: 'center',
				templet: function(d) {
					if (d.type === 0) {
						return '<span class="layui-badge layui-bg-green">目录</span>';
					}
					if (d.type === 1) {
						return '<span class="layui-badge layui-bg-blue">菜单</span>';
					}
					if (d.type === 2) {
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
			},{
				field: 'isDeleted',
				title: '状态',
				width: 60,
				templet: function(d) {
					if (d.isDeleted === 0) {
						return '<span class="layui-badge layui-bg-blue">启用</span>';
					}
					if (d.isDeleted === 1) {
						return '<span class="layui-badge">禁用</span>';
					}
				}
			}]
		]
	});
});

//function del(obj) {
//	layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {
//			icon: 3,
//			title: '提示'
//		},
//		function(index) { //确定回调
//			obj.del();
//			layer.close(index);
//		},
//		function(index) { //取消回调
//			layer.close(index);
//		}
//	);
//}
//
//
//var i = 1000;
////添加
//function add(pObj) {
//	var param = {};
//	param.name = '水果' + Math.random();
//	param.id = ++i;
//	param.pId = pObj ? pObj.id : 0;
//	treeGrid.addRow(tableId, pObj ? pObj.LAY_TABLE_INDEX + 1 : 0, param);
//}
//
//function print() {
//	console.log(treeGrid.cache[tableId]);
//	var loadIndex = layer.msg("对象已打印，按F12，在控制台查看！", {
//		time: 3000,
//		offset: 'auto' //顶部
//			,
//		shade: 0
//	});
//}
