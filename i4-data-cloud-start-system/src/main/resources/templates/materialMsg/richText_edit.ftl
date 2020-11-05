<@override name="body">
<title>爱思数据云平台·素材中心/图文草稿</title>
<body>
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<br>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">
            <div class="layui-col-xs12">

                <form class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-inline" style="width: 700px">
                                <input name="title" value="${model.title!}" lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">封面</label>
                            <div class="layui-input-inline" style="width: 400px">
                                <img src="${model.cover!}" id="cover" style="max-width: 400px">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">编辑器类型</label>
                        <div class="layui-input-block">
                            <#if richText.mdContent?? && richText.mdContent != "">
                                <input type="radio" name="editor" checked value="markdown" title="markdown编辑器" lay-filter="editor">
                                <input type="radio" name="editor"  value="html" title="富文本编辑器" lay-filter="editor">
                            <#else>
                                <input type="radio" name="editor" value="markdown" title="markdown编辑器" lay-filter="editor">
                                <input type="radio" name="editor" checked value="html" title="富文本编辑器" lay-filter="editor">
                            </#if>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">内容</label>
                        <div class="layui-input-block" id="content-editor" >
                            <textarea id="editor" placeholder="输入内容" style="display: none;" >${richText.content!}</textarea>
                        </div>
                    </div>

                    <!--隐藏域储存md文本-->
                    <textarea id="mdContent" style="display: none">${richText.mdContent!}</textarea>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">摘要说明</label>
                            <div class="layui-input-inline" style="width: 700px">
                                <textarea placeholder="默认取文本的前15个字符" lay-verify="required" class="layui-textarea" name="explainNote">${model.explainNote!}</textarea>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="${model.id!}" name="id">
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="save">立即保存</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</body>
<script>
    var mongoId = "${model.mongoId!}"
    var isMd = <#if richText.mdContent?? && richText.mdContent != "">true<#else>false</#if>
</script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/editormd.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/nkeditor/libs/JDialog/JDialog.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/nkeditor/NKeditor-all-min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/base/Editor.js?v=1.0"></script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/richText_edit.js?v=1.4"></script>
</@override>
<@extends name="/base.ftl"/>