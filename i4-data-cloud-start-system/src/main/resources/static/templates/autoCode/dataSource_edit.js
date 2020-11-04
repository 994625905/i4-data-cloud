var layer,form

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染表单 */
    form.render()

    /** 封面上传 */
    $("#dataSourceCover").click(()=>{
        if(limit){
            return Feng.msg("无权上传图片");
        }
        UploadFile.imageSelect("选择数据源封面","#dataSourceCover",300,300,1)
    })

    /** 生成代码 */
    $(".autoCode").click(()=>{
        BaseUtil.redirect(BasePath+"/autoCode/dataSourceMsg/readPage?id="+$("input[name='id']").val())
    })

    /** 提交的验证项 */
    form.verify({
        datasourceName:value=>{
            if(BaseUtil.isEmpty(value)){
                return "请填写数据源名称"
            }
        },
        datasourceUrl:value=>{
            if(BaseUtil.isEmpty(value)){
                return "请填写有效的数据源地址"
            }
        },
        databaseName:value=>{
            if(BaseUtil.isEmpty(value)){
                return "请填写指定的数据库"
            }
        },
        driverclassName:value=>{
            if(BaseUtil.isEmpty(value)){
                return "请填写有效的驱动名称"
            }
        },
        authUser:value=>{
            if(BaseUtil.isEmpty(value)){
                return "请填写有效的授权用户"
            }
            if(BaseRegax.haveCN(value)){
                return "授权用户不应该有中文"
            }
        },
        authPassword:value=>{
            if(BaseUtil.isEmpty(value)){
                return "请填写有效的授权密码"
            }
            if(BaseRegax.haveCN(value)){
                return "授权密码不应该有中文"
            }
        },
    })

    /** 提交项 */
    form.on("submit(save)",obj=>{
        if(limit){
            return Feng.msg("无权修改！");
        }
        var param = obj.field
        param.dataSourceCover = $("#dataSourceCover").attr("src")

        /** 判断用户密码是否修改，防重复加密 */
        if(param.authUser == authUser){
            delete param.authUser
        }
        if(param.authPassword == authPassword){
            delete param.authPassword
        }

        Request.asyncBody(BasePath+"/autoCode/dataSourceMsg/update",{model:param}).then(res=>{
            Feng.success("修改成功")
            parent.refresh()
        })
        return false;
    })

    /** 删除项 */
    form.on("submit(delete)",obj=>{
        if(limit){
            return Feng.msg("无权删除！");
        }
        Request.async(BasePath+"/autoCode/dataSourceMsg/delete",{id:obj.field.id}).then(res=>{
            Feng.success("删除成功")
            parent.refresh()
        })
        return false
    })

})