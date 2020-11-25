let layer,form

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染form表单 */
    form.render()

    form.on("submit(save)",obj=>{
        Request.asyncBody(BasePath+"/attendanceCenter/attendanceMend/update",{
            model:obj.field
        }).then(res=>{
            Feng.success("修改成功，赶紧发起申请吧")
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll()
                parent.refresh()
            },800)
        })
    })

})