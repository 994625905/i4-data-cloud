<@override name="body">
<title>爱思数据云平台·考勤中心/补卡申请·提交</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">补卡工作日：</label>
                    <div class="layui-input-block">
                        <input readonly value="${model.attendanceWorkDate}--<#if model.attendanceStage == 0>上班<#else>下班</#if>" class="layui-input">
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
                    <label class="layui-form-label">备注信息：</label>
                    <div class="layui-input-block">
                        <textarea name="comment" placeholder="附上文字说明" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input value="${model.id?c}" name="attendanceMendId" type="hidden">
                        <button class="layui-btn" lay-submit lay-filter="apply">发送申请</button>
                        <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceMend_apply.js?v=1.4"></script>
</@override>
<@extends name="/base.ftl"/>