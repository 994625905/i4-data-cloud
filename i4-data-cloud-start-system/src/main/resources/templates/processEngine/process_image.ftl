<@override name="body">
<title>爱思数据云平台·流程引擎/流转图片</title>
<body>
<br>
    <div class="layui-fluid text-center">
        <!-- 1.获取到规则流程图 -->
<#--        <img style="position: absolute;top: 0px;left: 0px;" src="${StaticServer}/processEngine//deployMsg/imageio?deploymentId=${map.deploymentId}&imageName=${map.imageName}">-->
        <img src="${StaticServer}/processEngine//deployMsg/imageio?procdefId=${param.procdefId!}">

        <!-- 2.根据当前活动的坐标，动态绘制DIV 判断流程是否走完，即map.acs==0-->
        <#if map.acs != '0'>
            <div style="position: absolute;border:1px solid red;top:${map.acs.y-1}px;left:${map.acs.x-1}px;width:${map.acs.width}px;height:${map.acs.height}px;"></div>
        </#if>
    </div>
</body>
</@override>
<@extends name="/base.ftl"/>