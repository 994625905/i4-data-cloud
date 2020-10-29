let layer,form;
let param = {}

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    /** 添加数据源 */
    $(".addDataSource").click(()=>{
        Feng.loadWindow("添加数据源",BasePath+"/autoCode/dataSourceMsg/addPage")
    })

    loadData();

})
/********************************加载表格数据*******************************/
function loadData(){
    Request.async(BasePath+"/autoCode/dataSourceMsg/loadData").then(res=>{
        var content = template("dataSourceList",{dataSourceList:res})
        $("#content").html(content)
    })
}
/********************************编辑页面*******************************/
function editPage(name,id){
    Feng.loadWindow("编辑"+name,BasePath+"/autoCode/dataSourceMsg/editPage?id="+id)
}
function refresh(){
    loadData();
}