<@override name="body">
<title>爱思数据云平台·系统管理/用户管理</title>
<style>
    .selectButton{
        text-align: center;
    }
</style>
<body>
<br>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-card">
            <!--角色信息-->
            <div class="layui-card-header">
                <form class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">角色名称：</label>
                        <div class="layui-input-inline">
                            <input name="name" class="layui-input" lay-verify="name">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">角色描述：</label>
                        <div class="layui-input-inline">
                            <input name="describeInfo" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="save">保存</button>
                            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
                        <div class="layui-form-mid layui-word-aux">权限细化到按钮</div>
                    </div>
                </form>
            </div>
            <!--角色权限-->
            <div class="layui-card-body">
                <#list menuList as menu>
                    <!--一级菜单-->
                    <#if (menu.child?size>0)>
                        <div class="layui-collapse my-1">
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">${menu.name}</h2>
                                <div class="layui-colla-content layui-show">
                                    <div class="layui-row layui-col-space5">
                                        <div class="layui-col-xs2 selectButton">
                                            <div class="layui-btn-group">
                                                <a class="layui-btn layui-btn-sm search-button all" data-id="${menu.id}">全选</a>
                                                <a class="layui-btn layui-btn-sm layui-btn-danger reverse" data-id="${menu.id}">反选</a>
                                                <a class="layui-btn layui-btn-sm layui-btn-primary clear" data-id="${menu.id}">不选</a>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs10">
                                            <!--二级菜单-->
                                            <#list menu.child as child>
                                                <#if (child.child?size>0)>
                                                    <div class="layui-collapse my-1">
                                                        <div class="layui-colla-item">
                                                            <h2 class="layui-colla-title">${child.name}</h2>
                                                            <div class="layui-colla-content layui-show">
                                                                <div class="layui-row">
                                                                    <div class="layui-col-xs2 selectButton">
                                                                        <div class="layui-btn-group">
                                                                            <a class="layui-btn layui-btn-sm search-button all" data-id="${child.id}">全</a>
                                                                            <a class="layui-btn layui-btn-sm layui-btn-danger reverse" data-id="${child.id}">反</a>
                                                                            <a class="layui-btn layui-btn-sm layui-btn-primary clear" data-id="${child.id}">不</a>
                                                                        </div>
                                                                    </div>
                                                                    <div class="layui-col-xs10">
                                                                        <!--二级菜单下的按钮（要求菜单只细化到二级）-->
                                                                        <#list child.child as button>
                                                                            <button class="layui-btn layui-btn-sm layui-btn-primary menu" parent-id="${button.parentId}" data-id="${button.id}">${button.name}</button>
                                                                        </#list>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                <#else>
                                                    <button class="layui-btn layui-btn-sm layui-btn-primary menu" parent-id="${child.parentId}" data-id="${child.id}">${child.name}</button>
                                                </#if>
                                            </#list>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <button class="layui-btn layui-btn-sm layui-btn-primary menu" parent-id="${menu.parentId}" data-id="${menu.id}">${menu.name}</button>
                    </#if>
                </#list>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/roleMsg_add.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>