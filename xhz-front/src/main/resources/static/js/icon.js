var vm = new Vue({
	el : '#app',
	data : {
		q : {
			name : ''
		},
		iconItems : iconLib
	},
	methods : {
		query : function() {
			var searchIcon = [];
			for (var i = 0; i < iconLib.length; i++) {
				var obj = iconLib[i];
				var label = iconLib[i].label;
				var icon = iconLib[i].icon;
				if (icon.toLowerCase().indexOf(this.q.name.toLowerCase()) >= 0
						|| label.indexOf(this.q.name) >= 0) {
					searchIcon.push(obj);
				}
			}
			this.iconItems = searchIcon;
		},
		copyIcon : function(btnId) {
			var btn = document.getElementById(btnId);
			var clipboard = new ClipboardJS(btn);
			clipboard.on('success', function(e) {
				var content = '已复制 ' + e.text + '成功！';
				vm.$message.success(content);
			});

			clipboard.on('error', function(e) {
				console.log(e);
			});
		},
		handleReset : function(name) {
			handleResetForm(vm, name);
			this.iconItems = iconLib;
		}
	}
});
