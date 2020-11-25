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
    table.on("sort(recordTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table工具栏 */
    table.on("toolbar(recordTable)",obj=>{
        Initlay.reloadTable(tableRender,param)
    })

})
/******************************加载表格******************************/
function loadTable(){
    let tabCols = [[
        {field:"userName",title:"用户名"},
        {field: "createDate",title:"打卡日期",sort:true},
        {field: "createTimeStr",title:"打卡时间",sort:true}
    ]]
    tableRender = Initlay.initTable("#recordTable",BasePath+"/attendanceCenter/attendanceRecord/loadTable",tabCols,null,param,null,null,30)
}