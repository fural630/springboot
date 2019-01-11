var vm = new Vue({
	el: '#app',
	data: {
		q: {
			name: ''
		},
		commonIcon: {
			iconName: '',
			space: 0,
			zoom: 1,
			rotate: 0
		},
		btnIcon: {
			iconName: '',
			space: 0,
			zoom: 4,
			color: '',
			style: '',
			rotate: 0
		},
		iconItems: [],
		hasResult: false,
		iconTempate: '<i class="fa fa-{0}{1}{2}"></i>{3}{4}',
		iconBtnTemplate: `<el-button{5}{6}{7}>
  <i class="fa fa-{0}{1}{2}"></i>{3}{4}
</el-button>`,
		iconBtnShowTemplate: '<button class="el-button{5}{6}{7}"><i class="fa fa-{0}{1}{2}"></i>{3}{4}</button>',
		template: '',
		iconCode: '',
		iconShow: '',
		iconCopyCode: '',
		btnIconShow: '',
		btnIconCode: '',
		iconShow90: '',
		iconShow180: '',
		iconShow270: '',
		iconShowHorizontal: '',
		iconShowVertical: '',
		windowIndex: 99,
		activeTab: "1"
	},
	methods: {
		query: function() {
			var searchIcon = [];
			for (var i = 0; i < iconLib.length; i++) {
				var obj = iconLib[i];
				var label = iconLib[i].label;
				var icon = iconLib[i].icon;
				if (icon.toLowerCase().indexOf(this.q.name.toLowerCase()) >= 0 ||
					label.indexOf(this.q.name) >= 0) {
					searchIcon.push(obj);
				}
			}
			this.iconItems = searchIcon;
		},
		copyIcon: function(iconText) {
			this.iconCode = iconText;
			this.template = this.iconTempate;
			var clipboard = new ClipboardJS('.copyBtn', {
				text: function() {
					return vm.parseCode(0, iconText);
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
		copyConfigIcon: function() {
			layer.close(this.windowIndex);
			var clipboard = new ClipboardJS('.configCopyBtn', {
				text: function() {
					return vm.iconCopyCode;
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
		configCopy: function(iconText) {
			this.iconCode = iconText;
			this.changeIconDate();
			this.windowIndex = openWindow({
				title: "高级复制图标配置",
				area: ['760px', '620px'],
				content: jQuery("#configCopyDialog"),
				btn: ['关闭'],
				yes: function(index) {
					layer.close(index);
				},
				success: function(layero, index) {
					vm.resetConfig();
				}
			});
		},
		changeIconDate: function() {
			if (this.activeTab === "0") {
				this.template = this.iconTempate;
				this.iconShow = this.parseCode(0, this.iconCode);
			} else if (this.activeTab === "1") {
				this.btnIconShow = this.parseTemplate(0, this.iconCode, this.iconBtnShowTemplate);
				this.btnIconShow = this.cleanTemplate(this.btnIconShow);
				this.template = this.iconBtnTemplate;
				this.btnIconCode = this.parseCode(0, this.iconCode);
				this.iconShow = this.parseTemplate(0, this.iconCode, this.iconTempate);
				this.iconShow = this.cleanTemplate(this.iconShow);
			}
			this.iconCopyCode = this.iconShow;
			this.iconShow90 = this.parseCode(1, " fa-rotate-90");
			this.iconShow180 = this.parseCode(1, " fa-rotate-180");
			this.iconShow270 = this.parseCode(1, " fa-rotate-270");
			this.iconShowHorizontal = this.parseCode(1, " fa-flip-horizontal");
			this.iconShowVertical = this.parseCode(1, " fa-flip-vertical");
		},
		parseCode: function(index, content) {
			var result = this.template.replace("{0}", this.iconCode);
			result = result.replace("{" + index + "}", content);
			return this.cleanTemplate(result);
		},
		cleanTemplate: function(result) {
			for (var i = 0; i < 10; i++) {
				result = result.replace("{" + i + "}", "");
			}
			return result;
		},
		parseTemplate: function(index, content, templateCode) {
			return templateCode.replace("{" + index + "}", content);
		},
		setRotate: function(rotateType) {
			this.commonIcon.rotate = rotateType;
		},
		setBtnRotate: function(rotateType) {
			this.btnIcon.rotate = rotateType;
		},
		setColor: function(colorType) {
			this.btnIcon.color = colorType;
		},
		setStyle: function(styleType) {
			this.btnIcon.style = styleType;
		},
		resetConfig: function() {
			this.commonIcon = {
				iconName: '',
				space: 0,
				zoom: 1,
				rotate: 0
			};
			this.btnIcon = {
				iconName: '',
				space: 0,
				zoom: 4,
				color: '',
				style: '',
				rotate: 0
			};
		},
		handleReset: function(name) {
			handleResetForm(vm, name);
			this.loadAllIcon();
		},
		loadAllIcon: function() {
			this.iconItems = iconLib;
		}
	},
	watch: {
		iconItems: function() {
			this.hasResult = this.iconItems.length == 0 ? true : false;
			vm.copyIcon();
		},
		commonIcon: {
			handler(newObj, oldObj) {
				var iName = newObj.iconName;
				var iSpace = newObj.space;
				var iZoom = newObj.zoom;
				var iRotate = newObj.rotate;
				var resultCode = this.parseTemplate(0, this.iconCode, this.iconTempate);
				resultCode = this.parseTemplate(4, iName, resultCode);
				var spaceStr = "";
				for (var i = 0; i < iSpace; i++) {
					spaceStr += "&nbsp;"
				}
				resultCode = this.parseTemplate(3, spaceStr, resultCode);
				var zoomStr = "";
				if (iZoom > 1) {
					zoomStr = " fa-" + iZoom + "x";
				}
				resultCode = this.parseTemplate(2, zoomStr, resultCode);
				if (iRotate == 1) {
					resultCode = this.parseTemplate(1, " fa-rotate-90", resultCode);
				} else if (iRotate == 2) {
					resultCode = this.parseTemplate(1, " fa-rotate-180", resultCode);
				} else if (iRotate == 3) {
					resultCode = this.parseTemplate(1, " fa-rotate-270", resultCode);
				} else if (iRotate == 4) {
					resultCode = this.parseTemplate(1, " fa-flip-horizontal", resultCode);
				} else if (iRotate == 5) {
					resultCode = this.parseTemplate(1, " fa-flip-vertical", resultCode);
				}
				resultCode = this.cleanTemplate(resultCode);
				this.iconCopyCode = resultCode;
			},
			deep: true
		},
		btnIcon: {
			handler(newObj, oldObj) {
				var iName = newObj.iconName;
				var iSpace = newObj.space;
				var iZoom = newObj.zoom;
				var iColor = newObj.color;
				var iStyle = newObj.style;
				var iRotate = newObj.rotate;
				var resultCode = this.parseTemplate(0, this.iconCode, this.iconBtnTemplate);
				resultCode = this.parseTemplate(4, iName, resultCode); //文字

				var spaceStr = "";
				for (var i = 0; i < iSpace; i++) {
					spaceStr += "&nbsp;"
				}
				resultCode = this.parseTemplate(3, spaceStr, resultCode); //空格

				var zoomStr = "";
				var zoomShowStr = "";
				if (iZoom == 1) {
					zoomStr = ' size="mini"';
					zoomShowStr = ' el-button--mini';
				} else if (iZoom == 2) {
					zoomStr = ' size="small"';
					zoomShowStr = ' el-button--small';
				} else if (iZoom == 3) {
					zoomStr = ' size="medium"';
					zoomShowStr = ' el-button--medium';
				}
				resultCode = this.parseTemplate(5, zoomStr, resultCode);

				var colorStr = "";
				var colorShowStr = "";
				if (iColor != "") {
					colorStr = ' type="' + iColor + '"';
					colorShowStr = ' el-button--' + iColor;
				}
				resultCode = this.parseTemplate(6, colorStr, resultCode);

				var styleStr = "";
				var styleShowStr = "";
				if (iStyle != "") {
					styleStr = ' ' + iStyle;
					styleShowStr = ' is-' + iStyle;
				}
				resultCode = this.parseTemplate(7, styleStr, resultCode);

				if (iRotate == 1) {
					resultCode = this.parseTemplate(1, " fa-rotate-90", resultCode);
				} else if (iRotate == 2) {
					resultCode = this.parseTemplate(1, " fa-rotate-180", resultCode);
				} else if (iRotate == 3) {
					resultCode = this.parseTemplate(1, " fa-rotate-270", resultCode);
				} else if (iRotate == 4) {
					resultCode = this.parseTemplate(1, " fa-flip-horizontal", resultCode);
				} else if (iRotate == 5) {
					resultCode = this.parseTemplate(1, " fa-flip-vertical", resultCode);
				}

				resultCode = this.cleanTemplate(resultCode);
				this.btnIconCode = resultCode;
				this.iconCopyCode = resultCode;

				var resultShowCode = this.parseTemplate(0, this.iconCode, this.iconBtnShowTemplate);
				resultShowCode = this.parseTemplate(4, iName, resultShowCode); //文字
				resultShowCode = this.parseTemplate(3, spaceStr, resultShowCode); //空格
				resultShowCode = this.parseTemplate(5, colorShowStr, resultShowCode); //颜色
				resultShowCode = this.parseTemplate(6, zoomShowStr, resultShowCode); //大小
				resultShowCode = this.parseTemplate(7, styleShowStr, resultShowCode); //样式

				if (iRotate == 1) {
					resultShowCode = this.parseTemplate(1, " fa-rotate-90", resultShowCode);
				} else if (iRotate == 2) {
					resultShowCode = this.parseTemplate(1, " fa-rotate-180", resultShowCode);
				} else if (iRotate == 3) {
					resultShowCode = this.parseTemplate(1, " fa-rotate-270", resultShowCode);
				} else if (iRotate == 4) {
					resultShowCode = this.parseTemplate(1, " fa-flip-horizontal", resultShowCode);
				} else if (iRotate == 5) {
					resultShowCode = this.parseTemplate(1, " fa-flip-vertical", resultShowCode);
				}

				this.btnIconShow = resultShowCode;
				this.btnIconShow = this.cleanTemplate(this.btnIconShow);
			},
			deep: true
		}
	},
	mounted: function() {
		this.loadAllIcon();
	}
});
