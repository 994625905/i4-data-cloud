let layer,form,laydate

layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 渲染时间框 */
    laydate.render({
        elem:"#startTime",
        type:"datetime",
        value:BaseDate.timeStampToDate(startTime),
        range: false,
        theme:"#000000"
    })
    laydate.render({
        elem:"#endTime",
        type:"datetime",
        value:BaseDate.timeStampToDate(endTime),
        range: false,
        theme:"#000000",
    })

    /** 绑定附件上传,大小不超过10M */
    $("#uploadEnclosure").click(()=>{
        let type = $("#enclosureType").val()
        if(type == "1"){
            UploadFile.imageSelect("上传附件图片（大小<=1024KB）",null,null,null,null,10*1024,res=>{
                $("#enclosure").text(res)
                $("input[name='enclosure']").val(res)
            })
        }else{
            UploadFile.fileSelect("上传附件（大小<=10 * 1024KB）","#enclosure",type,10*1024,res=>{
                $("input[name='enclosure']").val(res)
            })
        }
    })

    /** 保存提交项 */
    form.on("submit(save)",obj=>{
        var param = {
            ...obj.field,
        }
        param.startTime = BaseDate.dateStrToTimeStamp(param.startTime)
        param.endTime = BaseDate.dateStrToTimeStamp(param.endTime)
        Request.asyncBody(BasePath+"/leaveRout/leaveApply/update",{model:param}).then(res=>{
            Feng.success("编辑成功，选择流程发送审批吧")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },500)
        })
        return false
    })

})