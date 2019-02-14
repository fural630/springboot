var vm = new Vue({
    el: '#app',
    data: {
        moduleName: '',
        baseUrl: '',
        title: '',
        q: {

        },
        ruleValidate: {

        },
    },
    methods: {
        query: function () {
            layui.table.reload(vm.moduleName + 'Table', {
                where: {

                }
            });
        },
        add: function () {

        },
        update: function () {

        },
        del: function () {

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