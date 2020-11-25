let layer,table
let tableRender

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(leaveTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
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
        {field:"title",title:"标题",width: TABLE_COL_WIDTH.one_Cols(12)},
        {field:"typeName",title:"类型",sort:true,width:TABLE_COL_WIDTH.one_Cols(3)},
        {field:"reason",title:"原因"},
        {field:"startTimeStr",title:"开始时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"endTimeStr",title:"结束时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"id",title:"附件查看",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            return "<label class='layui-btn layui-btn-sm layui-btn-normal' onclick='showEnclosure("+d.id+",\""+d.title+"\")'>查看附件</label>"
        }},
        {field:"processStatus",title:"审批状态",toolbar:"#processStatusCols",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)},
    ]]
    tableRender = Initlay.initTable("#leaveTable",BasePath+"/leaveRout/leaveApply/loadWhereTable",tabCols,null,param)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/** 查看附件 */
function showEnclosure(id,title){
    Request.async(BasePath+"/leaveRout/leaveApply/getFileListByLeaveId",{id:id}).then(res=>{
        let content = template("fileList",{list:res})
        Feng.infoDetail("附件列表",content,()=>{

            /** 附件类型替换文本 */
            $.each($(".fileType"),(i,o)=>{
                $(o).text(UploadFile.getTypeText($(o).text()))
            })
        },null,"420px","500px")
    })
}