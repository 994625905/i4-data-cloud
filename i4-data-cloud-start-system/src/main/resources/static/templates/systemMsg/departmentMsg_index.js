let layer,form,table
let tableRender
let param = {}

layui.use(["layer","form","table"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(departmentTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.infoDetail("新增部门",template("addContent",{}),null,index=>{
                let param = {
                    name:$("#name").val(),
                    describeInfo:$("#describeInfo").val()
                }
                if(BaseUtil.isEmpty(param.name)){
                    return Feng.info("请填写部门名称")
                }
                Request.asyncBody(BasePath+"/systemMsg/departmentMsg/insert",{
                    model:param
                }).then(res=>{
                    layer.close(index)
                    Feng.success("新增成功");
                    refresh()
                })
            },"420px","300px")
        }
    })

    /** table操作列 */
    table.on("tool(departmentTable)",obj=>{
        if(obj.event == "edit"){
            Feng.infoDetail("编辑部门信息",template("editContent",{name:obj.data.name,describeInfo:obj.data.describeInfo}),null,index=>{
                let param = {
                    name:$("#name").val(),
                    describeInfo:$("#describeInfo").val(),
                    id:obj.data.id
                }
                if(BaseUtil.isEmpty(param.name)){
                    return Feng.info("请填写部门名称")
                }
                Request.asyncBody(BasePath+"/systemMsg/departmentMsg/update",{
                    model:param
                }).then(res=>{
                    layer.close(index)
                    Feng.success("修改成功");
                    refresh()
                })
            },"420px","300px")
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除"+obj.data.name+"吗？",()=>{
                Request.async(BasePath+"/systemMsg/departmentMsg/deleteById",{
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
        {field:"name",title:"部门名称"},
        {field:"describeInfo",title:"描述信息"},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool",width: TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender = Initlay.initTable("#departmentTable",BasePath+"/systemMsg/departmentMsg/loadTable",tabCols,"#toolbar",param)
}
/**************************刷新**************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}