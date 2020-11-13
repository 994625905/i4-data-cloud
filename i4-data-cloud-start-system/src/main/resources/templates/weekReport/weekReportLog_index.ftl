<@override name="body">
<title>爱思数据云平台·周报事务/周报记录</title>
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
                                    <label class="layui-form-label">年</label>
                                    <div class="layui-input-inline">
                                        <input id="year" name="year" class="layui-input" style="width: 100px">
                                    </div>
                                    <div class="layui-input-inline" style="width: 100px">
                                        <select name="month" class="layui-input">
                                            <option value="">请选择月份查询</option>
                                            <option value="01">01</option><option value="01">02</option><option value="01">03</option><option value="01">04</option>
                                            <option value="01">05</option><option value="01">06</option><option value="01">07</option><option value="01">08</option>
                                            <option value="01">09</option><option value="01">10</option><option value="01">11</option><option value="01">12</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">用户</label>
                                    <div class="layui-input-inline">
                                        <select name="userId" class="layui-input">
                                            <option value="">请选择用户</option>
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
                <div id="weekReportTable" lay-filter="weekReportTable"></div>
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
        <label class="layui-btn layui-btn-sm layui-btn-disabled">暂未提交</label>
    {{# }}}
</script>

<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="processLog">查看流转日志</a>
    <a class="layui-btn layui-btn-sm layui-btn-warm" lay-event="read">预览</a>
</script>
<script type="text/javascript" src="${StaticServer}/templates/weekReport/weekReportLog_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>