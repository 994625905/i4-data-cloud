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
                                    <div class="layui-input-inline">
                                        <select name="month" class="layui-input">
                                            <option value="">选择月份</option>
                                            <option value="1">1月</option>
                                            <option value="2">2月</option>
                                            <option value="3">3月</option>
                                            <option value="4">4月</option>
                                            <option value="5">5月</option>
                                            <option value="6">6月</option>
                                            <option value="7">7月</option>
                                            <option value="8">8月</option>
                                            <option value="9">9月</option>
                                            <option value="10">10月</option>
                                            <option value="11">11月</option>
                                            <option value="12">12月</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 140px">
                                        <select name="userId" class="layui-input">
                                            <option value="">选择用户</option>
                                            <#if userList??>
                                                <#list userList as user>
                                                    <option value="${user.id?c}">${user.realname}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button class="layui-btn layui-btn-primary search-button" lay-submit="" lay-filter="search">查询</button>
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
<!--table工具栏-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <@permission value="attendanceCenter:attendanceMonthLog/settleAll">
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="settleAll">统一校对核算日期</button>
        </@permission>
    </div>
</script>
<script type="text/html" id="operate">
    <@permission value="attendanceCenter:attendanceMonthLog/settleOne">
        <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="settleOne">逐一校对核算日期</label>
    </@permission>
</script>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceMonthLog_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>