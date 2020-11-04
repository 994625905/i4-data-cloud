<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<br>
    <div class="layui-fluid">

        <!--条件过滤框-->
        <div class="layui-row layui-col-space10">
            <div class="layui-col-xs12">
                <fieldset class="layui-elem-field search-fieldset">
                    <legend class="search-legend">条件查询</legend>
                    <div class="layui-field-box">
                        <div class="search-div">
                            <form class="layui-form" id="formParam">
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input id="date" name="date" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-form-mid layui-word-aux">你可以在此处创建图文草稿，然后保存下来，任务引用到图文内容的地方都支持从此处直接导入，eg：在此自定义一个周报模板</div>
                                </div>
                            </form>
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="layui-col-xs12">
                <div id="richTextTable" lay-filter="richTextTable"></div>
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

<!--操作列-->
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="read">预览</a>
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/richText_index.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>