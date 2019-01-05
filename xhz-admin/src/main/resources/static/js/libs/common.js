/**
 * 翻译日期
 * @param date
 * @param fmt
 * @returns {*}
 */
function transDate(date, fmt) {
    if (date) {
        if (typeof date == 'string') {
            return new Date(date).dateFormat(fmt);
        } else {
            try {
                return new Date(date.replace('-', '/').replace('-', '/')).dateFormat(fmt);
            } catch (e) {
                return '-';
            }
        }
    } else {
        return '';
    }
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
            iview.Message.error('请填写完整信息!');
            return false;
        }
    })
};


//重写alert
window.alert = function (msg, callback) {
    // parent.layer.alert 弹出在iframe外的页面。
    layer.alert(msg, function (index) {
        layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
};


//重写confirm式样框
window.confirm = function(msg, callback) {
	// 如果没有定义回调函数，直接返回true
	if (!callback) {
		return true;
	}
	layer.confirm(msg, {
		skin : 'layui-layer-molv',
		btn : [ '确定', '取消' ]
	}, function() {// 确定事件
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
};


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
       if (typeof(opt.type) == 'undefined') {
           opt.type = 'GET'
       }
       if (typeof(opt.async) == 'undefined') {
           opt.async = false;
       }
       if (typeof(opt.dataType) == 'undefined') {
           opt.dataType = 'json'
       }
       if (typeof(opt.contentType) == 'undefined') {
           opt.contentType = 'application/x-www-form-urlencoded;chartset=UTF-8'
       }
       if (typeof(opt.params) == 'undefined' || opt.params == null) {
           opt.params = {};
       }
       opt.params.date = new Date();
       if (typeof(opt.beforeSubmit) != 'undefined') {
           var flag = opt.beforeSubmit(opt);
           if (!flag) {
               return;
           }
       }

       if (typeof(opt.resultMsg) == 'undefined') {
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
                   var result = {code: null};
                   if (typeof data == 'string') {
                       result = eval('(' + data + ')')
                   } else if (typeof data == 'object') {
                       result = data;
                   }

                   if (opt.resultMsg && result.msg) {
                       layer.alert(result.msg, {icon: 5});
                   }
                   return;
               }
               if (opt.resultMsg && data.msg) {
                   layer.alert(data.msg, {icon: 6}, function () {
                       if (typeof(opt.successCallback) != 'undefined') {
                           opt.successCallback(data);
                       }
                   });
                   return;
               }

               if (typeof(opt.successCallback) != 'undefined') {
                   opt.successCallback(data);
               }
           },
           error: function () {
               //关闭遮罩
               dialogLoading(false);

               layer.alert("此页面发生未知异常,请联系管理员", {icon: 5});
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


function dialogLoading(flag) {
    if (flag) {
        layer.load(1, {
            shade: [0.2, '#000'],
            time: 5000
        });
    } else {
        layer.closeAll('loading');
    }
}