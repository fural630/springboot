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
            username: 'admin',
            password: '123'
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
                            username: vm.loginForm.username,
                            password: vm.loginForm.password
                        },
                        success: function (result) {
                            if (result.code == 0) { //登录成功
                                //如果是弹出层的登录框，为了可以直接登录后不跳转和刷新页面
                                if (getQueryString('target') == 'self') {
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                    vm.$notify({
                                        title: '登录成功',
                                        message: '您可以继续操作',
                                        type: 'success'
                                    });
                                } else {
                                    top.location.href = '/main.html';
                                }
                            } else {
                                vm.$message.error(result.msg);
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