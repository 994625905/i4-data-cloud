<@override name="body">
<title>爱思数据云平台·活动中心/活动行照片墙</title>
<style>
    .image-list{
        position: relative;
        overflow: hidden;
    }
    /** 高度自适应 */
    .image-list img{
        height: 200px;
        min-height: 200px;
        max-width: 100%;
        cursor: pointer;
    }
</style>
<body class="bg-layui">
<br>
<div class="layui-fluid">
    <div class="layui-card bg-white">
        <div class="layui-card-header">
            <div class="layui-btn-container">
                <button type="button" class="layui-btn layui-btn-danger">预览模式</button>
                <button type="button" class="layui-btn"><i class="layui-icon layui-icon-upload"></i>上传</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space15" id="imageDiv"></div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="imageContent">
    {{ each list as image}}
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
            <div class="image-list">
                <img src="{{image.url}}" alt="{{image.name}}" title="上传者：{{image.createUserName}}" data-id="{{image.id}}" data-name="{{image.name}}" onclick="readImage({{image.id}},'{{image.name}}')">
                <div class="flex flex-column">
                    <div class="flex justify-between mx-2">
                        <div>
                            <span class="cursor-pointer" onclick="dz({{image.id}},{{image.isDz}})">
                                <i class="iconfont {{if image.isDz}}icon-dianzan1{{else}}icon-dianzan{{/if}} text-primary" title="点赞"></i>
                            </span>
                            <span class="ml-1 layui-word-aux">{{image.dzCount}}</span>
                        </div>
                        <div>
                            <span><i class="iconfont icon-yuedu text-primary" title="阅读量"></i></span>
                            <span class="ml-1 layui-word-aux">{{image.readCount}}</span>
                        </div>
                    </div>
                    <div class="flex justify-between layui-word-aux">
                        <div>
                            <span>作者：</span>
                            <span>{{image.createUserName}}</span>
                        </div>
                        <div>
                            <span>时间：</span>
                            <span>{{image.createTimeStr}}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    {{ /each }}
</script>
<script>
    let activityId = ${activityId?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activity_image.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>