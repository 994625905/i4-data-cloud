let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(typeTable)",obj=>{
        if(obj.event == "add"){
            Feng.input("新增类型",value=>{
                if(BaseUtil.isEmpty(value)){
                    return Feng.msg("类型名称不能为空")
                }
                let p = {name:value}
                Request.asyncBody(BasePath+"/afternoonTea/type/insert",{model:p}).then(res=>{
                    Feng.success("新增成功")
                    refresh()
                })
            })
        }
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table操作列 */
    table.on("tool(typeTable)",obj=>{
        if(obj.event == "update"){

            Feng.input("新增编辑类型",value=>{
                if(BaseUtil.isEmpty(value)){
                    return Feng.msg("类型名称不能为空")
                }
                let p = {name:value,id:obj.data.id}
                Request.asyncBody(BasePath+"/afternoonTea/type/update",{model:p}).then(res=>{
                    Feng.success("编辑成功")
                    refresh()
                })
            },obj.data.name)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除该下午茶吗？",()=>{
                Request.async(BasePath+"/afternoonTea/type/delete",{id:obj.data.id}).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
    })

})
/*************************加载表格************************/
function loadTable(){
    let tabCols = [[
        {field:"name",title:"名称"},
        {field:"realName",title:"创建者"},
        {field:"createTimeStr",title:"创建时间"},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender = Initlay.initTable("#typeTable",BasePath+"/afternoonTea/type/loadTable",tabCols,"#toolbar",param,null,null,25)
}
/*************************刷新************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}