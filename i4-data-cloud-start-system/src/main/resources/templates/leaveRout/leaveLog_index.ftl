<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
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
                                        <input id="date" name="date" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">类型</label>
                                    <div class="layui-input-inline">
                                        <select name="typeId" class="layui-input">
                                            <#if typeList??>
                                                <option value="">请选择类型</option>
                                                <#list typeList as type>
                                                    <option value="${type.id}">${type.name}</option>
                                                </#list>
                                            </#if>
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
                                                    <option value="${user.id}">${user.realname}</option>
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
                <div id="leaveTable" lay-filter="leaveTable"></div>
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
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveLog_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>