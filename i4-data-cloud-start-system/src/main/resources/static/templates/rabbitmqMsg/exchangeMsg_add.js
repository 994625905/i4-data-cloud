let form,layer;//声明组件

layui.use(["form","layer"],function(){

    form = layui.form;
    layer = layui.layer;

    /** 渲染表单 */
    form.render();

    /** 验证项 */
    form.verify({
        name:value=>{
            if(BaseUtil.isEmpty(value)){
                return "交换机名称不为空"
            }
            if(value.length > 50){
                return "交换机名称不宜太长"
            }
        },
        describeInfo:value=>{
            if(BaseUtil.isEmpty(value)){
                return "描述信息不为空"
            }
            if(value.length > 100){
                return "描述信息不宜太长"
            }
        }
    })

    /** 提交项 */
    form.on("submit(save)",function(obj){
        var param = obj.field;
        Request.asyncBody(BasePath+"/rabbitmqMsg/exchangeMsg/insert",{model:param}).then(res=>{
            Feng.success("新增成功")
            BaseUtil.setTimeout(function(){
                parent.refresh()
                parent.layer.closeAll()
            },800)
        })
        return false;
    });

});