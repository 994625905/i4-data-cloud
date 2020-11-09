<@override name="body">
<title>爱思数据云平台·图文草稿/选择图文</title>
<style scoped>
    .colorClassName{background-color: #cbd3da;}
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row mt-2 p-1" id="imageTable"></div>
    <div id="pageDom"></div>
</div>
</body>
<script type="text/html" id="imageCol">
    {{each list as rich}}
        <div class="layui-col-xs2 p-1" style="overflow: hidden;" >
            <div class="flex flex-column align-center justify-center p-1 imageDiv border border-light-secondary rounded" style="height: 140px">
                <img src="{{rich.cover}}" data="{{rich.mongoId}}" style="max-height: 100px;" title="{{rich.explainNote}}">
                <span flex flex-column align-center justify-center style="margin-top:3px">{{rich.title}}</span>
            </div>
        </div>
    {{/each}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/richText_select.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>