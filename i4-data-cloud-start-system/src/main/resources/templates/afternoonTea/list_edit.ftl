<@override name="body">
<title>爱思数据云平台·下午茶/列表修改</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">名称：</label>
                        <div class="layui-input-block">
                            <input name="name" value="${model.name!}" lay-verify="required" placeholder="请输入名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">图片：</label>
                            <div class="layui-input-inline">
                                <img src="${model.image}" title="下午茶图片" style="max-width: 150px" id="uploadImage">
                            </div>
                            <div class="layui-form-mid layui-word-aux">限制条件，宽高300*300，或者等比例上传，规格小于1024KB</div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">单价：</label>
                            <div class="layui-input-inline">
                                <input type="number" name="price" value="${model.price!}" lay-verify="price" class="layui-input" placeholder="请输入单价（元）">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商家信息：</label>
                        <div class="layui-input-block">
                            <input name="storeAddress" value="${model.storeAddress!}" lay-verify="required" class="layui-input" placeholder="简介或者门店名称/地址，都行">
                        </div>
                    </div>
                    <input type="hidden" class="layui-input" name="id" value="${model.id?c}">
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <@permission value="afternoonTea:list/update">
                                <button class="layui-btn" lay-submit lay-filter="save">立即保存</button>
                            </@permission>
                            <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/list_edit.js?v=1.1"></script>
</@override>
<@extends name="/base.ftl"/>