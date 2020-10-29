<@override name="body">
<title>爱思数据云平台·消息队列/交换机管理</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---配置存储以MySQL为基石，Redis为核心（持久化不过期），应用热加载自动读取Redis缓存来创建交换机，绑定队列（持久化后则不重复创建）---
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger refreshCache">刷新Redis</button>
                </div>
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="exchangeTable" lay-filter="exchangeTable"></div>
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
<!--类型列，1直连direct，2主题topic，3广播fanout，4延时delay-->
<script type="text/html" id="typeCols">
    {{# if(d.type ==1 ){ }}
        <label class="layui-btn layui-btn-normal layui-btn-sm">直连</label>
    {{# }}}
    {{# if(d.type ==2 ){ }}
        <label class="layui-btn layui-btn-warm layui-btn-sm">主题</label>
    {{# }}}
    {{# if(d.type ==3 ){ }}
        <label class="layui-btn layui-btn-primary layui-btn-sm">广播</label>
    {{# }}}
    {{# if(d.type ==4 ){ }}
        <label class="layui-btn layui-btn-danger layui-btn-sm" >延时</label>
    {{# }}}
</script>
<!--isAck-->
<script type="text/html" id="isAckCols">
    {{# if(d.isAck ==1 ){ }}
        <label class="layui-btn layui-btn-sm">自动</label>
    {{# }}}
    {{# if(d.isAck ==0 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-danger">手动</label>
    {{# }}}
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="queue">队列</a>
</script>
<script type="text/html" id="queueList">
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                该交换机下绑定的队列，若想添加，请前往
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger refreshCache">队列管理</button>
                </div>
                处设置
            </div>
            <div class="layui-card-body">
                <div class="layui-btn-container">
                    {{each queueList as queue}}
                        <label class="layui-btn layui-btn-sm search-button">{{queue.name}}</label>
                    {{/each}}
                </div>
            </div>
        </div>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/rabbitmqMsg/exchangeMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>