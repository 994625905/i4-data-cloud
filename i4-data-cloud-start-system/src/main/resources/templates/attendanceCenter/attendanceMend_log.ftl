<@override name="body">
<title>爱思数据云平台·考勤中心/补卡申请</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                ---补卡申请：${param.title} 的流转日志---
            </div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-xs12">
                        <table class="layui-table" lay-skin="line">
                            <colgroup>
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="100">
                                <col>
                            </colgroup>
                            <thead>
                            <tr>
                                <th>节点名称</th>
                                <th>办理人</th>
                                <th>审批时间</th>
                                <th>审批结果</th>
                                <th>审批意见</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if nodeList??>
                                <#list nodeList as node >
                                    <tr>
                                        <td>${node.nodeName}</td>
                                        <td>${node.userName}</td>
                                        <td>${node.dealTimeStr}</td>
                                        <td>
                                            <#if node.dealType == 0>
                                                <span class="text-danger">拒绝</span>
                                            <#elseif node.dealType == 1>
                                                <span class="text-success">放行</span>
                                            <#elseif node.dealType == 2>
                                                <span class="text-light-muted">驳回</span>
                                            </#if>
                                        </td>
                                        <td>${node.comment}</td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</@override>
<@extends name="/base.ftl"/>