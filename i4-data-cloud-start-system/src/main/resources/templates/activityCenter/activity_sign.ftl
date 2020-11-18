<@override name="body">
<title>爱思数据云平台·活动中心/活动行</title>
<body class="bg-layui">
<br>
<div class="layui-fluid">

    <div class="layui-card">
        <div class="layui-card-header">
            <form class="layui-form" id="formParam">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 130px">
                        <select name="userId" class="layui-input">
                            <option value="">选择用户查询</option>
                            <#if userList??>
                                <#list userList as user>
                                    <option value="${user.id?c}">${user.realname}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 130px">
                        <select name="signCount" class="layui-input">
                            <option value="">选择签到类型</option>
                            <option value="0">不参与</option>
                            <option value="1">单人参与</option>
                            <option value="2">携带家属</option>
                        </select>
                    </div>
                </div>
                <#if model.trafficType == 1>
                    <div class="layui-inline">
                        <div class="layui-input-inline" style="width: 130px">
                            <select name="signTraffic" class="layui-input">
                                <option value="">选择交通方式</option>
                                <option value="0">跟团</option>
                                <option value="1">自驾</option>
                            </select>
                        </div>
                    </div>
                </#if>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-primary search-button" lay-submit="" lay-filter="search">查询</button>
                </div>
            </form>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-xs12">
                    <table class="layui-table" lay-skin="nob">
                        <thead>
                        <tr>
                            <th>签到总数量（包含携带家属）</th>
                            <th>不参与</th>
                            <th>单人参与</th>
                            <th>未签到人数（点击查看详情）</th>
                            <#if model.trafficType == 1>
                                <th>跟团</th>
                                <th>自驾</th>
                            </#if>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="text-danger">${sign.signCount}</td>
                            <td class="text-danger">${sign.refuseCount}</td>
                            <td class="text-danger">${sign.userCount}</td>
                            <td class="text-danger"><a class="i4_a" onclick="noSignCount(${model.id?c})">${sign.noCount}</a></td>
                            <#if model.trafficType == 1>
                                <td class="text-danger">${sign.traffic0}</td>
                                <td class="text-danger">${sign.traffic1}</td>
                            </#if>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="layui-col-xs12">
                    <div id="signTable" lay-filter="signTable"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    let trafficType = ${model.trafficType}
    let limitSign = ${model.limitSign}
</script>
<script type="text/html" id="operate">
    <@permission value="activityCenter:activity/updateSign">
        <label class="layui-btn layui-btn-sm" lay-event="edit">编辑</label>
    </@permission>
</script>
<script type="text/html" id="noSignUser">
    <div class="layui-fluid">
        <table class="layui-table" lay-size="sm" lay-skin="nob">
            <thead>
            <tr>
                <th>ID</th>
                <th>工号</th>
                <th>登录名</th>
                <th>用户姓名</th>
            </tr>
            </thead>
            <tbody>
                {{each list as user}}
                    <tr>
                        <th>{{user.id}}</th>
                        <th>{{user.jobNumber}}</th>
                        <th>{{user.loginname}}</th>
                        <th>{{user.realname}}</th>
                    </tr>
                {{/each}}
            </tbody>
        </table>
    </div>
</script>
<script type="text/html" id="signContent">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">签到人数</label>
                <div class="layui-input-inline">
                    <input type="number" id="signUserCount" value="{{data.signUserCount}}" class="layui-input" placeholder="签到人数">
                </div>
                <div class="layui-form-mid layui-word-aux"> 有事不参与的话，签到人数填0 </div>
            </div>
        </div>
        <#if model.trafficType == 1>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">参与方式</label>
                    <div class="layui-input-inline">
                        <select id="signTraffic" class="layui-input">
                            <option value="">请选择</option>
                            <option value="0" {{ if data.signTraffic == 0}} selected {{/if}} >跟团</option>
                            <option value="1" {{ if data.signTraffic == 1}} selected {{/if}} >自驾</option>
                        </select>
                    </div>
                    <div class="layui-form-mid layui-word-aux">不参与的此次可不选</div>
                </div>
            </div>
        </#if>
        <div class="layui-form-item">
            <label class="layui-form-label">描述信息</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" id="signDescribeInfo" placeholder="如果携带家属的话，注明携带成员身份，如果不去的话，说明原因。单人参与，该项非必填项">{{data.signDescribeInfo}}</textarea>
            </div>
        </div>
    </form>
</script>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activity_sign.js?v=1.3"></script>
</@override>
<@extends name="/base.ftl"/>