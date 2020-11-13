<@override name="body">
<title>爱思数据云平台·下午茶/点单任务点单区</title>
<style>
    .select-hide{
        display: none;
    }
    .select-show{
        display: block;
    }
</style>
<body class="p-2 bg-layui">
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ${model.title}--<span class="endTime font-weight-bolder"></span>--描述信息：<span class="text-light-muted">${model.describeInfo}</span>
            </div>
            <div class="layui-card-body">
                <form class="layui-form">

                    <input type="hidden" name="teaTaskId" value="${model.id?c}">

                    <#list menuList as menu>
                        <div class="layui-form-item menu">

                            <input type="hidden" name="teaMenuId" value="${menu.id?c}">
                            <div class="layui-inline">
                                <div class="layui-input-inline">${menu.date}(${menu.week})</div>
                            </div>
                            <#list menu.teaList as tea>
                                <div class="layui-inline tea border border-light-secondary <#if selectList??><#list selectList as select><#if select.teaMenuId == menu.id && select.teaId?? && select.teaId == tea.id>
                                    bg-layui</#if></#list></#if>" style="width: 160px;height: 160px">
                                    <div class="flex flex-column align-center justify-center position-relative">
                                        <i class="iconfont icon-xuanze text-success position-absolute select-hide <#if selectList??><#list selectList as select><#if select.teaMenuId == menu.id && select.teaId??
                                            && select.teaId == tea.id>select-show</#if></#list></#if>" style="font-size: 50px!important; top: 35%;left:35%" data-id="${tea.id?c}"></i>
                                        <img class="rounded" src="${tea.image}" style="max-width: 100px" title="${tea.name}">
                                        <span class="text-danger font-weight-bold">￥：${tea.price}</span>
                                        <span>${tea.name}</span>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </#list>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="save">提交</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                            <div class="layui-form-mid layui-word-aux">单天不选即为不吃</div>
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
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/task_order.js?v=1.7"></script>
</@override>
<@extends name="/base.ftl"/>