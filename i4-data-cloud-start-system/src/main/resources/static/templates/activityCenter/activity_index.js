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
            BaseUtil.redirect(BasePath+"/activityCenter/activity/imagePage")
        }
        if(obj.event == "read"){
            Feng.loadWindow(obj.data.title,BasePath+"/activityCenter/activity/readPage")
        }
        if(obj.event == "sign"){
            if(obj.data.userSign == 1){//已经签到，跳转到签到详情页
                Feng.loadWindow(obj.data.title+"签到详情页",BasePath+"/activityCenter/activity/signPage")
            }
            if(obj.data.userSign == 0){ // 需要先签到
                Request.async(BasePath+"/activityCenter/activity/sign",{id:obj.data.id}).then(res=>{

                })
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