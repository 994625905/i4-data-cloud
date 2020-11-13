<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">请假标题：</label>
                        <div class="layui-input-block">
                            <input name="title" lay-verify="required" value="${model.title!}" placeholder="请输入标题" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">请假类型：</label>
                        <div class="layui-input-inline">
                            <select class="layui-input" name="typeId" >
                                <#if typeList??>
                                    <#list typeList  as type>
                                        <option value="${type.id?c}" <#if model.typeId == type.id>selected</#if> >${type.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">请假原因：</label>
                        <div class="layui-input-block">
                            <textarea name="reason" lay-verify="required" placeholder="请填写原因" class="layui-textarea">${model.reason!}</textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">开始时间：</label>
                            <div class="layui-input-inline">
                                <input name="startTime" id="startTime" lay-verify="required" class="layui-input" >
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束时间：</label>
                            <div class="layui-input-inline">
                                <input name="endTime" id="endTime" lay-verify="required" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">上传附件：</label>
                        <div class="layui-input-inline">
                            <select id="enclosureType">
                                <option value="1">图片</option>
                                <option value="2">音频</option>
                                <option value="3">视频</option>
                                <option value="4">文档</option>
                                <option value="5">其他</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <button class="layui-btn" type="button" id="uploadEnclosure">点击选择</button>
                        </div>
                        <input name="enclosure" type="hidden" value="${model.enclosure!}">
                        <div class="layui-form-mid layui-word-aux" id="enclosure"><#if model.enclosure??>${model.enclosure}<#else>此处非必填项，有的话就上传</#if></div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input value="${model.id?c}" type="hidden" name="id">
                            <button class="layui-btn" lay-submit lay-filter="save">立即保存</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script>
    let startTime = ${model.startTime?c}
    let endTime = ${model.endTime?c}
</script>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveApply_edit.js?v=1.5"></script>
</@override>
<@extends name="/base.ftl"/>