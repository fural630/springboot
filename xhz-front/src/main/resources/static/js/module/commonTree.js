//全局树对象
var ztree;

/**
 * 获取树的选择结果
 * 如果是radio 返回单个选中的树节点对象
 * 如果是checkbox 返回一个选中的树节点对象数组
 * 如果以上都不是 返回单个选中的树节点对象
 * 如果整个树未选择节点，返回null
 * 注意：在radio和checkbox的时候，选中是指checkedNode，而不是selectedNodes
 * @param opt 
 */
function getSelectedData(opt) {
    var selectedNodes;
    if (opt.radio) {
        selectedNodes = ztree.getCheckedNodes(true);
        if (selectedNodes.length > 0) {
            return selectedNodes[0];
        }
    } else if (opt.checkbox) {
        selectedNodes = ztree.getCheckedNodes(true);
    } else {
        selectedNodes = ztree.getSelectedNodes();
        if (selectedNodes.length > 0) {
            return selectedNodes[0];
        }
    }
    return selectedNodes.length > 0 ? selectedNodes : null;
}
/**
 * 使树可根据弹出层自适应高度
 * @param windowHeight 弹出层窗口高度
 */
function resizeTreeHeight(windowHeight) {
    // 弹出层上方搜索框区域的高度
    var headerHeight = 107;
    $("#tree").height(windowHeight - headerHeight);
}

var vm = new Vue({
    el: '#appTree',
    data: {
        q: {
            name: ''
        }
    },
    methods: {
        fold: function () {
            // 折叠
            ztree.expandAll(false);
        },
        unfold: function () {
            // 展开
            ztree.expandAll(true);
        }
    },
    watch: {
        "q.name": function (newValue) {
            // 监听搜索框的内容，当数据变化时，对树进行过滤
            fuzzySearch('tree', '#search', false, true, newValue);
        }
    }
});


function initDeptTree(opt) {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        view: {
            dblClickExpand: false,
            selectedMulti: false
        },
        callback: {
            onClick: onClick
        }
    };
    if (opt.radio) {
        setting.check = {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        }
    } else if (opt.checkbox) {
        setting.check = {
            enable: true
        }
    }
    ztree = $.fn.zTree.init($("#tree"), setting, nodes);
    if (opt.selected.length > 0) {
        if (opt.radio || opt.checkbox) {
            var node = ztree.getNodeByParam("id", opt.selected[0]);
            ztree.selectNode(node);
            ztree.checkNode(node, true, true);
        } else {
            var node = ztree.getNodeByParam("id", opt.selected[0]);
            ztree.selectNode(node);
        }
    }
}

//单击展开节点
function onClick(e, treeId, treeNode) {
    ztree.expandNode(treeNode);
}

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