<@override name="body">
<title>爱思数据云平台·流程引擎/流程模型</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                此处的流程引擎为activiti。考虑到我需要一个web设计器来取代idea的插件设计，所以此处采用5.22版本，后台封装activiti相关的所有数据接口是以一个单独的微服务存在，maven坐标相对独立，支持无缝移植
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="modelTable" lay-filter="modelTable"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<!--table工具栏-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-sm layui-btn-primary" href="${designAdd}" target="_blank" title="新增一个流程模板">新流程模板</a>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="deploy">部署</a>
</script>
<script>
    let designEdit = "${designEdit}";
</script>
<script type="text/javascript" src="${StaticServer}/templates/processEngine/modelMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>