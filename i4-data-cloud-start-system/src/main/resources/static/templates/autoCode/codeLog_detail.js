let layer,table,form
let tableRender
let param = {
    autoCodeLogId:autoCodeLogId
}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form

    /** 初始化表格 */
    loadTable()

    /** 工具栏 */
    table.on("toolbar(codeDetailTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 排序 */
    table.on("sort(codeDetailTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

})
/*****************************加载表格****************************/
function loadTable(){
    var tabCols = [[
        {field:"loginName",title:"操作用户"},
        {field:"tableName",title:"表名",sort:true},
        {field:"createResult",title:"创建结果",templet:"#resultCols",sort:true},
        {field:"createTimeStr",title:"创建时间",sort:true}
    ]]
    tableRender = Initlay.initTable("#codeDetailTable",BasePath+"/autoCode/codeLog/loadDetailTable",tabCols,null,param)
}