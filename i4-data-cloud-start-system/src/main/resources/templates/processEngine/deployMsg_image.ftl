<@override name="body">
<title>爱思数据云平台·流程引擎/流程部署</title>
<body>
<br>
    <div class="layui-fluid">
        <!-- 1.获取到规则流程图 -->
<#--        <img style="position: absolute;top: 0px;left: 0px;" src="${StaticServer}/processEngine//deployMsg/imageio?deploymentId=${map.deploymentId}&imageName=${map.imageName}">-->
        <img src="${StaticServer}/processEngine//deployMsg/imageio?procdefId=${param.procdefId!}">

    </div>
</body>
</@override>
<@extends name="/base.ftl"/>