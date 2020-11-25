<@override name="body">
<title>爱思数据云平台·考勤中心/考勤记录</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                <button class="layui-btn layui-btn-normal" id="submit">选中提交</button>
            </div>
            <div class="layui-card-body">
                <div id="recordTable" lay-filter="recordTable"></div>
            </div>
        </div>
    </div>
</body>
<script>
    let workDate = "${param.workDate!}"
    let userId = "${param.userId?c}"
    let id = ${param.id?c}
    let attendanceStage = ${param.attendanceStage?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceDayLog_update.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>