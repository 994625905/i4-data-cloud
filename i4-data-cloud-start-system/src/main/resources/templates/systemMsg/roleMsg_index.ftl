<@override name="body">
<title>爱思数据云平台·系统管理/用户管理</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <!--表格-->
        <div class="layui-col-xs12">
            <div id="roleTable" lay-filter="roleTable"></div>
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
<!--状态列-->
<script type="text/html" id="statusTool">
    {{# if(d.status ==1 ){ }}
        <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="change">实习</a>
    {{# }}}
    {{# if(d.status ==2 ){ }}
        <a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="change">试用</a>
    {{# }}}
    {{# if(d.status ==3 ){ }}
        <a class="layui-btn layui-btn-sm" lay-event="change">正式</a>
    {{# }}}
    {{# if(d.status ==4 ){ }}
        <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="change">休假</a>
    {{# }}}
    {{# if(d.status ==5 ){ }}
        <a class="layui-btn layui-btn-disabled layui-btn-sm" lay-event="change">离职</a>
    {{# }}}
    {{# if(d.status ==6 ){ }}
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="change">其他</a>
    {{# }}}
</script>
<!--操作列-->
<script type="text/html" id="tool">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<script type="text/html" id="imageUrl">
    <img class="rounded" src="{{ d.headimage }}" onclick="showHeadImage('{{d.realname}}','{{d.id}}','{{d.headimage}}')" style="max-width: 50px">
</script>
<script type="text/html" id="changeStatus">
    <div class="layui-inline">
        <label class="layui-form-label">用户状态：</label>
        <div class="layui-input-inline" >
            <select name="userStatus" class="layui-input" style="width: 100px">
                <option value="1" {{ if status == 1}}selected{{/if}}>实习</option>
                <option value="2" {{ if status == 2}}selected{{/if}}>试用</option>
                <option value="3" {{ if status == 3}}selected{{/if}}>正式</option>
                <option value="4" {{ if status == 4}}selected{{/if}}>休假</option>
                <option value="5" {{ if status == 5}}selected{{/if}}>离职</option>
                <option value="6" {{ if status == 6}}selected{{/if}}>其他</option>
            </select>
        </div>
        <div class="layui-form-mid layui-word-aux">下拉选择切换</div>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/roleMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>