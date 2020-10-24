var form,layer;//声明组件

layui.use(["form","layer"],function(){

    form = layui.form;
    layer = layui.layer;

    /** 渲染表单 */
    form.render();

    /** 提交项 */
    form.on("submit(save)",function(obj){
        var param = obj.field;
        Request.asyncBody(BasePath+"/systemMsg/menuMsg/save",{model:param}).then(res=>{
            parent.refresh()
            parent.layer.closeAll()
        })
        return false;
    });

});