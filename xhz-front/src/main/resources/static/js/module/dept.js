var vm = new Vue({
    el: '#app',
    data: {
        moduleName: 'dept',
        baseUrl: '/sys/depts',
        title: '',
        q: {
            code: '',
            name: '',
            isDeleted: ''
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
        showList: false
    },
    methods: {
        query: function () {
            layui.table.reload(vm.moduleName + 'Table', {
                where: {

                }
            });
        },
        add: function () {
            vm.title = '添加机构';
            vm.showList = false;
        },
        update: function () {
            vm.title = '修改机构';
            vm.showList = false;
        },
        del: function () {

        },
        saveOrUpdate: function (layerIndex) {

        },
        openDialog: function () {

        },
        selectDept: function () {
            // alert(123);
            vm.dept.parentDeptName = '123';
        },
        reload: function () {
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
            handleResetForm(vm, name);
        }
    }
});

layui.config({
    base: '/plugins/layui/extend/'
}).extend({
    treeGrid: 'treeGrid'
}).use(['jquery', 'treeGrid'], function () {
    layui.treeGrid.render({
        id: vm.moduleName + "Table",
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
        cols: [
            [{
                type: 'radio'
            }, {
                field: 'code',
                title: '机构代码'
            }, {
                field: 'name',
                title: '机构名称'
            }, {
                field: 'parentDeptName',
                title: '上级机构名称'
            }, {
                field: 'orderNum',
                width: 100,
                title: '排序号'
            }]
        ],
    });

    // form.on('switch(isDeleted)', function (obj) {
    //     var menuId = this.value;
    //     if (obj.elem.checked) {
    //         confirm("确认要开启吗，开启后子节点都将被开启？", function () {
    //             vm.isDeletedMenuById(menuId, true);
    //         }, function () {
    //             $("input[type='checkbox'][value='" + menuId + "']").prop("checked", false);
    //             form.render('checkbox');
    //         });
    //     } else {
    //         confirm("确认要禁用吗，禁用后子节点都将被禁用？", function () {
    //             vm.isDeletedMenuById(menuId, false);
    //         }, function () {
    //             $("input[type='checkbox'][value='" + menuId + "']").prop("checked", true);
    //             form.render('checkbox');
    //         });
    //     }
    // });
});