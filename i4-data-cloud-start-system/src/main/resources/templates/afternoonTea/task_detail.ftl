<@override name="body">
<title>爱思数据云平台·下午茶/点单任务详情页</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">
            <#if menuList??>
                <#list menuList as menu>
                    <div class="layui-col-xs12">
                        <div class="layui-collapse">
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">${menu.date}(${menu.week})</h2>
                                <div class="layui-colla-content layui-show">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12">
                                            <table class="layui-table" lay-skin="nob">
                                                <thead>
                                                <tr>
                                                    <td>下午茶</td>
                                                    <td>单价（元）</td>
                                                    <td>点单数量</td>
                                                    <td>结算</td>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <#if menu.teaList??>
                                                    <#list menu.teaList as tea>
                                                        <tr>
                                                            <td>
                                                                <div class="flex align-center justify-start">
                                                                    <img src="${tea.image}" class="rounded" style="max-width: 50px">
                                                                    <div class="flex flex-column align-start justify-center pl-1">
                                                                        <span class="font-weight-bold">${tea.name}</span>
                                                                        <span class="text-light-muted">${tea.storeAddress!}</span>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td class="text-danger font-weight-bolder">￥：${tea.price}</td>
                                                            <td>${tea.selectCount}</td>
                                                            <td class="text-danger font-weight-bolder">￥：${tea.amount}</td>
                                                        </tr>
                                                    </#list>
                                                </#if>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="layui-col-xs12">
                                            <div id="selectTable${menu.id?c}" lay-filter="selectTable${menu.id?c}"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</body>
<script>
    let menuIdList = ${menuIdList}
    let taskId = ${model.id?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/task_detail.js?v=1.7"></script>
</@override>
<@extends name="/base.ftl"/>