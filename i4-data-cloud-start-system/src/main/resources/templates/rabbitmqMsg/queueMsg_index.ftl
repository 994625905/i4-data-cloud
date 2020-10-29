<@override name="body">
<title>爱思数据云平台·消息队列/队列管理</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---将配置文件的消息队列，以web管理的方式来控制，更能直观的了解到每一条数据通道的走向，有助于分类管理---
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger refreshCache">刷新Redis</button>
                </div>
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="queueTable" lay-filter="queueTable"></div>
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
<!--durable-->
<script type="text/html" id="durableCols">
    {{# if(d.durable ==1 ){ }}
        <label class="layui-btn layui-btn-sm">是</label>
    {{# }}}
    {{# if(d.durable ==0 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-danger">否</label>
    {{# }}}
</script>
<!--autoDelete-->
<script type="text/html" id="autoDeleteCols">
    {{# if(d.autoDelete ==1 ){ }}
        <label class="layui-btn layui-btn-sm">是</label>
    {{# }}}
    {{# if(d.autoDelete ==0 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-danger">否</label>
    {{# }}}
</script>
<!--消息队列是否只在当前connection生效，1true，0false-->
<script type="text/html" id="exclusiveCols">
    {{# if(d.exclusive ==1 ){ }}
        <label class="layui-btn layui-btn-sm">是</label>
    {{# }}}
    {{# if(d.exclusive ==0 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-danger">否</label>
    {{# }}}
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<script type="text/javascript" src="${StaticServer}/templates/rabbitmqMsg/queueMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>