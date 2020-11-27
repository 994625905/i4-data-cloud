let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(yearTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

})
/************************加载表格*************************/
function loadTable(){
    let tabCols = [[
        {field:"userName",title:"用户名"},
        {field:"year",title:"年"},
        {field:"normalDays",title:"正常工作日数"},
        {field:"workDays",title:"实际工作日数"},
        {field:"workOverHour",title:"加班时长"},
        {field:"mendCount",title:"补卡次数"},
        {field:"lateCount",title:"迟到次数"},
        {field:"earlyCount",title:"早退次数"},
        {field:"leaveCount",title:"请假次数"},
        {field:"leaveHour",title:"累计请假小时"}
    ]]
    tableRender = Initlay.initTable("#yearTable",BasePath+"/attendanceCenter/attendanceYear/loadTable",tabCols,null,param)
}