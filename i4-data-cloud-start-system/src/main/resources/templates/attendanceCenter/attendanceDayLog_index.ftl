<@override name="body">
<title>爱思数据云平台·考勤中心/考勤记录汇总</title>
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
                                    <div class="layui-input-inline" style="width: 130px">
                                        <select name="attendanceStage" class="layui-input">
                                            <option value="">选择时间段</option>
                                            <option value="0">上班</option>
                                            <option value="1">下班</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 130px">
                                        <select name="attendanceStatus" class="layui-input">
                                            <option value="">选择状态</option>
                                            <option value="0">正常</option>
                                            <option value="-2">未打卡</option>
                                            <option value="-1">迟到</option>
                                            <option value="1">早退</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 140px">
                                        <select name="workDateType" class="layui-input">
                                            <option value="">日期类型</option>
                                            <option value="0">正常工作日</option>
                                            <option value="1">周末</option>
                                            <option value="2">法定节假日</option>
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
                <div id="dayLogTable" lay-filter="dayLogTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="operate">
    <@permission value="attendanceCenter:attendanceDayLog/updatePage">
        <label class="layui-btn layui-btn-sm" lay-event="change">调整打卡</label>
    </@permission>
    <@permission value="attendanceCenter:attendanceDayLog/changeStatus">
        {{# if(d.settleStatus ==1 ){ }}
            <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="cancel">取消核算</label>
        {{# } else{ }}
            <label class="layui-btn layui-btn-sm layui-btn-primary" lay-event="resume">恢复核算</label>
        {{# }}}
    </@permission>
</script>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceDayLog_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>