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
                            <input name="name" value="${role.name!}" class="layui-input" lay-verify="name">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">角色描述：</label>
                        <div class="layui-input-inline">
                            <input name="describeInfo" value="${role.describeInfo!}" class="layui-input">
                        </div>
                    </div>
                    <input name="id" type="hidden" value="${role.id?c}">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="save">保存</button>
                            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                        </div>
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
                                                <a class="layui-btn layui-btn-sm search-button all" data-id="${menu.id?c}">全选</a>
                                                <a class="layui-btn layui-btn-sm layui-btn-danger reverse" data-id="${menu.id?c}">反选</a>
                                                <a class="layui-btn layui-btn-sm layui-btn-primary clear" data-id="${menu.id?c}">不选</a>
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
                                                                            <a class="layui-btn layui-btn-sm search-button all" data-id="${child.id?c}">全</a>
                                                                            <a class="layui-btn layui-btn-sm layui-btn-danger reverse" data-id="${child.id?c}">反</a>
                                                                            <a class="layui-btn layui-btn-sm layui-btn-primary clear" data-id="${child.id?c}">不</a>
                                                                        </div>
                                                                    </div>
                                                                    <div class="layui-col-xs10">
                                                                        <!--二级菜单下的按钮（要求菜单只细化到二级）-->
                                                                        <div class="layui-btn-container">
                                                                            <#list child.child as button>
                                                                                <button class="layui-btn layui-btn-sm layui-btn-primary menu" menu-parent-id="${child.parentId?c}" parent-id="${button.parentId?c}" data-id="${button.id?c}">${button.name}</button>
                                                                            </#list>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                <#else>
                                                    <button class="layui-btn layui-btn-sm layui-btn-primary menu" menu-parent-id="" parent-id="${child.parentId?c}" data-id="${child.id?c}">${child.name}</button>
                                                </#if>
                                            </#list>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <button class="layui-btn layui-btn-sm layui-btn-primary menu" menu-parent-id="" parent-id="${menu.parentId?c}" data-id="${menu.id?c}">${menu.name}</button>
                    </#if>
                </#list>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/roleMsg_edit.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>