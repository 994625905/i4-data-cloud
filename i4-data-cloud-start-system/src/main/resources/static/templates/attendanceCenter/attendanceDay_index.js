let layer,table,laydate
let tableRender
let param = {
    startDate:BaseUtil.replaceAll(BaseDate.rangeDate(-30),"-",""),
    endDate:BaseUtil.replaceAll(BaseDate.rangeDate(0),"-","")
}

layui.use(["layer","table","laydate"],()=>{

    layer = layui.layer
    table = layui.table
    laydate = layui.laydate

    /** 渲染时间 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseUtil.replaceAll(value.split(" - ")[0],"-","")
        param.endDate = BaseUtil.replaceAll(value.split(" - ")[1],"-","")
        Initlay.reloadTable(tableRender,param)
    },BaseDate.rangeDate(-30)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 排序 */
    table.on("sort(dayTable)",obj=>{
        debugger
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table工具栏 */
    table.on("toolbar(dayTable)",obj=>{
        Initlay.reloadTable(tableRender,param)
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
    ]]
    tableRender = Initlay.initTable("#dayTable",BasePath+"/attendanceCenter/attendanceDay/loadTable",tabCols,null,param,null,null,30)
}
/******************************查看补卡记录******************************/
function attendMendDetail(workDate,attendMendId){
    Feng.loadWindow(workDate+"：补卡记录",BasePath+"/attendanceCenter/attendanceMend/where?id="+attendMendId)
}
/******************************查看请假记录******************************/
function leaveDetail(workDate,leaveId){
    Feng.loadWindow(workDate+"：请假记录",BasePath+"/leaveRout/leaveApply/where?id="+leaveId)
}
