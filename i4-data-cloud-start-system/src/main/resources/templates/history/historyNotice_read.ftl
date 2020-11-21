<@override name="body">
<title>爱思数据云平台·点滴/历史公告·预览</title>
<body>
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<br>
    <div class="layui-fluid">

        <div class="layui-card">
            <div class="layui-card-header">
                <h3>${model.title!}</h3>
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <table class="layui-table" lay-size="sm" lay-skin="nob">
                            <colgroup>
                                <col width="150">
                                <col>
                            </colgroup>
                            <tbody>
                                <tr><td>公告类型</td><td>${model.typeName!}</td></tr>
                                <tr><td>发布者</td><td>${model.createUserName!}</td></tr>
                                <tr><td>描述信息</td><td>${model.explainInfo!}</td></tr>
                                <tr><td>查看附件</td>
                                    <td>
                                        <#if fileList?? && fileList?size &gt; 0>
                                            <div class="flex flex-column justify-start">
                                                <#list fileList as file>
                                                    <a href="${file.url}" target="_blank" class="i4_a" title="${file.name}">${file.name}</a>
                                                </#list>
                                            </div>
                                        <#else>
                                            <span class="layui-word-aux">暂无附件</span>
                                        </#if>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="layui-col-xs12">
                        <div id="content">
                            ${mongo.content!}
                        </div>
                    </div>
                </div>
            </div>
            <textarea id="mdContent" style="display: none">${mongo.mdContent!}</textarea>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/marked.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/prettify.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/raphael.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/underscore.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/flowchart.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/jquery.flowchart.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/sequence-diagram.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/editormd.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/nkeditor/libs/JDialog/JDialog.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/nkeditor/NKeditor-all-min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/base/Editor.js?v=1.0"></script>
<script type="text/javascript" src="${StaticServer}/templates/history/historyNotice_read.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>