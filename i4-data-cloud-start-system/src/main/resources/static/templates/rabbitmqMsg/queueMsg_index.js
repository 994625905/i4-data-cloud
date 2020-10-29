let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /**redis刷新按钮*/
    $(".refreshCache").click(()=>{
        Request.async(BasePath+"/rabbitmqMsg/queueMsg/refreshCache").then(res=>{
            Feng.success("刷新成功")
        })
    })

    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(queueTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.loadWindow("新增队列",BasePath+"/rabbitmqMsg/queueMsg/addPage",LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
    })

    /** table的操作列 */
    table.on("tool(queueTable)",obj=>{
        if(obj.event == "edit"){
            Feng.loadWindow("编辑队列:"+obj.data.name,BasePath+"/rabbitmqMsg/queueMsg/editPage?id="+obj.data.id,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "delete"){
            Feng.confirm("确认删除"+obj.data.name+"队列吗",()=>{
                Request.async(BasePath+"/rabbitmqMsg/queueMsg/deleteById",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除队列成功")
                    refresh()
                })
            })
        }
    })


})
/**************************加载表格**************************/
function loadTable(){
    let tabCols = [[
        {field:"name",title:"队列名称"},
        {field:"durable",title:"持久化设置",templet:"#durableCols",width: TABLE_COL_WIDTH.one_Cols(4)},
        {field:"autoDelete",title:"消息自动删除",templet:"#autoDeleteCols",width: TABLE_COL_WIDTH.one_Cols(6)},
        {field:"exclusive",title:"排他性",templet:"#exclusiveCols",width: TABLE_COL_WIDTH.one_Cols(4)},
        {field:"describeInfo",title:"描述信息",width: TABLE_COL_WIDTH.text},
        {field:"exchangeName",title:"绑定交换机"},
        {field:"routingKey",title:"路由key"},
        {field:"realName",title:"创建人"},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date},
        {field:"updateTimeStr",title:"最后修改时间",width:TABLE_COL_WIDTH.date},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool",width: TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender = Initlay.initTable("#queueTable",BasePath+"/rabbitmqMsg/queueMsg/loadTable",tabCols,"#toolbar",param)
}
/**************************刷新**************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}