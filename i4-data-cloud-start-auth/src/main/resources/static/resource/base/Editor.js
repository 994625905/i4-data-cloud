/**
 * 富文本编辑
 * @author wangjc
 * @type {{}}
 */
var Editor = {

    /**
     * Markdown编辑器
     * @param elem：id选择器
     * @param dom：实例
     * @param content：填充内容
     */
    markdown:function(elem,dom,content = null,height = 740){
        dom = editormd(elem, {
            height: height,
            watch: true,
            codeFold: true,
            toolbarIcons: function () {
                return [
                    "undo", "redo", "|",
                    "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                    "h1", "h2", "h3", "h4", "h5", "h6", "|",
                    "list-ul", "list-ol", "hr", "|",
                    "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
                    "goto-line", "watch", "preview", "fullscreen", "clear", "search", "|",
                    "help", "info"
                ]
            },
            pluginPath: BasePath+"/resource/plugin/editormd/plugins/",
            markdown: content,
            path: BasePath+"/resource/plugin/editormd/lib/",
            placeholder: '请在此书写你的内容',
            saveHTMLToTextarea: true,
            searchReplace: true,
            taskList: true,
            tex: true,// 开启科学公式TeX语言支持，默认关闭
            flowChart: true,//开启流程图支持，默认关闭
            sequenceDiagram: true,//开启时序/序列图支持，默认关闭,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
            imageUploadURL: BasePath+"/fileMsg/uploadImageByMd",
            onfullscreen:
                function () {
                    $(".layui-header").css("z-index", "-1");
                    $("#left-menu").css("z-index", "-1");
                    $(".layui-form-item>label,.layui-form-item>div:not(#content-editor)").css("z-index", -1);
                    $(".layui-card").css("z-index", "-1");
                },
            onfullscreenExit: function () {
                $(".layui-header").css("z-index", "999");
                $("#left-menu").css("z-index", "999");
                $(".layui-form-item>label,.layui-form-item>div:not(#content-editor)").css("z-index", "");
                $(".layui-card").css("z-index", "999");
            }
        });
        return dom
    },
    /**
     * kindEditor富文本编辑器
     * @param elem：id选择器（一般为textare）
     * @param dom：实例
     */
    kindEditor:function(elem,dom){
        dom = KindEditor.create('#'+elem, {
            cssData: 'body {font-family: "Helvetica Neue", Helvetica, "PingFang SC", 微软雅黑, Tahoma, Arial, sans-serif; font-size: 14px}',
            width: "auto",
            height: "740px",
            items: [
                'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', 'fullscreen', '/',
                'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image', 'graft',
                'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
                'link', 'unlink', 'about'
            ],
            uploadJson: BasePath+"/fileMsg/uploadImageByEditor",
            dialogOffset: 0, //对话框距离页面顶部的位置，默认为0居中，
            allowImageUpload: true,
            allowMediaUpload: true,
            themeType: 'black',
            fixToolBar: true,
            autoHeightMode: true,
            filePostName: 'file',//指定上传文件form名称，默认imgFile
            resizeType: 1,//可以改变高度
            afterCreate: function () {
                var self = this;
                KindEditor.ctrl(document, 13, function () {
                    self.sync();
                    K('form[name=example]')[0].submit();
                });
                KindEditor.ctrl(self.edit.doc, 13, function () {
                    self.sync();
                    KindEditor('form[name=example]')[0].submit();
                });
            },
            //错误处理 handler
            errorMsgHandler: function (message, type) {
                try {
                    JDialog.msg({type: type, content: message, timer: 2000});
                } catch (Error) {
                    alert(message);
                }
            }
        });
        return dom;
    }

}