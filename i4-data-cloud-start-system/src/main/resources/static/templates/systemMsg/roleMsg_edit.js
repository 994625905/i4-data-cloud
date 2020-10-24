var layer,form,element

layui.use(["layer","form","element"],()=>{

    layer = layui.layer
    form = layui.form
    element = layui.element

    /** 渲染表单 */
    form.render()

    /** 渲染已经绑定的权限 */
    var res = BaseAjax.getData(BasePath+"/systemMsg/roleMsg/getMenuButtonByRoleId",{ id:$("input[name='id']").val() })
    if(res.code == 200){
        var temp = $("button.menu")
        $.each(res.result,(i,o)=>{

            $.each(temp,(n,m)=>{
                if(o.id == $(m).attr("data-id")){
                    $(m).toggleClass("layui-btn-danger")
                }
            })
        })
    }else{
        Feng.error(res.message)
    }

    /** 全选按钮（测试是否会多次添加class） */
    $(".all").click(function(){
        $(this).parent(".layui-btn-group").parent(".selectButton").next().find("button").addClass("layui-btn-danger");
    });

    /** 反选 */
    $(".reverse").click(function(){
        $(this).parent(".layui-btn-group").parent(".selectButton").next().find("button").toggleClass("layui-btn-danger");
    });

    /** 不选 */
    $(".clear").click(function(){
        $(this).parent(".layui-btn-group").parent(".selectButton").next().find("button").removeClass("layui-btn-danger");
    });

    /** 具体菜单按钮 */
    $(".menu").click(function(){
        $(this).toggleClass("layui-btn-danger");
    });

    /** 验证项 */
    form.verify({
        name:function(value){
            if(value.length < 1){
                return "角色名称不为空"
            }
            /** 验重 */
            var res = BaseAjax.getData(BasePath+"/systemMsg/roleMsg/checkUnique",{name:value,id:$("input[name='id']").val()})
            if(res.code != 200){
                return res.message
            }
        }
    });

    /** 提交项 */
    form.on("submit(save)",obj=>{
        var menuIdArray = [];
        $.each($("button.layui-btn-danger.menu"),function(i,o){
            menuIdArray.push($(o).attr("parent-id"))
            menuIdArray.push($(o).attr("data-id"))
        })
        menuIdArray = unique1(menuIdArray)
        Request.asyncBody(BasePath+"/systemMsg/roleMsg/save",{
            model:{...obj.field},
            menuIdList:menuIdArray
        }).then(res=>{
            Feng.success("编辑角色成功")
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll();
                parent.refresh()
            },500)
        })
        return false
    })

})
/***************************去重*****************************/
function unique1(arr){
    var hash=[];
    for (var i = 0; i < arr.length; i++) {
        if(hash.indexOf(arr[i])==-1){
            hash.push(arr[i]);
        }
    }
    return hash;
}