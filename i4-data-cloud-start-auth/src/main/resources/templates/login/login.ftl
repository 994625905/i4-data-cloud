<@override name="body">
<title>i4-data-cloud认证中心-登陆</title>
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<style>
    #login-div{
        background:rgba(0,0,0,0.2);
        color: white;
        width: 400px;
        min-width: 260px;
        position: absolute;
        top: 25%;
        right: 15%;
    }
    .layui-input{
        background:rgba(0,0,0,0.2);
        color: white;
    }
    #getCode{
        background:rgba(0,0,0,0.2);
        color: white;
    }
    li{
        list-style: decimal;
    }
    #content{
        top: 25%;
        left: 10%;
        width: 600px;
        background-color: rgba(0,0,0,0);
    }
</style>
<body>
    <div class="layui-row" style="height: 100vh;width: 100%;background: url('${systemConstant.authLoginImage}');background-size: cover">
        <textarea id="mdContent" style="display: none">${content!}</textarea>
        <div class="layui-col-xs3 text-white" id="content"></div>

        <div class="bg-white" id="login-div">
            <form class="layui-form p-2" lay-filter="login-form">
                <div class="layui-anim layui-anim-upbit">
                    <div class="layui-form-item flex flex-column align-center justify-center">
                        <div class="font-weight-bold font-small">i4-data-cloud<i class="iconfont icon-yun"></i>认证中心</div>
                        <div class="text-hover-light my-1">让沟通变得高效与简单</div>
                    </div>

                    <div class="layui-tab layui-tab-brief text-center" lay-filter="loginType">
                        <ul class="layui-tab-title flex align-center justify-center" style="border-bottom-width:0;">
                            <li type="1" class="layui-this">账号密码</li>
                            <li type="2">短信验证码</li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <div class="layui-form-item">
                                    <input type="text" name="loginName" lay-verify="loginName" placeholder="登录账号" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-item password-block">
                                    <input type="password" name="password" lay-verify="password" placeholder="密码" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-tab-item">
                                <div class="layui-form-item">
                                    <input type="text" name="phone" lay-verify="phone" placeholder="手机号码" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-row layui-col-space20">
                                        <div class="layui-col-xs8">
                                            <input type="text" name="code" lay-verify="code" placeholder="验证码" class="layui-input" autocomplete="off">
                                            <input type="hidden" name="codeKey">
                                        </div>
                                        <div class="layui-col-xs4">
                                            <label class="layui-btn" id="getCode">获取验证码</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item" style="text-align: right">
                        <a class="text-danger" target="_blank" href="${StaticServer}/auth/page/forgetPassword">忘记密码</a>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="login-submit" id="login">
                            立即登录
                        </button>
                    </div>
                    <div class="layui-form-item flex align-center justify-between" style="text-align: right">
                        <div class="flex-1 flex align-center justify-start">
                            <span class="text-light">其他登录方式：</span>
                            <a class="text-light mx-1" title="微信"><i class="iconfont icon-weixin font-s"></i></a>
                            <a class="text-light mx-1" title="QQ"><i class="iconfont icon-qq font-s"></i></a>
                            <a class="text-light mx-1" title="微博"><i class="iconfont icon-weibo font-s"></i></a>
                        </div>
                        <div class="flex align-center justify-start">
                            <a class="text-success" target="_blank" href="${StaticServer}/auth/page/register">注册账号</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
<script>
    var redirect = "${redirect!''}";
</script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/marked.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/prettify.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/raphael.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/underscore.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/flowchart.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/jquery.flowchart.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/lib/sequence-diagram.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/editormd.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/base/Editor.js?v=1.0"></script>
<script type="text/javascript" src="${StaticServer}/templates/login/login.js"></script>
</@override>
<@extends name="/base.ftl"/>