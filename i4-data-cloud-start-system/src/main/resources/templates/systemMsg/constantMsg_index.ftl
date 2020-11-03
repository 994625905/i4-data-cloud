<@override name="body">
<title>爱思数据云平台·系统管理/用户管理</title>
<style>
    .layui-form-label{
        width: 125px;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header">
            ---系统常量设置/MySQL存储，同步到Redis缓存，直接通过key获取---
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="refreshCache">刷新Redis</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-row">
                <div class="layui-col-xs12">

                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>常量设置</legend>
                    </fieldset>
                    <form class="layui-form">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">登录背景</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.authLoginImage!}" title="${systemConstant.authLoginImage_name!}" id="authLoginImage" style="max-width: 300px">
                                    <input name="authLoginImage_id" value="${systemConstant.authLoginImage_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm authLoginImage">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('authLoginImage')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">网站logo</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.webLogoImage!}" title="${systemConstant.webLogoImage_name!}" id="webLogoImage" style="max-width: 200px">
                                    <input name="webLogoImage_id" value="${systemConstant.webLogoImage_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm webLogoImage">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('webLogoImage')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">默认头像</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.userHeadImage!}" title="${systemConstant.userHeadImage_name!}" id="userHeadImage" style="max-width: 100px">
                                    <input name="userHeadImage_id" value="${systemConstant.userHeadImage_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm userHeadImage">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('userHeadImage')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">默认密码</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <input name="defaultPassword" value="${systemConstant.defaultPassword!}" title="${systemConstant.defaultPassword_name!}" autocomplete="off" class="layui-input">
                                    <input name="defaultPassword_id" value="${systemConstant.defaultPassword_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm defaultPassword">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('defaultPassword')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">MySQL图标</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.mysqlImage!}" title="${systemConstant.mysqlImage_name!}" id="mysqlImage" style="max-width: 100px">
                                    <input name="mysqlImage_id" value="${systemConstant.mysqlImage_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm mysqlImage">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('mysqlImage')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">Oracle图标</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.oracleImage!}" title="${systemConstant.oracleImage_name!}" id="oracleImage" style="max-width: 100px">
                                    <input name="oracleImage_id" value="${systemConstant.oracleImage_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm oracleImage">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('oracleImage')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">文件上传的降级</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.errorImage!}" title="${systemConstant.errorImage_name!}" id="errorImage" style="max-width: 200px">
                                    <input name="errorImage_id" value="${systemConstant.errorImage_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm errorImage">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('errorImage')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">文档的封面</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.wordCover!}" title="${systemConstant.wordCover_name!}" id="wordCover" style="max-width: 100px">
                                    <input name="wordCover_id" value="${systemConstant.wordCover_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm wordCover">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('wordCover')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">视频的封面</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.videoCover!}" title="${systemConstant.videoCover_name!}" id="videoCover" style="max-width: 100px">
                                    <input name="videoCover_id" value="${systemConstant.videoCover_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm videoCover">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('videoCover')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">语音的封面</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.audioCover!}" title="${systemConstant.audioCover_name!}" id="audioCover" style="max-width: 100px">
                                    <input name="audioCover_id" value="${systemConstant.audioCover_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm audioCover">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('audioCover')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">其他的封面</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <img src="${systemConstant.otherCover!}" title="${systemConstant.otherCover_name!}" id="otherCover" style="max-width: 100px">
                                    <input name="otherCover_id" value="${systemConstant.otherCover_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm otherCover">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('otherCover')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">百度地图appKey</label>
                                <div class="layui-input-inline" style="width: 400px">
                                    <input name="baiduMapApi" value="${systemConstant.baiduMapApi!}" title="${systemConstant.baiduMapApi_name!}" autocomplete="off" class="layui-input">
                                    <input name="baiduMapApi_id" value="${systemConstant.baiduMapApi_id!}" type="hidden">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm baiduMapApi">提交</button>
                            </div>
                            <div class="layui-inline">
                                <button type="button" class="layui-btn layui-btn-sm constantKey" onclick="findConstantKey('baiduMapApi')">查看key</button>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">


                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript" src="${StaticServer}/templates/systemMsg/constantMsg_index.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>