let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /**redis刷新按钮*/
    $(".refreshCache").click(()=>{
        Request.async(BasePath+"/rabbitmqMsg/exchangeMsg/refreshCache").then(res=>{
            Feng.success("刷新成功")
        })
    })

    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(exchangeTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.loadWindow("新增交换机",BasePath+"/rabbitmqMsg/exchangeMsg/addPage",LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
    })

    /** table的操作列 */
    table.on("tool(exchangeTable)",obj=>{
        if(obj.event == "edit"){
            Feng.loadWindow("编辑交换机:"+obj.data.name,BasePath+"/rabbitmqMsg/exchangeMsg/editPage?id="+obj.data.id,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "delete"){
            Feng.confirm("确认删除"+obj.data.name+"交换机吗",()=>{
                Request.async(BasePath+"/rabbitmqMsg/exchangeMsg/deleteById",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除交换机成功")
                    refresh()
                })
            })
        }
        if(obj.event == "queue"){
            Request.async(BasePath+"/rabbitmqMsg/exchangeMsg/getQueueById",{id:obj.data.id}).then(res=>{
                Feng.infoDetail(obj.data.name,template("queueList",{queueList:res}),null,null,LAYOUT_SIZE.SM_WIDTH(),LAYOUT_SIZE.SM_HEIGHT())
            })
        }
    })


})
/**************************加载表格**************************/
function loadTable(){
    let tabCols = [[
        {field:"name",title:"交换机名称"},
        {field:"durable",title:"持久化设置",templet:"#durableCols",width: TABLE_COL_WIDTH.one_Cols(5)},
        {field:"autoDelete",title:"队列自动删除",templet:"#autoDeleteCols",width: TABLE_COL_WIDTH.one_Cols(5)},
        {field:"type",title:"交换机类型",templet:"#typeCols",width: TABLE_COL_WIDTH.one_Cols(6)},
        {field:"isAck",title:"监听的处理",templet:"#isAckCols",width: TABLE_COL_WIDTH.one_Cols(5)},
        {field:"describeInfo",title:"描述信息",width: TABLE_COL_WIDTH.text},
        {field:"realName",title:"创建人"},
        {field:"createTimeStr",title:"创建时间",width:TABLE_COL_WIDTH.date},
        {field:"updateTimeStr",title:"最后修改时间",width:TABLE_COL_WIDTH.date},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool",width: TABLE_COL_WIDTH.tool(3)}
    ]]
    tableRender = Initlay.initTable("#exchangeTable",BasePath+"/rabbitmqMsg/exchangeMsg/loadTable",tabCols,"#toolbar",param)
}
/**************************刷新**************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}