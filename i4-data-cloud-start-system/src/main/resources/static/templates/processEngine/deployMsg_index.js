let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(deployTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table的排序 */
    table.on("sort(deployTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(deployTable)",obj=>{
        if(obj.event == "delete"){
            Feng.confirm("谨慎哦，如果你不是想级联清空的话！",()=>{
                Request.async(BasePath+"/processEngine/deployMsg/delete",{
                    deploymentId:obj.data.deploymentId
                }).then(res=>{
                    Feng.success("删除成功")
                    Initlay.sortTable(tableRender,obj,param)
                })
            })
        }
        if(obj.event == "image"){
            Feng.loadWindow(obj.data.procdefName+"-》流程图",BasePath+"/processEngine/deployMsg/processImage",LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
    })


})
/*****************************加载表格****************************/
function loadTable(){
    var tabCols = [[
        {field:"procdefId",title:"流程定义ID",sort:true},
        {field:"procdefName",title:"流程定义名称"},
        {field:"procdefKey",title:"流程定义key"},
        {field:"procdefVersion",title:"流程定义版本",sort:true},
        {field:"deploymentId",title:"DeploymentId",sort:true},
        {field:"deploymentCreateTime",title:"Deployment Time",sort:true},
        {field:"resourceName",title:"资源名称"},
        {field:"imgName",title:"图片名称"},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)},
    ]]
    tableRender = Initlay.initTable("#deployTable",BasePath+"/processEngine/deployMsg/loadTable",tabCols,null,param)
}