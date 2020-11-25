let layer,form,laydate
let addIndex = 0

layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染form */
    form.render()

    /** 新增法定节假日 */
    $("#addHoliday").click(()=>{
        $(".addCalendar").before(template("calendarContent",{index:addIndex,type:2}))
        initDate(addIndex)
        renderDelete(addIndex)
        addIndex++
    })

    /** 新增法定工作日 */
    $("#addWordDay").click(()=>{
        $(".addCalendar").before(template("calendarContent",{index:addIndex,type:3}))
        initDate(addIndex)
        renderDelete(addIndex)
        addIndex++
    })

    /** 提交按钮 */
    form.on("submit(save)",obj=>{
        let list = []
        $.each($(".calendar"),(i,o)=>{
            list.push({
                date:BaseUtil.replaceAll($(o).find(".calendarDate").val(),"-",""),
                year:year,
                month:$(o).find(".calendarMonth").val(),
                week:$(o).find(".calendarWeek").val(),
                type:$(o).find(".calendarType").val(),
                holidayName:function(){
                    if($(o).find(".calendarHolidayName")){
                        return $(o).find(".calendarHolidayName").val()
                    }
                    return;
                }()
            })
        })

        /** 参数校验 */
        let flag = list.length > 0,msg = "请新增日历设置"
        $.each(list,(i,o)=>{
            if(o.type == 2 && BaseUtil.isEmpty(o.holidayName)){
                flag = false
                msg = "假日名称为必填项"
                return;
            }
        })
        if(!flag){
            Feng.error(msg)
            return false;
        }
        /** 提交 */
        Request.asyncBody(BasePath+"/attendanceCenter/attendanceCalendar/insert",{
            calendarList:list
        }).then(res=>{
            Feng.success("新增成功")
            BaseUtil.setTimeout(()=>{
                parent.layer.closeAll()
                parent.refresh()
            },800)
        })
        return false;
    })

})
/**********************渲染日期*********************/
function initDate(index){

    Initlay.loadLayDate("#date"+index,value=>{

        /** 依次设置月，周 */
        $("#month"+index).val(value.split("-")[1])
        $("#week"+index).val(BaseDate.getWeekByDate(value))

    },"date",year+"-01-01",year+"-01-01",year+"-12-31")
}
/**********************绑定删除*********************/
function renderDelete(index){

    $("#delete"+index).click(function(){
        $(this).parents(".calendar").remove()
    })
}