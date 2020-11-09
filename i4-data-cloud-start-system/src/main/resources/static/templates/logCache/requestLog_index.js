let layer,table,form,laydate
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-1),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}
layui.use(["layer","table","form","laydate"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form
    laydate = layui.laydate

    /** 渲染表单 */
    form.render()

    /** 渲染时间框 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")

    },BaseDate.rangeDate(-1)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.render("submit(requestLogTable)",obj=>{
        param.actionResult = obj.field.actionResult
        param.actionType = obj.field.actionType
        param.actionContent = obj.field.actionContent
        param.moduleName = obj.field.moduleName
        param.userId = obj.field.userId
        param.startDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[1],"end")

        Initlay.reloadTable(tableRender,param)
        return false
    })

    /** 工具栏 */
    table.on("toolbar(requestLogTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 排序 */
    table.on("sort(requestLogTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })


})
/** 加载表格 */
function loadTable(){
    var tabCols = [[
        {field:"loginName",title:"操作用户",width:TABLE_COL_WIDTH.one_Cols(4)},
        {field:"requestIp",title:"请求IP",width:TABLE_COL_WIDTH.one_Cols(4)},

        {field:"moduleName",title:"模块"},
        {field:"actionContent",title:"内容"},
        {field:"actionMethod",title:"方法",width:TABLE_COL_WIDTH.one_Cols(4)},
        {field:"actionName",title:"类型",sort:true,width:TABLE_COL_WIDTH.one_Cols(2)},
        {field:"actionException",title:"异常"},
        {field:"actionTime",title:"耗时",sort:true,width:TABLE_COL_WIDTH.one_Cols(2)},
        {field:"action_result",title:"结果",sort:true,width:TABLE_COL_WIDTH.one_Cols(2)},
        {field:"week",title:"周",width:TABLE_COL_WIDTH.one_Cols(2)},
        {field:"createTimeStr",title:"请求时间",sort:true,width:TABLE_COL_WIDTH.date}
    ]]
    tableRender = Initlay.initTable("#requestLogTable",BasePath+"/logCache/requestLog/loadTable",tabCols,null,param)
}
