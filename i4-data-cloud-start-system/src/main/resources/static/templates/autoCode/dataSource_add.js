var layer,form

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染表单 */
    form.render()

    /** 封面上传 */
    $("#dataSourceCover").click(()=>{
        UploadFile.imageSelect("选择数据源封面","#dataSourceCover",300,300,1)
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
        var param = obj.field
        param.dataSourceCover = $("#dataSourceCover").attr("src")
        Request.asyncBody(BasePath+"/autoCode/dataSourceMsg/insert",{model:param}).then(res=>{
            Feng.success("添加成功")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },800)
        })
        return false;
    })

})