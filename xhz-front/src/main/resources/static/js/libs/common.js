//重写alert
window.alert = function (msg, callback) {
    // parent.layer.alert 弹出在iframe外的页面。
    layer.alert(msg, function (index) {
        layer.close(index);
        if (typeof (callback) === "function") {
            callback("ok");
        }
    });
};

// 重写confirm式样框
window.confirm = function (msg, callback, callback2) {
    // 如果没有定义回调函数，直接返回true
    if (!callback) {
        return true;
    }
    layer.confirm(msg, {
        icon: 3,
        skin: 'layui-layer-lan',
        btn: ['确定', '取消']
    }, function () { // 确定事件
        if (typeof (callback) === "function") {
            callback("ok");
        }
    }, function () { // 取消事件
        if (typeof (callback2) === "function") {
            callback2("ok");
        }
    });
};


/**
 * 
 * @param options
 */
window.openWindow = function (options) {
    let globalParams = {
        skin: 'layui-layer-lan', //皮肤
        title: '标题', //标题
        type: 1, //打开窗口的类型 1：html里的div内容 2：iframe方式，页面的路径
        closeBtn: 1, //关闭按钮的形状 0、1
        anim: -1,
        isOutAnim: false,
        shadeClose: false,
        area: ['90%', '95%'],
        content: '',
        btn: false, //按钮
        top: false //窗口弹出是否在iframe上层
    };
    globalParams = $.extend(globalParams, options);
    if (globalParams.top) {
        return parent.layer.open(globalParams);
    } else {
        return layer.open(globalParams);
    }
};

/**
 * 预览图片
 * @param data
 */
function eyeImages(data) {
    layer.photos({
        photos: {
            "title": "预览", //相册标题
            "start": 0, //初始显示的图片序号，默认0
            "data": data
        },
        anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
    });
};

/**
 * 重置验证
 * @param vue vue对象
 * @param name
 */
function handleResetForm(vue, name) {
    vue.$refs[name].resetFields();
};

/**
 * 表单验证
 * @param vue vue对象
 * @param name 验证规则
 * @param callback 验证通过回调函数
 */
function handleSubmitValidate(vue, name, callback) {
    vue.$refs[name].validate(function (valid) {
        if (valid) {
            callback();
        } else {
            vue.$message.error('请填写完整信息!');
            return false;
        }
    })
};

function toUrl(href) {
    window.location.href = href;
}

function newTab(title, url) {
    top.$.learuntab.openTab(title, url);
}

/**
 * 超时登录的弹出层
 */
function openLoginDialog(title) {
    openWindow({
        title: title,
        type: 2,
        top: true,
        shade: [0.8, '#000'],
        area: ['900px', '630px'],
        anim: 6,
        resize: false,
        closeBtn: 0,
        content: '/login.html?target=self'
    });
}

function dialogLoading(flag) {
    if (flag) {
        layer.load(2, {
            shade: [0.2, '#000'],
            time: 10 * 1000
        });
    } else {
        layer.closeAll('loading');
    }
}


/**
 * 用JS获取地址栏参数的方法 使用示例 location.href = http://localhost:8080/index.html?id=123
 * getQueryString('id') --> 123;
 * 
 * @param name
 * @returns {null}
 * @constructor
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}


/**
 * 主要功能:导出功能公共方法
 *
 * @param formId 表单ID,带'#'号,如'#formId'
 * @param url 请求后台地址
 * @param extraObj 往后台请求额外参数,对象格式,如:{'aaa': 111}
 */
function exportFile(formId, url, extraObj) {
    var form = $('<form>'); //定义一个form表单
    form.attr('style', 'display: none');
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', url);

    var json = getJson(formId);
    if (typeof extraObj != 'undefined') {
        json = $.extend(json, extraObj);
    }

    $('body').append(form); //将表单放置在web中
    for (var i in json) {
        var input = $('<input>');
        input.attr('type', 'hidden');
        input.attr('name', i);
        input.attr('value', json[i]);
        form.append(input);
    }

    form.submit(); //表单提交
}

/**
 * 将form转化为json
 * @param form 传入 form表单的dom $("#baseFm")
 * @returns {___anonymous49_50}  序列化的键值对 {key:value,key2:value2,....}
 */
function getJson(form) {
    var o = {};
    var $form = $(form).find('input,textarea,select');
    $.each($form, function (i, item) {
        var $this = $(item);

        if ($this.attr("type") == 'radio') {
            o[$this.attr("name")] = $("input[name='" + $this.attr("name") + "']:checked").val();
            return true;
        }
        o[$this.attr("name")] = $this.val();
    })
    return o;
}

/**
 * 
 * Ajax.request({ url: '', //访问路径 dataType: 'json', //访问类型 'json','html'等
 * params: getJson(form), resultMsg: true, false, //是否需要提示信息 type:
 * 'GET',//,'get','post' beforeSubmit: function (data) {},//提交前处理
 * successCallback: function (data) {} //提交后处理 });
 */
Ajax = function () {

    //var opt = { type:'GET',dataType:'json',resultMsg:true };
    function request(opt) {
        //添加遮罩层
        dialogLoading(true);

        if (typeof opt.cache == 'undefined') {
            opt.cache = false;
        }

        if (typeof opt == 'undefined') {
            return;
        }
        //opt = $.extend(opt, p);
        if (typeof (opt.type) == 'undefined') {
            opt.type = 'GET'
        }
        if (typeof (opt.async) == 'undefined') {
            opt.async = false;
        }
        if (typeof (opt.dataType) == 'undefined') {
            opt.dataType = 'json'
        }
        if (typeof (opt.contentType) == 'undefined') {
            opt.contentType = 'application/x-www-form-urlencoded;chartset=UTF-8'
        }
        if (typeof (opt.params) == 'undefined' || opt.params == null) {
            opt.params = {};
        }
        // opt.params.date = new Date();
        if (typeof (opt.beforeSubmit) != 'undefined') {
            var flag = opt.beforeSubmit(opt);
            if (!flag) {
                return;
            }
        }

        if (typeof (opt.resultMsg) == 'undefined') {
            opt.resultMsg = true;
        }

        $.ajax({
            async: opt.async,
            url: opt.url,
            dataType: opt.dataType,
            contentType: opt.contentType,
            data: opt.params,
            crossDomain: opt.crossDomain || false,
            type: opt.type,
            cache: opt.cache,
            success: function (data) {
                //关闭遮罩
                dialogLoading(false);

                if (typeof data == 'string' && data.indexOf("exception") > 0 || typeof data.code != 'undefined' && data.code != '0') {
                    var result = {
                        code: null
                    };
                    if (typeof data == 'string') {
                        result = eval('(' + data + ')')
                    } else if (typeof data == 'object') {
                        result = data;
                    }

                    if (opt.resultMsg && result.msg) {
                        layer.alert(result.msg, {
                            icon: 5
                        });
                    }
                    return;
                }
                if (opt.resultMsg && data.msg) {
                    layer.alert(data.msg, {
                        icon: 6
                    }, function () {
                        if (typeof (opt.successCallback) != 'undefined') {
                            opt.successCallback(data);
                        }
                    });
                    return;
                }

                if (typeof (opt.successCallback) != 'undefined') {
                    opt.successCallback(data);
                }
            },
            error: function (xhr) {
                //关闭遮罩
                dialogLoading(false);
                var result = eval('(' + xhr.responseText + ')');
                // 处理登录超时后台返回session超时的情况
                if (result.code == 301) {
                    openLoginDialog(result.msg);
                } else {
                    layer.alert(result.msg, {
                        icon: 5,
                        anim: 6
                    }, function (index) {
                        layer.close(index);
                    });
                }
            }
        });
    }

    return {
        /**
         * Ajax调用request
         */
        request: request
    };
}();


/**
 * 缓存字典数据
 * 使用方法：字典 调用方式为在table的列或者columns 的列中 formatter:function(value){ return Dict.getDictValue(groupCode,value);}
 * 其中value为类型code  返回值为类型名称
 */
Dict = function () {
    return {
        getDictValue: function (groupCode, dictKey) {
            var dictValue = '-';
            Ajax.request({
                url: '/sys/dict/getDictValue',
                dataType: 'json',
                params: {
                    groupCode: groupCode,
                    dictKey: dictKey
                },
                cache: true,
                async: false,
                type: 'GET',
                successCallback: function (data) {
                    dictValue = data.dictValue;
                }
            });
            return dictValue;
        }
    };
}();

Ztree = function () {
    function request(opt) {
        // 获取数据请求的url地址
        if (typeof opt.url == 'undefined') {
            opt.url = '';
        }
        // ztree是否启用radio
        if (typeof opt.radio == 'undefined') {
            opt.radio = false;
        }
        // ztree是否启用checkbox
        if (typeof opt.checkbox == 'undefined') {
            opt.checkbox = false;
        }
        // 设置ztree的选中值，需要传ID
        if (typeof opt.selected == 'undefined') {
            opt.selected = [];
        }
        // 弹出层大小设置
        if (typeof opt.area == 'undefined') {
            opt.area = ['300px', '460px'];
        }

        openWindow({
            type: 2,
            area: opt.area,
            content: '/common/commonTree.html',
            maxmin: true,
            btn: ['确定', '取消'],
            success: function (layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.resizeTreeHeight($(iframeWin).height());
                iframeWin.initDeptTree(opt);
            },
            yes: function (index, layero) {
                if (typeof (opt.yesCallback) != 'undefined') {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    var data = iframeWin.getSelectedData(opt);
                    opt.yesCallback(data, index);
                }
            },
            resizing: function (layero) { // 拉伸弹出层监听
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.resizeTreeHeight($(iframeWin).height());
            },
            full: function (layero) { // 最大化弹出层监听
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.resizeTreeHeight($(iframeWin).height());
            },
            restore: function (layero) { //重置弹出层监听
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.resizeTreeHeight($(iframeWin).height());
            }
        });
    }
    return {
        request: request
    };
}();