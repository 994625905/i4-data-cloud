<@override name="body">
<title>爱思数据云平台·文件查看/选择图片</title>
<style scoped>
    .colorClassName{background-color: #cbd3da;}
</style>
<body>
<br>
<div class="layui-fluid">
    <button type="button" class="layui-btn layui-btn-sm" id="uploadImage">上传图片</button>
    <div class="layui-row mt-2 p-1" id="imageTable"></div>
    <div id="pageDom"></div>
</div>
</body>
<script type="text/html" id="imageCol">
    {{each list as img}}
        <div class="layui-col-xs2 p-1" style="overflow: hidden;" >
            <div class="flex flex-column align-center justify-center p-1 imageDiv border border-light-secondary rounded" style="height: 140px">
                <img src="{{img.url}}" style="max-height: 100px;" title="{{img.description}}" data-name="{{img.name}}" data-suffix="{{img.suffix}}"
                        data-type="{{img.type}}" data-size="{{img.size}}" data-width="{{img.width}}" data-height="{{img.height}}">
                <span flex flex-column align-center justify-center style="margin-top:3px">{{img.name}}</span>
            </div>
        </div>
    {{/each}}
</script>
<script type="text/javascript">
    var limit_width = <#if param.width??>${param.width?c}<#else>null</#if>
    var limit_height = <#if param.height??>${param.height?c}<#else>null</#if>
    var limit_flag = ${param.limitProp!'0'}
    var size = <#if param.fileSize??>${param.fileSize?c}<#else>null</#if>
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/fileFind_selectImage.js"></script>
</@override>
<@extends name="/base.ftl"/>