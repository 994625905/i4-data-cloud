let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** 排序 */
    table.on("sort(typeTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table工具栏 */
    table.on("toolbar(typeTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
        if(obj.event == "add"){
            Feng.input("新增类型",value=>{
                Request.async(BasePath+"/leaveRout/leaveType/insert",{
                    name:value
                }).then(res=>{
                    Feng.success("新增成功")
                    Initlay.reloadTable(tableRender,param)
                })
            })
        }
    })

    /** table的操作列 */
    table.on("tool(typeTable)",obj=>{
        if(obj.event == "edit"){
            Feng.input("编辑类型",value=>{
                Request.async(BasePath+"/leaveRout/leaveType/update",{
                    name:value,
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("编辑成功")
                    Initlay.reloadTable(tableRender,param)
                })
            },obj.data.name)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除吗？",()=>{
                Request.async(BasePath+"/leaveRout/leaveType/delete",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功")
                    Initlay.reloadTable(tableRender,param)
                })
            })
        }
    })

})
/**************************加载表格***************************/
function loadTable(){
    var tabCols = [[
        {field:"id",title:"ID"},
        {field:"name",title:"名称"},
        {field: "createTimeStr",title: "创建时间",sort:true},
        {fixed:"right",title: "操作",align:"center",toolbar:"#tool"}
    ]]
    tableRender = Initlay.initTable("#typeTable",BasePath+"/leaveRout/leaveType/loadTable",tabCols,"#toolbar",param)
}