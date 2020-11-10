<@override name="body">
<title>爱思数据云平台·日志捕获/请求日志</title>
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
                                        <input name="moduleName" class="layui-input" placeholder="模块查询" style="max-width: 130px">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input name="actionContent" class="layui-input" placeholder="内容查询" style="max-width: 110px">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 100px">
                                        <select name="actionType" class="layui-input">
                                            <option value="">选择类型</option>
                                            <option value="1">新增</option>
                                            <option value="2">删除</option>
                                            <option value="3">修改</option>
                                            <option value="4">查看</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 100px">
                                        <select name="actionResult" class="layui-input">
                                            <option value="">选择结果</option>
                                            <option value="1">成功</option>
                                            <option value="0">失败</option>
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
                <div id="requestTable"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="content">
    {{if list}}
    {{each list as requestLog}}
        <div class="layui-col-sm4">
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong>{{requestLog.date}}</strong>
                    {{if requestLog.week == "星期六"}}
                    <span class="bg-success text-white">{{requestLog.week}}</span>
                    {{else if requestLog.week == "星期日"}}
                    <span class="bg-danger text-white">{{requestLog.week}}</span>
                    {{else}}
                    <span>{{requestLog.week}}</span>
                    {{/if}}
                </div>
                <div class="layui-card-body" style="height: 425px">
                    <ul class="layui-timeline">
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">end</h3>
                                <ul>
                                    {{each requestLog.detail as log}}
                                        <li class="text-ellipsis">
                                            <p class="align-center">
                                                <span class="text-light-muted">{{log.time}}</span>
                                                {{if log.actionType == 1}}
                                                    <span class="mx-02 layui-badge layui-bg-green" style="padding: 0 3px">新增</span>
                                                {{else if log.actionType == 2}}
                                                    <span class="mx-02 layui-badge" style="padding: 0 3px">删除</span>
                                                {{else if log.actionType == 3}}
                                                    <span class="mx-02 layui-badge layui-bg-cyan" style="padding: 0 3px">修改</span>
                                                {{else }}
                                                    <span class="mx-02 layui-badge layui-bg-blue" style="padding: 0 3px">查看</span>
                                                {{/if}}
                                                <span class="flex-1">
                                                    <span class="layui-badge layui-bg-black" style="padding: 0 3px">{{log.moduleName}}：</span>
                                                    {{if log.actionResult == 1}}
                                                        <span>{{log.actionContent}}</span>
                                                    {{else }}
                                                        <span>{{log.actionException}}</span>
                                                    {{/if}}
                                                </span>
                                                {{if log.actionResult == 1}}
                                                    <span class="layui-badge layui-bg-gray">耗时{{log.actionTime}}秒</span>
                                                {{else }}
                                                    <span class="layui-badge">异常/失败</span>
                                                {{/if}}
                                            </p>
                                        </li>
                                    {{/each}}
                                    <li>
                                        <p class="align-center">
                                            <span class="text-light-muted">…………</span>
                                        </p>
                                    </li>
                                    <li>
                                        <p class="align-center">
                                            {{if requestLog.limit < 10}}
                                            <span class="text-light-muted">已经没有更多了</span>
                                            {{else}}
                                            <a class="text-primary" onclick="loadDetail('{{requestLog.date}}')">点击查看更多日志</a>
                                            {{/if}}
                                        </p>
                                    </li>
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
    {{/each}}
    {{/if}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/logCache/request_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>