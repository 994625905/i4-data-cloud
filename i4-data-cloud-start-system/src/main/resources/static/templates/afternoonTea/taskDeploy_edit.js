let layer,form,laydate
let addMax = 7 - (new Date().getDay() > 5 ? (-1):new Date().getDay())+1;//点单上限
addMax = addMax - menuListLength
let addMenu = menuListLength + 1;
let addDate = menuListLength;
let addTea = menuListLength * 5;//按最大来算，保证下午茶可选项id不冲突

layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 渲染截止时间 */
    laydate.render({
        elem:"#endTime",
        type:"datetime",
        value: BaseDate.timeStampToDate(modelEndTime),
        range: false,
        theme:"#007bff"
    })

    /** 渲染现有元素的事件 */
    for(let i = 0;i<menuListLength;i++){
        let addTemp = i
        /** 渲染日期 */
        laydate.render({
            elem:"#date"+addTemp,
            range: false,
            theme:"#007bff",
            done:value=>{
                $("#week"+addTemp).text(BaseDate.getWeekByDate(value))
            }
        })

        /** 添加下午茶可选项 */
        let addTeaMax = 5 - parseInt($("#teaList"+addTemp).attr("data-size"));//下午茶可选项为5项
        $("#addTea"+addTemp).click(()=>{
            if(addTeaMax > 0){
                Feng.loadWindow("选择下午茶",BasePath+"/afternoonTea/list/selectPage",LAYOUT_SIZE.BASE_HEIGHT(),LAYOUT_SIZE.BASE_WIDTH(),null,()=>{
                    let res = BaseAjax.getData(BasePath+"/afternoonTea/list/getTeaSelectTemp")
                    if(res.code == 200 && res.result){

                        /** 验重操作 */
                        let flag = true
                        $.each($("#teaList"+addTemp).parent(".menu").find("img"),(i,o)=>{
                            if($(o).attr("data-id") == res.result.id){
                                flag = false
                            }
                        })
                        if(flag){
                            $("#teaList"+addTemp).before(template("teaContent",{tea:res.result,addTea:addTea}))

                            /** 绑定删除 */
                            $("#deleteTea"+addTea).click(function(){
                                $(this).parents(".tea").remove()
                                addTeaMax++
                            })

                            addTeaMax--
                            addTea++
                        }else{
                            Feng.msg("该下午茶已经在可选项中")
                        }
                    }else{
                        Feng.msg(res.message)
                    }
                })
            }else{
                Feng.msg("下午茶可选项已达上限")
            }
        })
        /** 删除下午茶可选项 */
        for(let n=0;n<5;n++){
            $("#deleteTea"+addTemp+"_"+n).click(function(){
                $(this).parents(".tea").remove()
                addTeaMax++
            })
        }

        /** 渲染删除 */
        $("#delete"+addTemp).click(function(){
            $(this).parents(".menu").remove()
            addMax++
            addDate--
        })
    }

    /** 新增按钮 */
    $("#addMenu").click(()=>{
        if(addMax < 1){
            return Feng.msg("本周可点单数量已达上限")
        }
        let addTemp = addMenu
        $(".menuList").append(template("menuContent",{index:addTemp}))
        $("#week"+addTemp).text(BaseDate.getWeekByDate(BaseDate.rangeDate(addDate)))

        /** 渲染日期 */
        laydate.render({
            elem:"#date"+addTemp,
            value:BaseDate.rangeDate(addDate),
            range: false,
            theme:"#007bff",
            done:value=>{
                $("#week"+addTemp).text(BaseDate.getWeekByDate(value))
            }
        })

        /** 渲染添加下午茶可选项 */
        let addTeaMax = 5;//下午茶可选项为5项
        $("#addTea"+addTemp).click(()=>{
            if(addTeaMax > 0){
                Feng.loadWindow("选择下午茶",BasePath+"/afternoonTea/list/selectPage",LAYOUT_SIZE.BASE_HEIGHT(),LAYOUT_SIZE.BASE_WIDTH(),null,()=>{
                    let res = BaseAjax.getData(BasePath+"/afternoonTea/list/getTeaSelectTemp");
                    if(res.code == 200 && res.result){

                        /** 验重操作 */
                        let flag = true
                        $.each($("#teaList"+addTemp).parent(".menu").find("img"),(i,o)=>{
                            if($(o).attr("data-id") == res.result.id){
                                flag = false
                            }
                        })
                        if(flag){
                            $("#teaList"+addTemp).before(template("teaContent",{tea:res.result,addTea:addTea}))

                            /** 绑定删除 */
                            $("#deleteTea"+addTea).click(function(){
                                $(this).parents(".tea").remove()
                                addTeaMax++
                            })

                            addTeaMax --
                            addTea++
                        }else{
                            Feng.msg("该下午茶已经在可选项中")
                        }
                    }else{
                        Feng.msg(res.message)
                    }
                })
            }else{
                Feng.msg("下午茶可选项已达上限")
            }
        })

        /** 渲染删除 */
        $("#delete"+addTemp).click(function(){
            $(this).parents(".menu").remove()
            addMax++
            addDate--
        })

        addMax--
        addDate++
        addMenu++
    })

    /** 表单提交 */
    form.on("submit(save)",obj=>{
        let flag = true
        let param = {
            model:{
                id:obj.field.id,
                title:obj.field.title,
                endTime:BaseDate.dateStrToTimeStamp(obj.field.endTime),
                describeInfo:obj.field.describeInfo
            }
        }
        let menuList = []
        $.each($(".menuList .menu"),(i,o)=>{
            menuList.push({
                date:$(o).find("input[name='date']").val(),
                week:BaseDate.getWeekByDate($(o).find("input[name='date']").val()),
                endTime:BaseDate.dateStrToTimeStamp(obj.field.endTime),
                teaIds:function(){

                    let teaList = []
                    $.each($(o).find(".tea"),(n,m)=>{
                        teaList.push($(m).find("img").attr("data-id"))
                    })
                    if(teaList.length == 0){
                        flag = false
                    }
                    return teaList
                }().join(",")
            })
        })
        if(menuList.length == 0){
            flag = false
        }
        param.menuList = menuList

        if(!flag){
            Feng.msg("请保证下午茶可选项的完整性")
            return false
        }
        /** 正式提交 */
        Request.asyncBody(BasePath+"/afternoonTea/taskDeploy/update",param).then(res=>{
            Feng.success("保存成功")
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll()
                parent.refresh()
            },500)
        })

        return false;
    })

})