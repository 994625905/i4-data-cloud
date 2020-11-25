<@override name="body">
<title>爱思数据云平台·考勤中心/补卡申请</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                详情补卡记录如下
            </div>
            <div class="layui-card-body">
                <div id="mendTable" lay-filter="mendTable"></div>
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
<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="processLog">查看流转日志</a>
</script>
<script>
    let param = {
        id:${param.id?c}
    }
</script>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceMend_where.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>