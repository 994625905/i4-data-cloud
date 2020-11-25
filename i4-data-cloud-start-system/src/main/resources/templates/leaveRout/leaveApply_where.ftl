<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                详情请假记录如下：
            </div>
            <div class="layui-card-body">
                <div id="leaveTable" lay-filter="leaveTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="processStatusCols">
    {{# if(d.processStatus == 0){ }}
        <label class="layui-btn layui-btn-sm" lay-event="processDoing">审批中</label>
    {{# } else if(d.processStatus == 1) { }}
        <label class="layui-btn layui-btn-sm layui-btn-primary" lay-event="process">已通过</label>
    {{# } else if(d.processStatus == 2) { }}
        <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="process">未通过</label>
    {{# } else { }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">暂未申请</label>
    {{# }}}
</script>
<!--附件列表-->
<script type="text/html" id="fileList">
    <div class="layui-fluid">
        <table class="layui-table" lay-size="sm" lay-skin="nob">
            <colgroup>
                <col width="250">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>名称</th>
                <th>类型</th>
            </tr>
            </thead>
            <tbody>
            {{each list as file}}
            <tr>
                <th><a class="i4_a" href="{{file.url}}" target="_blank" title="{{file.name}}">{{file.name}}</a></th>
                <th class="fileType">{{file.type}}</th>
            </tr>
            {{/each}}
            </tbody>
        </table>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="processLog">查看流转日志</a>
</script>
<script>
    let param = {
        id:${param.id?c}
    }
</script>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveApply_where.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>