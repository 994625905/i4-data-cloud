let layer,form,laydate,upload
let editor,editorMd;//声明组件
let editMdText;//切换时的缓存

layui.use(["layer","form","laydate","upload"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate
    upload = layui.upload

    /** 渲染form表单 */
    form.render()

    /** 渲染日期选择器 */
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
    laydate.render({
        elem:"#signStartTime",
        type:"datetime",
        range: false,
        theme:"#000000"
    })
    laydate.render({
        elem:"#signEndTime",
        type:"datetime",
        range: false,
        theme:"#000000"
    })

    /** 渲染百度地图 */
    BaiduMap.init("baiduMap","address","baiduMapSearchResultPanel")

    /** 初始化Markdown */
    $("#content-editor").append("<div id='editormd'></div>");
    editorMd = Editor.markdown("editormd",editorMd)

    /** 绑定富文本编辑器的切换 */
    form.on('radio(editor)', function (data) {
        if(data.value === "markdown") {
            editor.remove();
            $("#content-editor").append("<div id='editormd'></div>");
            editorMd = Editor.markdown("editormd",editorMd,editMdText)
        }
        if(data.value === "html") {
            editMdText = editorMd.getMarkdown()
            editorMd.editor.remove();
            editor = Editor.kindEditor("editor",editor)
        }
    });

    /** 活动封面的上传 */
    $("#coverImage").click(()=>{
        UploadFile.imageSelect("选择活动封面","#coverImage",300,200,1,1024)
    })

    /**  报名与否的切换*/
    form.on("switch(isSign)",obj=>{
        if(obj.elem.checked){
            $(".signStartTime").show()
            $(".signEndTime").show()
            $(".limitSign").show()
            $(".trafficType").show()
        }else{
            $(".signStartTime").hide()
            $(".signEndTime").hide()
            $(".limitSign").hide()
            $(".trafficType").hide()
        }
    })

    /** 上传附件 */
    $("#uploadEnclosure").click(()=>{
        let type = $("#fileType").val()
        if(type == 1){
            UploadFile.listSelect("上传附件",10*1024,res=>{
                let content = ""
                $.each(res,(i,o)=>{
                    content += template("fileContent",{file:o,typeText:UploadFile.getTypeText(type)})
                })
                $("#imageList").append(content)
                /** 绑定删除 */
                $(".deleteFile").click(function(){
                    $(this).parents(".tr-file").remove()
                })
            })
        }else{
            UploadFile.fileSelect("上传附件",null,type,10*1024,res=>{
                $("#imageList").append(template("fileContent",{file:res,typeText:UploadFile.getTypeText(type)}))
                /** 绑定删除 */
                $(".deleteFile").click(function(){
                    $(this).parents(".tr-file").remove()
                })
            })
        }
    })

    /** 提交项 */
    form.on("submit(save)",obj=>{
        let param = {
            model:{
                title:obj.field.title,
                typeId:obj.field.typeId,
                headUserId:obj.field.headUserId,
                startTime:BaseDate.dateStrToTimeStamp(obj.field.startTime),
                endTime:BaseDate.dateStrToTimeStamp(obj.field.endTime),
                isSign:obj.field.isSign == "on"?1:0,
                signStartTime:BaseDate.dateStrToTimeStamp(obj.field.signStartTime),
                signEndTime:BaseDate.dateStrToTimeStamp(obj.field.signEndTime),
                limitSign:obj.field.limitSign,
                trafficType:obj.field.trafficType,
                coverImage:$("#coverImage").attr("src"),
                address:obj.field.address,
            },
            mdContent:function(){
                if(obj.field.editor == "markdown"){
                    return editorMd.getMarkdown();
                }
                return ""
            }(),
            content:function(){
                if(obj.field.editor == "markdown"){
                    return editorMd.getPreviewedHTML();
                }
                if(obj.field.editor == "html"){
                    return editor.html();
                }
            }(),
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
        }
        /** 提交项的限制 */
        if(obj.field.startTime > obj.field.endTime){
            Feng.error("活动开始时间应小于结束时间")
            return false
        }
        if(obj.field.signEndTime < obj.field.signStartTime){
            Feng.error("签到开始时间应小于结束时间")
            return false
        }
        if(param.model.isSign == 1 && BaseUtil.isEmpty(param.model.signStartTime)){
            Feng.error("签到开始时间为必填项")
            return false
        }
        if(param.model.isSign == 1 && BaseUtil.isEmpty(param.model.signEndTime)){
            Feng.error("签到截止时间为必填项")
            return false
        }
        if(param.model.isSign == 1 && BaseUtil.isEmpty(param.model.limitSign)){
            Feng.error("携带限制为必填项")
            return false
        }
        if(param.model.isSign == 1 && BaseUtil.isEmpty(param.model.trafficType)){
            Feng.error("交通方式为必填项")
            return false
        }
        if(BaseUtil.isEmpty(param.content)){
            Feng.error("活动内容为必填项")
            return false
        }
        Request.asyncBody(BasePath+"/activityCenter/activityDeploy/insert",param).then(res=>{
            Feng.success("新增活动成功！去发布吧")
            BaseUtil.setTimeout(()=>{
                history.go(-1);
                parent.refresh()
            },1000)
        })
        return false
    })


})