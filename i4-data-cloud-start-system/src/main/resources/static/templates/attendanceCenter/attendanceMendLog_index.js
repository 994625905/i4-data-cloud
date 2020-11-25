let layer,laydate,form
let tableRender
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-365),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}
layui.use(["layer","form","laydate","table"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate
    table = layui.table

    /** 渲染form表单 */
    form.render()

    /** 渲染日期框 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        refresh()
    },BaseDate.rangeDate(-365)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.userId = obj.field.userId
        param.attendanceStage = obj.field.attendanceStage
        refresh()

        return false
    })

    /** table工具栏 */
    table.on("toolbar(mendTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table的操作列 */
    table.on("tool(mendTable)",obj=>{
        if(obj.event == "processLog"){
            Feng.loadWindow(obj.data.title+":查看流转日志",BasePath+"/attendanceCenter/attendanceMendLog/logPage?processId="+obj.data.processId+
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
    tableRender = Initlay.initTable("#mendTable",BasePath+"/attendanceCenter/attendanceMendLog/loadTable",tabCols,null,param,null,null,30)
}
/*****************************刷新表格****************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}