$(document).attr('title','认证中心_找回密码')

var layer,element,form
var retrieveType = 1;// 1手机验证码找回，2电子邮箱验证码找回
var getPhoneCodeStatus = true,getEmailCodeStatus = true;//获取验证码按钮的状态
var getPhoneCodeTime = 60,getEmailCodeTime = 60;//获取验证码的等待时间


layui.use(["layer","element","form"],()=>{

    layer = layui.layer
    element = layui.element
    form = layui.form

    /** tab选项卡的切换 */
    element.on("tab(retrieveType)",function(){
        retrieveType = $(this).attr("type")
    })

    /** form表单的验证项 */
    form.verify({
        loginName:function(value){
            if(BaseUtil.isEmpty(value)){
                return "登录账号不能为空";
            }
        },
        phone:function(value){
            if(!BaseRegax.isMobile(value) && retrieveType == "1"){
                return "手机号输入有误";
            }
        },
        phoneCode:function(value){
            if(BaseUtil.isEmpty(value) && retrieveType == "1"){
                return "短信验证码不能为空";
            }
        },
        email:function(value){
            if(!BaseRegax.isEmail(value) && retrieveType == "2"){
                return "电子邮箱输入有误";
            }
        },
        emailCode:function(value){
            if(BaseUtil.isEmpty(value) && retrieveType == "2"){
                return "邮箱验证码不能为空";
            }
        }
    });

    /** 获取短信验证码操作 */
    $("#getPhoneCode").click(()=>{
        var phone = $("input[name='phone']").val()
        if(!BaseRegax.isMobile(phone)){
            return Feng.error("请输入有效的手机号码")
        }
        if(getPhoneCodeStatus){
            Request.async(BasePath+"/auth/code/getAuthToken").then(res=>{
                /** 发送请求获取验证码 */
                Request.async(BasePath+"/auth/code/getPhoneCode",{phone:phone,token:res}).then(r=>{

                    getPhoneCodeStatus = false
                    $("#getPhoneCode").toggleClass("layui-btn-disabled")
                    $("input[name='phoneCodeKey']").val(r)
                    Feng.success("验证码已发送，请注意查收")

                    var timer = setInterval(()=>{
                        if(getPhoneCodeTime >= 1){
                            getPhoneCodeTime -- ;
                            $("#getPhoneCode").html("<span class='text-light-muted'>还剩<font class='text-danger'>"+getPhoneCodeTime+"</font>秒</span>")
                        }else{
                            getPhoneCodeTime = 60;
                            getPhoneCodeStatus = true;
                            $("#getPhoneCode").toggleClass("layui-btn-disabled")
                            $("#getPhoneCode").html("获取验证码")
                            clearInterval(timer);
                        }
                    },1000);
                })
            })
        }else{
            Feng.msg("请稍后再点击获取")
        }
    })

    /** 获取邮箱验证码 */
    $("#getEmailCode").click(()=>{
        var email = $("input[name='email']").val()
        if(!BaseRegax.isEmail(email)){
            return Feng.error("请输入有效的电子邮箱")
        }
        if(getEmailCodeStatus){
            Request.async(BasePath+"/auth/code/getAuthToken").then(res=>{
                /** 发送请求获取验证码 */
                Request.async(BasePath+"/auth/code/getEmailCode",{email:email,token:res}).then(r=>{

                    getEmailCodeStatus = false
                    $("#getEmailCode").toggleClass("layui-btn-disabled")
                    $("input[name='emailCodeKey']").val(r)
                    Feng.success("验证码已发送，请注意查收")

                    var timer = setInterval(()=>{
                        if(getEmailCodeTime >= 1){
                            getEmailCodeTime -- ;
                            $("#getEmailCode").html("<span class='text-light-muted'>还剩<font class='text-danger'>"+getEmailCodeTime+"</font>秒</span>")
                        }else{
                            getEmailCodeTime = 60;
                            getEmailCodeStatus = true;
                            $("#getEmailCode").toggleClass("layui-btn-disabled")
                            $("#getEmailCode").html("获取验证码")
                            clearInterval(timer);
                        }
                    },1000);
                })
            })
        }else{
            Feng.msg("请稍后再点击获取")
        }
    })

    /** 提交找回 */
    form.on("submit(retrieve)",obj=>{
        if(retrieveType == "1"){
            Request.async(BasePath+"/auth/code/checkPhoneCode",{
                loginName:obj.field.loginName,
                phone:obj.field.phone,
                codeKey:obj.field.phoneCodeKey,
                code:obj.field.phoneCode,
            }).then(res=>{
                loadChangePwd(res.id)
            })
        }
        if(retrieveType == "2"){
            Request.async(BasePath+"/auth/code/checkEmailCode",{
                loginName:obj.field.loginName,
                email:obj.field.email,
                codeKey:obj.field.emailCodeKey,
                code:obj.field.emailCode,
            }).then(res=>{
                loadChangePwd(res.id)
            })
        }
        return false
    })

})
/** 加载修改密码的内容，并绑定提交事件 */
function loadChangePwd(userId){
    $("#form").html(template("changePassword",{}))
    form.render()

    form.verify({
        newPassword:function(value){
            if(BaseRegax.haveCN(value)){
                return "密码不能包含中文字符";
            }
            if(value.length > 15 || value.length < 6 ){
                return "密码长度在6到15位之间"
            }
        },
        surePassword:function(value){
            if(value != $("input[name='newPassword']").val()){
                return "确认密码不一致！"
            }
        },
    });

    form.on("submit(save)",obj=>{
        Request.async(BasePath+"/auth/user/changePassword",{
            password:obj.field.surePassword,
            userId:userId,
        }).then(res=>{
            Feng.success("密码重置成功！")

            BaseUtil.setTimeout(()=>{
                var userAgent = navigator.userAgent;
                if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
                    window.location.href="about:blank";
                    window.close();
                } else {
                    window.opener = null;
                    window.open("", "_self");
                    window.close();
                }
            },800)
        })
        return false
    })
}