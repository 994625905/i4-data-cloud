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
        range: false,
        theme:"#000000"
    })
    laydate.render({
        elem:"#endTime",
        type:"datetime",
        range: false,
        theme:"#000000"
    })

    /** 上传附件 */
    $("#uploadEnclosure").click(()=>{
        let type = $("#fileType").val()
        if(type == 1){
            UploadFile.listSelect("上传附件",1024,res=>{
                let content = ""
                $.each(res,(i,o)=>{
                    content += template("fileContent",{file:o,typeText:UploadFile.getTypeText(type)})
                })
                $("#enclosureList").append(content)
                /** 绑定删除 */
                $(".deleteFile").click(function(){
                    $(this).parents(".tr-file").remove()
                })
            })
        }else{
            UploadFile.fileSelect("上传附件",null,type,10*1024,res=>{
                $("#enclosureList").append(template("fileContent",{file:res,typeText:UploadFile.getTypeText(type)}))
                /** 绑定删除 */
                $(".deleteFile").click(function(){
                    $(this).parents(".tr-file").remove()
                })
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
        Request.asyncBody(BasePath+"/leaveRout/leaveApply/insert",{
            model:param,
            fileList:function(){
                let list = []
                $.each($(".tr-file"),(i,o)=>{
                    list.push({
                        url:$(o).attr("data-url"),
                        name:$(o).attr("data-name"),
                        type:$(o).attr("data-type"),
                        size:$(o).attr("data-size"),
                        suffix:$(o).attr("data-suffix"),
                    })
                })
                return list
            }()
        }).then(res=>{
            Feng.success("新增成功，选择流程发送审批吧")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },500)
        })
        return false
    })

})