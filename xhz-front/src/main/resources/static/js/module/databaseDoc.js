var vm = new Vue({
	el : '#app',
	data : {
		moduleName : 'databaseDoc',
		baseUrl : '/develop/databaseDocs',
		q : {
			id : ''
		},
		datasourceItem : {}
	},
	methods : {
		query : function() {
		},
		reload : function() {
			vm.query();
		},
		handleReset : function(name) {
			handleResetForm(vm, name);
		}
	},
	created: function() {
		Ajax.request({
			url: '/develop/databases',
			async: true,
			type: 'GET',
			successCallback: function(r) {
//				console.log(r.data);
			}
		});
	}
});

layui.use('table', function() {
	var table = layui.table;
	var $ = layui.$;
	var form = layui.form;
	
});