let form,layer
let ccUserDiv

layui.use(["form","layer"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染form表单 */
    form.render()

    /** form表单的提交 */
    form.on("submit(apply)",obj=>{
        let param = {
            attendanceMendId:obj.field.attendanceMendId,
            processDefId:obj.field.processDefId.split(",")[0],
            deploymentId:obj.field.processDefId.split(",")[1],
            comment:obj.field.comment
        }
        Request.asyncBody(BasePath+"/attendanceCenter/attendanceMend/apply",{processModel:param}).then(res=>{
            Feng.success("发送成功")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },500)
        })
    })

})