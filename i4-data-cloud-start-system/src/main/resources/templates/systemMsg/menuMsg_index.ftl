<@override name="body">
<title>爱思数据云平台·系统管理/菜单管理</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-xs12">
            <div id="menuTable" lay-filter="menuTable"></div>
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
<!--状态列-->
<script type="text/html" id="statusTool">
    {{# if(d.status ==0 ){ }}
        <a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="freeze">冻结</a>
    {{# }else{ }}
        <a class="layui-btn layui-btn-sm" lay-event="active">激活</a>
    {{# }}}
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="edit"><i class="layui-icon" title="编辑">&#xe642;</i></a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete"><i class="layui-icon" title="删除">&#xe640;</i></a>
</script>

<script type="text/javascript" src="${StaticServer}/templates/systemMsg/menuMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>