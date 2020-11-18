let element,layer,table,form
let tableRender
let param = {}

layui.use(["element","layer","table","form"],()=>{

    element = layui.element
    layer = layui.layer
    table = layui.table
    form = layui.form

    /** 渲染表单 */
    form.render()

    /** 初始化表格 */
    loadTable()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.signCount = obj.field.signCount
        param.userId = obj.field.userId
        param.signTraffic = obj.field.signTraffic
        refresh()

        return false
    })

    /** 表格的工具栏 */
    table.on("toolbar(signTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** 表格的操作列，有权限者可操作 */
    table.on("tool(signTable)",obj=>{
        if(obj.event == "edit"){
            let content = template("signContent",{data:obj.data})
            Feng.infoDetail("编辑签到-->此处权限为活动负责人所有",content,()=>{
                form.render()
            },index=>{
                let p = {
                    signUserCount:$("#signUserCount").val(),
                    signTraffic:$("#signTraffic")?$("#signTraffic").val():null,
                    signDescribeInfo:$("#signDescribeInfo").val(),
                    activityId:obj.data.activityId,
                    signUserId:obj.data.signUserId,
                    id:obj.data.id
                }
                /** 提交项的判断 */
                if(BaseUtil.isEmpty(p.signUserCount) || p.signUserCount < 0){
                    return Feng.error("签到人数为必填项")
                }
                if(limitSign == 1 && p.signUserCount > 1){
                    return Feng.error("不允许携带家属")
                }
                if(trafficType == 1 && BaseUtil.isEmpty(p.signTraffic) && p.signUserCount > 0){
                    return Feng.error("交通方式为必填项")
                }
                if(p.signUserCount > 1 && BaseUtil.isEmpty(p.signDescribeInfo)){
                    return Feng.error("请注明携带成员身份")
                }
                if(p.signUserCount == 0 && BaseUtil.isEmpty(p.signDescribeInfo)){
                    return Feng.error("请注明不参与的原因")
                }
                Request.asyncBody(BasePath+"/activityCenter/activity/updateSign",{model:p}).then(res=>{
                    Feng.close(index)
                    Feng.success("修改成功")
                    refresh()
                })
            },LAYOUT_SIZE.SM_WIDTH(),LAYOUT_SIZE.SM_HEIGHT())
        }
    })

})
/*********************加载表格*********************/
function loadTable(){
    let tabCols = [[
        {field: "signRealName",title:"签到人员"},
        {field: "signUserCount",title: "签到数量"},
        {field: "signDescribeInfo",title: "描述信息"},
        {field: "signTraffic",title: "交通方式",hide:trafficType == 1},
        {field: "signTimeStr",title: "签到时间",sort:true},
        {fixed:"right",title:"操作",toolbar:"#operate",width: TABLE_COL_WIDTH.tool(1)},
    ]]
    tableRender = Initlay.initTable("#signTable",BasePath+"/activityCenter/activity/loadSignTable",tabCols,null,param,null,null,50)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}
/** 未签到的人数 */
function noSignCount(activityId){
    Request.async(BasePath+"/activityCenter/activity/noSignList",{activityId:activityId}).then(res=>{
        let content = template("noSignUser",{list:res})
        Feng.infoDetail("未签到人数列表",content,null,null,"420px","500px")
    })
}