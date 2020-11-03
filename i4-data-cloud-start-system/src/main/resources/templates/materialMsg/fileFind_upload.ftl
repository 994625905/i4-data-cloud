<@override name="body">
<title>爱思数据云平台·文件管理/文件上传</title>
<body>
<div style="width: 90%;text-align: center;">
    <form class="layui-form">
        <br>
        <#if param.type == 1>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <button type="button" class="layui-btn" id="uploadImageBtn">上传图片</button>
                </label>
                <div class="layui-input-block">
                    <img src="" id="uploadImage">
                </div>
                <input type="hidden" name="url" lay-verify="required">
            </div>
        <#else>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <button type="button" class="layui-btn" id="uploadFileBtn">上传文件</button>
                </label>
                <div class="layui-input-block">
                    <input readonly name="url" lay-verify="required" class="layui-input">
                </div>
            </div>
        </#if>
        <#if param.type != 1>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <button type="button" class="layui-btn" id="uploadCoverBtn">上传封面</button>
                </label>
                <div class="layui-input-block">
                    <#if param.type == 2>
                        <img src="${systemConstant.audioCover}" id="uploadCover">
                        <input type="hidden" name="cover" value="${systemConstant.audioCover}" lay-verify="required">
                    <#elseif param.type == 3>
                        <img src="${systemConstant.videoCover}" id="uploadCover">
                        <input type="hidden" name="cover" value="${systemConstant.videoCover}" lay-verify="required">
                    <#elseif param.type == 4>
                        <img src="${systemConstant.wordCover}" id="uploadCover">
                        <input type="hidden" name="cover" value="${systemConstant.wordCover}" lay-verify="required">
                    <#elseif param.type == 5>
                        <img src="${systemConstant.otherCover}" id="uploadCover">
                        <input type="hidden" name="cover" value="${systemConstant.otherCover}" lay-verify="required">
                    </#if>
                </div>
            </div>
        </#if>

        <#if param.limitProp?? && param.limitProp == '1'>
            <div class="layui-form-item">
                <label class="layui-form-label">限制条件：</label>
                <div class="layui-input-block">
                    <input readonly class="layui-input text-muted border-white" value="限制宽高：${param.width?c}*${param.height?c}，允许等比例上传<#if param.fileSize??>限制大小：${param.fileSize?c}KB</#if>">
                </div>
            </div>
        <#elseif param.width?? && param.height??>
            <div class="layui-form-item">
                <label class="layui-form-label">限制条件：</label>
                <div class="layui-input-block">
                    <input readonly class="layui-input text-muted border-white" value="限制宽高：${param.width?c}*${param.height?c}，不允许等比例上传 <#if param.fileSize??> 限制大小：${param.fileSize?c}KB </#if>">
                </div>
            </div>
        </#if>

        <input type="hidden" name="suffix">
        <input type="hidden" name="size">
        <input type="hidden" name="type" value="${param.type}">
        <input type="hidden" name="width" >
        <input type="hidden" name="height">

        <div class="layui-form-item">
            <label class="layui-form-label">文件名称：</label>
            <div class="layui-input-block">
                <input name="name" lay-verify="required" placeholder="请输入文件名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述信息：</label>
            <div class="layui-input-block">
                <input type="text" name="description" placeholder="描述信息" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save">立即提交</button>
                <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    var type = ${param.type}
    var limit_width = <#if param.width??>${param.width?c}<#else>null</#if>
    var limit_height = <#if param.height??>${param.height?c}<#else>null</#if>
    var limit_flag = ${param.limitProp!'0'}
    var size = <#if param.fileSize??>${param.fileSize?c}<#else>null</#if>
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/fileFind_upload.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>