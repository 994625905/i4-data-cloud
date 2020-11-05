<@override name="body">
<title>爱思数据云平台·关于平台/认证首页信息</title>
<body>
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<br>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">
            <div class="layui-col-xs12">

                <form class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-input-block" id="content-editor"></div>
                    </div>
                    <textarea id="mdContent" placeholder="输入内容" style="display: none;" >${content!}</textarea>
                    <input type="hidden" value="${systemConstant.authInfo!}" name="mongoId">
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="save">保存刷新</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/editormd.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/base/Editor.js?v=1.0"></script>
<script type="text/javascript" src="${StaticServer}/templates/about/authInfo_index.js?v=1.5"></script>
</@override>
<@extends name="/base.ftl"/>