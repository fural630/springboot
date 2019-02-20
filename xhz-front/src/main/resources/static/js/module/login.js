var vm = new Vue({
    el: '#login',
    data: {
        baseUrl: '',
        title: '新豪智软件开发平台',
        username: '账号',
        password: '密码',
        rememberPwd: false,
        passwordType: 'password',
        loginForm: {
            username: '',
            password: ''
        },
        ruleValidate: {
            username: [{
                required: true,
                message: '账号不能为空',
                trigger: 'blur'
            }],
            password: [{
                required: true,
                message: '密码不能为空',
                trigger: 'blur'
            }]
        },
    },
    methods: {
        showPwd: function () {
            if (this.passwordType === 'password') {
                this.passwordType = ''
            } else {
                this.passwordType = 'password'
            }
        },
        handleLogin: function () {
             this.$refs.loginForm.validate(function (valid) {
                 if (valid) {
                	 $.ajax({
                         type: "POST",
                         url: "/sys/login",
                         data: {
                        	username : vm.loginForm.username,
                        	password : vm.loginForm.password
                         },
                         success: function (result) {
                             if (result.code == 0) {//登录成功
                                 parent.location.href = 'index.html';
                             } else {
                            	 console.log(result.msg);
//                                 vm.refreshCode();
//                                 iview.Message.error(result.msg);
                             }
                         }
                     });
                 } else {
                     return false;
                 }
             })
        }
    }
});