let layer,table
let tableRender

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(mendTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table的操作列 */
    table.on("tool(mendTable)",obj=>{
        if(obj.event == "processLog"){
            Feng.loadWindow(obj.data.title+":查看流转日志",BasePath+"/attendanceCenter/attendanceMend/logPage?processId="+obj.data.processId+
                    "&title="+(obj.data.attendanceWorkDate+"-"+(obj.data.attendanceStage==0?"上班":"下班")),LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
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
/*****************************加载表格****************************/
function loadTable(){
    let tabCols = [[
        {field:"userName",title:"用户名"},
        {field:"attendanceWorkDate",title:"补卡日期"},
        {field:"attendanceStage",title: "时间段",templet(d){
            if(d.attendanceStage == 0){
                return "<span>上班</span>"
            }
            return "<span class='layui-word-aux'>下班</span>"
        }},
        {field:"reason",title:"补卡原因"},
        {field:"createTimeStr",title:"创建时间"},
        {field:"processStatus",title:"审批状态",toolbar:"#processStatusCols",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate"}
    ]]
    tableRender = Initlay.initTable("#mendTable",BasePath+"/attendanceCenter/attendanceMend/loadWhereTable",tabCols,null,param,null,null,30)
}