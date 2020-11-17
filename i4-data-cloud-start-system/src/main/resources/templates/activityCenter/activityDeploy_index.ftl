<@override name="body">
<title>爱思数据云平台·活动中心/活动发布</title>
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
                                    <input id="year" name="year" class="layui-input" placeholder="选择年份查询">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline" style="width: 120px">
                                    <select name="typeId" class="layui-input">
                                        <option value="">选择类型</option>
                                        <#if typeList??>
                                            <#list typeList as type>
                                                <option value="${type.id}">${type.name}</option>
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
            <div id="activityTable" lay-filter="activityTable"></div>
        </div>
    </div>

</div>
</body>
<script type="text/html" id="toolbar">
    <@permission value="activityCenter:activityDeploy/addPage">
        <label class="layui-btn layui-btn-sm" lay-event="add">新增活动</label>
    </@permission>
</script>
<script type="text/html" id="imageUrl">
    <div class="flex align-center justify-start" style="line-height: 20px">
        <img class="rounded" src="{{ d.coverImage }}" onclick="showHeadImage('{{d.title}}','{{d.id}}','{{d.coverImage}}')" style="max-width: 100px">
        <div class="flex flex-column align-start justify-start pl-1">
            <span class="font-weight-bold">{{d.title}}</span>
            <span class="text-light-muted">地点：{{d.address}}</span>
        </div>
    </div>
</script>
<script type="text/html" id="imageTool">
    <label class="layui-btn layui-btn-normal layui-btn-sm" lay-event="image">照片墙</label>
</script>
<script type="text/html" id="operate">
    {{# if(d.status ==0 ){ }}
        <@permission value="activityCenter:activityDeploy/deploy">
            <label class="layui-btn layui-btn-sm layui-btn-normal" lay-event="deploy">未发布</label>
        </@permission>
    {{# }else if(d.status ==1 ){ }}
        <@permission value="activityCenter:activityDeploy/deploy">
            <label class="layui-btn layui-btn-sm layui-btn-primary" lay-event="closeDeploy">已发布</label>
        </@permission>
    {{# } else{ }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">已过期</label>
    {{# }}}
    <@permission value="activityCenter:activityDeploy/editPage">
        <label class="layui-btn layui-btn-sm" lay-event="edit">编辑</label>
    </@permission>
    <@permission value="activityCenter:activityDeploy/delete">
        <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</label>
    </@permission>
</script>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activityDeploy_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>