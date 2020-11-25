<@override name="body">
<title>爱思数据云平台·考勤中心/日历设置</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ${param.year?c}年日历设置，填写年份的法定节假日和法定工作日（补班日）
            </div>
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item addCalendar">
                        <div class="layui-input-block">
                            <div class="layui-btn-container">
                                <label class="layui-btn layui-btn-sm" id="addHoliday">添加法定节假日</label>
                                <label class="layui-btn layui-btn-sm layui-btn-danger" id="addWordDay">添加法定工作日</label>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="save">立即保存</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="calendarContent">
    <div class="layui-form-item calendar">
        <div class="layui-inline mr-0">
            <label class="layui-form-label px-1">日：</label>
            <div class="layui-input-inline" style="width: 100px">
                <input id="date{{index}}" readonly class="layui-input calendarDate">
            </div>
        </div>
        <div class="layui-inline mr-0">
            <label class="layui-form-label px-1" style="width: 20px">周</label>
            <div class="layui-input-inline" style="width: 70px">
                <input id="week{{index}}" readonly class="layui-input calendarWeek">
            </div>
        </div>
        <div class="layui-inline mr-0">
            <label class="layui-form-label px-1" style="width: 20px">年</label>
            <div class="layui-input-inline" style="width: 50px">
                <input id="year{{index}}" value="${param.year?c}" readonly class="layui-input calendarYear">
            </div>
        </div>
        <div class="layui-inline mr-0">
            <label class="layui-form-label px-1" style="width: 20px">月</label>
            <div class="layui-input-inline" style="width: 50px">
                <input id="month{{index}}" readonly class="layui-input calendarMonth">
            </div>
        </div>
        <div class="layui-inline mr-0">
            <label class="layui-form-label px-1" style="width: 30px">类型</label>
            <div class="layui-input-inline" style="width: 100px">
                <input value="{{if type == 2}}法定节假日{{else}}法定工作日{{/if}}" readonly class="layui-input">
                <input id="type{{index}}" value="{{type}}" type="hidden" class="calendarType">
            </div>
        </div>
        {{if type == 2}}
            <div class="layui-inline mr-0">
                <label class="layui-form-label px-1" style="width: 30px">名称</label>
                <div class="layui-input-inline" style="width: 100px">
                    <input id="holidayName{{index}}" placeholder="假期名称" class="layui-input calendarHolidayName">
                </div>
            </div>
        {{/if}}
        <div class="layui-inline mr-0">
            <a title="删除"><i class="iconfont icon-shanchu1 text-danger" id="delete{{index}}"></i></a>
        </div>
    </div>
</script>
<script>
    let year = ${param.year?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceCalendar_add.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>