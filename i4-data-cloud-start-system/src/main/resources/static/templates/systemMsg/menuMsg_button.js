var layer,table
var tableRender
var param = {
    pid:pid
}
layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 加载表格 */
    loadMenuTable();

    /** 工具栏 */
    table.on("toolbar(menuTable)",obj=>{
        if(obj.event == "add"){
            Feng.loadWindow("新增菜单",BasePath+"/systemMsg/menuMsg/addButton?pid="+param.pid,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH());
        }
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "share"){

        }
    })

    /** 操作列 */
    table.on("tool(menuTable)",obj=>{
        if(obj.event == "freeze"){
            Feng.confirm("确定激活"+obj.data.name+"吗？",()=>{
                Request.async(BasePath+"/systemMsg/menuMsg/changeStatus",{
                    status:1,
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("修改成功");
                    Initlay.reloadTable(tableRender);
                })
            })
        }
        if(obj.event == "active"){
            Feng.confirm("确定冻结"+obj.data.name+"吗？",()=>{
                Request.async(BasePath+"/systemMsg/menuMsg/changeStatus",{
                    status:0,
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("修改成功");
                    Initlay.reloadTable(tableRender);
                })
            })
        }
        if(obj.event == "edit"){
            Feng.loadWindow("编辑"+obj.data.name,BasePath+"/systemMsg/menuMsg/editButton?id="+obj.data.id,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH());
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除"+obj.data.name+"吗？",()=>{
                Request.async(BasePath+"/systemMsg/menuMsg/deleteById",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功");
                    Initlay.reloadTable(tableRender);
                })
            })
        }
    })


})
/*******************加载菜单表格********************/
function loadMenuTable(){
    var tabCols = [[
        {field:"name",title:"按钮"},
        {field:'url',title: '链接'},
        {field:'permission',title: '权限标识'},
        {field:'sort',title: '排序'},
        {field:'createTimeStr',title: '创建时间'},
        {field:'updateTimeStr',title: '修改时间'},
        {field:'status',title: '状态',align:"center",toolbar:"#statusTool"},
        {field:'id',title: '操作',align:"center",toolbar:"#tool"}
    ]]
    tableRender = Initlay.initTable("#menuTable",BasePath+"/systemMsg/menuMsg/loadMenuTable",tabCols,"#toolbar",param)
}
/*******************刷新********************/
function refresh(){
    Initlay.reloadTable(tableRender);
}