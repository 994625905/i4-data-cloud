<@override name="body">
<title>爱思数据云平台·文件管理/文件查看</title>
<style>
    .layui-table-cell{ height: auto; white-space: normal; }
</style>
<body>
<!--分割线-->
<br>
<div class="layui-fluid">

    <!--条件过滤框-->
    <div class="layui-row">
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
                        </form>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>

    <!--表格-->
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs12">
            <div class="layui-tab layui-tab-card" lay-filter="type">
                <ul class="layui-tab-title">
                    <li class="layui-this" type="1">图片</li>
                    <li type="2">音频</li>
                    <li type="3">视频</li>
                    <li type="4">文档</li>
                    <li type="5">其他</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <div id="fileTable_image" lay-filter="fileTable_image"></div>
                    </div>
                    <div class="layui-tab-item">
                        <div id="fileTable_audio" lay-filter="fileTable_audio"></div>
                    </div>
                    <div class="layui-tab-item">
                        <div id="fileTable_video" lay-filter="fileTable_video"></div>
                    </div>
                    <div class="layui-tab-item">
                        <div id="fileTable_doc" lay-filter="fileTable_doc"></div>
                    </div>
                    <div class="layui-tab-item">
                        <div id="fileTable_other" lay-filter="fileTable_other"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="toolbar">
    <button class="layui-btn layui-btn-sm" lay-event="upload"><i class="layui-icon layui-icon-upload"></i>上传</button>
</script>
<script type="text/html" id="statusTool">
    {{# if(d.status ==0 ){ }}
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="freeze">禁用</a>
    {{# }else{ }}
        <a class="layui-btn layui-btn-sm" lay-event="active">正常</a>
    {{# }}}
</script>
<script type="text/html" id="operate">
    <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</button>
    <button class="layui-btn layui-btn-sm" lay-event="copy">复制链接</button>
</script>
<script type="text/html" id="imageUrl">
    <img src="{{ d.url }}" onclick="showImage('{{d.name}}','{{d.id}}','{{d.url}}')">
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/fileFind_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>