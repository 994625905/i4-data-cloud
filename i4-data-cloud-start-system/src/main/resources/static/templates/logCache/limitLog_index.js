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
        param.requestPath = obj.field.requestPath
        param.type = obj.field.type
        param.startDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[1],"end")

        Initlay.reloadTable(tableRender,param)
        return false
    })

    /** 工具栏 */
    table.on("toolbar(limitLogTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 排序 */
    table.on("sort(limitLogTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })


})
/** 加载表格 */
function loadTable(){
    var tabCols = [[
        {field:"name",title:"限流名称"},
        {field:"period",title:"单位（秒）",width:TABLE_COL_WIDTH.one_Cols(4)},
        {field:"count",title:"上限次数",width:TABLE_COL_WIDTH.one_Cols(4)},
        {field:"prefix",title:"限流前缀",width:TABLE_COL_WIDTH.one_Cols(4)},
        {field:"limitKey",title:"限流Key"},
        {field:"requestPath",title:"请求路径"},
        {field:"type",title:"限流类型",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            if(d.type == 0){
                return "<label class='layui-btn layui-btn-sm layui-btn-normal'>IP限制</label>";
            }
            if(d.type == 1){
                return "<label class='layui-btn layui-btn-sm layui-btn-danger'>用户限制</label>";
            }
            if(d.type == 2){
                return "<label class='layui-btn layui-btn-sm'>普通限制</label>";
            }
        }},
        {field:"typeContent",title:"限制对象"},
        {field:"createTimeStr",title:"限制时间",sort:true,width:TABLE_COL_WIDTH.date}
    ]]
    tableRender = Initlay.initTable("#limitLogTable",BasePath+"/logCache/limitLog/loadTable",tabCols,null,param)
}
