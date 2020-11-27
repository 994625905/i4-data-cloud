<@override name="body">
<title>爱思数据云平台·系统管理/用户邀请码</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header">
            ---除了通过邀请码来注册，您也可以来手动添加用户，前提权限足够，并且新用户需要在第一次登陆的时候完成各项值的初始化。---
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger experience">体验一下吧</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space10">
                <!--表格-->
                <div class="layui-col-xs12">
                    <div id="inviteCodeTable" lay-filter="inviteCodeTable"></div>
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
<!--用户状态列-->
<script type="text/html" id="statusTool">
    {{# if(d.userStatus ==1 ){ }}
        <a class="layui-btn layui-btn-normal layui-btn-sm">实习</a>
    {{# }}}
    {{# if(d.userStatus ==2 ){ }}
        <a class="layui-btn layui-btn-warm layui-btn-sm">试用</a>
    {{# }}}
    {{# if(d.userStatus ==3 ){ }}
        <a class="layui-btn layui-btn-sm">正式</a>
    {{# }}}
    {{# if(d.userStatus ==4 ){ }}
        <a class="layui-btn layui-btn-primary layui-btn-sm">休假</a>
    {{# }}}
    {{# if(d.userStatus ==5 ){ }}
        <a class="layui-btn layui-btn-disabled layui-btn-sm">离职</a>
    {{# }}}
    {{# if(d.userStatus ==6 ){ }}
        <a class="layui-btn layui-btn-danger layui-btn-sm">其他</a>
    {{# }}}
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <a class="layui-btn layui-btn-sm" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<!--显示状态-->
<script type="text/html" id="status">
    {{if status}}
        <a class="layui-btn layui-btn-normal layui-btn-sm">有效</a>
    {{else}}
        <a class="layui-btn layui-btn-disabled layui-btn-sm">已过期</a>
    {{/if}}
</script>
<!--显示邀请码-->
<script type="text/html" id="showQRCode">
    <div class="layui-fluid flex flex-column align-center justify-center m-5">
        <div class="flex flex-column align-center justify-center" id="content">
            <span class="font-small my-1">{{name}}</span>
            {{if userStatus ==1}}
                <label class="layui-btn layui-btn-normal layui-btn-sm">用户状态：实习</label>
            {{else if userStatus ==2}}
                <label class="layui-btn layui-btn-warm layui-btn-sm">用户状态：试用</label>
            {{else if userStatus ==3}}
                <label class="layui-btn layui-btn-sm">用户状态：正式</label>
            {{else if userStatus ==4}}
                <label class="layui-btn layui-btn-primary layui-btn-sm">用户状态：休假</label>
            {{else if userStatus ==5}}
                <label class="layui-btn layui-btn-disabled layui-btn-sm">用户状态：离职</label>
            {{else if userStatus ==6}}
                <label class="layui-btn layui-btn-danger layui-btn-sm">用户状态：其他</label>
            {{/if}}
            <img src="data:image/png;base64,{{encode}}" style="max-width: 300px" title="{{code}}">
            <span class="font-weight-bold font">{{code}}</span>
            <span class="text-light-muted my-1 font-smaller">所属部门：[{{departmentName}}]</span>
            <span class="text-light-muted my-1 font-smaller">携带角色：[{{roleNames}}]</span>
        </div>
        <div class="layui-form-item">
            {{if status}}
                <label class="layui-btn layui-btn-normal">当前有效</label>
            {{else}}
                <label class="layui-btn layui-btn-disabled">当前已过期</label>
            {{/if}}
        </div>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/inviteCodeMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>