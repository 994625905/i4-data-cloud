<@override name="body">
<title>爱思数据云平台·下午茶/列表</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">

        <!--条件过滤框-->
        <div class="layui-col-xs12">
            <fieldset class="layui-elem-field search-fieldset">
                <legend class="search-legend">条件查询</legend>
                <div class="layui-field-box">
                    <div class="search-div">
                        <form class="layui-form" id="formParam">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input name="name" class="layui-input" placeholder="下午茶名称">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button class="layui-btn layui-btn-primary search-button" lay-submit="" lay-filter="search">查询</button>
                            </div>
                        </form>
                    </div>
                </div>
            </fieldset>
        </div>

        <!--表格-->
        <div class="layui-col-xs12">
            <div id="listTable" lay-filter="listTable"></div>
        </div>
    </div>
</div>
</body>

<!--table工具栏-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <@permission value="afternoonTea:list/insert">
            <button class="layui-btn layui-btn-sm layui-btn-primary" lay-event="add">新增</button>
        </@permission>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <@permission value="afternoonTea:list/update">
        <a class="layui-btn layui-btn-sm" lay-event="update">修改</a>
    </@permission>
    <@permission value="afternoonTea:list/delete">
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
    </@permission>
</script>
<script type="text/html" id="imageUrl">
    <img class="rounded" src="{{ d.image }}" onclick="showHeadImage('{{d.name}}','{{d.id}}','{{d.image}}')" style="max-width: 100px">
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/list_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>