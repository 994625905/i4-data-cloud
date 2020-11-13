<@override name="body">
<title>爱思数据云平台·消息队列/交换机管理</title>
<body>
<div style="width: 90%;text-align: center;">
    <form class="layui-form">
        <br>
        <div class="layui-form-item">
            <label class="layui-form-label">名称：</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="name" value="${model.name}" placeholder="请输入交换机名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述信息：</label>
            <div class="layui-input-block">
                <input type="text" name="describeInfo" value="${model.describeInfo}" lay-verify="describeInfo" placeholder="请输入描述信息" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">持久化：</label>
            <div class="layui-input-inline">
                <select class="layui-input" name="durable" >
                    <option value="1" <#if model.durable == 1>selected</#if>>是</option>
                    <option value="0" <#if model.durable == 0>selected</#if>>否</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">自动删除：</label>
            <div class="layui-input-inline">
                <select class="layui-input" name="autoDelete" >
                    <option value="1" <#if model.autoDelete == 1>selected</#if>>是</option>
                    <option value="0" <#if model.autoDelete == 0>selected</#if>>否</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">当所有队列在完成使用此exchange时，是否删除。默认否</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">类型：</label>
            <div class="layui-input-inline">
                <select class="layui-input" name="type" >
                    <option value="1" <#if model.type == 1>selected</#if>>直连交换机</option>
                    <option value="2" <#if model.type == 2>selected</#if>>主题交换机</option>
                    <option value="3" <#if model.type == 3>selected</#if>>广播交换机</option>
                    <option value="4" <#if model.type == 4>selected</#if>>延时交换机</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">默认是直连交换机</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">监听处理：</label>
            <div class="layui-input-inline">
                <select class="layui-input" name="isAck" >
                    <option value="1" <#if model.isAck == 1>selected</#if>>自动</option>
                    <option value="0" <#if model.isAck == 0>selected</#if>>手动</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">消息监听的消息处理方式，，默认手动</div>
        </div>
        <input value="${model.id?c}" name="id" type="hidden">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save">立即提交</button>
                <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/rabbitmqMsg/exchangeMsg_edit.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>