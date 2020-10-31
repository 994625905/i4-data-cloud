<@override name="body">
<title>爱思数据云平台·系统管理/部门管理</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <!--表格-->
        <div class="layui-col-xs12">
            <div id="departmentTable" lay-filter="departmentTable"></div>
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
<script type="text/html" id="addContent">
    <form class="layui-form p-1">
        <div class="layui-anim layui-anim-upbit">
            <div class="layui-form-item">
                <input name="name" id="name" class="layui-input" placeholder="新增部门名称">
            </div>
            <div class="layui-form-item">
                <input name="describeInfo" id="describeInfo" class="layui-input" placeholder="添加描述信息">
            </div>
        </div>
    </form>
</script>
<script type="text/html" id="editContent">
    <form class="layui-form p-1">
        <div class="layui-anim layui-anim-upbit">
            <div class="layui-form-item">
                <input name="name" id="name" value="{{name}}" class="layui-input" placeholder="新增部门名称">
            </div>
            <div class="layui-form-item">
                <input name="describeInfo" id="describeInfo" value="{{describeInfo}}" class="layui-input" placeholder="添加描述信息">
            </div>
        </div>
    </form>
</script>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/departmentMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>