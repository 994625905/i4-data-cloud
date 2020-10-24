var layer,form,table
var tableRender
var param = {}

layui.use(["layer","form","table"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(roleTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.loadWindow("新增角色（设置权限菜单，权限细化到按钮）",BasePath+"/systemMsg/roleMsg/add")
        }
    })

    /** table操作列 */
    table.on("tool(roleTable)",obj=>{
        if(obj.event == "edit"){
            Feng.loadWindow(obj.data.name+"-->设置权限，权限细化到按钮",BasePath+"/systemMsg/roleMsg/edit?id="+obj.data.id)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除"+obj.data.name+"吗？",()=>{
                Request.async(BasePath+"/systemMsg/roleMsg/deleteById",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
    })

})
/**************************加载表格**************************/
function loadTable(){
    var tabCols = [[
        {field:"name",title:"角色名称"},
        {field:"describeInfo",title:"描述信息"},
        {field:"createTimeStr",title:"创建时间"},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool"}
    ]]
    tableRender = Initlay.initTable("#roleTable",BasePath+"/systemMsg/roleMsg/loadTable",tabCols,"#toolbar",param)
}
/**************************刷新**************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}