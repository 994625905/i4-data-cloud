<@override name="body">
<title>爱思数据云平台·下午茶/列表</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
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
                                <div class="layui-input-inline">
                                    <input name="teaName" class="layui-input" placeholder="下午茶名称">
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

        <!--表格-->
        <div class="layui-col-xs12">
            <div id="selectTable" lay-filter="selectTable"></div>
        </div>

        <!--报表-->
        <div class="layui-col-xs12">
            <div id="selectChartByType" style="height: 450px"></div>
        </div>

        <!--报表-->
        <div class="layui-col-xs12">
            <div id="selectChart" style="height: 450px"></div>
        </div>

    </div>
</div>
</body>
<script type="text/html" id="imageUrl">
    {{# if(d.teaImage){ }}
        <div class="flex align-center justify-start">
            <img class="rounded" src="{{ d.teaImage }}" onclick="showHeadImage('{{d.teaName}}','{{d.id}}','{{d.teaImage}}')" style="max-width: 50px">
            <div class="flex flex-column align-center justify-start pl-1">
                <div>
                    <span class="text-danger font-weight-bolder">￥:{{d.teaPrice}}</span>
                    <span class="font-weight-bold">{{d.teaName}}</span>
                </div>
                <span class="text-light-muted">{{d.teaStoreAddress}}</span>
            </div>
        </div>
    {{# } else{ }}
        <div class="flex align-center justify-start">
            <img class="rounded" src="${systemConstant.loseImage!}" onclick="showHeadImage('没有点单','{{d.id}}','${systemConstant.loseImage!}')" style="max-width: 50px">
            <div class="flex flex-column align-center justify-start pl-1">
                <span class="text-light-muted">没有点单（或者该下午茶已被删除）</span>
            </div>
        </div>
    {{# }}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/select_index.js?v=1.3"></script>
</@override>
<@extends name="/base.ftl"/>