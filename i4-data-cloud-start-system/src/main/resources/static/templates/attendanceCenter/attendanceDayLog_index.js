let layer,table,laydate,form
let tableRender
let param = {
    startDate:BaseUtil.replaceAll(BaseDate.rangeDate(-30),"-",""),
    endDate:BaseUtil.replaceAll(BaseDate.rangeDate(0),"-","")
}

layui.use(["layer","table","laydate","form"],()=>{

    layer = layui.layer
    table = layui.table
    laydate = layui.laydate
    form = layui.form

    /** 渲染form表单 */
    form.render()

    /** 渲染时间 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseUtil.replaceAll(value.split(" - ")[0],"-","")
        param.endDate = BaseUtil.replaceAll(value.split(" - ")[1],"-","")
        Initlay.reloadTable(tableRender,param)
    },BaseDate.rangeDate(-30)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 查询按钮的点击事件 */
    form.on("submit(search)",obj=>{
        param.userId = obj.field.userId
        param.attendanceStage = obj.field.attendanceStage
        param.attendanceStatus = obj.field.attendanceStatus
        param.workDateType = obj.field.workDateType
        refresh()
        return false
    })

    /** 排序 */
    table.on("sort(dayLogTable)",obj=>{
        refresh()
    })

    /** table工具栏 */
    table.on("toolbar(dayLogTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table的操作列 */
    table.on("tool(dayLogTable)",obj=>{
        if(obj.event == "change"){
            Feng.loadWindow(obj.data.workDate+"：打卡调整--》"+(obj.data.attendanceStage==0?"上班":"下班"),
                    BasePath+"/attendanceCenter/attendanceDayLog/updatePage?id="+obj.data.id+"&workDate="+obj.data.workDate+"&userId="+obj.data.userId+"&attendanceStage="+obj.data.attendanceStage)
        }
        if(obj.event == "cancel"){
            Feng.confirm("该天的考勤数据将作废，确定取消核算吗？",()=>{
                Request.async(BasePath+"/attendanceCenter/attendanceDayLog/changeStatus",{
                    model:{
                        id:obj.data.id,
                        settleStatus:0,
                        updateStatus:1
                    }
                }).then(res=>{
                    Feng.success("取消成功！")
                    refresh()
                })
            })
        }
        if(obj.event == "resume"){
            Feng.confirm("恢复该天的考勤数据，确定吗？",()=>{
                Request.async(BasePath+"/attendanceCenter/attendanceDayLog/changeStatus",{
                    model:{
                        id:obj.data.id,
                        settleStatus:1,
                        updateStatus:1
                    }
                }).then(res=>{
                    Feng.success("恢复成功！")
                    refresh()
                })
            })
        }
    })
})
/******************************加载表格******************************/
function loadTable(){
    let tabCols = [[
        {field:"userName",title:"用户名"},
        {field: "workDate",title:"日期",sort:true},
        {field: "workDateType",title:"日期类型",templet(d) {
            if(d.workDateType == 0){
                return "<span class='text-success'>正常工作日</span>"
            }
            if(d.workDateType == 1){
                return "<span class='text-warning'>周末</span>"
            }
            if(d.workDateType == 2){
                return "<span class='text-danger'>节假日</span>"
            }
        }},
        {field:"attendanceStage",title: "时间段",templet(d){
            if(d.attendanceStage == 0){
                return "<span>上班</span>"
            }
            return "<span class='layui-word-aux'>下班</span>"
        }},
        {field: "attendanceTimeStr",title:"打卡时间"},
        {field: "attendanceStatus",title:"打卡状态",templet(d){
            if(d.attendanceStatus == -2){
                return "<span class='text-danger'>未打卡</span>"
            }
            if(d.attendanceStatus == -1){
                return "<span class='text-warning'>迟到</span>"
            }
            if(d.attendanceStatus == 0){
                return "<span class='text-success'>正常</span>"
            }
            if(d.attendanceStatus == -2){
                return "<span class='text-secondary'>早退</span>"
            }
        }},
        {field: "workHour",title:"工作时长（h）"},
        {field: "workHourOver",title:"加班时长（h）"},
        {field: "updateStatus",title:"数据状态",templet(d){
            if(d.updateStatus == 0){
                return "<span class='text-success'>自动统计</span>"
            }
            return "<span class='text-danger'>人为改动</span>"
        }},
        {field: "attendMendId",title:"补卡记录",templet(d){
            if(d.attendMendId){
                return "<label class='layui-btn layui-btn-sm' onclick='attendMendDetail("+d.workDate+","+d.attendMendId+")'>查看</label>";
            }
            return "<label class='layui-btn layui-btn-disabled layui-btn-sm'>暂无</label>";
        }},
        {field: "leaveId",title:"请假记录",templet(d){
            if(d.leaveId){
                return "<label class='layui-btn layui-btn-sm' onclick='leaveDetail("+d.workDate+","+d.leaveId+")'>查看</label>";
            }
            return "<label class='layui-btn layui-btn-disabled layui-btn-sm'>暂无</label>";
        }},
        {fixed:"right",title:"操作",toolbar:"#operate",width: TABLE_COL_WIDTH.tool(3)},
    ]]
    tableRender = Initlay.initTable("#dayLogTable",BasePath+"/attendanceCenter/attendanceDayLog/loadTable",tabCols,null,param,null,null,30)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/******************************查看补卡记录******************************/
function attendMendDetail(workDate,attendMendId){

}
/******************************查看请假记录******************************/
function leaveDetail(workDate,leaveId){
    Feng.loadWindow(workDate+"：请假记录",BasePath+"/leaveRout/leaveLog/where?id="+leaveId)
}
