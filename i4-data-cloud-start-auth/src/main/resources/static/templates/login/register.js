var layer,form,laydate,upload,element
var getPhoneCodeStatus = true,getEmailCodeStatus = true;//获取验证码按钮的状态
var getPhoneCodeTime = 60,getEmailCodeTime = 60;//获取验证码的等待时间

layui.use(["layer","form","laydate","upload","element"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate
    upload = layui.upload
    element = layui.element

    /** 账号密码的验证项 */
    form.verify({
        inviteCode:value=>{
            if(BaseUtil.isEmpty(value)){
                return "内部邀请码是必填项"
            }
        },
        loginName:value=>{
            if(BaseUtil.isEmpty(value)){
                return "登录账号不能为空";
            }
        },
        password:value=>{
            if(BaseRegax.haveCN(value)){
                return "密码不能包含中文字符";
            }
            if(value.length > 15 || value.length < 6 ){
                return "密码长度在6到15位之间"
            }
        },
        surePassword:value=>{
            if(value != $("input[name='password']").val()){
                return "确认密码不一致！"
            }
        },
        realName:value=>{
            if(BaseUtil.isEmpty(value)){
                return "真实姓名不可为空"
            }
            if(value.length > 8){
                return "真实姓名长度不可大于8"
            }
        }
    })

    /** 账号密码的提交项 */
    form.on("submit(register)",obj=>{
        Request.async(BasePath+"/auth/user/register",obj.field).then(res=>{
            setPhone(res.userId,res.id)
        })
        return false
    })

})
/*****************************设置手机***************************/
function setPhone(userId,userInfoId){
    element.progress('registerSpeed', '25%')
    $("#registerForm").html(template("setPhone",{}))
    form.render()

    /** 手机，验证码的验证项 */
    form.verify({
        phone:function(value){
            if(!BaseRegax.isMobile(value)){
                return "手机号输入有误";
            }
        },
        phoneCode:function(value){
            if(BaseUtil.isEmpty(value)){
                return "短信验证码不能为空";
            }
        },
    })

    /** 获取短信验证码 */
    $("#getPhoneCode").click(()=>{
        var phone = $("input[name='phone']").val()
        if(!BaseRegax.isMobile(phone)){
            return Feng.error("请输入有效的手机号码")
        }
        if(getPhoneCodeStatus){
            Request.async(BasePath+"/auth/code/getAuthToken").then(res=>{
                /** 发送请求获取验证码 */
                Request.async(BasePath+"/auth/code/getPhoneCodeByRegister",{phone:phone,token:res}).then(r=>{

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

    /** 提交手机 */
    form.on("submit(savePhone)",obj=>{
        Request.async(BasePath+"/auth/user/updatePhoneByUserId",{
            userId:userId,
            phone:obj.field.phone,
            codeKey:obj.field.phoneCodeKey,
            code:obj.field.phoneCode,
        }).then(res=>{
            setEmail(res.id,userInfoId)
        })
        return false
    })

}
/*****************************设置电子邮箱***************************/
function setEmail(userId,userInfoId){
    element.progress('registerSpeed', '50%')
    $("#registerForm").html(template("setEmail",{}))
    form.render()

    /** 手机，验证码的验证项 */
    form.verify({
        email:function(value){
            if(!BaseRegax.isEmail(value)){
                return "电子邮箱输入有误";
            }
        },
        emailCode:function(value){
            if(BaseUtil.isEmpty(value)){
                return "邮箱验证码不能为空";
            }
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
                Request.async(BasePath+"/auth/code/getEmailCodeByRegister",{email:email,token:res}).then(r=>{

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

    /** 提交电子邮箱 */
    form.on("submit(saveEmail)",obj=>{
        Request.async(BasePath+"/auth/user/updateEmailByUserId",{
            userId:userId,
            email:obj.field.email,
            codeKey:obj.field.emailCodeKey,
            code:obj.field.emailCode,
        }).then(res=>{
            setUserInfo(res.userId,userInfoId)
        })
        return false
    })

}
/*****************************设置个人信息***************************/
function setUserInfo(userId,userInfoId){
    element.progress('registerSpeed', '75%')
    $("#registerForm").html(template("setUserInfo",{}))

    /** 渲染日期 */
    laydate.render({
        elem:"#birthday",
        type:"date",
        range: false,
        theme:"#007bff"
    })

    /** 头像上传 */
    UploadFile.image("#headimage",res=>{
        $("#headimage").attr("src",res.fileUrl)
    },null,100,100,true)

    /** 绑定地址三级联动 */
    loadArea()

    form.render()

    /** 提交用户信息 */
    form.on("submit(saveUserInfo)",obj=>{
        Request.asyncBody(BasePath+"/auth/user/updateUserInfo",{
            userInfo:{
                ...obj.field,
                id:userInfoId,
                userId:userId,
                headimage:$("#headimage").attr("src")
            }
        }).then(res=>{
            Feng.success("顺利完成注册！前往登录吧")
            element.progress('registerSpeed', '100%')
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
            },1000)
        })
        return false
    })

}
/********************************地址的三级联动********************************/
function loadArea(){
    var provinceHtml = template("provinceContent",{list:Area})
    $("select[name='province']").html(provinceHtml)
    form.render("select")

    /** 绑定切换事件--市 */
    form.on("select(province)",obj=>{
        var cityList = getCityByProvince(obj.value)
        var cityHtml = template("cityContent",{list:cityList})
        $("select[name='city']").html(cityHtml)
        form.render("select")

        /** 绑定切换事件--区 */
        form.on("select(city)",res=>{
            var areaList = getAreaByCity(obj.value,res.value)
            var areaHtml = template("areaContent",{list:areaList})
            $("select[name='area']").html(areaHtml)
            form.render("select")
        })
    })
}