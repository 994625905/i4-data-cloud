<@override name="body">
<title>爱思大数据</title>

<!--前端框架资源，独立引入，互不影响-->
<link rel="stylesheet" href="${StaticServer}/resource/plugin/model/css/font.css">
<link rel="stylesheet" href="${StaticServer}/resource/plugin/model/css/xadmin.css">
<#--<link rel="stylesheet" href="${StaticServer}/resource/plugin/model/css/theme10.min.css"><!--黑色简约风格&ndash;&gt;-->
<script type="text/javascript" src="${StaticServer}/resource/plugin/model/js/xadmin.js"></script>

<body class="index">
<!-- 顶部开始 -->
<div class="container" style="background-color: white;">
    <div class="logo">
        <a href="./index.html"><img src="${systemConstant.webLogoImage!}" style="max-height: 45px"></a>
    </div>
    <div class="left_open">
        <a style="color: black"><i title="展开左侧栏" class="iconfont icon-caidan text-primary"></i></a>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a onclick="indexOpen('主题配置','${StaticServer}/webTheme/index')" style="color: black;">主题<span class="layui-badge-dot"></span></a>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:" style="font-family: 楷体;color: black;"><img class="rounded" src="${userInfo.headimage!}" alt="logo" style="height: 40px;">${user.realname}</a>
            <dl class="layui-nav-child">
                <dd><a onclick="indexOpen('个人信息','${StaticServer}/myInfo/userInfo/index')">个人信息</a></dd>
                <dd><a onclick="layout()">注销登录</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <span class="layui-badge-dot"></span><a href="javascript:" id="systemReport" style="color: black;">系统公告</a>
            <dl class="layui-nav-child">
                <dd>
                    <a id="loadReport">发布公告</a>
                </dd>
                <dd>
                    <a>在线人数:<font id="onlineCount" color="red">${onlineCount!}</font></a>
                </dd>
            </dl>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <#if menuList ??>
                <#list menuList as menu>
                    <li>
                        <#if menu.url?? && menu.url != "" && menu.url != "#">
                            <a onclick="xadmin.add_tab('${menu.name!}','${StaticServer}/${menu.url!}?menuId=${menu.id?c}&menuName=${menu.name!}')">
                                <#if menu.icon?? && menu.icon != "">
                                    <i class="iconfont ${menu.icon!}"></i>
                                <#else>
                                    <i class="iconfont icon-icon_huabanfuben"></i>
                                </#if>
                                <cite>${menu.name!}</cite>
                            </a>
                        <#else>
                            <a href="javascript:">
                                <#if menu.icon?? && menu.icon != "">
                                    <i class="iconfont ${menu.icon!}"></i>
                                <#else>
                                    <i class="iconfont icon-icon_huabanfuben"></i>
                                </#if>
                                <cite>${menu.name!}</cite>
                                <i class="iconfont nav_right">&#xe697;</i>
                            </a>
                        </#if>
                        <#if (menu.child?size>0)>
                            <ul class="sub-menu">
                                <#list menu.child as child0>
                                    <li>
                                        <#if child0.url?? && child0.url != "" && child0.url != "#">
                                            <a onclick="xadmin.add_tab('${child0.name!}','${StaticServer}/${child0.url!}?menuId=${menu.id?c}&menuName=${menu.name!}')">
                                                <cite>${child0.name!}</cite>
                                            </a>
                                        <#else>
                                            <a href="javascript:">
                                                <cite>${child0.name!}</cite>
                                            </a>
                                        </#if>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
</div>
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>数据首页
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='./welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<div class="i4_div_bottom">
    <div style="text-align: center;">
        <font style="font-family: 楷体">Copyright ©2019 爱思701研发部 All Rights Reserved.</font><br>
        <font style="font-family: 楷体">www.i4.cn 我们一直在努力</font>
    </div>
</div>
<div class="page-content-bg"></div>
</body>
<script type="text/javascript">
    var logout = "${logout!}"
    var redirect = "${redirect!}"
</script>
<script type="text/javascript" src="${StaticServer}/templates/index.js"></script>
</@override>
<@extends name="/base.ftl"/>