let layer,form

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    $(".endTime").text("截止点单："+BaseDate.timeStampToDate(modelEndTime))

    /** 点击事件 */
    $(".tea").click(function(){

        if($(this).find(".icon-xuanze").hasClass("select-show")){

            $(this).find(".icon-xuanze").toggleClass("select-show")
            $(this).toggleClass("bg-layui")
        }else{

            $(this).parents(".menu").find(".tea").removeClass("bg-layui")
            $(this).parents(".menu").find(".icon-xuanze").removeClass("select-show")
            $(this).find(".icon-xuanze").toggleClass("select-show")
            $(this).toggleClass("bg-layui")
        }
    })

    /** 提交事件 */
    form.on("submit(save)",obj=>{
        let selectList = []
        $.each($(".menu"),(i,o)=>{

            let selectTemp = {
                teaTaskId:obj.field.teaTaskId,
                teaMenuId:$(o).find("input[name='teaMenuId']").val()
            }
            if($(o).find(".icon-xuanze.select-show").length > 0){
                selectTemp.teaId = $(o).find(".icon-xuanze.select-show").attr("data-id")
            }
            selectList.push(selectTemp)
        })
        Request.asyncBody(BasePath+"/afternoonTea/task/order",{selectList:selectList}).then(res=>{
            Feng.success("点单成功")
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll()
                parent.refresh()
            },500)
        })
        return false
    })

})