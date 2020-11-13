let layer,table,element

layui.use(["layer","element","table"],()=>{

    layer = layui.layer
    element = layui.element
    table = layui.table

    /** 初始化表格 */
    for(let i=0;i< menuIdList.length;i++){
        var tableRender = null;
        let param = {
            taskId:taskId,
            menuId:menuIdList[i]
        }
        loadTable(tableRender,"#selectTable"+menuIdList[i],param)
    }

})
/*********************初始化表格***********************/
function loadTable(tableRender,tableDom,param){
    let tabCols = [[
        {field: "selectUserId",title: "用户Id",width:TABLE_COL_WIDTH.one_Cols(4),sort:true},
        {field:"realName",title:"用户实名"},
        {field:"teaName",title:"下午茶名称"}
    ]]
    tableRender = Initlay.initTable(tableDom,BasePath+"/afternoonTea/task/detailTable",tabCols,null,param)
}