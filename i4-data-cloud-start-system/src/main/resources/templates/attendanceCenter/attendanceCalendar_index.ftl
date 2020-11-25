<@override name="body">
<title>爱思数据云平台·考勤中心/日历设置</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">

            <!--条件过滤框-->
            <div class="layui-col-xs12">
                <fieldset class="layui-elem-field search-fieldset">
                    <legend class="search-legend">条件查询</legend>
                    <div class="layui-field-box">
                        <div class="search-div">
                            <form class="layui-form" id="formParam">
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input id="year" name="year" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 130px">
                                        <select name="type" class="layui-input">
                                            <option value="">选择日历类型</option>
                                            <option value="2">法定节假日</option>
                                            <option value="3">法定工作日</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button class="layui-btn layui-btn-primary search-button" lay-submit="" lay-filter="search">查询</button>
                                </div>
                                <div class="layui-inline">
                                    <button type="button" class="layui-btn" id="about">关于</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </fieldset>
            </div>

            <div class="layui-col-xs12">
                <div id="calendarTable" lay-filter="calendarTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <@permission value="attendanceCenter:attendanceCalendar/addPage">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增日历</button>
        </@permission>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="operate">
    <@permission value="attendanceCenter:attendanceCalendar/delete">
        <label class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</label>
    </@permission>
</script>
<script type="text/html" id="aboutContent">
    <div class="m-1 p-1">
        <p>
            摘要：由行政人员录入数据，作用于考勤记录和月核算，以及年总结。
        </p>
        <p class="m-1">
            虽然休假规律可循，但中央政府每年都有可能出现微调（eg：2019年的劳动节4天），且法定补班日不一定，春节假期不一定，公司内部的规章制度不一定，
            我也没找到能根据年份提供整年365天万历表的第三方API接口（大多只提供具体日和节假日，且无法查询具体节假日的休假时间段），
            所以无法用于考勤规则，就决定手动录入指定年份的法定休假日和法定补班日。
        </p>
        <p class="m-1">
            另外，拥有“编辑，删除”功能的角色，只能向后一天删除（eg：当天是2020-11-25，只能做2020-11-26以后时间段的删除），不可更改历史记录，因为它已经在考勤计算规则中生效了，如果需要调整，还请到具体的”考勤记录“或者”月结算“中调整。
        </p>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceCalendar_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>