<@override name="body">
<title>爱思数据云平台·周报事务/周报提交</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---你可以在此处开始记录周报，每日添加保存，累计到周尾，提交至对应的流程，试一下吧---
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-sm" onclick="addWeekReport()">新增</button>
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
        <button class="layui-btn layui-btn-sm" lay-event="title">定义标题</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
    </div>
</script>
<!--title模板设置-->
<script type="text/html" id="titleContent">
    <div class="layui-form-item">
        <label class="layui-form-label">设置模板：</label>
        <div class="layui-input-block">
            <input id="titleTemplate" name="titleTemplate" value="<#if userTemplate??>${userTemplate.weekreportTitle!}</#if>" placeholder="注意填充的参数不可变动" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">eg：王进潮（研发部）大数据小组{year}年第（{week}）周工作总结及计划（{startDate}-{endDate}）</div>
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
        <label class="layui-btn layui-btn-sm layui-btn-disabled">暂未提交</label>
    {{# }}}
</script>

<!--操作列-->
<script type="text/html" id="operate">
    {{# if(d.processId != null && d.processId != ""){ }}
        <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="processLog">查看流转日志</a>
    {{# } else { }}
        <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="apply">发送</a>
        <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</a>
    {{# }}}
    <a class="layui-btn layui-btn-sm layui-btn-warm" lay-event="read">预览</a>
</script>
<script type="text/javascript" src="${StaticServer}/templates/weekReport/weekReportApply_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>