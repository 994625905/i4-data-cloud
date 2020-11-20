<@override name="body">
<title>爱思数据云平台·点单/历史照片墙</title>
<style>
    .image-list{
        position: relative;
        overflow: hidden;
        line-height: 20px;
        background-color: #FFFFFF;
    }
    /** 高度自适应 */
    .image-list img{
        height: 200px;
        min-height: 200px;
        max-width: 100%;
        cursor: pointer;
    }
</style>
<body class="">
<br>
<div class="layui-fluid">
    <div class="layui-card bg-layui">
        <div class="layui-card-header">
            往期无法同步过来的数据，皆可以历史照片墙的方式存档，行政同事有新建/修改相册的权限，所有用户有在相册内上传照片的权限
            <label class="layui-btn layui-btn-sm" id="insertGroup">创建相册</label>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space15" id="groupDiv"></div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="groupContent">
    {{each list as groups }}
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">

            <@permission value="history:historyImageWall/update">
                <a class="position-absolute cursor-pointer update{{size}}" data-id="{{groups.id}}" style="top: 0px;right:5px;z-index: 9999" title="删除">
                    <i class="iconfont icon-bianji text-primary"></i>
                </a>
            </@permission>
            <@permission value="history:historyImageWall/delete">
                <a class="position-absolute cursor-pointer delete{{size}}" data-id="{{groups.id}}" style="top: 0px;right:0px;z-index: 9999" title="删除">
                    <i class="iconfont icon-shanchu1 text-danger"></i>
                </a>
            </@permission>

            <div class="image-list p-1">
                <div class="flex align-center justify-center border border-light-secondary rounded p-1">
                    <img src="{{if groups.imageCover}}{{groups.imageCover}}{{else}}${systemConstant.photoGroupImage}{{/if}}"
                         title="{{groups.name}}" class="cursor-pointer" onclick="photoGroup({{groups.id}},'{{groups.name}}')">
                </div>
                <div class="flex flex-column">
                    <div class="flex justify-start font-xs">
                        <span>相册名称：</span>
                        <span class="layui-word-aux groupName">{{groups.name}}</span>
                    </div>
                    <div class="flex justify-start font-xs">
                        <span>相册描述：</span>
                        <span class="text-ellipsis layui-word-aux groupDescribeInfo">{{groups.describeInfo}}</span>
                    </div>
                    <div class="flex justify-start font-xs">
                        <span>数量：</span>
                        <span class="layui-word-aux">{{groups.imageCount}}</span>
                    </div>
                </div>
            </div>
        </div>
    {{/each}}
</script>
<script type="text/html" id="insertContent">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">相册名称</label>
            <div class="layui-input-block">
                <input name="groupName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">相册描述</label>
            <div class="layui-input-block">
                <input name="groupDescribeInfo" class="layui-input">
            </div>
        </div>
    </form>
</script>
<script type="text/html" id="updateContent">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">相册名称</label>
            <div class="layui-input-block">
                <input value="{{name}}" name="groupName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">相册描述</label>
            <div class="layui-input-block">
                <input value="{{describeInfo}}" name="groupDescribeInfo" class="layui-input">
            </div>
        </div>
    </form>
</script>
<script type="text/javascript" src="${StaticServer}/templates/history/historyImageWall_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>