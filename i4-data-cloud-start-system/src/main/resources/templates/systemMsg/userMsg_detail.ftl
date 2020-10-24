<@override name="body">
<title>爱思数据云平台·系统管理/用户详情</title>
<style>
    .layui-form-label{
        width: 140px;
    }
    .layui-form-item{
        margin-bottom:0;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <!--条件过滤框-->
        <div class="layui-col-xs12">
            <form class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户头像：</label>
                        <div class="layui-input-inline">
                            <img src="${detail.headimage}" style="max-width: 100px">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登陆名称：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.loginname}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">真实姓名：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.realname}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">性别：</label>
                        <div class="layui-input-inline">
                            <div class="pl-1 flex align-center justify-start" style="line-height: 1.3;height: 38px">
                                <#if detail.status == 1>
                                    <label class="layui-btn layui-btn-normal layui-btn-xs" title="男"><i class="iconfont icon-nansheng"></i></label>
                                <#elseif detail.status == 2>
                                    <label class="layui-btn layui-btn-danger layui-btn-xs" title="女"><i class="iconfont icon-nvsheng"></i></label>
                                <#elseif detail.status == 3>
                                    <label class="layui-btn layui-btn-disabled layui-btn-xs" title="未知"><i class="iconfont icon-weizhizhuangtai"></i></label>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">生日：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.birthday}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">联系电话：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.phone}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">电子邮箱：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.email}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">状态：</label>
                        <div class="layui-input-inline">
                            <div class="pl-1 flex align-center justify-start" style="line-height: 1.3;height: 38px">
                                <#if detail.status == 1>
                                    <label class="layui-btn layui-btn-normal layui-btn-xs">实习</label>
                                <#elseif detail.status == 2>
                                    <label class="layui-btn layui-btn-warm layui-btn-xs" lay-event="change">试用</label>
                                <#elseif detail.status == 3>
                                    <label class="layui-btn layui-btn-xs" lay-event="change">正式</label>
                                <#elseif detail.status == 4>
                                    <label class="layui-btn layui-btn-primary layui-btn-xs" lay-event="change">休假</label>
                                <#elseif detail.status == 5>
                                    <label class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="change">离职</label>
                                <#elseif detail.status == 6>
                                    <label class="layui-btn layui-btn-danger layui-btn-xs" lay-event="change">其他</label>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">个性签名：</label>
                        <div class="layui-input-inline" style="width: 700px">
                            <input class="layui-input text-muted border-white" value="${detail.signature}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">地址信息：</label>
                        <div class="layui-input-inline" style="width: 700px">
                            <input class="layui-input text-muted border-white" value="${detail.country}，${detail.province}，${detail.city}，${detail.area}，${detail.detailAddress}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">创建时间：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.createTimeStr}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">最后修改时间：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input text-muted border-white" value="${detail.updateTimeStrInfo}" readonly>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户角色：</label>
                        <div class="layui-input-inline">
                            <div class="pl-1 flex align-center justify-start" style="line-height: 1.3;height: 38px">
                                <#if roleList??>
                                    <#list roleList as role>
                                        <label class="layui-btn search-button layui-btn-xs" title="角色名称">${role.name}</label>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</@override>
<@extends name="/base.ftl"/>