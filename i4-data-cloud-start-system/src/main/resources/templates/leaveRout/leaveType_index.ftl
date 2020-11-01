<@override name="body">
<title>爱思数据云平台·请假事务/请假类型</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---尝试添加一个请假类型吧，要求类型名称不重复（自动验重）---
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="typeTable" lay-filter="typeTable"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<!--table工具栏-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-btn-primary" lay-event="add">新增</button>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveType_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>