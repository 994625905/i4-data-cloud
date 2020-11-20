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
            <label class="layui-btn layui-btn-sm layui-btn-primary" lay-event="sign">已报名</label>
        {{# } else{ }}
            <label class="layui-btn layui-btn-sm layui-btn-danger" lay-event="sign">未报名</label>
        {{# }}}
    {{# } else{ }}
        <label class="layui-btn layui-btn-sm layui-btn-disabled">无需报名</label>
    {{# }}}
</script>
<script type="text/html" id="signContent">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">携带成员</label>
                <div class="layui-input-inline">
                    <input readonly class="layui-input" value="{{ if limitSign == 0 }}允许{{else}}禁止{{/if}}">
                </div>
                <div class="layui-form-mid layui-word-aux"> 允许携带家属（签到数可>1）， 禁止携带家属（签到数必<=1） </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">交通方式</label>
                <div class="layui-input-inline">
                    <input readonly class="layui-input" value="{{ if trafficType == 0 }}自行前往{{else}}集体乘车（允许自驾）{{/if}}">
                </div>
                <div class="layui-form-mid layui-word-aux"> 活动设定的交通方式</div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">签到人数</label>
                <div class="layui-input-inline">
                    <input type="number" id="signUserCount" value="0" class="layui-input" placeholder="签到人数">
                </div>
                <div class="layui-form-mid layui-word-aux"> 有事不参与的话，签到人数填0 </div>
            </div>
        </div>
        {{ if trafficType == 1}}
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">参与方式</label>
                    <div class="layui-input-inline">
                        <select id="signTraffic" class="layui-input">
                            <option value="">请选择</option>
                            <option value="0">跟团</option>
                            <option value="1">自驾</option>
                        </select>
                    </div>
                    <div class="layui-form-mid layui-word-aux">不参与的此次可不选</div>
                </div>
            </div>
        {{/if}}
        <div class="layui-form-item">
            <label class="layui-form-label">描述信息</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" id="signDescribeInfo" placeholder="如果携带家属的话，注明携带成员身份，如果不去的话，说明原因。单人参与，该项非必填项"></textarea>
            </div>
        </div>
    </form>
</script>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activity_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>