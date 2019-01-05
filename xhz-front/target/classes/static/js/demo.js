var vm = new Vue({
  el: '#app',
  data : {
	  q : {
		  name : '',
		  status : ''
	  },
	  statusList : [
		  { text : '正常', value : '1' },
		  { text : '禁用', value : '0' }
	  ],
	  hobbyList : [
		  {text : '运动', value : '1'},
		  {text : '美食', value : '2'},
		  {text : '电影', value : '3'},
		  {text : '看书', value : '4'}
	  ],
	  showList : true,
	  title : '',
	  user : {
		  status : '1',
		  username : '',
		  email : '',
		  deptName : '',
		  hobby : [],
		  desc : '',
		  admin : false,
		  birthDay : ''
	  }
  },
  methods : {
	  query : function () {
		  layui.table.reload('demoTable', {
			  where : {
				  name : this.q.name,
				  status : this.q.status === undefined ? '' : this.q.status
			  }
		  });
	  },
	  add : function () {
		  this.title = '添加';
		  this.user.status = '1';
		  this.showList = false;
	  },
	  reload : function () {
		  this.showList = true;
	  },
      handleReset: function (name) {
    	  this.$refs[name].resetFields();
      }
  }
});


layui.use('table', function() {
	var table = layui.table;
	var $ = layui.$;
	
	table.render({
		elem: '#demoTable'
		,toolbar: '#demoTable'
		,url:'/sys/demo/list'
		,autoSort : false
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
		  ,{field : 'id', width : 60, title : 'ID', sort : true }
		  ,{field : 'userName', width : 80, title : '姓名'}
		  ,{field : 'email', width : 200, title : '邮箱地址'}
		  ,{field : 'mobile', width : 200, title : '电话'}
		  ,{field : 'sex', width : 70,  title : '性别', sort : true, 
			  templet : function (d) {
				  return transGender(d.sex)
			  }
		  }
		  ,{field : 'birthDay', width : 160, title : '出生日期', 
			  templet : function (d) {
				  return transDate(d.birthDay, 'yyyy年MM月dd日')
			  }
		  }
		  ,{field : 'status', width : 80, title : '状态', sort : true, 
			  templet : function (d) {
				  return transUserStatus(d.status)
			  }
		  }
		]]
		,page: true
	});
	
	table.on('sort(demoTable)', function (obj){
		table.reload('demoTable', {
		  initSort : obj,
		  where : {
			  field: obj.field,
			  order: obj.type
		  }
	  });
	});
});