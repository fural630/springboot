var vm = new Vue({
    el: '#app',
    data: {
        moduleName: 'dictionary',
        baseUrl: '/sys/dictionarys',
        q: {
            name: '',
            value: ''
        },
        dictionary: {
            id: '',
            parentId: '',
            parentName: '',
            type: '0',
            name: '',
            value: '',
            orderNum: '0',
            remark: '',
            isDeleted: '0',
        },
        title: '',
        ruleValidate: {
            name: [{
                required: true,
                message: '名称不能为空',
                trigger: 'blur'
            }],
            value: [{
                required: true,
                message: '此项不能为空',
                trigger: 'blur'
            }]
        }
    },
    methods: {
        query: function () {
            treeGrid.reload(vm.moduleName + 'Table', {
                where: {
                    name: vm.q.name,
                    value: vm.q.value
                }
            });
        },
        add: function () {
            var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
            var type = '0';
            var parentId = '0';
            var parentName = '顶级目录';
            if (selectedData.id != undefined) {
                type = (parseInt(selectedData.type) + 1) + "";
                parentId = selectedData.id;
                parentName = selectedData.name;
            }
            if (type === '2') {
                vm.$message.warning("参数配置下不允许再添加字典，请选择目录层级！");
                return;
            }
            vm.title = '添加字典';
            vm.dictionary = {
                id: '',
                parentId: parentId,
                name: '',
                parentName: parentName,
                type: type,
                value: '',
                orderNum: '0',
                remark: '',
                isDeleted: '0'
            };
            vm.openDialog();
        },
        update: function () {
            var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
            if (selectedData.id == undefined) {
                vm.$message.warning("请选择要修改的数据！");
                return;
            }
            this.title = '修改字典';
            var id = selectedData.id;

            Ajax.request({
                url: vm.baseUrl + "/" + id,
                async: true,
                type: 'GET',
                successCallback: function (r) {
                    vm.dictionary = r.data;
                    if (vm.dictionary.parentName == null) {
                        vm.dictionary.parentName = "顶级目录";
                    }
                    vm.openDialog();
                }
            });
        },
        del: function () {
            var selectedData = treeGrid.radioStatus(vm.moduleName + 'Table');
            if (selectedData.id == undefined) {
                vm.$message.warning("请选择要删除的数据！");
                return;
            }
            confirm("确认要删除吗，删除后子节点都将被删除？", function () {
                var i = selectedData.id;
                Ajax.request({
                    url: vm.baseUrl + "/" + i,
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
            var type = vm.dictionary.id == '' ? 'POST' : 'PATCH';
            Ajax.request({
                url: vm.baseUrl,
                params: JSON.stringify(vm.dictionary),
                contentType: "application/json",
                type: type,
                successCallback: function () {
                    layer.close(layerIndex);
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        openDialog: function () {
            openWindow({
                title: vm.title,
                area: '600px',
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
        reload: function () {
            vm.query();
        },
        isDeletedById: function (id, isDeleted) {
            var url = isDeleted ? vm.baseUrl + "/enable/" + id : vm.baseUrl + "/disable/" + id;
            Ajax.request({
                url: url,
                async: true,
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });

        },
        handleSubmit: function (name, layerIndex) {
            handleSubmitValidate(vm, name, function () {
                vm.saveOrUpdate(layerIndex);
            });
        },
        handleReset: function (name) {
            handleResetForm(vm, name);
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
        idField: 'id',
        url: vm.baseUrl,
        method: 'GET',
        cellMinWidth: 100,
        loading: false,
        treeId: 'id', //树形id字段名称
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
                field: 'type',
                width: 100,
                title: '类型',
                align: 'center',
                templet: function (d) {
                    if (d.type === "0") {
                        return '<span class="layui-badge layui-bg-green">目录</span>';
                    }
                    if (d.type === "1") {
                        return '<span class="layui-badge layui-bg-blue">参数配置</span>';
                    }
                },
                event: 'openChild'
            }, {
                field: 'name',
                width: 200,
                title: '名称',
                event: 'openChild'
            }, {
                field: 'value',
                width: 200,
                title: '值',
                event: 'openChild'
            }, {
                field: 'orderNum',
                width: 60,
                title: '排序',
                align: 'center',
                event: 'openChild'
            }, {
                field: 'remark',
                title: '备注',
                event: 'openChild'
            }, {
                field: 'createTime',
                title: '创建时间',
                templet: function (d) {
                    return constant.transDate(d.createTime);
                },
                event: 'openChild'
            }, {
                field: 'updateTime',
                title: '修改时间',
                templet: function (d) {
                    return constant.transDate(d.updateTime);
                },
                event: 'openChild'
            }, {
                field: 'isDeleted',
                title: '操作',
                width: 90,
                templet: function (d) {
                    var checked = d.isDeleted === '0' ? 'checked' : '';
                    return '<input ' + checked + ' type="checkbox" name="isDeleted" value="' + d.id +
                        '" lay-skin="switch" lay-text="开启|禁用" lay-filter="isDeleted">'
                }
            }]
        ]
    });

    treeGrid.on('tool(' + vm.moduleName + 'Table)', function (obj) {
        if (obj.event === 'openChild') {
            var o = obj.data;
            treeGrid.treeNodeOpen(vm.moduleName + 'Table', o, !o[treeGrid.config.cols.isOpen]);
        }
    });

    form.on('switch(isDeleted)', function (obj) {
        var id = this.value;
        if (obj.elem.checked) {
            confirm("确认要开启吗，开启后子节点都将被开启？", function () {
                vm.isDeletedById(id, true);
            }, function () {
                $("input[type='checkbox'][value='" + id + "']").prop("checked", false);
                form.render('checkbox');
            });
        } else {
            confirm("确认要禁用吗，禁用后子节点都将被禁用？", function () {
                vm.isDeletedById(id, false);
            }, function () {
                $("input[type='checkbox'][value='" + id + "']").prop("checked", true);
                form.render('checkbox');
            });
        }
    });
});