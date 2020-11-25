<@override name="body">
<title>爱思数据云平台·考勤中心/打卡记录</title>
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
                                        <input id="date" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-form-mid layui-word-aux">
                                        此处对接的是打卡机的记录，没有做实时性，每天凌晨3点33分定时去拉取数据
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </fieldset>
            </div>

            <div class="layui-col-xs12">
                <div id="recordTable" lay-filter="recordTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceRecord_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>