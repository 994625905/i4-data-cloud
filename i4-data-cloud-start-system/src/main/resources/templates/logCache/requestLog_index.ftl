<@override name="body">
<title>爱思数据云平台·日志捕获/请求日志</title>
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
                                    <div class="layui-input-inline" style="width: 130px">
                                        <select name="userId" class="layui-input">
                                            <option value="">请选择用户</option>
                                            <#if userList??>
                                                <#list userList as user>
                                                    <option value="${user.id?c}">${user.loginname}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input name="moduleName" class="layui-input" placeholder="模块查询" style="max-width: 130px">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input name="actionContent" class="layui-input" placeholder="内容查询" style="max-width: 110px">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 100px">
                                        <select name="actionType" class="layui-input">
                                            <option value="">选择类型</option>
                                            <option value="1">新增</option>
                                            <option value="2">删除</option>
                                            <option value="3">修改</option>
                                            <option value="4">查看</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 100px">
                                        <select name="actionResult" class="layui-input">
                                            <option value="">选择结果</option>
                                            <option value="1">成功</option>
                                            <option value="0">失败</option>
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
                <div id="requestLogTable" lay-filter="requestLogTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/logCache/requestLog_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>