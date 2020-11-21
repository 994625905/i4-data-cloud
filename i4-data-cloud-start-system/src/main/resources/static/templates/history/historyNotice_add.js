let layer,form
let editor,editorMd;//声明组件
let editMdText;//切换时的缓存

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    /** 渲染form表单 */
    form.render()

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
                typeId:obj.field.typeId,
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
        if(BaseUtil.isEmpty(param.mdContent) || BaseUtil.isEmpty(param.content)){
            Feng.error("公告内容为必填项")
            return false
        }
        Request.asyncBody(BasePath+"/history/historyNotice/insert",param).then(res=>{
            Feng.success("新增公告成功，赶紧发布吧")
            BaseUtil.setTimeout(()=>{
                parent.refresh()
                parent.layer.closeAll()
            },800)
        })
        return false
    })

})