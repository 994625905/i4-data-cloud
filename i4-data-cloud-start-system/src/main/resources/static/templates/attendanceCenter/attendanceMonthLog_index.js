let layer,table,laydate,form
let tableRender
let param = {
    year:BaseDate.currYear()
}

layui.use(["layer","table","laydate","form"],()=>{

    layer = layui.layer
    table = layui.table
    laydate = layui.laydate
    form = layui.form

    /** 渲染form表单 */
    form.render()

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
            refresh()
        }
    })

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.userId = obj.field.userId
        param.month = obj.field.month
        refresh()
        return false
    })

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(monthTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "settleAll"){

        }
    })

    /** table排序 */
    table.on("sort(monthTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(monthTable)",obj=>{
        if(obj.event == "settleOne"){

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
        {field:"workDays",title:"实际工作日数",sort:true},
        {field:"workOverHour",title:"加班时长",sort:true},
        {field:"mendCount",title:"补卡次数",sort:true},
        {field:"lateCount",title:"迟到次数",sort:true},
        {field:"earlyCount",title:"早退次数",sort:true},
        {field:"leaveCount",title:"请假次数",sort:true},
        {field:"leaveHour",title:"累计请假小时",sort:true},
        {field: "updateStatus",title:"数据状态",templet(d){
            if(d.updateStatus == 0){
                return "<span class='text-success'>自动统计</span>"
            }
            return "<span class='text-danger'>人为改动</span>"
        }},
        {fixed:"right",title:"操作",toolbar:"#operate",width: TABLE_COL_WIDTH.tool(3)}
    ]]
    tableRender = Initlay.initTable("#monthTable",BasePath+"/attendanceCenter/attendanceMonthLog/loadTable",tabCols,"#toolbar",param,null,null,30)
}
/**************刷新**************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}