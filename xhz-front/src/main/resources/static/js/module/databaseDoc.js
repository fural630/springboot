var vm = new Vue({
	el : '#app',
	data : {
		moduleName : 'databaseDoc',
		baseUrl : '/develop/databaseDocs',
		q : {
			id : ''
		},
		datasourceItem : [],
		databaseShow : false,
		database : {
			name : '',
			url : '',
			dbType : '',
			lastTestTime : ''
		}
	},
	methods : {
		query : function() {
			if (vm.q.id === '') {
				vm.$message.warning("请选择要查询的数据源！");
				return;
			}
			Ajax.request({
				url: vm.baseUrl + '/' + vm.q.id,
				async: true,
				successCallback: function(r) {
					vm.databaseShow = true;
					vm.reload(r.data);
				}
			});
		},
		reload : function (data) {
			vm.parseDatabase(data.databaseDO);
			var tableList = data.databaseTableDOList;
			if (tableList.length > 0) {
				$("#doc").html("");
				for (var i = 0 ; i < tableList.length; i++) {
					var table = tableList[i];
					var html = "";
					html += '<fieldset class="layui-elem-field layui-field-title">';
					html +=		'<legend><a name="' + table.name + '">' + table.name + ' - ' + table.remark + '</a></legend>';
					html +=	'</fieldset>';
					html += '<table lay-filter="databaseTable_' + i + '" lay-size="sm">';
					html += 	'<thead>';
					html += 		'<tr>';
					html += 			'<th lay-data="{field:\'remark\', width:140}">字段名称</th>';
					html += 			'<th lay-data="{field:\'name\', width:160}">字段标识</th>';
					html += 			'<th lay-data="{field:\'type\', width:120}">字段类型</th>';
					html += 			'<th lay-data="{field:\'dataLength\', width:80, align : \'center\'}">长度</th>';
					html += 			'<th lay-data="{field:\'propertyName\', width:120}">实体字段</th>';
					html += 			'<th lay-data="{field:\'propertyType\', width:100}">实体类型</th>';
					html += 			'<th lay-data="{field:\'nullable\', width:100, align : \'center\'}">允许空值</th>';
					html += 			'<th lay-data="{field:\'keyFlag\', width:70, align : \'center\'}">主键</th>';
					html += 		'</tr>';
					html += 	'</thead>';
					html += 	'<tbody>';
					var fieldList = table.databaseTableFieldDOList;
					if (fieldList.length > 0) {
						for (var j = 0 ; j < fieldList.length; j++) {
							var field = fieldList[j];
							html += '<tr>';
							html += '<td>' + field.remark + '</td>';
							html += '<td>' + field.name + '</td>';
							html += '<td>' + field.type + '</td>';
							html += '<td>' + (field.dataLength == null ? '' : field.dataLength) + '</td>';
							html += '<td>' + field.propertyName + '</td>';
							html += '<td>' + field.propertyType + '</td>';
							html += '<td>' + (field.nullable === "1" ? '<span class="layui-badge layui-bg-blue">是</span>' : '<span class="layui-badge layui-bg-orange">否</span>') + '</td>';
							html += '<td>' + (field.keyFlag  === "1" ? '<i class="fa fa-key fa-rotate-90"></i>' : '') + '</td>';
							html += '</tr>';
						}
					}
					html += 	'</tbody>';
					html += '</table>';
					$("#doc").append(html);
					layui.table.init('databaseTable_' + i, {
						width: 899
					});
				}
				vm.content(tableList);
			}
		},
		content : function (tableList) {
			var html = "";
			for (var i = 0; i < tableList.length; i++) {
				var table = tableList[i];
				html += '<li><a href="#' + table.name + '"><cite>' + table.name + '</cite><em>' + table.remark + '</em></a></li>';
			}
			$(".site-dir").html(html);
			layui.layer.open({
			  type: 1,
			  content: $("#contentDialog"),
			  skin: 'layui-layer-dir',
			  area: 'auto',
			  maxHeight: $(window).height() - 300,
			  title : '目录',
			  offset: 'r',
			  shade: false,
			  success: function(layero, index){
		          layer.style(index, {
		            marginLeft: -15
		          });
		        }
			});
		},
		parseDatabase : function (databaseDO) {
			vm.database = databaseDO;
			vm.database.lastTestTime = constant.transDate(databaseDO.lastTestTime);
		},
		queryDatabaseDoc : function () {
			Ajax.request({
				url: vm.baseUrl + '/pull/' + vm.q.id,
				async: true,
				successCallback: function(r) {
					alert("拉取成功！", function () {
						vm.reload();
					});
				}
			});
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
					for (var i = 0; i < r.data.length; i++) {
						var database = r.data[i];
						if (i == 1) {
							vm.q.id = database.id;
						}
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