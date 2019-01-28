var vm = new Vue({
	el : '#app',
	data : {
		moduleName : 'databaseDoc',
		baseUrl : '/develop/databaseDocs',
		q : {
			id : ''
		},
		datasourceItem : []
	},
	methods : {
		query : function() {
			
		},
		queryDatabaseDoc : function () {
			Ajax.request({
				url: '/develop/databaseDocs/' + vm.q.id,
				async: true,
				successCallback: function(r) {
					
				}
			});
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
				if (r.data.length > 0) {
					for (var database of r.data) {
						var obj = {
								label : database.name,
								value : database.id
						}
						vm.datasourceItem.push(obj);
					}
				}
			}
		});
	}
});

layui.use(['table'], function() {
	var table = layui.table;
});