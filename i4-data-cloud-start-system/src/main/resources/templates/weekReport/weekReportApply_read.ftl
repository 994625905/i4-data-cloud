<@override name="body">
<title>爱思数据云平台·周报事务/周报提交</title>
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
                    <div class="layui-col-xs12 bg-layui">
                        <table class="layui-table bg-white" lay-size="sm" lay-skin="nob">
                            <colgroup>
                                <col width="150">
                                <col>
                            </colgroup>
                            <tbody>
                                <tr><td>提交者</td><td>${model.realName!}</td></tr>
                                <tr><td>年份</td><td>${model.year?c!}</td></tr>
                                <tr><td>周次</td><td>${model.week?c!}</td></tr>
                                <tr><td>开始时间</td><td>${model.startDate!}</td></tr>
                                <tr><td>结束时间</td><td>${model.endDate!}</td></tr>
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
                    <div class="layui-col-xs12 bg-layui">
                        <div id="content" class="bg-white">
                            ${weekReport.content!}
                        </div>
                    </div>
                </div>
            </div>
            <textarea id="mdContent" style="display: none">${weekReport.mdContent!}</textarea>
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
<script type="text/javascript" src="${StaticServer}/templates/weekReport/weekReportApply_read.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>