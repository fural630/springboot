var vm = new Vue({
	el : '#app',
	data : {
		q : {
			name : ''
		},
		commonIcon : {
			iconName : '',
			space : 0,
			zoom : 1,
			rotate : ''
		},
		iconItems : iconLib,
		hasResult : false,
		template : '<i class="fa fa-{0}{1}"></i>{2}{3}',
		iconCode : '',
		iconShow : '',
		iconCopyCode : '',
		iconShow90 : '',
		iconShow180 : '',
		iconShow270 : '',
		iconShowHorizontal : '',
		iconShowVertical : ''
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
		copyIcon : function(iconText) {
			var clipboard = new ClipboardJS('.copyBtn', {
				text : function () {
					return this.parseCode(0, iconText);
				}
			});
			clipboard.on('success', function(e) {
				var content = '已复制 ' + e.text + '成功！';
				vm.$message.success(content);
			});

			clipboard.on('error', function(e) {
				console.log(e);
			});
		},
		configCopy : function (iconText) {
			var text = this.template;
			this.iconCode = iconText;
			this.iconShow = this.parseCode(0, iconText);
			this.iconCopyCode = this.parseCode(0, iconText);
			 openWindow({
                title: "高级复制图标配置",
                area: ['710px', '550px'],
                content: jQuery("#configCopyDialog"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    layer.close(index);
                }
            });
		},
		parseCode : function (index, content) {
			var result = this.template.replace("{0}", this.iconCode);
			result = result.replace("{"+index+"}", content);
			return this.cleanTemplate(result);
		},
		cleanTemplate : function (result) {
			for (var i = 0 ; i < 4; i++) {
				result = result.replace("{"+i+"}", "");
			}
			return result;
		},
		parseTemplate : function (index, content, templateCode) {
			return templateCode.replace("{"+index+"}", content);
		},
		handleReset : function(name) {
			handleResetForm(vm, name);
			this.iconItems = iconLib;
		}
	},
	watch : {
		iconItems : function() {
			this.hasResult = this.iconItems.length == 0 ? true : false;
			vm.copyIcon();
		},
		iconCode : function () {
			this.iconShow90 = this.parseCode(1, " fa-rotate-90");
			this.iconShow180 = this.parseCode(1, " fa-rotate-180");
			this.iconShow270 = this.parseCode(1, " fa-rotate-270");
			this.iconShowHorizontal = this.parseCode(1, " fa-flip-horizontal");
			this.iconShowVertical = this.parseCode(1, " fa-flip-vertical");
		},
		commonIcon: {
		　　　　handler(newObj, oldObj) {
				var iName = newObj.iconName;
				var iSpace = newObj.space;
				var iZoom = newObj.zoom;
				var iRotate = newObj.rotate;
				var resultCode = this.parseTemplate(0, this.iconCode, this.template);
				resultCode = this.parseTemplate(3, iName, resultCode);
				var spaceStr = "";
				for (var i = 0 ; i < iSpace; i++) {
					spaceStr += "&nbsp;"
				}
				resultCode = this.parseTemplate(2, spaceStr, resultCode);
				var zoomStr = "";
				if (iZoom > 1) {
					zoomStr = " fa-"+iZoom+"x";
				}
				resultCode = this.parseTemplate(1, zoomStr, resultCode);
				resultCode = this.cleanTemplate(resultCode);
				this.iconCopyCode = resultCode;
		　　　　},
		　　　　deep: true
	　　}
	},
	created : function () {
//		this.configCopy('camera-retro');
	}
});

