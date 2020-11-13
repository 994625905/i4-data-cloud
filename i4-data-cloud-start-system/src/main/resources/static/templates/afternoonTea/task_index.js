let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(taskTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 排序 */
    table.on("sort(taskTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(taskTable)",obj=>{
        if(obj.event == "order"){
            Feng.loadWindow(obj.data.title+"：点单区",BasePath+"/afternoonTea/task/orderPage?id="+obj.data.id)
        }
        if(obj.event == "detail"){
            Feng.loadWindow(obj.data.title+"详情信息",BasePath+"/afternoonTea/task/detailPage?id="+obj.data.id)
        }
    })

})
/** 加载表格 */
function loadTable(){
    let tabCols = [[
        {field:"title",title:"标题"},
        {field:"menuCount",title:"对应数量",width:TABLE_COL_WIDTH.one_Cols(4),sort:true},
        {field:"endTimeStr",title:"下单截止时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"realName",title:"创建者"},
        {field: "status",title: "状态",width: TABLE_COL_WIDTH.one_Cols(3),templet(d){
            return "<span class='text-success'>已发布</span>";
        }},
        {fixed:"right",title:"操作",align:"center",width: TABLE_COL_WIDTH.tool(2),toolbar:"#operate"},
    ]]
    tableRender = Initlay.initTable("#taskTable",BasePath+"/afternoonTea/task/loadTable",tabCols,"#toolbar",param)
}
function refresh(){
    Initlay.reloadTable(tableRender,param)
}