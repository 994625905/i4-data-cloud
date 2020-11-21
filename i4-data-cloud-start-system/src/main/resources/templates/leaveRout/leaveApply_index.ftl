<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---你可以在此处提交请假表单，然后按照步骤发送审批流程，试一下吧---
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-sm" onclick="addLeave()">请假</button>
                </div>
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="applyTable" lay-filter="applyTable"></div>
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
                <col width="300">
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
    {{# if(d.processId != null && d.processId != ""){ }}
        <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="processLog">查看流转日志</a>
    {{# } else { }}
        <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="apply">发送</a>
        <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
    {{# }}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveApply_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>