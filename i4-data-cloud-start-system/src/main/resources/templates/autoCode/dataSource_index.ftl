<@override name="body">
<title>爱思数据云平台·代码生成/数据源配置</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header">
            --可用的数据源配置，如下所示，点击添加一个数据源试试--
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger addDataSource">添加</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space10" id="content"></div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="dataSourceList">
    {{each dataSourceList as dataSource}}
        <div class="layui-col-sm2">
            <div class="border border-light-secondary flex flex-column justify-center align-center m-2 p-2 dataSource" onclick="editPage('{{dataSource.datasourceName}}','{{dataSource.id}}')">
                <img src="{{dataSource.dataSourceCover}}" title="{{dataSource.datasourceName}}" class="rounded" style="max-width: 200px">
                <div class="flex flex-column justify-start align-start">
                    <span class="font-weight-bold font-kai" style="font-size: 18px">{{dataSource.datasourceName}}</span>
                    <span class="font-xs">数据库：{{dataSource.databaseName}}</span>
                    <span class="font-xs">类型号：{{dataSource.driverclassType}}</span>
                </div>
            </div>
        </div>
    {{/each}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/autoCode/dataSource_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>