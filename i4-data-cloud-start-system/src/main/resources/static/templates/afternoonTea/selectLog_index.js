let layer,form,table,laydate
let tableRender
let selectChartByType = echarts.init(document.getElementById("selectChartByType"))
let selectChart = echarts.init(document.getElementById("selectChart"))
let param = {
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-30),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end"),
}

layui.use(["layer","form","table","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table
    laydate = layui.laydate

    /** 渲染form */
    form.render()

    /** 渲染日期选择器 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        Initlay.reloadTable(tableRender,param)
        loadChartByType()
        loadChart()
    },BaseDate.rangeDate(-30)+" - "+BaseDate.rangeDate(0))

    /** 初始化表格 */
    loadTable()
    loadChart()
    loadChartByType()

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.name = obj.field.name
        param.userId = obj.field.userId
        Initlay.reloadTable(tableRender,param)
        loadChartByType()
        loadChart()

        return false;
    })

    /** table工具栏 */
    table.on("toolbar(selectLogTable)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender,param)
        }
    })

    /** table排序 */
    table.on("sort(selectLogTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

})
/*************************加载表格************************/
function loadTable(){
    let tabCols = [[
        {field:"realName",title:"用户",width:TABLE_COL_WIDTH.one_Cols(6)},
        {field:"teaImage",title:"图片",align:"center",templet:"#imageUrl"},
        {field:"dateWeek",title:"所属日期",width:TABLE_COL_WIDTH.one_Cols(7)},
        {field:"createTimeStr",title:"选择时间",sort:true,width:TABLE_COL_WIDTH.date},
        {field:"taskTitle",title:"标题"}
    ]]
    tableRender = Initlay.initTable("#selectLogTable",BasePath+"/afternoonTea/selectLog/loadTable",tabCols,null,param,null,null,25)
}
/*************************加载报表根据类型************************/
function loadChartByType(){
    Request.async(BasePath+"/afternoonTea/selectLog/loadChartByType",param).then(res=>{
        InitChart.barAndPie(selectChartByType,res,"按类型")
    })
}
/*************************加载报表************************/
function loadChart(){
    Request.async(BasePath+"/afternoonTea/selectLog/loadChart",param).then(res=>{
        InitChart.barAndPie(selectChart,res,"按名称")
    })
}
/*************************加载封面************************/
function showHeadImage(name,id,image){
    Initlay.photo([{
        alt:name,
        pid:id,
        src:image
    }])
}