<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="/lib/layui/css/layui.css">
<link rel="stylesheet" href="/css/frame.css">
<link rel="stylesheet" href="/font/font-awesome/css/font-awesome.min.css">
</head>
<body class="frame-body">
	<div class="frame-fluid">
		<div class="frame-card">
			<div class="layui-form frame-card-header layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">长输入框</label>
					<div class="layui-input-inline">
						<input type="text" name="title" autocomplete="off"
							class="layui-input">
					</div>
					<label class="layui-form-label">长输入框</label>
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
	</div>
	<script src="/lib/layui/layui.js"></script>
	<script>
		layui.use('table', function() {
			var table = layui.table;
			var $ = layui.$;
			table.render({
				elem: '#userManageTable'
				,url:'/user/getUserTable'
				,cols: [[
				  {type:'checkbox'}
				  ,{field:'id', width:80, title: 'ID', sort: true}
				  ,{field:'username', width:80, title: '用户名'}
				  ,{field:'sex', width:80, title: '性别', sort: true}
				  ,{field:'city', width:80, title: '城市'}
				  ,{field:'sign', title: '签名', minWidth: 100}
				  ,{field:'experience', width:80, title: '积分', sort: true}
				  ,{field:'score', width:80, title: '评分', sort: true}
				  ,{field:'classify', width:80, title: '职业'}
				  ,{field:'wealth', width:135, title: '财富', sort: true}
				]]
				,page: true
			});
		});
	</script>
</body>
</html>