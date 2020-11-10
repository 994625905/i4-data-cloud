let layer,form,laydate
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-9),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}
layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染表单 */
    form.render()

    /** 渲染时间框 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")

        loadTable()
    },BaseDate.rangeDate(-9)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.actionResult = obj.field.actionResult
        param.actionType = obj.field.actionType
        param.actionContent = obj.field.actionContent
        param.moduleName = obj.field.moduleName
        param.startDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp($("#date").val().split(" - ")[1],"end")

        loadTable()
        return false
    })


})
/** 填充页面 */
function loadTable(){
    Request.async(BasePath+"/logCache/request/loadTable",param).then(res=>{
        var content = template("content",{list:res})
        $("#requestTable").html(content)
    })
}
/** 详情页 */
function loadDetail(date){
    let p = "?date="+date
    if(!BaseUtil.isEmpty(param.actionResult)){
        p += "&actionResult="+param.actionResult
    }
    if(!BaseUtil.isEmpty(param.actionType)){
        p += "&actionType="+param.actionType
    }
    if(!BaseUtil.isEmpty(param.actionContent)){
        p += "&actionContent="+param.actionContent
    }
    if(!BaseUtil.isEmpty(param.moduleName)){
        p += "&moduleName="+param.moduleName
    }
    Feng.loadWindow(date+"：详情页",BasePath+"/logCache/request/detailPage"+p)
}