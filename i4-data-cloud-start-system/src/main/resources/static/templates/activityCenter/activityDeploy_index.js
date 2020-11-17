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
        if(obj.event == "add"){
            BaseUtil.redirect(BasePath+"/activityCenter/activityDeploy/addPage")
        }
    })

    /** table的操作列 */
    table.on("tool(activityTable)",obj=>{
        if(obj.event == "deploy"){
            Feng.confirm("确定发布吗？开启活动的可见性",()=>{
                Request.asyncBody(BasePath+"/activityCenter/activityDeploy/deploy",{
                    model:{
                        id:obj.data.id,status:1
                    }
                }).then(res=>{
                    Feng.success("发布成功")
                    refresh()
                })
            })
        }
        if(obj.event == "closeDeploy"){
            Feng.confirm("确定取消发布吗?关闭活动的可见性！",()=>{
                Request.asyncBody(BasePath+"/activityCenter/activityDeploy/deploy",{
                    model:{
                        id:obj.data.id,status:0
                    }
                }).then(res=>{
                    Feng.success("取消发布成功")
                    refresh()
                })
            })
        }
        if(obj.event == "edit"){
            BaseUtil.redirect(BasePath+"/activityCenter/activityDeploy/editPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
        if(obj.event == "delete"){
            Feng.confirm("会级联删除所有相关内容，确定吗？",()=>{
                Request.async(BasePath+"/activityCenter/activityDeploy/delete",{id:obj.data.id,mongoId:obj.data.mongoId}).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
    })

})
/*********************加载表格*********************/
function loadTable(){
    let tabCols = [[
        {field:"coverImage",title:"活动封面",templet:"#imageUrl"},
        {field:"headUserName",title:"负责人",width:TABLE_COL_WIDTH.one_Cols(5)},
        {field:"typeName",title: "活动类型",width:TABLE_COL_WIDTH.one_Cols(4)},
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
        {fixed:"right",title:"操作",toolbar:"#operate",width: TABLE_COL_WIDTH.tool(3)},
    ]]
    tableRender = Initlay.initTable("#activityTable",BasePath+"/activityCenter/activityDeploy/loadTable",tabCols,"#toolbar",param)
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