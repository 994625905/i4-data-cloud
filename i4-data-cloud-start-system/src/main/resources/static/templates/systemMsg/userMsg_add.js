var layer,form

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染表单 */
    form.render()

    /** 验证项 */
    form.verify({
        loginName:function(value){
            if(BaseRegax.haveCN(value)){
                return "登录名称不能包含中文"
            }
            if(value.length > 20 || value.length < 1){
                return "登录名称控制在20位以内"
            }
            /** 验重 */
            var res = BaseAjax.getData(BasePath+"/systemMsg/userMsg/checkUnique",{loginName:value})
            if(res.code != 200){
                return res.message
            }
        }
    });

    /** 保存 */
    form.on("submit(save)",function(obj){
        var param = obj.field;
        Request.async(BasePath+"/systemMsg/userMsg/addSave",{
            loginName:obj.field.loginName,
            password:obj.field.password
        }).then(res=>{
            Feng.success("添加成功")
            BaseUtil.setTimeout(()=>{
                parent.refresh();
                parent.layer.closeAll()
            },500)
        })
        return false;
    });


})