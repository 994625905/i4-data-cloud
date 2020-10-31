let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(modelTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table的排序 */
    table.on("sort(modelTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(modelTable)",obj=>{
        if(obj.event == "edit"){
            window.open(designEdit+obj.data.modelId, "_blank");
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除该流程模板吗",()=>{
                Request.async(BasePath+"/processEngine/modelMsg/deleteById",{
                    modelId:obj.data.modelId
                }).then(res=>{
                    Feng.success("删除成功")
                    Initlay.sortTable(tableRender,obj,param)
                })
            })
        }
        if(obj.event == "deploy"){
            Feng.confirm("确定部署该流程模板吗？",()=>{
                Request.async(BasePath+"/processEngine/modelMsg/deploy",{
                    modelId:obj.data.modelId
                }).then(res=>{
                    Feng.success("部署成功！deploymentId:"+res)
                })
            })
        }
    })


})
/*****************************加载表格****************************/
function loadTable(){
    var tabCols = [[
        {field:"modelId",title:"模版ID",sort:true},
        {field:"modelName",title:"模版名称"},
        {field:"modelKey",title:"模版Key"},
        {field:"version",title:"版本号",sort:true},
        {field:"metaInfo",title:"元数据信息"},
        {field:"createTime",title:"创建时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"lastUpdateTime",title:"最后修改时间",sort:true,width: TABLE_COL_WIDTH.date},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(3)},
    ]]
    tableRender = Initlay.initTable("#modelTable",BasePath+"/processEngine/modelMsg/loadTable",tabCols,"#toolbar",param)
}