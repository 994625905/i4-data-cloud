<@override name="body">
<title>爱思数据云平台·代码生成/代码生成日志管理</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">
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
                            </form>
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="layui-col-xs12">
                <div id="codeLogTable" lay-filter="codeLogTable"></div>
            </div>
        </div>
    </div>
</body>
<!--result-->
<script type="text/html" id="resultCols">
    {{# if(d.createResult ==1 ){ }}
        <label class="layui-btn layui-btn-sm">成功</label>
    {{# }}}
    {{# if(d.createResult ==0 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-danger">失败</label>
    {{# }}}
</script>
<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="detail">查看详情</a>
</script>
<script type="text/javascript" src="${StaticServer}/templates/autoCode/codeLog_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>