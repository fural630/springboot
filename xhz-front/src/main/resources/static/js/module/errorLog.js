var vm = new Vue({
    el: '#app',
    data: {
        moduleName: 'errorLog',
        baseUrl: '/sys/errorLogs',
        title: '',
        q: {
            id: ''
        },
        ruleValidate: {

        },
    },
    methods: {
        query: function () {
            layui.table.reload(vm.moduleName + 'Table', {
                where: {
                    id: vm.q.id
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
        deleteBatch: function () {
            var ids = [];
            confirm('确定要删除所有记录，删除后不可恢复？', function () {
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
                title: '错误编号'
            }, {
                field: 'method',
                width: 90,
                title: '请求方式'
            }, {
                field: 'requestUrl',
                width: 200,
                title: '请求地址'
            }, {
                field: 'createTime',
                width: 160,
                title: '创建时间',
                templet: function (d) {
                    return constant.transDate(d.createTime);
                }
            }, {
                field: 'message',
                title: '错误详情'
            }]
        ],
    });
});