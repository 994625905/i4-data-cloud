<@override name="body">
<title>爱思数据云平台·请假事务/请假模板</title>
<style>
    .layui-table td{
        line-height: 35px;
        border-color: #0C0C0C;
    }
    .span-time{
        color: white;
        width: 45px;
        border-color: #0C0C0C;
    }
</style>
<body>
<br>
    <div class="layui-fluid">
        <div style="width: 1000px;position:absolute;left: 15%">

            <div class="layui-row layui-col-space10">
                <div class="flex justify-center align-center">
                    <h2>员工请（休）假申请表</h2>
                </div>
                <div class="flex justify-start align-center m-2">
                    <div class="layui-form-item flex-1 mb-0">
                        <label class="layui-form-label">部门：</label>
                        <div class="layui-input-inline border-bottom">
                            <input class="layui-input" style="border-color: white">
                        </div>
                    </div>
                    <div class="layui-form-item flex-1 mb-0">
                        <label class="layui-form-label">姓名：</label>
                        <div class="layui-input-inline border-bottom">
                            <input class="layui-input" style="border-color: white">
                        </div>
                    </div>
                    <div class="layui-form-item flex-1 mb-0">
                        <label class="layui-form-label">职务：</label>
                        <div class="layui-input-inline border-bottom">
                            <input class="layui-input" style="border-color: white">
                        </div>
                    </div>
                </div>

                <div class="layui-col-xs12">
                    <div class="layui-form">
                        <table class="layui-table" id="applyLeave">
                            <colgroup>
                                <col width="250">
                                <col width="250">
                                <col width="250">
                                <col width="250">
                            </colgroup>
                            <tbody>
                            <tr>
                                <td rowspan="2">假别</td>
                                <td colspan="3" rowspan="2">
                                    <div class="layui-form-item" pane="">
                                        <#if typeList??>
                                            <#list typeList as type>
                                                <input type="checkbox" lay-skin="primary" title="${type.name}">
                                            </#list>
                                        </#if>
                                    </div>
                                </td>
                            </tr>
                            <tr></tr>
                            <tr>
                                <td rowspan="2">请假时间</td>
                                <td colspan="3" rowspan="2">
                                    <div class="flex justify-start align-center my-1">
                                        自<div class="border-bottom span-time">.</div>年<div class="border-bottom span-time">.</div>月<div class="border-bottom span-time">.</div>日
                                        <div class="border-bottom span-time">.</div>时<div class="border-bottom span-time">.</div>分至
                                        <div class="border-bottom span-time">.</div>年<div class="border-bottom span-time">.</div>月<div class="border-bottom span-time">.</div>日
                                        <div class="border-bottom span-time">.</div>时<div class="border-bottom span-time">.</div>
                                    </div>
                                    <div class="flex justify-start align-center my-1">
                                        总共时长<div class="border-bottom span-time">.</div>天<div class="border-bottom span-time">.</div>小时
                                    </div>
                                </td>
                            </tr>
                            <tr></tr>
                            <tr>
                                <td style="line-height: 90px">请假事由</td>
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td>工作交接人签字</td>
                                <td></td>
                                <td>直属上司意见</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>行政人事部意见</td>
                                <td></td>
                                <td>公司领导审批</td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveType_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>