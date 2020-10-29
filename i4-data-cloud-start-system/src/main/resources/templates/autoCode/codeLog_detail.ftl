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
                <div id="codeDetailTable" lay-filter="codeDetailTable"></div>
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
<script>
    var autoCodeLogId = ${param.id?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/autoCode/codeLog_detail.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>