var vm = new Vue({
	el : '#app',
	data : {
		menuItem : []
	},
	methods : {
		openTab : function(title, url) {
			newTab(title, url);
		}
	},
	created : function() {
		Ajax.request({
			url : "/sys/menus/enable/",
			async: true,
			successCallback: function(r) {
				if (r.data.length > 0) {
					for (var menu of r.data) {
						if (menu.url != null) {
							vm.menuItem.push(menu);
						}
					}
				}
			}
		});
	}
});