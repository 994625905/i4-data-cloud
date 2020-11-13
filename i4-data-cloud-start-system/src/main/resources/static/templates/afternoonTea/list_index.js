let layer,form,table
let tableRender
let param = {}

layui.use(["layer","form","table"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table

    /** 渲染form */
    form.render()

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.name = obj.field.name
        param.typeId = obj.field.typeId
        refresh()

        return false;
    })

    /** table工具栏 */
    table.on("toolbar(listTable)",obj=>{
        if(obj.event == "add"){
            Feng.loadWindow("新增下午茶列表",BasePath+"/afternoonTea/list/addPage")
        }
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table排序 */
    table.on("sort(listTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table操作列 */
    table.on("tool(listTable)",obj=>{
        if(obj.event == "update"){
            Feng.loadWindow("修改"+obj.data.name,BasePath+"/afternoonTea/list/editPage?id="+obj.data.id)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除该下午茶吗？",()=>{
                Request.async(BasePath+"/afternoonTea/list/delete",{id:obj.data.id}).then(res=>{
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
        {field:"image",title:"图片",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"typeName",title:"类型",width:TABLE_COL_WIDTH.one_Cols(4)},
        {field:"price",title:"单价（元）",sort:true,width:TABLE_COL_WIDTH.one_Cols(5)},
        {field:"storeAddress",title:"商家信息"},
        {field:"createTimeStr",title:"上架时间",sort:true,width:TABLE_COL_WIDTH.date},
        {field:"realName",title:"创建用户",width:TABLE_COL_WIDTH.one_Cols(5)},
        {field:"selectCount",title:"点单次数",sort:true,width:TABLE_COL_WIDTH.one_Cols(5)},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender = Initlay.initTable("#listTable",BasePath+"/afternoonTea/list/loadTable",tabCols,"#toolbar",param,null,null,25)
}
/*************************刷新************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}

/*************************加载封面************************/
function showHeadImage(name,id,image){
    Initlay.photo([{
        alt:name,
        pid:id,
        src:image
    }])
}