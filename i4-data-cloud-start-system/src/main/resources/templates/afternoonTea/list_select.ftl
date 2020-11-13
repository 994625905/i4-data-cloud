<@override name="body">
<title>爱思数据云平台·下午茶/列表选择</title>
<body>
<br>
<div class="layui-fluid">
    <button type="button" class="layui-btn layui-btn-sm" id="uploadImage">上传图片</button>
    <div class="layui-row mt-2 p-1" id="listTable"></div>
    <div id="pageDom"></div>
</div>
</body>
<script type="text/html" id="imageCol">
    {{each list as tea}}
    <div class="layui-col-xs2 p-1" style="overflow: hidden;" >
        <div class="flex flex-column align-center justify-center p-1 imageDiv border border-light-secondary rounded" style="height: 140px">
            <img src="{{tea.image}}" style="max-height: 100px;" title="{{tea.storeAddress}}" data-id="{{tea.id}}" data-name="{{tea.name}}" data-price="{{tea.price}}">
            <span class="text-danger font-weight-bold" style="margin-top:2px">￥：{{tea.price}}</span>
            <span style="margin-top:3px">{{tea.name}}</span>
        </div>
    </div>
    {{/each}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/list_select.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>