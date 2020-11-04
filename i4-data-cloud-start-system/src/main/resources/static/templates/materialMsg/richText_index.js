let layer,form,table,laydate
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-60),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end")
}

layui.use(["layer","form","table","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate
    table = layui.table

    /** 初始化日期 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        refresh()
    },BaseDate.rangeDate(-60)+" - "+BaseDate.rangeDate(0))

    /** table工具栏 */
    table.on("toolbar(richTextTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.loadWindow(obj.data.title,BasePath+"/materialMsg/richText/addPage")
        }
    })

    /** 排序 */
    table.on("sort(richTextTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** 操作列 */
    table.on("tool(richTextTable)",obj=>{
        if(obj.event == "read"){
            Feng.loadWindow(obj.data.title,BasePath+"/materialMsg/richText/readPage?id="+obj.data.id)
        }
        if(obj.event == "edit"){
            Feng.loadWindow(obj.data.title,BasePath+"/materialMsg/richText/editPage?id="+obj.data.id)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除吗？",()=>{
                Request.async(BasePath+"/materialMsg/richText/delete",{id:obj.data.id}).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
    })

})
/** 加载表格 */
function loadTable(){
    var tabCols = [[
        {field:"cover",title:"封面"},
        {field:"title",title:"标题"},
        {field:"wordNumber",title:"字数",sort:true,width: TABLE_COL_WIDTH.one_Cols(3)},
        {field:"realName",title:"作者"},
        {field:"createTimeStr",title:"创建时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"updateTimeStr",title:"修改时间",sort:true,width: TABLE_COL_WIDTH.date},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(3)}
    ]]
    tableRender = Initlay.initTable("#richTextTable",BasePath+"/materialMsg/richText/loadTable",tabCols,"#toolbar",param)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}