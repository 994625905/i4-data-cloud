let layer,table,laydate,form
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-30),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}

layui.use(["layer","table","laydate","form"],()=>{

    layer = layui.layer
    table = layui.table
    laydate = layui.laydate
    form = layui.form

    /** 渲染日期组件 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        Initlay.reloadTable(tableRender,param)
    },BaseDate.rangeDate(-30)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 工具栏 */
    table.on("toolbar(codeLogTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 排序 */
    table.on("sort(codeLogTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** 查询详情页 */
    table.on("tool(codeLogTable)",obj=>{
        if(obj.event == "detail"){
            Feng.loadWindow(obj.dataSourceName+"->本次生成表格",BasePath+"/autoCode/codeLog/detailPage?id="+obj.data.id)
        }
    })

})
/*****************************加载表格****************************/
function loadTable(){
    var tabCols = [[
        {field:"datasourceName",title:"数据源"},
        {field:"loginName",title:"操作用户",sort:true},
        {field:"createAuthor",title:"代码作者"},
        {field:"createPackagePrefix",title:"包前缀"},
        {field:"createLocal",title:"指定保存路径"},
        {field:"createResult",title:"创建结果",templet:"#resultCols",sort:true},
        {field:"createTimeStr",title:"创建时间",sort:true},
        {fixed:"right",title:"操作",align:"center",toolbar:"#operate",width: TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender = Initlay.initTable("#codeLogTable",BasePath+"/autoCode/codeLog/loadTable",tabCols,null,param)
}