<@override name="body">
<title>爱思数据云平台·系统管理/新增用户</title>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <!--条件过滤框-->
        <div class="layui-col-xs12">
            <form class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登陆名称：</label>
                        <div class="layui-input-block">
                            <input name="loginName" class="layui-input" lay-verify="loginName" placeholder="不包含汉字且控制20位以内">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">初始密码：</label>
                        <div class="layui-input-block">
                            <input name="password" readonly value="${systemConstant.defaultPassword}" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户状态：</label>
                        <div class="layui-input-block">
                            <select name="status">
                                <option value="1">实习</option>
                                <option value="2">试用</option>
                                <option value="3">正式</option>
                                <option value="4">休假</option>
                                <option value="5">离职</option>
                                <option value="6">其他</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="save">提交保存</button>
                        <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/userMsg_add.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>