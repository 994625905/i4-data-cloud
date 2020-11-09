<@override name="body">
<title>爱思数据云平台·代码生成/数据源配置</title>
<style>
    .layui-form-label{
        width: 95px;
    }
    .layui-input-block,.layui-form-mid{
        margin-left: 125px;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header">
            <a href="javascript:history.go(-1)" class="layui-btn layui-btn-sm" title="返回"><i class="layui-icon layui-icon-prev"></i></a>
            --如下所示，可以先选择一张数据表做个测试，成功后到指定的路径中查看--
        </div>
        <div class="layui-card-body">
            <div class="layui-row">
                <div class="layui-col-sm12 p-2">
                    <div id="tables" class="mb-1 ml-15"></div>
                </div>
                <div class="layui-form-mid layui-word-aux">如果获取数据表的结果集失败，且autocode服务运行正常，可以尝试一下替换驱动试试</div>
                <hr>
                <div class="layui-col-sm12">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">默认作者：</label>
                            <div class="layui-input-block">
                                <input name="defaultAuthor" lay-verify="required" value="${dataSource.defaultAuthor}" placeholder="生成代码的默认作者" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">主要体现在生成代码的注释上，</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">核心包名：</label>
                            <div class="layui-input-block">
                                <input name="defaultPackagePrefix" lay-verify="defaultPackagePrefix" value="${dataSource.defaultPackagePrefix}" placeholder="核心业务包名" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">前缀在程序写死为cn.i4.data.cloud，后缀为entity（子包：dto/model/view）/service（子包：impl）/mapper……，还有对应mapper的xml文件，如果参数为shop.order。那么自动代码生成的包名为cn.i4.data.cloud.shop.order.entity</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">存放路径：</label>
                            <div class="layui-input-block">
                                <input name="defaultLocal" lay-verify="required" value="${dataSource.defaultLocal}" placeholder="默认存放的地址" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">摈弃自动下载zip压缩包，采用在指定路径生成的方式存放</div>
                        </div>
                        <input type="hidden" name="id" value="${dataSource.id}">
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="create">一键生成</button>
                                <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="processCreate">
                                    <div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var driverClass = "${dataSource.driverclassName}"
    var dataSourceUrl = "${dataSource.datasourceUrl}"
    var user = "${dataSource.authUser}"
    var password = "${dataSource.authPassword}"
</script>
<script type="text/javascript" src="${StaticServer}/templates/autoCode/dataSource_read.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>