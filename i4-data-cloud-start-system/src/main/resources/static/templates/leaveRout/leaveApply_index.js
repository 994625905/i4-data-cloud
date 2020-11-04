let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(applyTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table的操作列 */
    table.on("tool(applyTable)",obj=>{
        if(obj.event == "apply"){
            Feng.loadWindow(obj.title+":发送申请",BasePath+"/leaveRout/leaveApply/applyPage?id="+obj.data.id,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "edit"){
            Feng.loadWindow("修改请假申请",BasePath+"/leaveRout/leaveApply/editPage?id="+obj.data.id)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除吗？",()=>{
                Request.async(BasePath+"/leaveRout/leaveApply/delete",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
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
        {field:"typeName",title:"类型"},
        {field:"reason",title:"原因"},
        {field:"startTimeStr",title:"开始时间",width:TABLE_COL_WIDTH.date},
        {field:"endTimeStr",title:"结束时间",width:TABLE_COL_WIDTH.date},
        {field:"enclosure",title:"附件查看",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            if(d.enclosure){
                return "<label class='layui-btn layui-btn-sm layui-btn-normal' onclick='showEnclosure(\""+d.title+"\",\""+d.enclosure+"\")'>查看附件</label>"
            }
            return "<label class='layui-btn layui-btn-sm layui-btn-disabled'>没有附件</label>"
        }},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date},
        {field:"processStatus",title:"审批状态",toolbar:"#processStatusCols",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(3)},
    ]]
    tableRender = Initlay.initTable("#applyTable",BasePath+"/leaveRout/leaveApply/loadTable",tabCols,null,param)
}
/** 新增 */
function addLeave(){
    Feng.loadWindow("新增请假申请",BasePath+"/leaveRout/leaveApply/addPage")
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/** 查看附件 */
function showEnclosure(name,url){
    BaseUtil.openBlank(url,name)
}