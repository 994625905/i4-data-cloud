<@override name="body">
<title>爱思数据云平台·认证中心/忘记密码</title>
<style>
    .forget-div{
        width: 400px;
        min-width: 260px;
        position: absolute;
        top: 15%;
        left: 40%;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="forget-div">
        <form class="layui-form p-2" id="form">
            <div class="layui-anim layui-anim-upbit">
                <div class="layui-form-item flex flex-column align-center justify-center">
                    <div class="font-weight-bold font-small">i4-data-cloud<i class="iconfont icon-yun"></i>认证中心</div>
                    <div class="text-danger my-1">密码找回</div>
                </div>

                <div class="layui-form-item" style="padding: 10px">
                    <input type="text" name="loginName" lay-verify="loginName" placeholder="找回密码的账号" autocomplete="off" class="layui-input">
                </div>

                <div class="layui-tab layui-tab-brief text-center" lay-filter="retrieveType">
                    <ul class="layui-tab-title flex align-center justify-center">
                        <li type="1" class="layui-this">手机号验证</li>
                        <li type="2">邮箱验证</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <div class="layui-form-item">
                                <input type="text" name="phone" lay-verify="phone" placeholder="预留的手机号码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-row layui-col-space20">
                                    <div class="layui-col-xs8">
                                        <input type="text" name="phoneCode" lay-verify="phoneCode" placeholder="验证码" class="layui-input" autocomplete="off">
                                        <input type="hidden" name="phoneCodeKey">
                                    </div>
                                    <div class="layui-col-xs4">
                                        <label class="layui-btn layui-btn-primary" id="getPhoneCode">获取验证码</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            <div class="layui-form-item">
                                <input type="text" name="email" lay-verify="email" placeholder="预留的电子邮箱" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-row layui-col-space20">
                                    <div class="layui-col-xs8">
                                        <input type="text" name="emailCode" lay-verify="emailCode" placeholder="验证码" class="layui-input" autocomplete="off">
                                        <input type="hidden" name="emailCodeKey">
                                    </div>
                                    <div class="layui-col-xs4">
                                        <label class="layui-btn layui-btn-primary" id="getEmailCode">获取验证码</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="retrieve">
                        提交找回
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/html" id="changePassword">
    <div class="layui-form-item pt-5">
        <input type="password" name="newPassword" lay-verify="newPassword" placeholder="请设置新密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-item">
        <input type="password" name="surePassword" lay-verify="surePassword" placeholder="确认密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="save">确认修改</button>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/login/forgetPassword.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>