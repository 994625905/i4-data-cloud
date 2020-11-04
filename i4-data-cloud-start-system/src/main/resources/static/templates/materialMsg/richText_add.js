var layer,form;//组件
var editor,editorMd;//声明组件
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
            editorMd = Editor.markdown("editormd",editorMd)
        }
        if(data.value === "html") {
            editorMd.editor.remove();
            editor = Editor.kindEditor("editor",editor)
        }
    });

    /** 封面的上传 */
    $("#cover").click(()=>{
        Feng.loadWindow("封面选择",BasePath+"/fileMsg/imageSelect",null,null,null,()=>{
            Request.async(BasePath+"/fileMsg/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#cover").attr("src",res)
                }
            })
        })
    })

    /** 提交项 */
    form.on("submit(publish)",obj=>{
        loadSubmit(1)
        return false;
    })

})
/*****************************提交项，0保存草稿，1发布******************************/
function loadSubmit(status){
    var param = {
        note:{
            title:$("input[name='title']").val(),
            author:$("input[name='author']").val(),
            cover:$("#cover").attr("src"),
            explainNote:$("textarea[name='explainNote']").val(),
            createType:$("#createType").hasClass("layui-btn-danger")?0:1,
            reprintLink:$("input[name='reprintLink']").val(),
            status:status
        },
        noteTypeInfo:{
            noteTypeId:$("select[name='type']").val()
        },
        noteLabels:(function(){
            var list = []
            $.each($(".noteLabel.layui-btn-danger"),function(i,o){
                list.push($(o).attr("data"))
            })
            return list
        }()),
        mdContent:(function(){
            if($("input[name='editor']:checked").val() == "markdown"){
                return editorMd.getMarkdown();
            }
            return ""
        }()),
        content:(function(){
            if($("input[name='editor']:checked").val() == "markdown"){
                return editorMd.getPreviewedHTML();
            }
            if($("input[name='editor']:checked").val() == "html"){
                return editor.html();
            }
        }()),
    }
    if(param.noteLabels.length == 0){
        return Feng.info("请至少绑定一个标签")
    }
    if(param.note.createType == 1 && BaseUtil.isEmpty(param.note.reprintLink)){
        return Feng.info("转载请标明原文地址！")
    }
    if(BaseUtil.isEmpty(param.content)){
        return Feng.info("请编写完内容再提交！")
    }
    Request.asyncBody(BasePath+"/noteMsg/save",param).then(res=>{
        if(res){
            Feng.success(status == 0?"以保存到草稿":"发布成功")
            parent.refresh()
            parent.layer.closeAll()
        }
    })
}
