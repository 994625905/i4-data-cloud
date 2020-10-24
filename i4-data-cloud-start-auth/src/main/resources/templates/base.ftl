<@extends name="/assign.ftl"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache"  />
    <meta http-equiv="content-type" content="no-cache, must-revalidate" />
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
    <title>认证中心-登录</title>
    <link rel="icon" href="${StaticServer}/images/web/web.ico"/>

    <!--引入jquery-->
    <script type="text/javascript" src="${StaticServer}/resource/plugin/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${StaticServer}/resource/plugin/jquery/jquery.cookie.js"></script>

    <!--引入layui-->
    <link rel="stylesheet" href="${StaticServer}/resource/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${StaticServer}/resource/plugin/layui/layui.js"></script>

    <!--引入JS模板引擎-->
    <script type="text/javascript" src="${StaticServer}/resource/plugin/template/template.js"></script>

    <!--引入asslgn-->
    <script type="text/javascript" src="${StaticServer}/assign.js"></script>

    <!--地址三级联动-->
    <script type="text/javascript" src="${StaticServer}/resource/plugin/map/Area.js"></script>

    <!--引入工具-->
    <script type="text/javascript" src="${StaticServer}/resource/base/BaseUtil.js?v=1.0"></script>
    <script type="text/javascript" src="${StaticServer}/resource/base/Feng.js?v=1.0"></script>
    <script type="text/javascript" src="${StaticServer}/resource/base/Request.js?v=1.0"></script>
    <script type="text/javascript" src="${StaticServer}/resource/base/Initlay.js?v=1.0"></script>
    <script type="text/javascript" src="${StaticServer}/resource/base/UploadFile.js?v=1.0"></script>
    <link rel="stylesheet" href="${StaticServer}/resource/base/free.css">

    <!--引入iconfont资源库-->
    <link rel="stylesheet" href="https://at.alicdn.com/t/font_2121546_p8349ls9bmo.css">

    <@block name="body" >base_body_content</@block>

</html>