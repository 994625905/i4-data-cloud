let layer,form;//组件
let editor,editorMd;//声明组件
let editMdText;//切换时的缓存
layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form
    form.render()

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

    /** 封面的上传,300*200 */
    $("#cover").click(()=>{
        UploadFile.imageSelect("封面选择","#cover",300,200,1,200)
    })

    /** 提交项 */
    form.on("submit(save)",obj=>{
        let param = {
            model:{
                title:obj.field.title,
                cover:$("#cover").attr("src"),
                explainNote:obj.field.explainNote
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
        }
        Request.asyncBody(BasePath+"/materialMsg/richText/insert",param).then(res=>{
            Feng.success("新增成功");
            parent.refresh()
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll()
            },500)
        })
        return false;
    })

})
