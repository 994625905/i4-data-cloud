<@override name="body">
<title>爱思数据云平台·流程引擎/流程部署</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                Activiti流程引擎，在开发者的角度上弥补了JBPM的设计失败，无论是提供的系统源码，还是依赖的第三方jar，都充分说明后者缺少一位严谨的架构师。
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="deployTable" lay-filter="deployTable"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="image">查流程图</a>
</script>
    <script type="text/javascript" src="${StaticServer}/templates/processEngine/deployMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>