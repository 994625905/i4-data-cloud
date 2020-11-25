let layer,form,laydate
let editor,editorMd;//声明组件
let editMdText;//切换时的缓存

layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 周报模板的填充 */
    if(!BaseUtil.isEmpty($("#title").val())){
        var title = $("#title").val()
        title = BaseUtil.replaceAll(title,"{year}",BaseDate.currYear())
        title = BaseUtil.replaceAll(title,"{week}",BaseDate.currWeekNum())
        title = BaseUtil.replaceAll(title,"{startDate}",BaseDate.currMonday())
        title = BaseUtil.replaceAll(title,"{endDate}",BaseDate.currFriday())
        $("#title").val(title)
    }

    /** 渲染年份 */
    laydate.render({
        elem:"#year",
        type:"year",
        value:BaseDate.currYear(),
        range: false,
        theme:"#007bff"
    })

    /** 周次填充 */
    $("#week").val(BaseDate.currWeekNum());

    /** 渲染开始日期--结束日期 */
    laydate.render({
        elem:"#startDate",
        value: BaseDate.currMonday(),
        range: false,
        theme:"#007bff"
    })
    laydate.render({
        elem:"#endDate",
        value:BaseDate.currFriday(),
        range: false,
        theme:"#007bff"
    })

    /** 初始化markdown编辑器 */
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

    /** 导入图文草稿 */
    RichText.import("#importRichText",res=>{
        $("#editor").val(res.content)

        /** md的渲染 */
        if($("input[name='editor']:checked").val() == "markdown"){
            editorMd.editor.remove();
            $("#content-editor").append("<div id='editormd'></div>");
            editorMd = Editor.markdown("editormd",editorMd,res.mdContent)
        }
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
            model:{
                title:obj.field.title,
                year:obj.field.year,
                week:obj.field.week,
                startDate:obj.field.startDate,
                endDate:obj.field.endDate,
            },
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
            }(),
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
        }
        Request.asyncBody(BasePath+"/weekReport/weekReportApply/insert",param).then(res=>{
            Feng.success("新增成功，选择周报提交吧")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },500)
        })
        return false
    })

})