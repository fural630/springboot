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
	vm.query();
}

var vm = new Vue({
	el : '#app',
	data : {
		q : {
			czryMc : '',
			czryZh : '',
			gwzzid : ''
		},
		czry : {
			czryMc : '',
			czryZh : '',
			bmmc : '',
			dwmc : '',
			sjhm : '',
			email : '',
			sfzhm : '',
			xbdm : '',
			gwzzid : '',
			jsIdList : [],
			zfzh : '',
			zjyxksrq : '',
			zjyxjzrq : ''
		},
		gwzzList : constant.gwzzList,
		sexList : constant.sexList,
		qxList : constant.qxList,
		ruleValidate : {
			czryMc : [ {
				required : true,
				message : '姓名不能为空',
				trigger : 'blur'
			} ],
			czryZh : [ {
				required : true,
				message : '账号不能为空',
				trigger : 'blur'
			} ],
			bmmc : [ {
				required : true,
				message : '所属部门不能为空',
				trigger : 'blur'
			} ],
			xbdm : [ {
				required : true,
				message : '性别不能为空',
				trigger : 'blur',
				trigger : 'change',
				type : 'number'
			} ],
			jsIdList : [ {
				required : true,
				message : '角色不能为空',
				trigger : 'change',
				type : 'array'
			} ],
			dwmc : [ {
				required : true,
				message : '所属单位不能为空',
				trigger : 'blur'
			} ],
		},
		showList : true,
		zfzhForm : false,
		title : ''
	},
	computed : {
		czryZh () {
			return this.czry.czryZh
		}
	},
	watch : {
		czryZh (newValue, oldValue) {
　　　　		this.czry.sjhm = newValue
	　　},
	  'czry.jsIdList' : function (newValue, oldValue) {
		  this.czryJsOnChange(newValue);
	  }
	},
	methods : {
		czryJsOnChange : function (jsList) {
			var index = $.inArray("2fcb67cdfb9f416b8aff4941754a3f18", jsList);
			if (index >= 0) {
				this.zfzhForm = true;
			} else {
				this.zfzhForm = false;
			}
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
                			dwmc : list[i].sjdwmc,
                			icon : icon
                		};
                		zNodes.push(zNode);
                	}
                    ztree = $.fn.zTree.init($("#zzbmTree"), setting, zNodes);
                    ztree.expandAll(true);
                }
            });
		},
		query : function() {
			var dwId = '';
			var szbmId = '';
			var nodes = ztree.getSelectedNodes();
			if (nodes.length > 0) {
				var node = nodes[0];
				if (node.bmlx == '1') {
					dwId = node.id;
				} else if (node.bmlx == '2') {
					szbmId = node.id;
				}
			}
			layui.table.reload('czryTable', {
				where : {
					gwzzid : this.q.gwzzid === undefined ? '' : this.q.gwzzid,
					czryMc : this.q.czryMc,
					czryZh : this.q.czryZh,
					dwId : dwId,
					szbmId : szbmId
				}
			});
		},
		add : function() {
			var nodes = ztree.getSelectedNodes();
			if (nodes.length == 0) {
				iview.Message.warning('请在左侧组织树上选择要添加人员所在的部门!');
				return;
			}
			var node = nodes[0];
			if (node.bmlx == '1') {
				iview.Message.warning('人员不能在单位下，请选择一个部门节点!');
				return;
			}
			var bmmc = node.name;
			var dwmc = node.dwmc;
			var dwid = node.parentId;
			var szbmId = node.id;
			this.title = '添加';
			this.showList = false;
			this.czry = {
				czryMc : '',
				czryZh : '',
				bmmc : bmmc,
				dwmc : dwmc,
				dwid : dwid,
				szbmId : szbmId,
				sjhm : '',
				email : '',
				sfzhm : '',
				xbdm : '',
				gwzzid : '',
				jsIdList : [],
				zfzh : '',
				zjyxksrq : '',
				zjyxjzrq : ''
			}
		},
		update : function() {
			var checkStatus = layui.table.checkStatus('czryTable');
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

			var czryId = checkStatus.data[0].czryId;

			Ajax.request({
				url : "../sys/czry/info/" + czryId,
				async : true,
				successCallback : function(r) {
					vm.czry = r.czry;
					vm.czry.zjyxksrq = transDate(r.czry.zjyxksrq, 'yyyy-MM-dd');
					vm.czry.zjyxjzrq = transDate(r.czry.zjyxjzrq, 'yyyy-MM-dd');
				}
			});

		},
		del : function () {
			var checkStatus = layui.table.checkStatus('czryTable');
			var length = checkStatus.data.length;
			if (length == 0) {
				iview.Message.warning('请勾选要删除的数据!');
				return;
			}
			var ids = [];
			for (var i = 0 ; i < length; i++) {
				ids.push(checkStatus.data[i].czryId);
			}
			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/czry/deleteBatchByIds",
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
			var url = this.czry.czryId == null ? "../sys/czry/save"
					: "../sys/czry/update";
			Ajax.request({
				url : url,
				params : JSON.stringify(vm.czry),
				contentType : "application/json",
				type : 'POST',
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
	},
	created : function () {
		this.reloadTree();
	}
});

layui.use('table', function() {
	var table = layui.table;
	var $ = layui.$;

	table.render({
		elem : '#czryTable',
		toolbar : '#czryToolbar',
		url : '/sys/czry/page',
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
			field : 'czryId',
			title : 'ID',
			hide : true
		}, {
			field : 'czryDm',
			title : '人员代码'
		}, {
			field : 'czryMc',
			title : '姓名'
		}, {
			field : 'czryZh',
			title : '账号'
		}, {
			field : 'dwmc',
			title : '所在单位'
		}, {
			field : 'bmmc',
			title : '所在部门'
		}, {
			field : 'gwzzmc',
			title : '岗位职责'
		}, {
			field : 'zfzh',
			title : '执法证号'
		}, {
			field : 'zjyxksrq',
			title : '执法证有效期起',
			templet : function(d) {
				return transDate(d.zjyxksrq, 'yyyy年MM月dd日')
			}
		}, {
			field : 'zjyxjzrq',
			title : '执法证有效期止',
			templet : function(d) {
				return transDate(d.zjyxjzrq, 'yyyy年MM月dd日')
			}
		} ] ],
		page : true
	});

	table.on('sort(czryTable)', function(obj) {
		var column = obj.field;
		if (obj.field == "birthDay") {
			column = "birth_day";
		}
		table.reload('czryTable', {
			initSort : obj,
			where : {
				field : column,
				order : obj.type
			}
		});
	});
});