let layer

layui.use(["layer"],function(){
    layer = layui.layer;

    /** markdown富文本渲染 */
    if(!BaseUtil.isEmpty($("#mdContent").val())){
        $("#content").html("")
        editormd.markdownToHTML("content", {
            markdown: $("#mdContent").val(),
            htmlDecode : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
            htmlDecode : "style,script,iframe",  // you can filter tags decode
            markdownSourceCode: false, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
            emoji: false,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true// 默认不解析
        });
    }

});