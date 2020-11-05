var layer,form,element;
var param = {
    loginType:"1"//初始化为账号密码登录
}
var getCodeStatus = true;//获取验证码按钮的状态
var getCodeTime = 60;//获取验证码的等待时间

layui.use(["layer","form","element"],()=>{

    layer = layui.layer
    form = layui.form
    element = layui.element

    element.on("tab(loginType)",function(){
        param.loginType = $(this).attr("type")
    })

    /** 富文本内容的渲染 */
    editormd.markdownToHTML("content", {
        markdown: $("#mdContent").val(),
        htmlDecode : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
        htmlDecode : "style,script,iframe",  // you can filter tags decode
        markdownSourceCode: false, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
        emoji: false,
        taskList: true,
        tex: true,  // 默认不解析
        flowChart: true,  // 默认不解析
        sequenceDiagram: true// 默认不解析
    });


    /** 提交验证 */
    form.verify({
        loginName:function(value){
            if(BaseUtil.isEmpty(value) && param.loginType == "1"){
                return "用户名不能为空";
            }
        },
        password:function(value){
            if(BaseUtil.isEmpty(value) && param.loginType == "1"){
                return "密码不能为空";
            }
        },
        phone:function(value){
            if(!BaseRegax.isMobile(value) && param.loginType == "2"){
                return "手机号输入有误";
            }
        },
        code:function(value){
            if(BaseUtil.isEmpty(value) && param.loginType == "2"){
                return "验证码不能为空";
            }
        }
    });

    /** 获取短信验证码操作 */
    $("#getCode").click(()=>{
        var phone = $("input[name='phone']").val()
        if(!BaseRegax.isMobile(phone)){
            return Feng.error("请输入有效的手机号码")
        }
        if(getCodeStatus){
            Request.async(BasePath+"/auth/code/getAuthToken").then(res=>{
                /** 发送请求获取验证码 */
                Request.async(BasePath+"/auth/code/getPhoneCode",{phone:phone,token:res}).then(r=>{

                    getCodeStatus = false
                    $("#getCode").toggleClass("layui-btn-disabled")
                    $("input[name='codeKey']").val(r)
                    Feng.success("验证码已发送，请注意查收")

                    var timer = setInterval(()=>{
                        if(getCodeTime >= 1){
                            getCodeTime -- ;
                            $("#getCode").html("<span class='text-light-muted'>还剩<font class='text-danger'>"+getCodeTime+"</font>秒</span>")
                        }else{
                            getCodeTime = 60;
                            getCodeStatus = true;
                            $("#getCode").toggleClass("layui-btn-disabled")
                            $("#getCode").html("获取验证码")
                            clearInterval(timer);
                        }
                    },1000);
                })
            })
        }else{
            Feng.msg("请稍后再点击获取")
        }
    })

    /** 提交，区分类型 1账号密码，2短信验证码 */
    form.on("submit(login-submit)",obj=>{
        if(param.loginType == "1"){
            Request.async(BasePath+"/auth/do/login",{
                loginName:obj.field.loginName,
                password:obj.field.password,
            }).then(res=>{
                BaseUtil.redirect(redirect+"?authorization="+res.authorization)
            })
        }
        if(param.loginType == "2"){
            Request.async(BasePath+"/auth/do/loginBySms",{
                phone:obj.field.phone,
                codeKey:obj.field.codeKey,
                code:obj.field.code,
            }).then(res=>{
                BaseUtil.redirect(redirect+"?authorization="+res.authorization)
            })
        }
        return false
    })


})