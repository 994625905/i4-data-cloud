<@override name="body">
<title>爱思数据云平台·活动中心/照片墙详情页</title>
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
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card bg-layui">
        <div class="layui-card-header">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="imageRead"><i class="iconfont icon-yulan1"></i>预览</button>
            <button type="button" class="layui-btn layui-btn-sm" id="imageUpload"><i class="layui-icon layui-icon-upload"></i>上传</button>
            <label class="layui-word-aux">注意：上传后当前列表可能会排版稍微凌乱，因为懒加载预留了一个触发的块元素占据了位置，关掉当前弹框重新进来就好了</label>
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space15" id="imageDiv"></div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="imageContent">
    {{ each list as image index}}
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">

            <@permission value="activityCenter:imageWall/deleteImage">
                <a class="position-absolute cursor-pointer deleteImage{{size}}" data-id="{{image.id}}" style="top: 0px;right:0px;z-index: 9999" title="删除">
                    <i class="iconfont icon-shanchu text-danger"></i>
                </a>
            </@permission>

            <div class="image-list p-1">
                <div class="flex align-center justify-center border border-light-secondary rounded p-1">
                    <img src="{{image.url}}" alt="{{image.name}}" title="上传者：{{image.createUserName}}" data-id="{{image.id}}" data-name="{{image.name}}" onclick="readImage({{image.id}},'{{image.name}}',{{size+index}})">
                </div>
                <div class="flex flex-column">
                    <div class="flex justify-between mx-2">
                        <span>
                            <span class="cursor-pointer">
                                <i class="iconfont {{if image.isLike == 1}}icon-dianzan1{{else}}icon-dianzan{{/if}} text-primary like{{size}}" data-id="{{image.id}}" data-count="{{image.likeCount}}" title="点赞"></i>
                            </span>
                            <span class="layui-word-aux">{{image.likeCount}}</span>
                        </span>
                        <span>
                            <span><i class="iconfont icon-yuedu text-primary" title="阅读量"></i></span>
                            <span class="layui-word-aux">{{image.readCount}}</span>
                        </span>
                    </div>
                    <div class="flex justify-between layui-word-aux">
                        <span>
                            <span>作者：</span>
                            <span>{{image.createUserName}}</span>
                        </span>
                        <span>
                            <span>时间：</span>
                            <span>{{image.createTimeStr}}</span>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    {{ /each }}
</script>
<script>
    let activityId = ${activityId?c}
    let activityTitle = "${activityTitle!}"
</script>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/imageWall_detail.js?v=1.3"></script>
</@override>
<@extends name="/base.ftl"/>