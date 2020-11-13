<@override name="body">
<title>爱思数据云平台·下午茶/点单任务</title>
<body>
<br>
<div class="layui-fluid">

    <div class="layui-card">
        <div class="layui-card-header">
            --- 负责人在点单区发布每周的下午茶单子，所有用户的下午茶专区将会对应显示相关的信息。---
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-xs12">
                    <div id="taskTable" lay-filter="taskTable"></div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script type="text/html" id="toolbar">
    <label class="layui-btn layui-btn-sm" lay-event="add">新增点单</label>
</script>
<script type="text/html" id="operate">
    {{# if(d.status ==0 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-normal" lay-event="deploy">未发布</label>
        <label class="layui-btn layui-btn-sm" lay-event="edit">编辑</label>
        <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</label>
    {{# } else{ }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">已过期</label>
        <label class="layui-btn layui-btn-sm" lay-event="detail">详情</label>
    {{# }}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/taskDeploy_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>