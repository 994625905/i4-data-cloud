<@override name="body">
<title>爱思数据云平台·系统管理/用户邀请码</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header">
            ---用户注册时使用到的邀请码，同时携带角色权限分配，非内部邀请下，无法注册通过。---
        </div>
        <div class="layui-card-body">
            <div class="layui-row layui-col-space10">
                <!--新增表单-->
                <div class="layui-col-xs3">
                    <form class="layui-form p-1">
                        <div class="layui-anim layui-anim-upbit">
                            <div class="layui-form-item flex flex-column align-center justify-center">
                                <div class="font-weight-bold font-small">添加<i class="iconfont icon-yaoqingma"></i>邀请码</div>
                            </div>

                            <div class="layui-form-item">
                                <input type="text" name="name" lay-verify="required" placeholder="内部邀请码名称" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <select name="userStatus" class="layui-input" >
                                    <option value="1" >实习</option>
                                    <option value="2" >试用</option>
                                    <option value="3" >正式</option>
                                    <option value="4" >休假</option>
                                    <option value="5" >离职</option>
                                    <option value="6" >其他</option>
                                </select>
                            </div>
                            <div class="layui-form-item">
                                <input name="overTime" id="overTime" class="layui-input" lay-verify="required" placeholder="有效结束时间" readonly>
                            </div>
                            <div class="layui-form-item">
                                <select id="departmentId" class="layui-input" >
                                    <#if departmentList??>
                                        <#list departmentList as department>
                                            <option value="${department.id}" >${department.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                                <div class="layui-form-mid layui-word-aux">携带部门</div>
                            </div>
                            <div class="layui-form-item" id="inviteRoleDiv">
                                <div class="layui-form-mid layui-word-aux">携带角色</div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn layui-btn-normal produce" lay-submit lay-filter="produce">生成邀请码</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!--卡片区-->
                <div class="layui-col-xs8">
                    <div class="layui-fluid flex flex-column align-center justify-center m-5">
                        <div class="flex flex-column align-center justify-center" id="content">

                        </div>
                        <div class="layui-form-item">
                            <button class="layui-btn layui-btn-danger refresh" style="display: none">刷新二维码</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!--显示状态-->
<script type="text/html" id="showQRCode">
    <span class="font-small my-1">{{name}}</span>
    <img src="data:image/png;base64,{{encode}}" style="max-width: 300px" title="{{code}} 角色：[{{roleNames}}]">
    <span class="font-weight-bold font">{{code}}</span>
    <span class="text-light-muted my-2 font-smaller">角色：[{{roleNames}}]</span>
</script>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/inviteCodeMsg_add.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>