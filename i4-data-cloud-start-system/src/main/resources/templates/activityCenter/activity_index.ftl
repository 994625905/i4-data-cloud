<@override name="body">
<title>爱思数据云平台·活动中心/活动行</title>
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
    <label class="layui-word-aux">需要签到的活动，无论是否参与，都需要签到登记，不参加的说明原因</label>
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
    <label class="layui-btn layui-btn-primary layui-btn-sm" lay-event="image"><i class="iconfont icon-zhaopianqiang text-danger"></i></label>
</script>
<script type="text/html" id="statusTool">
    {{# if(d.status ==2 ){ }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">已过期</label>
    {{# } else{ }}
        <label class="layui-btn layui-btn-sm layui-btn-normal">进行中</label>
    {{# }}}
</script>
<script type="text/html" id="operate">
    <label class="layui-btn layui-btn-sm" lay-event="read">查看</label>
    {{# if(d.isSign ==1 ){ }}
        {{# if(d.userSign ==1 ){ }}
            <label class="layui-btn layui-btn-sm layui-btn-primary" lay-event="sign">已报名<i class="iconfont icon-yiqiandao"></i></label>
        {{# } else{ }}
            <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="sign">未报名<i class="iconfont icon-weiqiandao"></i></label>
        {{# }}}
    {{# } else{ }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">无需报名</label>
    {{# }}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activity_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>