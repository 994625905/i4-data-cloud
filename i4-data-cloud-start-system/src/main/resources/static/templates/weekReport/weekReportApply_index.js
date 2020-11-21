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
        if(obj.event == "title"){
            Feng.infoDetail("标题模板设置",template("titleContent",{}),null,Index=>{
                let titleTemplate = $("#titleTemplate").val()
                if(BaseUtil.isEmpty(titleTemplate)){
                    return Feng.msg("请填写标题模板")
                }
                Request.async(BasePath+"/weekReport/weekReportApply/setTitle",{
                    titleTemplate:titleTemplate
                }).then(res=>{
                    Feng.success("成功设置标题模板")
                    Feng.close(Index)
                })
            },"900px","240px")
        }
        if(obj.event == "add"){
            addWeekReport()
        }
    })

    /** table的操作列 */
    table.on("tool(applyTable)",obj=>{
        if(obj.event == "apply"){
            Feng.loadWindow(obj.data.title+":提交",BasePath+"/weekReport/weekReportApply/applyPage?id="+obj.data.id,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "edit"){
            Feng.loadWindow("修改周报："+obj.data.title,BasePath+"/weekReport/weekReportApply/editPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除吗？",()=>{
                Request.async(BasePath+"/weekReport/weekReportApply/delete?id="+obj.data.id+"&mongoId="+obj.data.mongoId,{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
        if(obj.event == "processLog"){
            Feng.loadWindow(obj.data.title+":查看流转日志",BasePath+"/weekReport/weekReportProcess/logPage?processId="+obj.data.processId+"&title="+obj.data.title,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "processDoing"){
            Feng.loadWindow(obj.data.title+":查看流程图",BasePath+"/processEngine/deployMsg/processTaskImage?procdefId="+obj.data.processDefId+"&processInstanceId="+obj.data.processInstanceId+"&receiveUserId="+
                obj.data.receiveUserId,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "process"){
            Feng.loadWindow(obj.data.title+":查看流程图",BasePath+"/processEngine/deployMsg/processImage?procdefId="+obj.data.processDefId,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "read"){
            Feng.loadWindow(obj.data.title+":预览",BasePath+"/weekReport/weekReportApply/readPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
    })

})
/*****************************加载表格*******************************/
function loadTable(){
    let tabCols = [[
        {field:"title",title:"标题"},
        {field:"year",title:"年份",sort:true,width: TABLE_COL_WIDTH.one_Cols(4)},
        {field:"week",title:"周次",sort:true,width: TABLE_COL_WIDTH.one_Cols(5),templet(d) {return "第"+d.week+"周";}},
        {field:"startDate",title:"开始时间",width:TABLE_COL_WIDTH.date_no_time},
        {field:"endDate",title:"结束时间",width:TABLE_COL_WIDTH.date_no_time},
        {field:"id",title:"附件查看",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            return "<label class='layui-btn layui-btn-sm layui-btn-normal' onclick='showEnclosure("+d.id+",\""+d.title+"\")'>查看附件</label>"
        }},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date},
        {field:"processStatus",title:"审批状态",toolbar:"#processStatusCols",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(3)},
    ]]
    tableRender = Initlay.initTable("#applyTable",BasePath+"/weekReport/weekReportApply/loadTable",tabCols,"#toolbar",param)
}
/** 新增 */
function addWeekReport(){
    Feng.loadWindow("新增周报",BasePath+"/weekReport/weekReportApply/addPage")
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/** 查看附件 */
function showEnclosure(id,title){
    Request.async(BasePath+"/weekReport/weekReportApply/getFileListByWeekReportId",{id:id}).then(res=>{
        let content = template("fileList",{list:res})
        Feng.infoDetail("附件列表",content,()=>{

            /** 附件类型替换文本 */
            $.each($(".fileType"),(i,o)=>{
                $(o).text(UploadFile.getTypeText($(o).text()))
            })
        },null,"450px","500px")
    })
}