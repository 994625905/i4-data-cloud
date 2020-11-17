<@override name="body">
<title>爱思数据云平台·活动中心/活动新增</title>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=${systemConstant.baiduMapApi}"></script>
<script type="text/javascript" src="${StaticServer}/resource/base/BaiduMap.js?v=1.1"></script>
<link rel="stylesheet" href="${StaticServer}/resource/plugin/editormd/css/editormd.css" />
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header">
            <a href="javascript:history.go(-1);parent.refresh()" class="layui-btn layui-btn-normal layui-btn-sm" title="返回"><i class="layui-icon layui-icon-prev"></i></a>
            --备注："提前报名"选项开启后，无论参与否，都需要签到，不参与的在签到处说明原因--
        </div>
        <div class="layui-card-body">
            <form class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">活动标题：</label>
                    <div class="layui-input-block">
                        <input name="title" lay-verify="required" placeholder="请填写活动的标题" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">活动类型：</label>
                        <div class="layui-input-inline">
                            <select name="typeId" lay-verify="required" class="layui-input">
                                <option value="请选择"></option>
                                <#if typeList??>
                                    <#list typeList as type>
                                        <option value="${type.id}">${type.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">负责人：</label>
                        <div class="layui-input-inline">
                            <select name="headUserId" class="layui-input" lay-search="">
                                <option value="">可搜索选择</option>
                                <#if userList??>
                                    <#list userList as u >
                                        <option value="${u.id}">${u.realname}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">开始时间：</label>
                        <div class="layui-input-inline">
                            <input name="startTime" id="startTime" placeholder="活动开始时间" class="layui-input" lay-verify="required">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">结束时间：</label>
                        <div class="layui-input-inline">
                            <input name="endTime" id="endTime" placeholder="活动结束时间" class="layui-input" lay-verify="required">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">提前报名：</label>
                        <div class="layui-input-inline">
                            <input type="checkbox" name="isSign" lay-skin="switch" lay-filter="isSign" lay-text="需要|无需">
                        </div>
                    </div>
                    <div class="layui-inline signStartTime" style="display: none">
                        <label class="layui-form-label">开始报名：</label>
                        <div class="layui-input-inline">
                            <input name="signStartTime" id="signStartTime" class="layui-input" placeholder="提前报名的开始时间">
                        </div>
                    </div>
                    <div class="layui-inline signEndTime" style="display: none">
                        <label class="layui-form-label">截止报名：</label>
                        <div class="layui-input-inline">
                            <input name="signEndTime" id="signEndTime" class="layui-input" placeholder="提前报名的截止时间">
                        </div>
                    </div>
                    <div class="layui-inline limitSign" style="display: none">
                        <label class="layui-form-label">携带限制：</label>
                        <div class="layui-input-inline">
                            <select class="layui-input" name="limitSign">
                                <option value="">携带家属的限制</option>
                                <option value="0">允许</option>
                                <option value="1">禁止</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline trafficType" style="display: none">
                        <label class="layui-form-label">交通方式：</label>
                        <div class="layui-input-inline">
                            <select class="layui-input" name="trafficType">
                                <option value="">选择设置的交通方式</option>
                                <option value="0">自行前往</option>
                                <option value="1">集体乘车（允许自驾）</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">活动封面：</label>
                    <div class="layui-input-block">
                        <img id="coverImage" src="${systemConstant.activityCoverImage!}" title="活动封面" style="max-width: 600px">
                    </div>
                </div>

                <!--百度地图的API接入-->
                <div class="layui-form-item">
                    <label class="layui-form-label">活动地址：</label>
                    <div class="layui-input-block">
                        <input name="address" id="address" class="layui-input" lay-verify="required">
                    </div>
                </div>
                <div id="baiduMapSearchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                <div id="baiduMap" style="width: 90%; height: 400px;left:110px;"></div>

                <div class="layui-form-item">
                    <label class="layui-form-label">编辑器：</label>
                    <div class="layui-input-block">
                        <input type="radio" name="editor" checked value="markdown" title="markdown编辑器" lay-filter="editor">
                        <input type="radio" name="editor" value="html" title="富文本编辑器" lay-filter="editor">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">活动内容：</label>
                    <div class="layui-input-block" id="content-editor" >
                        <textarea id="editor" placeholder="输入内容" style="display: none;" ></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
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
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" id="content-editor" >
                        <div class="layui-upload-list">
                            <table class="layui-table">
                                <thead><tr><th>附件名称</th><th>类型</th><th>大小</th><th>后缀</th><th>操作</th></tr></thead>
                                <tbody id="imageList"></tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item mb-5 pb-5">
                    <div class="layui-input-block">
                        <@permission value="activityCenter:activityDeploy/insert">
                            <button class="layui-btn" lay-submit lay-filter="save">新增活动</button>
                        </@permission>
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
<script type="text/javascript" src="${StaticServer}/templates/activityCenter/activityDeploy_add.js?v=1.3"></script>
</@override>
<@extends name="/base.ftl"/>