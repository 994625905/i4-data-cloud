<@override name="body">
<title>爱思数据云平台·活动中心/活动预览</title>
<body class="bg-layui">
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<br>
<div class="layui-fluid">

    <div class="layui-card">
        <div class="layui-card-header">
            <h2 class="font-weight-bolder">${model.title!}</h2>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-xs12">
                    <img src="${model.coverImage}" style="max-width: 600px">
                    <div>
                        <span class="">负责人：</span>
                        <span class="layui-word-aux">${model.headUserName}</span>
                    </div>
                    <div class="">
                        <span>活动类型：</span>
                        <span class="layui-word-aux">${model.typeName}</span>
                        <span class="ml-2">开始时间：</span>
                        <span class="layui-word-aux">${model.startTimeStr}</span>
                        <span class="ml-2">结束时间：</span>
                        <span class="layui-word-aux">${model.endTimeStr}</span>
                    </div>
                    <#if model.isSign == 1>
                        <div class="">
                            <span>提前报名：</span>
                            <span class="layui-word-aux">需要</span>
                            <span class="ml-2">开始报名：</span>
                            <span class="layui-word-aux">${model.signStartTimeStr}</span>
                            <span class="ml-2">截止报名：</span>
                            <span class="layui-word-aux">${model.signEndTimeStr}</span>
                            <span class="ml-2">携带限制：</span>
                            <span class="layui-word-aux"> <#if model.limitSign == 0>允许携带家属<#else>禁止携带家属</#if> </span>
                            <span class="ml-2">交通方式：</span>
                            <span class="layui-word-aux"> <#if model.trafficType == 0>自行前往<#else>集体乘车</#if> </span>
                        </div>
                    </#if>
                    <div class="">
                        <span>活动地址：</span>
                        <span class="layui-word-aux">${model.address}</span>
                    </div>
                    <div class="flex align-center justify-start">
                        <span>附件列表：</span>
                        <div class="flex flex-column align-center justify-start">
                            <#if fileList??  && fileList?size &lt; 0>
                                <#list fileList as file>
                                    <a href="${file.url}" target="_blank" class="i4_a">${file.name}</a>
                                </#list>
                            <#else>
                                <span class="layui-word-aux">暂无</span>
                            </#if>
                        </div>
                    </div>
                    
                </div>
                <div class="layui-col-xs12">
                    <div class="layui-card">
                        <div class="layui-card-header"><h3 class="font-weight-bold">详情内容</h3></div>
                    </div>
                    <div class="layui-card-body">
                        <div id="content">${mongo.content!}</div>
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
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activity_read.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>