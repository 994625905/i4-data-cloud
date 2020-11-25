<@override name="body">
<title>爱思数据云平台·考勤中心/月结算</title>
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
                                        <input id="year" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-word-aux">默认每个月第3天凌晨3点33分，自动核算上个月的考勤</div>
                                </div>
                            </form>
                        </div>
                    </div>
                </fieldset>
            </div>

            <div class="layui-col-xs12">
                <div id="monthTable" lay-filter="monthTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceMonth_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>