<@override name="body">
<title>爱思数据云平台·文件查看/选择文件</title>
<style scoped>
    .colorClassName{background-color: #cbd3da;}
</style>
<body>
<br>
<div class="layui-fluid">
    <button type="button" class="layui-btn layui-btn-sm" id="uploadFile">
        <#if param.type == 2>
            上传音频
        <#elseif param.type == 3>
            上传视频
        <#elseif param.type == 4>
            上传文档
        <#elseif param.type == 5>
            上传其他文件
        </#if>
    </button>
    <div class="layui-row mt-2 p-1" id="imageTable"></div>
    <div id="pageDom"></div>
</div>
</body>
<script type="text/html" id="imageCol">
    {{each list as file}}
        <div class="layui-col-xs2 p-1" style="overflow: hidden;" >
            <div class="flex flex-column align-center justify-center p-1 imageDiv border border-light-secondary rounded" style="height: 140px">
                <img src="{{file.cover}}" data="{{file.url}}" style="max-height: 100px;" title="{{file.description}}">
                <span flex flex-column align-center justify-center style="margin-top:3px">{{file.name}}</span>
            </div>
        </div>
    {{/each}}
</script>
<script type="text/javascript">
    var size = <#if param.fileSize??>${param.fileSize?c}<#else>null</#if>
    var type = ${param.type!}
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/fileFind_selectFile.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>