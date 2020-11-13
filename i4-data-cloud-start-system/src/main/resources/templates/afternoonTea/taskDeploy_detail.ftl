<@override name="body">
<title>爱思数据云平台·下午茶/点单任务详情页</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">标题：</label>
                        <div class="layui-input-block">
                            <input readonly value="${model.title}" class="layui-input">
                        </div>
                    </div>
                    <div class="menuList">
                        <#list menuList as menu>
                            <div class="layui-form-item menu">
                                <div class="layui-inline">
                                    <label class="layui-form-label">时间</label>
                                    <div class="layui-input-inline">
                                        <input readonly value="${menu.date}" class="layui-input">
                                    </div>
                                    <div class="layui-form-mid layui-word-aux">${menu.week}</div>
                                </div>
                                <#list menu.teaList as tea>
                                    <div class="layui-inline tea border border-light-secondary" style="line-height: 15px">
                                        <div class="flex flex-column align-center justify-center">
                                            <img class="rounded" src="${tea.image}" style="max-width: 45px" title="${tea.name}">
                                            <span class="text-danger font-weight-bold">￥：${tea.price}</span>
                                            <span class="font-xs">${tea.name}</span>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                        </#list>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">截止点单：</label>
                            <div class="layui-input-inline">
                                <input readonly id="endTime" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">描述信息：</label>
                        <div class="layui-input-block">
                            <textarea name="describeInfo"  class="layui-textarea" readonly>${model.describeInfo}</textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <#if model.status == 1>
                                <label class="layui-btn ">已发布</label>
                            <#elseif model.status == 2>
                                <label class="layui-btn layui-btn-disabled">已过期</label>
                            </#if>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script>
    let modelEndTime = ${model.endTime?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/taskDeploy_detail.js?v=1.6"></script>
</@override>
<@extends name="/base.ftl"/>