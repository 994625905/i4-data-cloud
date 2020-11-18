<@override name="body">
<title>爱思数据云平台·活动中心/照片墙</title>
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
            照片墙与活动行同步，发布一个活动就创建一个图组，每个用户都有阅读，点赞，上传的权限，活动发布者有删除权限，封面选用点赞数最高，时间最靠前的一张（默认第二天生效）
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space15" id="imageDiv">
                <#if activityList??>
                    <#list activityList as activity>
                        <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                            <div class="image-list p-1">
                                <div class="flex align-center justify-center border border-light-secondary rounded p-1">
                                    <img src="<#if activity.imageCover??>${activity.imageCover}<#else>${systemConstant.authLoginImage}</#if>"
                                         title="${activity.title}" class="cursor-pointer" onclick="photoGroup(${activity.id?c},'${activity.title}')">
                                </div>
                                <div class="flex flex-column">
                                    <div class="flex justify-start font-xs">
                                        <span>标题：</span>
                                        <span class="layui-word-aux">${activity.title}</span>
                                    </div>
                                    <div class="flex justify-start font-xs">
                                        <span>地点：</span>
                                        <span class="text-ellipsis layui-word-aux">${activity.address}</span>
                                    </div>
                                    <div class="flex justify-start font-xs">
                                        <span>数量：</span>
                                        <span>${activity.imageCount}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/imageWall_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>