let layer,table
let tableRender
let param = {
    attendanceStage:attendanceStage,
    workDate:workDate,
    userId:userId
}
layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(recordTable)",obj=>{
        Initlay.reloadTable(tableRender,param)
    })

    /** 提交项 */
    $("#submit").click(()=>{
        let radioArray = table.checkStatus("recordTable")
        if(radioArray.data.length == 0){
            return Feng.msg("请选中将调整的打卡记录")
        }

        /** 提交判断，上班和下班 */
        if(attendanceStage == 0){
            let timeStamp = BaseDate.dateStrToTimeStamp(BaseDate.d8ToD11(workDate)+" 09:00:00");
            if(radioArray[0].createTime > timeStamp){

                Feng.confirm("您调整的考勤已经属于迟到，确定吗？",()=>{
                    submit(radioArray[0].id,-1)
                })
                return;
            }
        }
        if(attendanceStage == 1){
            let timeStamp = BaseDate.dateStrToTimeStamp(BaseDate.d8ToD11(workDate)+" 18:30:00");
            if(radioArray[0].createTime < timeStamp){

                Feng.confirm("您调整的考勤已经属于早退，确定吗？",()=>{
                    submit(radioArray[0].id,1)
                })
                return;
            }
        }
        submit()
    })

})
/******************************加载表格******************************/
function loadTable(){
    let tabCols = [[
        {fixed: "left",type:"radio",title: "单选"},
        {field:"userName",title:"用户名"},
        {field: "createDate",title:"打卡日期"},
        {field: "createTimeStr",title:"打卡时间"}
    ]]
    tableRender = Initlay.initTable("#recordTable",BasePath+"/attendanceCenter/attendanceRecordLog/loadTable",tabCols,null,param,null,null,30)
}
function submit(attendanceId,attendanceStatus){
    Request.asyncBody(BasePath+"/attendanceCenter/attendanceDayLog/update",{
        model:{
            id:id,
            attendanceId:attendanceId,
            updateStatus:1,
            attendanceStatus:attendanceStatus
        }
    }).then(res=>{
        Feng.success("调整成功！")
        BaseUtil.setTimeout(()=>{
            parent.layer.closeAll()
            parent.refresh()
        },800)
    })
}