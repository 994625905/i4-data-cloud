<@override name="body">
<title>爱思数据云平台·系统管理/菜单管理</title>
<body>
<div style="width: 90%;text-align: center;">
    <form class="layui-form">
        <br>
        <div class="layui-form-item">
            <label class="layui-form-label">上级节点：</label>
            <div class="layui-input-block">
                <select name="parentId">
                    <option value="0">已是顶级节点</option>
                    <#if pMenuList??>
                        <#list pMenuList as pMenu>
                            <#if pMenu.id == menu.parentId>
                                <option value="${pMenu.id?c}" selected>${pMenu.name}</option>
                            <#else>
                                <option value="${pMenu.id?c}">${pMenu.name}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>

        <input type="hidden" value="${menu.type!}" name="type" class="layui-input"><!--标注是菜单-->

        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称：</label>
            <div class="layui-input-block">
                <input type="text" value="${menu.name!}" name="name" lay-verify="required" placeholder="请输入菜单名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单链接：</label>
            <div class="layui-input-block">
                <input type="text" value="${menu.url!}" name="url" placeholder="请输入菜单链接" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限标识：</label>
            <div class="layui-input-block">
                <input type="text" value="${menu.permission!}" name="permission" placeholder="shiro控制权限" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单排序：</label>
            <div class="layui-input-block">
                <input type="number" value="${menu.sort!}" name="sort" lay-verify="required" placeholder="默认自增升序" class="layui-input">
            </div>
        </div>
        <#if menu.parentId == 0>
            <div class="layui-form-item">
                <label class="layui-form-label">图标选择：</label>
                <div class="layui-input-block">
                    <input type="text" value="${menu.icon!}" name="icon" placeholder="阿里妈妈图标库" class="layui-input">
                </div>
            </div>
        </#if>
        <input type="hidden" name="id" value="${menu.id?c}">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save">立即提交</button>
                <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/systemMsg/menuMsg_edit.js?v=1.0"></script>
</@override>
<@extends name="/base.ftl"/>