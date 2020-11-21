let layer,table,form,laydate
let tableRender
let param = {
    year:BaseDate.currYear()
}

layui.use(["layer","table","form","laydate"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form
    laydate = layui.laydate

    /** 渲染年 */
    laydate.render({
        elem:"#year",
        type:"year",
        value:BaseDate.currYear(),
        range: false,
        theme:"#000000"
    })

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.year = obj.field.year
        param.month = obj.field.month
        param.userId = obj.field.userId
        refresh()
        return false
    })

    /** table的工具栏 */
    table.on("toolbar(weekReportTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table的操作列 */
    table.on("tool(weekReportTable)",obj=>{
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
            Feng.loadWindow(obj.data.title+":预览",BasePath+"/weekReport/weekReportLog/readPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
    })

})
/*****************************加载表格*******************************/
function loadTable(){
    let tabCols = [[
        {field:"title",title:"标题"},
        {field:"year",title:"年份",sort:true},
        {field:"week",title:"周次",sort:true,templet(d) {return "第"+d.week+"周";}},
        {field:"startDate",title:"开始时间",width:TABLE_COL_WIDTH.date},
        {field:"endDate",title:"结束时间",width:TABLE_COL_WIDTH.date},
        {field:"id",title:"附件查看",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            return "<label class='layui-btn layui-btn-sm layui-btn-normal' onclick='showEnclosure("+d.id+",\""+d.title+"\")'>查看附件</label>"
        }},
        {field:"processStatus",title:"审批状态",toolbar:"#processStatusCols",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(4)},
    ]]
    tableRender = Initlay.initTable("#weekReportTable",BasePath+"/weekReport/weekReportLog/loadTable",tabCols,"#toolbar",param)
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