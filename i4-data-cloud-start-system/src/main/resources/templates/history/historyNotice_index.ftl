<@override name="body">
<title>爱思数据云平台·点单/历史公告</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---你可以在这里创建公告，然后发布，就会加载到首页的专区栏目里，试一下吧---
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <div id="noticeTable" lay-filter="noticeTable"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<!--table工具栏-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-btn-primary" lay-event="add">新增</button>
    </div>
</script>
<script type="text/html" id="statusTool">
    {{# if(d.status == 0){ }}
        <label class="layui-btn layui-btn-sm" lay-event="deploy">未发布</label>
    {{# } else { }}
        <span>{{d.deployTimeStr}}</span>
    {{# }}}
</script>
<!--附件列表-->
<script type="text/html" id="fileList">
    <div class="layui-fluid">
        <table class="layui-table" lay-size="sm" lay-skin="nob">
            <colgroup>
                <col width="300">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>名称</th>
                <th>类型</th>
            </tr>
            </thead>
            <tbody>
            {{each list as file}}
            <tr>
                <th><a class="i4_a" href="{{file.url}}" target="_blank" title="{{file.name}}">{{file.name}}</a></th>
                <th class="fileType">{{file.type}}</th>
            </tr>
            {{/each}}
            </tbody>
        </table>
    </div>
</script>
<!--已读人员-->
<script type="text/html" id="readUser">
    <div class="layui-fluid">
        <table class="layui-table" lay-size="sm" lay-skin="nob">
            <thead>
                <tr>
                    <th>工号</th>
                    <th>登录名</th>
                    <th>用户姓名</th>
                </tr>
            </thead>
            <tbody>
            {{each list as user}}
                <tr>
                    <th>{{user.jobNumber}}</th>
                    <th>{{user.loginname}}</th>
                    <th>{{user.realname}}</th>
                </tr>
            {{/each}}
            </tbody>
        </table>
    </div>
</script>
<!--操作列-->
<script type="text/html" id="operate">
    <label class="layui-btn layui-btn-sm layui-btn-warm" lay-event="read">预览</label>
    {{# if(d.status == 0){ }}
        <label class="layui-btn layui-btn-sm" lay-event="edit">编辑</label>
    {{# } else { }}
        <label class="layui-btn layui-btn-sm layui-btn-normal" lay-event="readUser">已读人员</label>
    {{# }}}
    <label class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</label>
</script>
<script type="text/javascript" src="${StaticServer}/templates/history/historyNotice_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>