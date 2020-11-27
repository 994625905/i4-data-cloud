let layer,table,laydate,form
let tableRender
let param = {
    year:BaseDate.currYear(),
}

layui.use(["layer","table","laydate","form"],()=>{

    layer = layui.layer
    table = layui.table
    laydate = layui.laydate
    form = layui.form

    /** 渲染form表单 */
    form.render()

    /** 渲染年份 */
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
            refresh()
        }
    })

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.userId = obj.field.userId
        refresh()
        return false
    })

    /** table工具栏 */
    table.on("toolbar(yearTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table的排序 */
    table.on("sort(yearTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

})
/************************加载表格*************************/
function loadTable(){
    let tabCols = [[
        {field:"userName",title:"用户名"},
        {field:"year",title:"年"},
        {field:"normalDays",title:"正常工作日数"},
        {field:"workDays",title:"实际工作日数",sort:true},
        {field:"workOverHour",title:"加班时长",sort:true},
        {field:"mendCount",title:"补卡次数",sort:true},
        {field:"lateCount",title:"迟到次数",sort:true},
        {field:"earlyCount",title:"早退次数",sort:true},
        {field:"leaveCount",title:"请假次数",sort:true},
        {field:"leaveHour",title:"累计请假小时",sort:true}
    ]]
    tableRender = Initlay.initTable("#yearTable",BasePath+"/attendanceCenter/attendanceYearLog/loadTable",tabCols,null,param)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}