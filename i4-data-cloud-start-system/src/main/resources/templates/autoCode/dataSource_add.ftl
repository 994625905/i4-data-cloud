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
            <span>
                自动代码的生成采用标准的分类存放，且都添加各自继承的基类，各司其职，以此来约束代码的规范性
            </span>
        </div>
        <div class="layui-card-body">
            <form class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">上传封面：</label>
                    <div class="layui-input-inline">
                        <img src="${systemConstant.mysqlImage!}" class="rounded" style="max-width: 200px" id="dataSourceCover">
                    </div>
                    <input type="hidden" value="${systemConstant.mysqlImage!}" name="dataSourceCover" lay-verify="dataSourceCover">
                    <div class="layui-form-mid layui-word-aux">限制宽高：300*300，允许等比例上传</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">数据源名称：</label>
                    <div class="layui-input-block">
                        <input name="datasourceName" lay-verify="datasourceName" placeholder="请输入数据源名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">数据源链接：</label>
                    <div class="layui-input-block">
                        <input name="datasourceUrl" lay-verify="datasourceUrl" placeholder="请输入数据源链接" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">完整的链接，包括后代参数</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">数据库名称：</label>
                    <div class="layui-input-block">
                        <input name="databaseName" lay-verify="databaseName" placeholder="请输入数据库名称" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        实体存放entity，下分为三个子包：dto(数据传递),model（数据持久化）,view（数据接收渲染）；mapper对应指定的xml文件，service针对mapper做相关core操作
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">驱动名称：</label>
                    <div class="layui-input-block">
                        <input name="driverclassName" value="com.mysql.cj.jdbc.Driver" lay-verify="driverclassName" placeholder="请输入有效的驱动名称" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">MySQL驱动推荐使用：com.mysql.cj.jdbc.Driver，或者您也可以改为其他（com.mysql.jdbc.Driver），但目前只提供MySQL的处理逻辑吧。。</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">驱动类型：</label>
                    <div class="layui-input-block">
                        <select class="layui-input" name="driverclassType" >
                            <option value="MySQL" selected>MySQL</option>
                        </select>
                    </div>
                    <div class="layui-form-mid layui-word-aux">目前集成的驱动类型只有MySQL，类似于其他的PostgreSQL，Oracle……只等后续添加。。。</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">授权用户：</label>
                        <div class="layui-input-inline">
                            <input name="authUser" lay-verify="authUser" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">授权密码：</label>
                        <div class="layui-input-inline">
                            <input type="password" name="authPassword" lay-verify="authPassword" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">为保证数据库隐私，授权用户和密码均采用可逆加密的方式隐藏</div>
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" >
                    <legend>以下项皆为默认项，也可在具体操作时填写</legend>
                </fieldset>
                <div class="layui-form-item">
                    <label class="layui-form-label">默认作者：</label>
                    <div class="layui-input-block">
                        <input name="defaultAuthor" placeholder="生成代码的默认作者" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">主要体现在生成代码的注释上，</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">核心包名：</label>
                    <div class="layui-input-block">
                        <input name="defaultPackagePrefix" placeholder="核心业务包名" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">前缀在程序写死为cn.i4.data.cloud，后缀为entity（子包：dto/model/view）/service（子包：impl）/mapper……，还有对应mapper的xml文件，如果参数为shop.order。那么自动代码生成的包名为cn.i4.data.cloud.shop.order.entity</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">存放路径：</label>
                    <div class="layui-input-block">
                        <input name="defaultLocal" placeholder="默认存放的地址" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">摈弃自动下载zip压缩包，采用在指定路径生成的方式存放</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="save">立即提交</button>
                        <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/autoCode/dataSource_add.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>