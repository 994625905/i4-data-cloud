<@override name="body">
<title>爱思数据云平台·请假事务/请假记录</title>
<body>
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">周报标题：</label>
                        <div class="layui-input-block">
                            <input id="title" name="title" value="<#if userTemplate??>${userTemplate.weekreportTitle!}</#if>" lay-verify="required" placeholder="请输入标题" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">年份：</label>
                            <div class="layui-input-inline">
                                <input id="year" name="year" class="layui-input" lay-verify="required">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">周次：</label>
                            <div class="layui-input-inline">
                                <input id="week" type="number" name="week" class="layui-input" lay-verify="required">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日：</label>
                            <div class="layui-input-inline">
                                <input id="startDate" name="startDate" class="layui-input" lay-verify="required">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日：</label>
                            <div class="layui-input-inline">
                                <input id="endDate" name="endDate" class="layui-input" lay-verify="required">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">编辑器类型</label>
                        <div class="layui-input-block">
                            <input type="radio" name="editor" checked value="markdown" title="markdown编辑器" lay-filter="editor">
                            <input type="radio" name="editor" value="html" title="富文本编辑器" lay-filter="editor">
                            <label class="layui-btn layui-btn-sm" id="importRichText">导入图文</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">内容</label>
                        <div class="layui-input-block" id="content-editor" >
                            <textarea id="editor" placeholder="输入内容" style="display: none;" ></textarea>
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
<script type="text/javascript" src="${StaticServer}/resource/plugin/editormd/editormd.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/nkeditor/libs/JDialog/JDialog.min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/plugin/nkeditor/NKeditor-all-min.js"></script>
<script type="text/javascript" src="${StaticServer}/resource/base/Editor.js?v=1.0"></script>
<script type="text/javascript" src="${StaticServer}/templates/weekReport/weekReportApply_add.js?v=1.4"></script>
</@override>
<@extends name="/base.ftl"/>