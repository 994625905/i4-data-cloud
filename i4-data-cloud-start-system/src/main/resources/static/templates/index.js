var layer

layui.use(["layer"],()=>{

    layer = layui.layer

})
/*******************注销登录*******************/
function layout(){
    BaseUtil.redirect(logout+"?authorization="+BaseCookie.getStr("authorization")+"&redirect="+redirect)
}