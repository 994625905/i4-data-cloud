let layer,form;//组件
let editorMd;//声明组件
layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form
    form.render()

    /** 初始化富文本编辑器 */
    $("#content-editor").append("<div id='editormd'></div>");
    editorMd = Editor.markdown("editormd",editorMd,$("#mdContent").val(),850)

    /** 提交项 */
    form.on("submit(save)",obj=>{

        Request.async(BasePath+"/about/authInfo/update",{
            content:editorMd.getMarkdown(),
            mongoId:obj.field.mongoId
        }).then(res=>{
            Feng.success("刷新成功")
        })

        return false;
    })

})
