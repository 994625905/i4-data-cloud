let layer,table,form,laydate
let tableRender
let param = {
    year:BaseDate.currYear()
}

layui.use(["layer","table","form","laydate"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form
    laydate = layui.laydate

    /** 渲染form表单 */
    form.render()

    /** 渲染日期框 */
    laydate.render({
        elem:"#year",
        type:"year",
        value:BaseDate.currYear(),
        theme:"#007bff",
        done:value=>{
            param.year = value
            refresh()
        }
    })

    /** 关于 */
    $("#about").click(()=>{
        Feng.open("说明信息",template("aboutContent",{}),null,"350px","550px")
    })

    /** 初始化表格 */
    loadTable()

    /** table的工具栏 */
    table.on("toolbar(calendarTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.loadWindow("新增日历设置",BasePath+"/attendanceCenter/attendanceCalendar/addPage?year="+param.year)
        }
    })

    /** 查询按钮 */
    form.on("submit(search)",obj=>{
        param.type = obj.field.type
        refresh()
        return false;
    })

    /** table的排序 */
    table.on("sort(calendarTable)",obj=>{
        Initlay.sortTable(tableRender,obj,param)
    })

    /** table的操作列 */
    table.on("tool(calendarTable)",obj=>{
        if(obj.event == "delete"){
            Feng.confirm("确定删除吗？",()=>{
                Request.async(BasePath+"/attendanceCenter/attendanceCalendar/delete",{
                    id:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功！")
                    refresh()
                })
            })
        }
    })

})
/*************************加载表格*************************/
function loadTable(){
    let tabCols = [[
        {field:"year",title:"年份",},
        {field:"month",title:"月份",sort:true},
        {field:"week",title:"星期",sort:true},
        {field:"date",title:"具体日期",sort:true},
        {field:"holidayName",title:"假日名称"},
        {field:"type",title:"日历类型",templet(d){
            if(d.type == 2){
                return "<label class='layui-btn layui-btn-sm'>法定节假日</label>"
            }
            return "<label class='layui-btn layui-btn-sm layui-btn-danger'>法定工作日</label>"
        }},
        {field:"createTimeStr",title:"创建时间"},
        {field:"createUserName",title:"创建者"},
        {fixed:"right",title:"操作",width: TABLE_COL_WIDTH.tool(1),templet(d){
            if(d.date > BaseUtil.replaceAll(BaseDate.rangeDate(-1),"-","")){
                return template("operate",{})
            }
        }}
    ]]
    tableRender = Initlay.initTable("#calendarTable",BasePath+"/attendanceCenter/attendanceCalendar/loadTable",tabCols,"#toolbar",param,null,null,50)
}
/*************************刷新*************************/
function refresh(){
    Initlay.reloadTable(tableRender,param)
}