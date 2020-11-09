<@override name="body">
<title>爱思数据云平台·日志捕获/限流拦截</title>
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
                                    <label class="layui-form-label">请求路径</label>
                                    <div class="layui-input-inline">
                                        <input name="requestPath" class="layui-input" style="max-width: 130px">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">类型</label>
                                    <div class="layui-input-inline" style="width: 100px">
                                        <select name="type" class="layui-input">
                                            <option value="">选择类型</option>
                                            <option value="0">IP限制</option>
                                            <option value="1">用户限制</option>
                                            <option value="2">普通限制</option>
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
                <div id="limitLogTable" lay-filter="limitLogTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/logCache/limitLog_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>