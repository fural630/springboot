var vm = new Vue({
    el: '#app',
    data: {
        moduleName: 'dept',
        baseUrl: '/sys/depts',
        title: '',
        q: {
            code: '',
            name: ''
        },
        dept: {
            deptId: '',
            parentId: '0',
            parentDeptName: '',
            name: '',
            orderNum: ''
        },
        ruleValidate: {
            name: [{
                required: true,
                message: '机构名称不能为空',
                trigger: 'blur'
            }]
        },
        showList: true
    },
    methods: {
        query: function () {
            treeGrid.reload(vm.moduleName + 'Table', {
                where: {
                    code: vm.q.code,
                    name: vm.q.name
                }
            });
        },
        add: function () {
            var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
            vm.handleReset(vm.moduleName + 'Form');
            if (selectedData.deptId != undefined) {
                vm.dept.parentId = selectedData.deptId;
                vm.dept.parentDeptName = selectedData.name;
                vm.dept.orderNum = (parseInt(selectedData.orderNum) + 1).toString();
            }
            vm.title = '添加机构';
            vm.showList = false;
        },
        update: function () {
            var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
            if (selectedData.deptId == undefined) {
                vm.$message.warning("请选择要修改的数据！");
                return;
            }
            var id = selectedData.deptId
            Ajax.request({
                url: vm.baseUrl + "/" + id,
                async: true,
                successCallback: function (r) {
                    vm.dept = r.data;
                    vm.title = '修改机构';
                    vm.showList = false;
                }
            });
        },
        del: function () {
            var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
            if (selectedData.deptId == undefined) {
                vm.$message.warning("请选择要删除的数据！");
                return;
            }
            confirm("确认要删除吗，删除后子机构都将被删除？", function () {
                var deptId = selectedData.deptId;
                Ajax.request({
                    url: vm.baseUrl + "/" + deptId,
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
            var type = vm.dept.deptId == '' ? 'POST' : 'PATCH';
            Ajax.request({
                url: vm.baseUrl,
                params: JSON.stringify(vm.dept),
                contentType: "application/json",
                type: type,
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        selectDept: function () {
            // alert(123);
            //            vm.dept.parentDeptName = '123';
        },
        reload: function () {
            vm.showList = true;
            vm.query();
        },
        back: function () {
            vm.showList = true;
        },
        handleSubmit: function (name, layerIndex) {
            handleSubmitValidate(vm, name, function () {
                vm.saveOrUpdate(layerIndex);
            });
        },
        handleReset: function (name) {
            vm.dept = {
                deptId: '',
                parentId: '0',
                parentDeptName: '',
                name: '',
                orderNum: '0'
            };
            handleResetForm(vm, name);
        }
    }
});

var treeGrid = null;

layui.config({
    base: '/plugins/layui/extend/'
}).extend({
    treeGrid: 'treeGrid'
}).use(['jquery', 'treeGrid'], function () {
    var form = layui.form;
    treeGrid = layui.treeGrid; //很重要
    treeGrid.render({
        id: vm.moduleName + 'Table',
        elem: '#' + vm.moduleName + 'Table',
        idField: 'deptId',
        url: vm.baseUrl,
        method: 'GET',
        cellMinWidth: 100,
        loading: false,
        treeId: 'deptId', //树形id字段名称
        treeUpId: 'parentId', //树形父id字段名称
        treeShowName: 'name', //以树形式显示的字段
        iconOpen: false, //是否显示图标【默认显示】
        isOpenDefault: false, //节点默认是展开还是折叠【默认展开】
        page: false,
        height: 'full-150',
        cols: [
            [{
                type: 'radio'
            }, {
                field: 'code',
                width: 230,
                title: '机构代码',
                event: 'openChild'
            }, {
                field: 'orderNum',
                width: 100,
                title: '排序号',
                event: 'editOrderNmu'
            }, {
                field: 'name',
                title: '机构名称',
                event: 'openChild'
            }, {
                field: 'parentDeptName',
                title: '上级机构名称',
                event: 'openChild'
            }]
        ],
    });

    treeGrid.on('tool(' + vm.moduleName + 'Table)', function (obj) {
        if (obj.event === 'openChild') {
            var o = obj.data;
            treeGrid.treeNodeOpen(vm.moduleName + 'Table', o, !o[treeGrid.config.cols.isOpen]);
        }
        if (obj.event === 'editOrderNmu') {
            alert(obj.data.orderNum);
        }
    });


});