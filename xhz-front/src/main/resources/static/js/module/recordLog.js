var vm = new Vue({
    el: '#app',
    data: {
        moduleName: 'recordLog',
        baseUrl: '/sys/recordLogs',
        title: '',
        q: {
            userName: '',
            operation: '',
            createDateList: []
        },
        ruleValidate: {

        },
    },
    computed: {
        createDateStart: function () {
            return this.q.createDateList[0]
        },
        createDateEnd: function () {
            return this.q.createDateList[1]
        }
    },
    methods: {
        query: function () {
            layui.table.reload(vm.moduleName + 'Table', {
                where: {
                    userName: vm.q.userName,
                    operation: vm.q.operation,
                    createDateStart: vm.createDateStart === undefined ? '' : vm.createDateStart,
                    createDateEnd: vm.createDateEnd === undefined ? '' : vm.createDateEnd
                }
            });
        },
        add: function () {

        },
        update: function () {

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
        saveOrUpdate: function (layerIndex) {

        },
        openDialog: function () {

        },
        reload: function () {
            vm.query();
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

layui.use('table', function () {
    var table = layui.table;

    table.render({
        elem: '#' + vm.moduleName + 'Table',
        url: vm.baseUrl + '/page',
        autoSort: false,
        page: true,
        parseData: function (res) {
            return {
                'code': res.code,
                'count': res.data.total,
                'data': res.data.list
            };
        },
        cols: [
            [{
                type: 'checkbox'
            }, {
                field: 'id',
                width: 140,
                title: 'ID'
            }, {
                field: 'account',
                width: 170,
                title: '操作人账号'
            }, {
                field: 'userName',
                width: 100,
                title: '操作人名称'
            }, {
                field: 'method',
                title: '请求方法'
            }, {
                field: 'operation',
                width: 100,
                title: '操作方式'
            }, {
                field: 'params',
                title: '方法参数'
            }, {
                field: 'ip',
                width: 130,
                title: 'IP地址'
            }, {
                field: 'createDate',
                width: 160,
                title: '操作时间',
                templet: function (d) {
                    return constant.transDate(d.createDate);
                }
            }]
        ],
    });
});