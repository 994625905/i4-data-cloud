<@override name="body">
<title>爱思数据云平台·下午茶/点单任务</title>
<body>
<br>
<div class="layui-fluid">

    <div class="layui-card">
        <div class="layui-card-header">
            --- 可以在此点单，也可以在首页的下午茶专区点（超过设置的截止日期，任务将会失效，还请及时处理）---
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
<script type="text/html" id="operate">
    {{# if(d.status ==1 ){ }}
        <label class="layui-btn layui-btn-sm" lay-event="order">进入选项</label>
    {{# } else{ }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">已过期</label>
        <@permission value="afternoonTea:task/detailPage">
            <label class="layui-btn layui-btn-sm layui-btn-normal" lay-event="detail">点单详情</label>
        </@permission>
    {{# }}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/task_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>