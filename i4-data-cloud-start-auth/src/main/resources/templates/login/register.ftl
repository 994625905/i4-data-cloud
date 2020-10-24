<@override name="body">
<title>爱思数据云平台·认证中心/注册账号</title>
<style>
    .register-div{
        width: 600px;
        min-width: 350px;
        position: absolute;
        top: 15%;
        left: 40%;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="registerSpeed">
        <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
    </div>
    <div class="register-div">
        <form class="layui-form p-2">
            <div class="layui-anim layui-anim-upbit" id="registerForm">
                <div class="layui-form-item flex flex-column align-center justify-center">
                    <div class="font-weight-bold font-small">i4-data-cloud<i class="iconfont icon-yun"></i>认证中心</div>
                    <div class="text-danger my-1">注册账号</div>
                </div>

                <div class="layui-form-item">
                    <input type="text" name="inviteCode" lay-verify="inviteCode" placeholder="内部邀请码，必填项" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="text" name="loginName" lay-verify="loginName" placeholder="登录账号" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="password" name="password" lay-verify="password" placeholder="登录密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="password" name="surePassword" lay-verify="surePassword" placeholder="确认密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="text" name="realName" lay-verify="realName" placeholder="真实姓名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="register">注册账号</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/html" id="setPhone">
    <div class="layui-form-item flex flex-column align-center justify-center">
        <div class="font-weight-bold font-small">i4-data-cloud<i class="iconfont icon-yun"></i>认证中心</div>
        <div class="text-danger my-1">设置手机号码</div>
    </div>
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
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="savePhone">保存手机</button>
    </div>
</script>
<script type="text/html" id="setEmail">
    <div class="layui-form-item flex flex-column align-center justify-center">
        <div class="font-weight-bold font-small">i4-data-cloud<i class="iconfont icon-yun"></i>认证中心</div>
        <div class="text-danger my-1">设置电子邮箱</div>
    </div>
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
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="saveEmail">保存邮箱</button>
    </div>
</script>
<script type="text/html" id="setUserInfo">
    <div class="layui-form-item flex flex-column align-center justify-center">
        <div class="font-weight-bold font-small">i4-data-cloud<i class="iconfont icon-yun"></i>认证中心</div>
        <div class="text-danger my-1">设置个人信息</div>
    </div>
    <div class="layui-form-item">
        <img src="${systemConstant.userHeadImage}" id="headimage" class="rounded" style="max-height: 100px" title="头像">
        <div class="layui-form-mid layui-word-aux">点击上传（限制宽高100*100,允许等比上传）</div>
    </div>
    <div class="layui-form-item">
        <select name="gender">
            <option value="1">男</option>
            <option value="2">女</option>
            <option value="3">未知</option>
        </select>
    </div>
    <div class="layui-form-item">
        <input name="birthday" id="birthday" class="layui-input" placeholder="设置您的生日" lay-verify="required" readonly>
    </div>
    <div class="layui-form-item">
        <input name="signature" class="layui-input" placeholder="来句个性签名吧" lay-verify="required">
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline" style="width: 100px">
            <select name="country" lay-verify="required">
                <option value="中国">中国</option>
            </select>
        </div>
        <div class="layui-input-inline" style="width: 100px">
            <select name="province" lay-verify="required" lay-filter="province"></select>
        </div>
        <div class="layui-input-inline" style="width: 150px">
            <select name="city" lay-verify="required" lay-filter="city"></select>
        </div>
        <div class="layui-input-inline" style="width: 100px">
            <select name="area" lay-verify="required" lay-filter="area"></select>
        </div>
    </div>
    <div class="layui-form-item">
        <input name="detailAddress" class="layui-input" placeholder="完善详细的地址" lay-verify="required">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="saveUserInfo">保存信息</button>
    </div>
</script>
<script type="text/html" id="provinceContent">
    <option value="">请选择省份</option>
    {{each list as item }}
        <option value="{{item.provinceName}}">{{item.provinceName}}</option>
    {{/each}}
</script>
<script type="text/html" id="cityContent">
    <option value="">请选择市</option>
    {{each list as item }}
        <option value="{{item.cityName}}">{{item.cityName}}</option>
    {{/each}}
</script>
<script type="text/html" id="areaContent">
    <option value="">请选择区</option>
    {{each list as item }}
        <option value="{{item.areaName}}">{{item.areaName}}</option>
    {{/each}}
</script>
<script type="text/javascript" src="${StaticServer}/templates/login/register.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>