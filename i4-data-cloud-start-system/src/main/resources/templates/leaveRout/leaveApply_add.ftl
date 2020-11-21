<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">请假标题：</label>
                        <div class="layui-input-block">
                            <input name="title" lay-verify="required" placeholder="请输入标题" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">请假类型：</label>
                        <div class="layui-input-inline">
                            <select class="layui-input" name="typeId" >
                                <#if typeList??>
                                    <#list typeList  as type>
                                        <option value="${type.id?c}">${type.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">请假原因：</label>
                        <div class="layui-input-block">
                            <textarea name="reason" lay-verify="required" placeholder="请填写原因" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">开始时间：</label>
                            <div class="layui-input-inline">
                                <input name="startTime" id="startTime" lay-verify="required" class="layui-input" >
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束时间：</label>
                            <div class="layui-input-inline">
                                <input name="endTime" id="endTime" lay-verify="required" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">上传附件：</label>
                        <div class="layui-input-inline">
                            <select id="fileType">
                                <option value="1">图片</option>
                                <option value="2">音频</option>
                                <option value="3">视频</option>
                                <option value="4">文档</option>
                                <option value="5">其他</option>
                            </select>
                        </div>
                        <div class="layui-form-mid layui-word-aux">只提供图片批量上传</div>
                        <div class="layui-input-inline">
                            <label class="layui-btn layui-btn-normal" id="uploadEnclosure">上传</label>
                            <div class="layui-form-mid layui-word-aux">非必填项</div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" id="content-editor" >
                            <div class="layui-upload-list">
                                <table class="layui-table">
                                    <thead><tr><th>附件名称</th><th>类型</th><th>大小</th><th>后缀</th><th>操作</th></tr></thead>
                                    <tbody id="enclosureList"></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="save">立即保存</button>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="fileContent">
    <tr class="tr-file" data-url="{{file.url}}" data-name="{{file.name}}" data-type="{{file.type}}" data-size="{{file.size}}" data-suffix="{{file.suffix}}">
        <td class="fileName">{{file.name}}</td>
        <td class="fileType">{{typeText}}</td>
        <td class="fileSize">{{file.size}}KB</td>
        <td class="fileSuffix">{{file.suffix}}</td>
        <td><button class="layui-btn layui-btn-xs layui-btn-danger deleteFile">删除</button></td>
    </tr>
</script>
<script type="text/javascript" src="${StaticServer}/templates/leaveRout/leaveApply_add.js?v=1.4"></script>
</@override>
<@extends name="/base.ftl"/>