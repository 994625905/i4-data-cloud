let layer,form,table,laydate
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-30),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}

layui.use(["layer","form","table","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table
    laydate = layui.laydate

    /** 渲染form */
    form.render()

    /** 渲染日期选择器 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        Initlay.reloadTable(tableRender,param)
    },BaseDate.rangeDate(-30)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.name = obj.field.name
        Initlay.reloadTable(tableRender,param)

        return false;
    })

    /** table工具栏 */
    table.on("toolbar(selectTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table排序 */
    table.on("sort(selectTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

})
/*************************加载表格************************/
function loadTable(){
    let tabCols = [[
        {field:"image",title:"图片",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"dateWeek",title:"所属日期",width:TABLE_COL_WIDTH.one_Cols(7)},
        {field:"createTimeStr",title:"选择时间",sort:true,width:TABLE_COL_WIDTH.date},
        {field:"taskTitle",title:"标题"},
        {field:"realName",title:"用户",width:TABLE_COL_WIDTH.one_Cols(6)}
    ]]
    tableRender = Initlay.initTable("#selectTable",BasePath+"/afternoonTea/select/loadTable",tabCols,null,param,null,null,25)
}
/*************************加载封面************************/
function showHeadImage(name,id,image){
    Initlay.photo([{
        alt:name,
        pid:id,
        src:image
    }])
}