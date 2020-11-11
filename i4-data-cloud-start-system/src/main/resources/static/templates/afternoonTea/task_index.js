let layer,table,form
let tableRender
let param = {}

layui.use(["layer","table","form"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(taskTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
        if(obj.event == "add"){
            Feng.loadWindow("新增点单任务",BasePath+"/afternoonTea/task/addPage")
        }
    })

    /** 排序 */
    table.on("sort(taskTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(taskTable)",obj=>{
        if(obj.event == "deploy"){
            Feng.confirm("确定部署吗？",()=>{
                Request.async(BasePath+"/afternoonTea/task/deploy",{id:obj.data.id}).then(res=>{
                    Feng.success("发布成功")
                    refresh()
                })
            })
        }
        if(obj.event == "edit"){
            Feng.loadWindow("编辑点单任务："+obj.data.title,BasePath+"/afternoonTea/task/editPage?id="+obj.data.id)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除吗？",()=>{
                Request.async(BasePath+"/afternoonTea/task/delete",{id:obj.data.id}).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
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
        {fixed:"right",title:"操作",align:"center",width: TABLE_COL_WIDTH.tool(3),toolbar:"#operate"},
    ]]
    tableRender = Initlay.initTable("#taskTable",BasePath+"/afternoonTea/task/loadTable",tabCols,"#toolbar",param)
}
function refresh(){
    Initlay.reloadTable(tableRender,param)
}