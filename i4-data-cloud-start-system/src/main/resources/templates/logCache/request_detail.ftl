<@override name="body">
<title>爱思数据云平台·日志捕获/请求日志</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">

            <div class="layui-col-sm12 bg-white">
                <ul class="layui-timeline">
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">end</h3>
                            <ul id="detailContent">
                                <#list list as log>
                                    <li>
                                        <p class="align-center">
                                            <span class="text-light-muted">${log.time}</span>
                                            <#if log.actionType == 1>
                                                <span class="mx-02 layui-badge layui-bg-green" style="padding: 0 3px">新增</span>
                                            <#elseif log.actionType == 2>
                                                <span class="mx-02 layui-badge" style="padding: 0 3px">删除</span>
                                            <#elseif log.actionType == 3>
                                                <span class="mx-02 layui-badge layui-bg-cyan" style="padding: 0 3px">修改</span>
                                            <#else>
                                                <span class="mx-02 layui-badge layui-bg-blue" style="padding: 0 3px">查看</span>
                                            </#if>
                                            <span class="flex-1">
                                                <span class="layui-badge layui-bg-black" style="padding: 0 3px">${log.moduleName}：</span>
                                                <#if log.actionResult == 1>
                                                    <span>${log.actionContent}</span>
                                                <#else>
                                                    <span>${log.actionException}</span>
                                                </#if>
                                            </span>
                                            <#if log.actionResult == 1>
                                                <span class="layui-badge layui-bg-gray">耗时${log.actionTime}秒</span>
                                            <#else>
                                                <span class="layui-badge">异常/失败</span>
                                            </#if>
                                        </p>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                        <div class="layui-timeline-content layui-text">
                            <div class="layui-timeline-title">start</div>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
    </div>
</body>
</@override>
<@extends name="/base.ftl"/>