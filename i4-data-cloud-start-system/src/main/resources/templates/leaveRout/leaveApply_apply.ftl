<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">假条：</label>
                        <div class="layui-input-block">
                            <input readonly value="${model.title}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">选择流程：</label>
                        <div class="layui-input-inline">
                            <select class="layui-input" name="processDefId" lay-verify="required" lay-filter="processDefId">
                                <#if processList??>
                                    <#list processList  as process>
                                        <option value="${process.procdefId},${process.deploymentId}">${process.procdefName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">抄送对象：</label>
                        <div class="layui-input-inline" id="ccUser"></div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">备注信息：</label>
                        <div class="layui-input-block">
                            <textarea name="comment" placeholder="附上文字说明" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input value="${model.id?c}" name="leaveId" type="hidden">
                            <button class="layui-btn" lay-submit lay-filter="apply">发送申请</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script>
    let userList = ${userList}
</script>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveApply_add.js?v=1.4"></script>
</@override>
<@extends name="/base.ftl"/>