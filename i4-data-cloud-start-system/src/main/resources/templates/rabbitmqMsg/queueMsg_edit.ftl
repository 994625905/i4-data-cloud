<@override name="body">
    <title>爱思数据云平台·消息队列/队列管理</title>
    <body>
    <div style="width: 90%;text-align: center;">
        <form class="layui-form">
            <br>
            <div class="layui-form-item">
                <label class="layui-form-label">名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="name" value="${model.name}" placeholder="请输入队列名称" class="layui-input">
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
                <div class="layui-form-mid layui-word-aux">默认队列消息持久化</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">自动删除：</label>
                <div class="layui-input-inline">
                    <select class="layui-input" name="autoDelete" >
                        <option value="1" <#if model.autoDelete == 1>selected</#if>>是</option>
                        <option value="0" <#if model.autoDelete == 0>selected</#if>>否</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">队列没被使用时。默认不删除</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排他性：</label>
                <div class="layui-input-inline">
                    <select class="layui-input" name="exclusive" >
                        <option value="1" <#if model.exclusive == 1>selected</#if>>是</option>
                        <option value="0" <#if model.exclusive == 0>selected</#if>>否</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">队列只在当前connection生效，默认false</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">交换机：</label>
                <div class="layui-input-inline">
                    <select class="layui-input" name="exchangeId" >
                        <option value="">请选择交换机绑定</option>
                        <#list exchangeList as exchange>
                            <option value="${exchange.id?c}" <#if model.exchangeId?? && model.exchangeId == exchange.id>selected</#if> >${exchange.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">为了规范，此处不允许一个队列绑定到多个交换机</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">绑定路由：</label>
                <div class="layui-input-inline">
                    <input name="routingKey" class="layui-input" value="${model.routingKey!}" lay-verify="routingKey">
                </div>
                <div class="layui-form-mid layui-word-aux">非绑定到广播交换机，则必须设置</div>
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
    <script type="text/javascript" src="${StaticServer}/templates/rabbitmqMsg/queueMsg_edit.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>