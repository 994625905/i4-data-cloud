let layer,form,upload

layui.use(["layer","form","upload"],()=>{

    layer = layui.layer
    form = layui.form
    upload = layui.upload

    /** 渲染form表单 */
    form.render()

    /** 绑定图片上传 */
    $("#uploadImage").click(()=>{
        UploadFile.imageSelect("选择下午茶图片","#uploadImage",300,300,1,1024)
    })

    /**  提交限制*/
    form.verify({
        price:value=>{
            if(BaseUtil.isEmpty(value)){
                return "单价不能为空"
            }
            if(value <= 0){
                return "单价必须大于0"
            }
        }
    })

    /** 提交 */
    form.on("submit(save)",obj=>{
        let param = {
            ...obj.field,
            image:$("#uploadImage").attr("src")
        }
        Request.asyncBody(BasePath+"/afternoonTea/list/insert",{model:param}).then(res=>{
            Feng.success("新增成功")
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll()
                parent.refresh()
            },500)
        })
        return false
    })
})