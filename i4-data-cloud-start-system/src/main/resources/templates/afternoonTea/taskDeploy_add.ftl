<@override name="body">
<title>爱思数据云平台·下午茶/点单任务新增</title>
<body>
<br>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">标题：</label>
                        <div class="layui-input-block">
                            <input name="title" lay-verify="required" placeholder="请输入名称" class="layui-input">
                        </div>
                    </div>
                    <div class="menuList"></div>
                    <div class="layui-form-item">
                        <div class="layui-input-block align-center">
                            <label class="layui-btn layui-btn-sm" id="addMenu"><i class="layui-icon layui-icon-add-circle"></i>新增</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">截止点单：</label>
                            <div class="layui-input-inline">
                                <input id="endTime" name="endTime" lay-verify="required" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">描述信息：</label>
                        <div class="layui-input-block">
                            <textarea name="describeInfo"  placeholder="非必填项" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <@permission value="afternoonTea:taskDeploy/insert">
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
<script type="text/html" id="menuContent">
    <div class="layui-form-item menu">
        <div class="layui-inline">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-inline">
                <input name="date" id="date{{index}}" lay-verify="required" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux week" id="week{{index}}"></div>
        </div>
        <div class="layui-inline" id="teaList{{index}}">
            <div class="layui-input-inline">
                <a title="添加下午茶可选项"><i class="iconfont icon-xinzeng text-primary" id="addTea{{index}}"></i></a>
                <a title="删除"><i class="iconfont icon-shanchu1 text-danger ml-1" id="delete{{index}}"></i></a>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="teaContent">
    <div class="layui-inline tea border border-light-secondary" style="line-height: 15px">
        <div class="flex flex-column align-center justify-center position-relative">
            <a class="position-absolute" style="top: -10px;right:-7px" title="删除" id="deleteTea{{addTea}}"><i class="iconfont icon-shanchu text-danger"></i></a>
            <img class="rounded" src="{{tea.image}}" style="max-width: 45px" title="{{tea.name}}" data-id="{{tea.id}}">
            <span class="text-danger font-weight-bold">￥：{{tea.price}}</span>
            <span class="font-xs">{{tea.name}}</span>
        </div>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/afternoonTea/taskDeploy_add.js?v=1.6"></script>
</@override>
<@extends name="/base.ftl"/>