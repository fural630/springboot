var constant = {
	sexItem : [ {
		label : '男',
		value : 1
	}, {
		label : '女',
		value : 0
	} ],
	isDeletedItem : [ {
		label : '正常',
		value : 1
	}, {
		label : '禁用',
		value : 0
	} ],
	dbTypeItem : [{
		label : 'Oracle',
		value : 1
	}, {
		label : 'Mysql',
		value : 0
	}],
	
	/**
	 * 翻译性别
	 */
	transGender : function(gender) {
		if (gender == 1) {
			return '男';
		}
		if (gender == 0) {
			return '女';
		}
		return '';
	},
	
	/**
	 * 翻译删除状态
	 */
	transIsDeleted : function (isDeleted) {
		if (isDeleted == 1) {
			return '<button class="layui-btn layui-btn-xs layui-btn-normal">正常</button>';
		}
		if (isDeleted == 0) {
			return '<button class="layui-btn layui-btn-xs layui-btn-danger">禁用</button>';
		}
		return '';
	},
	
	/**
	 * 翻译日期
	 * @param date
	 * @param fmt
	 * @returns {*}
	 */
	transDate : function(date, fmt) {
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
	},
}