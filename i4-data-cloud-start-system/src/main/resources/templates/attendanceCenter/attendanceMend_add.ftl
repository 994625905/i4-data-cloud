<@override name="body">
<title>爱思数据云平台·考勤中心/补卡申请·新增</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">补卡日期：</label>
                            <div class="layui-input-inline">
                                <select class="layui-input" name="attendanceDayId">
                                    <option value="">选择补卡日期时间段</option>
                                    <#if dayList?? && dayList?size &gt; 0>
                                        <#list dayList as day>
                                            <option value="${day.id}">${day.workDate}-<#if day.attendanceStage == 0>上班<#else>下班</#if></option>
                                        </#list>
                                    <#else>
                                        <option value="">没有需要补卡的时间段</option>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-word-aux">仅限当前月或者还未结算的上月</div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">补卡原因：</label>
                        <div class="layui-input-block">
                            <textarea name="reason" lay-verify="required" placeholder="请填写原因" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="save">立即保存</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/attendanceCenter/attendanceMend_add.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>