let layer,table,laydate
let tableRender
let param = {
    year:BaseDate.currYear()
}

layui.use(["layer","table","laydate"],()=>{

    layer = layui.layer
    table = layui.table
    laydate = layui.laydate

    /** 渲染日期框 */
    laydate.render({
        elem:"#year",
        type:"year",
        value:BaseDate.currYear(),
        theme:"#007bff",
        done:value=>{
            if(value > BaseDate.currYear()){
                return Feng.tip("年份超出结算记录","#year",2);
            }
            param.year = value
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(monthTable)",obj=>{
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
        {field:"month",title:"月"},
        {field:"normalDays",title:"正常工作日数"},
        {field:"workDays",title:"实际工作日数"},
        {field:"workOverHour",title:"正常加班时长"},
        {field:"holidayOverHour",title:"假期加班时长"},
        {field:"mendCount",title:"补卡次数"},
        {field:"lateCount",title:"迟到次数"},
        {field:"earlyCount",title:"早退次数"},
        {field:"leaveCount",title:"请假次数"},
        {field:"leaveHour",title:"累计请假小时"},
        {field: "updateStatus",title:"数据状态",templet(d){
            if(d.updateStatus == 0){
                return "<span class='text-success'>自动统计</span>"
            }
            return "<span class='text-danger'>人为改动</span>"
        }},
    ]]
    tableRender = Initlay.initTable("#monthTable",BasePath+"/attendanceCenter/attendanceMonth/loadTable",tabCols,null,param)
}