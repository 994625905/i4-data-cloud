<@override name="body">
<title>爱思数据云平台·文件管理/图片上传批量</title>
<body>
<div style="width: 90%;text-align: center;">
    <form class="layui-form">
        <br>
        <div class="layui-form-item">
            <label class="layui-btn layui-btn-normal" id="uploadList">批量选择：</label>
            <div class="layui-input-block">
                <div class="layui-upload-list">
                    <table class="layui-table">
                        <thead>
                        <tr><th>图片名</th>
                            <th>大小</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr></thead>
                        <tbody id="imageList"></tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">描述信息：</label>
            <div class="layui-input-block">
                <input type="text" name="description" placeholder="批量上传的描述信息" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" id="batchUpload">开始上传</button>
                <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    var type = 1
    var size = <#if param.fileSize??>${param.fileSize?c}<#else>null</#if>
</script>
<script type="text/javascript" src="${StaticServer}/templates/materialMsg/fileFind_uploadBatch.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>