let layer,form,laydate
let editor,editorMd;//声明组件
let editMdText;//切换时的缓存

layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 渲染年份 */
    laydate.render({
        elem:"#year",
        type:"year",
        range: false,
        theme:"#000000"
    })

    /** 周次填充 */
    $("#week").val(BaseDate.currWeekNum());

    /** 渲染开始日期--结束日期 */
    laydate.render({
        elem:"#startDate",
        range: false,
        theme:"#000000"
    })
    laydate.render({
        elem:"#endDate",
        range: false,
        theme:"#000000"
    })

    /** 初始化富文本编辑器 */
    if(isMd){
        $("#content-editor").append("<div id='editormd'></div>");
        editorMd = Editor.markdown("editormd",editorMd,$("#mdContent").val())
    }else{
        editor = Editor.kindEditor("editor",editor)
    }

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
            model:{
                id:obj.field.id,
                title:obj.field.title,
                year:obj.field.year,
                week:obj.field.week,
                startDate:obj.field.startDate,
                endDate:obj.field.endDate,
                enclosure:obj.field.enclosure,
            },
            mongoId:mongoId,
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
        Request.asyncBody(BasePath+"/weekReport/weekReportApply/update",param).then(res=>{
            Feng.success("编辑成功，选择周报提交吧")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },500)
        })
        return false
    })

})