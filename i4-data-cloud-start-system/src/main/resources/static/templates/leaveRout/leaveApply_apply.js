let form,layer
let ccUserDiv

layui.use(["form","layer"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染form表单 */
    form.render()

    /** 渲染下拉多谢 */
    ccUserDiv = InitSelect.selectByData("#ccUser",function(){
        $.each(userList,(i,o)=>{
            o.value = o.id
            o.name = o.realname
        })
        return res.result
    }())

    /** form表单的提交 */
    form.on("submit(apply)",obj=>{
        let param = {
            leaveId:obj.field.leaveId,
            processDefId:obj.field.processDefId.split(",")[0],
            deploymentId:obj.field.processDefId.split(",")[1],
            comment:obj.field.comment
        }
        let ccUserList = [];
        $.each(ccUserDiv.getValue(),function(i,o){
            ccUserList.push(o.value)
        })
        Request.asyncBody(BasePath+"/leaveRout/leaveApply/apply",{processModel:param,ccUserList:ccUserList}).then(res=>{
            Feng.success("发送成功")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },500)
        })
    })

})