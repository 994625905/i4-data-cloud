<@override name="body">
<title>爱思数据云平台·下午茶/类型</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <!--表格-->
        <div class="layui-col-xs12">
            <div id="typeTable" lay-filter="typeTable"></div>
        </div>
    </div>
</div>
</body>

<!--table工具栏-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <@permission value="afternoonTea:type/insert">
            <button class="layui-btn layui-btn-sm layui-btn-primary" lay-event="add">新增</button>
        </@permission>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <@permission value="afternoonTea:type/update">
        <a class="layui-btn layui-btn-sm" lay-event="update">修改</a>
    </@permission>
    <@permission value="afternoonTea:type/delete">
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
    </@permission>
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/type_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>