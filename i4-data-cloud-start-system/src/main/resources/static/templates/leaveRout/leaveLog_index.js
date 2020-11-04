let layer,table,form,laydate
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-60),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}

layui.use(["layer","table","form","laydate"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 渲染日期框 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        refresh()
    },BaseDate.rangeDate(-60)+" - "+BaseDate.rangeDate(0))


    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(applyTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.userId = obj.field.userId
        param.typeId = obj.field.typeId
        refresh()
        return false
    })

    /** table工具栏 */
    table.on("toolbar(leaveTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table排序 */
    table.on("sort(leaveTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(leaveTable)",obj=>{
        if(obj.event == "processLog"){
            Feng.loadWindow(obj.data.title+":查看流转日志",BasePath+"/leaveRout/leaveProcess/logPage?processId="+obj.data.processId+"&title="+obj.data.title,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "processDoing"){
            Feng.loadWindow(obj.data.title+":查看流程图",BasePath+"/processEngine/deployMsg/processTaskImage?procdefId="+obj.data.processDefId+"&processInstanceId="+obj.data.processInstanceId+"&receiveUserId="+
                            obj.data.receiveUserId,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "process"){
            Feng.loadWindow(obj.data.title+":查看流程图",BasePath+"/processEngine/deployMsg/processImage?procdefId="+obj.data.processDefId,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
    })

})
/*****************************加载表格*******************************/
function loadTable(){
    let tabCols = [[
        {field:"title",title:"标题"},
        {field:"typeName",title:"类型",sort:true},
        {field:"reason",title:"原因"},
        {field:"startTimeStr",title:"开始时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"endTimeStr",title:"结束时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"enclosure",title:"附件查看",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            if(d.enclosure){
                return "<label class='layui-btn layui-btn-sm layui-btn-normal' onclick='showEnclosure(\""+d.title+"\",\""+d.enclosure+"\")'>查看附件</label>"
            }
            return "<label class='layui-btn layui-btn-sm layui-btn-disabled'>没有附件</label>"
        }},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"processStatus",title:"审批状态",toolbar:"#processStatusCols",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)},
    ]]
    tableRender = Initlay.initTable("#leaveTable",BasePath+"/leaveRout/leaveLog/loadTable",tabCols,null,param)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/** 查看附件 */
function showEnclosure(name,url){
    BaseUtil.openBlank(url,name)
}