var vm = new Vue({
    el: '#login',
    data: {
        baseUrl: '',
        title: '新豪智集成框架',
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
            toUrl("/");
            // this.$refs.loginForm.validate(function (valid) {
            //     if (valid) {
            //         toUrl("/");
            //     } else {
            //         return false;
            //     }
            // })
        }
    }
});