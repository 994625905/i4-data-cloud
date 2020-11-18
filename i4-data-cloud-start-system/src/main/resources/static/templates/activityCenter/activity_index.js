let layer,form,table,laydate
let tableRender
let param = {}

layui.use(["layer","form","table","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 渲染日期 */
    laydate.render({
        elem:"#year",
        type:"year",
        range: false,
        theme:"#000000"
    })

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.year = obj.field.year
        param.typeId = obj.field.typeId
        Initlay.reloadTable(tableRender,param)
        return false
    })

    /** table的工具栏 */
    table.on("toolbar(activityTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table的操作列 */
    table.on("tool(activityTable)",obj=>{
        if(obj.event == "image"){
            Feng.loadWindow(obj.data.title+"--》照片墙",BasePath+"/activityCenter/activity/imagePage?id="+obj.data.id)
        }
        if(obj.event == "read"){
            Feng.loadWindow(obj.data.title,BasePath+"/activityCenter/activity/readPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
        if(obj.event == "sign"){
            if(obj.data.userSign == 1){//已经签到，跳转到签到详情页
                Feng.loadWindow(obj.data.title+"签到详情页",BasePath+"/activityCenter/activity/signPage?id="+obj.data.id)
            }
            if(obj.data.userSign == 0){ // 需要先签到

                /** 签到截止时间的判断 */
                if(obj.data.signStartTime > BaseDate.currTime()){
                    return Feng.msg("签到时间还未到")
                }
                if(obj.data.signEndTime < BaseDate.currTime()){
                    return Feng.msg("签到时间已结束")
                }

                let signContent = template("signContent",{limitSign:obj.data.limitSign,trafficType:obj.data.trafficType})
                Feng.infoDetail("填写签到选项",signContent,()=>{
                    form.render()
                },index=>{
                    let p = {
                        signUserCount:$("#signUserCount").val(),
                        signTraffic:$("#signTraffic")?$("#signTraffic").val():null,
                        signDescribeInfo:$("#signDescribeInfo").val(),
                        activityId:obj.data.id
                    }
                    /** 提交项的判断 */
                    if(BaseUtil.isEmpty(p.signUserCount) || p.signUserCount < 0){
                        return Feng.error("签到人数为必填项")
                    }
                    if(obj.data.limitSign == 1 && p.signUserCount > 1){
                        return Feng.error("不允许携带家属")
                    }
                    if(obj.data.trafficType == 1 && BaseUtil.isEmpty(p.signTraffic) && p.signUserCount > 0){
                        return Feng.error("交通方式为必填项")
                    }
                    if(p.signUserCount > 1 && BaseUtil.isEmpty(p.signDescribeInfo)){
                        return Feng.error("请注明携带成员身份")
                    }
                    if(p.signUserCount == 0 && BaseUtil.isEmpty(p.signDescribeInfo)){
                        return Feng.error("请注明不参与的原因")
                    }
                    Request.asyncBody(BasePath+"/activityCenter/activity/sign",{
                        model:p,
                        signStartTime:obj.data.signStartTime,
                        signEndTime:obj.data.signEndTime
                    }).then(res=>{
                        Feng.close(index)
                        Feng.success("签到成功")
                        refresh()
                    })
                },LAYOUT_SIZE.SM_WIDTH(),LAYOUT_SIZE.SM_HEIGHT())
            }
        }
    })

})
/*********************加载表格*********************/
function loadTable(){
    let tabCols = [[
        {field:"coverImage",title:"活动封面",templet:"#imageUrl"},
        {field:"headUserName",title:"负责人",width:TABLE_COL_WIDTH.one_Cols(5)},
        {field:"typeName",title: "活动类型",width:TABLE_COL_WIDTH.one_Cols(5)},
        {field:"startTimeStr",title:"开始时间",width:TABLE_COL_WIDTH.date},
        {field:"endTimeStr",title:"结束时间",width:TABLE_COL_WIDTH.date},
        {field:"signStartTimeStr",title:"开始报名",width:TABLE_COL_WIDTH.date,templet(d){
            if(d.isSign == 1){
                return d.signStartTimeStr
            }
            return "<span class='layui-word-aux'>无需报名</span>"
        }},
        {field:"signEndTimeStr",title:"截止报名",width:TABLE_COL_WIDTH.date,templet(d){
            if(d.isSign == 1){
                return d.signEndTimeStr
            }
            return "<span class='layui-word-aux'>无需报名</span>"
        }},
        {field:"id",title:"照片墙",align:"center",templet:"#imageTool",width:TABLE_COL_WIDTH.tool(1)},
        {field:"status",title:"活动状态",align:"center",templet:"#statusTool",width:TABLE_COL_WIDTH.one_Cols(4)},
        {fixed:"right",title:"操作",toolbar:"#operate",width: TABLE_COL_WIDTH.tool(2)},
    ]]
    tableRender = Initlay.initTable("#activityTable",BasePath+"/activityCenter/activity/loadTable",tabCols,"#toolbar",param)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/*************************加载封面************************/
function showHeadImage(name,id,image){
    Initlay.photo([{
        alt:name,
        pid:id,
        src:image
    }])
}