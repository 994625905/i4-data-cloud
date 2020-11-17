<@override name="body">
<title>爱思数据云平台·文件查看/选择图片</title>
<style>
    .select-hide{
        display: none;
    }
    .select-show{
        display: block;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-btn-group">
        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="selectSubmit">选中提交项</button>
        <button type="button" class="layui-btn layui-btn-sm" id="batchUploadImage">批量上传页</button>
    </div>
    <div class="layui-row mt-2 p-1" id="imageTable"></div>
    <div id="pageDom"></div>
</div>
</body>
<script type="text/html" id="imageCol">
    {{each list as img}}
        <div class="layui-col-xs2 p-1" style="overflow: hidden;" >
            <div class="flex flex-column align-center justify-center p-1 imageDiv border border-light-secondary rounded position-relative" style="height: 140px">
                <i class="iconfont icon-xuanze text-success position-absolute select-hide" style="font-size: 50px!important; top: 35%;left:35%" data-url="{{img.url}}" data-name="{{img.name}}" data-suffix="{{img.suffix}}"
                    data-type="{{img.type}}" data-size="{{img.size}}" data-width="{{img.width}}" data-height="{{img.height}}"></i>
                <img src="{{img.url}}" style="max-height: 100px;" title="{{img.description}}" data-name="{{img.name}}" data-suffix="{{img.suffix}}" data-type="{{img.type}}" data-size="{{img.size}}">
                <span flex flex-column align-center justify-center style="margin-top:3px">{{img.name}}</span>
            </div>
        </div>
    {{/each}}
</script>
<script type="text/javascript">
    var size = <#if param.fileSize??>${param.fileSize?c}<#else>null</#if>
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/fileFind_selectImageList.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>