<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="/plugins/layui/css/layui.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/font/font-awesome/css/font-awesome.min.css">
</head>
<body class="frame-body">
	<div class="frame-fluid">
		<div class="frame-card">
			<div class="layui-form frame-card-header layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="title" autocomplete="off"
							class="layui-input">
					</div>
					<label class="layui-form-label">所属部门</label>
					<div class="layui-input-inline">
						<input type="text" name="title" autocomplete="off"
							class="layui-input">
					</div>
					<button class="layui-btn"><i class="fa fa-search" aria-hidden="true"></i> 搜索</button>
					<button class="layui-btn"><i class="fa fa-reply" aria-hidden="true"></i> 重置</button>
				</div>
			</div>
			<div class="layui-card-body">
				<button class="layui-btn layui-btn-sm" data-type="add">添加</button>
				<button class="layui-btn layui-btn-sm layui-btn-danger" data-type="batchdel">批量删除</button>
				<table class="layui-hide" id="userManageTable"></table>
			</div>
		</div>
		<script type="text/html" id="sexTpl">
  			{{#  if(d.sex == 0){ }}
    			女
  			{{#  } else if (d.sex == 1) { }}
    			男
  			{{#  } }}
		</script>
		<script type="text/html" id="statusTpl">
  			{{#  if(d.status == 0){ }}
				<button class="layui-btn layui-btn-sm layui-btn-danger">禁用</button>
  			{{#  } else if (d.status == 1) { }}
				<button class="layui-btn layui-btn-sm layui-btn-normal">正常</button>
  			{{#  } }}
		</script>
		<script type="text/html" id="userToolbar">
			<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
	</div>
	<script src="/plugins/layui/layui.js"></script>
	<script>
		layui.use('table', function() {
			var table = layui.table;
			var $ = layui.$;
			table.render({
				elem: '#userManageTable'
				,url:'/user/getUserTable'
				,request : {
					pageName : 'nowPage',
					limitName : 'pageSize'
				}
				,response : {
					countName : 'totalNum',
					dataName : 'items',
					statusCode : 200
				}
				,cols: [[
				  {type:'checkbox'}
				  ,{field : 'id', width : 80, title : 'ID'}
				  ,{field : 'name', width : 80, title : '姓名'}
				  ,{field : 'account', width : 200, title : '账号'}
				  ,{field : 'phone', width : 140, title : '电话'}
				  ,{field : 'email', width : 180, title : '邮箱'}
				  ,{field : 'sex', width : 80,  title : '性别', sort : true, templet : '#sexTpl'}
				  ,{field : 'birthDay', width : 120, title : '出生日期'}
				  ,{field : 'departmentCid', minWidth : 100, title: '所属部门'}
				  ,{field : 'status', width : 80, title : '状态', sort : true, templet : '#statusTpl'}
				  ,{fixed : 'right', width:160, align:'center', title : '操作' , toolbar: '#userToolbar'}
				]]
				,page: true
			});
		});
	</script>
</body>
</html>