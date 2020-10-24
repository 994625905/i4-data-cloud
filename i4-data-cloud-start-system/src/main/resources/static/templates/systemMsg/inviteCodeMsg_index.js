var layer,table
var tableRender
var param = {

}
layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** 体验按钮 */
    $(".experience").click(()=>{
        Feng.loadWindow("新增邀请码",BasePath+"/systemMsg/inviteCode/addPage")
    })

    /** table工具栏 */
    table.on("toolbar(inviteCodeTable)",obj=>{
        if(obj.event == "add"){
            Feng.loadWindow("新增邀请码",BasePath+"/systemMsg/inviteCode/addPage")
        }
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table排序 */
    table.on("sort(inviteCodeTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table操作列 */
    table.on("tool(inviteCodeTable)",obj=>{
        if(obj.event == "detail"){
            Request.async(BasePath+"/systemMsg/inviteCode/createQRCode",{
                code:obj.data.code,
                roleNames:obj.data.roleNames
            }).then(res=>{
                var contentInfo = template("showQRCode",{
                    status:obj.data.overTime > new Date().getTime()/1000,
                    userStatus:obj.data.userStatus,
                    name:obj.data.name,
                    code:obj.data.code,
                    encode:res,
                    roleNames:obj.data.roleNames
                })
                Feng.infoDetail(obj.data.name+"二维码卡片",contentInfo,null,null,LAYOUT_SIZE.SM_WIDTH(),LAYOUT_SIZE.SM_HEIGHT())
            })
        }
        if(obj.event == "delete"){
            Feng.confirm("确认删除吗",()=>{
                Request.async(BasePath+"/systemMsg/inviteCode/deleteById",{id:obj.data.id,code:obj.data.code}).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
    })

})
/****************************加载表格*****************************/
function loadTable(){
    var tabCols = [[
        {field:"name",title:"名称"},
        {field:"code",title:"邀请码"},
        {field:"userStatus",title:"邀请用户状态",templet:"#statusTool",sort:true},
        {field:"createUserName",title:"创建用户"},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date,sort:true},
        {field:"effectTime",title:"有效时间",width: TABLE_COL_WIDTH.date,templet(d){
            return BaseDate.getTimeStr(d.effectTime)
        }},
        {field:"overTimeStr",title:"当前状态",sort:true,templet(d){
            return template("status",{status:d.overTime > new Date().getTime()/1000})
        }},
        {fixed:"right",title:"操作项",toolbar:"#tool"}
    ]]
    tableRender = Initlay.initTable("#inviteCodeTable",BasePath+"/systemMsg/inviteCode/loadTable",tabCols,"#toolbar",param)
}
/****************************刷新*****************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}