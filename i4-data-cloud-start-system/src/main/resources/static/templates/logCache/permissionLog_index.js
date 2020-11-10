let layer,table,form,laydate
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-30),"start"),
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

        Initlay.reloadTable(tableRender,param)
    },BaseDate.rangeDate(-30)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.userId = obj.field.userId
        param.requestPath = obj.field.requestPath
        param.type = obj.field.type
        param.startDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[1],"end")

        Initlay.reloadTable(tableRender,param)
        return false
    })

    /** 工具栏 */
    table.on("toolbar(permissionLogTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 排序 */
    table.on("sort(permissionLogTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

})
/** 加载表格 */
function loadTable(){
    var tabCols = [[
        {field:"loginName",title:"拦截用户",width: TABLE_COL_WIDTH.one_Cols(5)},
        {field:"type",title:"拦截类型",sort:true,width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            if(d.type == 0){
                return "<label class='layui-btn layui-btn-sm layui-btn-normal'>打开页面</label>";
            }
            if(d.type == 1){
                return "<label class='layui-btn layui-btn-sm layui-btn-danger'>数据接口</label>";
            }
        }},
        {field:"className",title:"类名称"},
        {field:"methodName",title:"方法名称",width:TABLE_COL_WIDTH.one_Cols(7)},
        {field:"requestPath",title:"请求路径",width: TABLE_COL_WIDTH.one_Cols(11)},
        {field:"permission",title:"权限标识",width: TABLE_COL_WIDTH.one_Cols(11)},
        {field:"createTimeStr",title:"拦截时间",sort:true,width:TABLE_COL_WIDTH.date}
    ]]
    tableRender = Initlay.initTable("#permissionLogTable",BasePath+"/logCache/permissionLog/loadTable",tabCols,null,param)
}
