let layer,table
let tableRender
let param = {}

layui.use(["layer","table"],()=>{

    layer = layui.layer
    table = layui.table

    /** 初始化表格 */
    loadTable()

    /** table工具栏 */
    table.on("toolbar(noticeTable)",obj=>{
        if(obj.event == "refresh"){
            refresh()
        }
        if(obj.event == "add"){
            Feng.loadWindow("新增公告",BasePath+"/history/historyNotice/addPage")
        }
    })

    /** table操作列 */
    table.on("tool(noticeTable)",obj=>{
        if(obj.event == "deploy"){
            Feng.confirm("确定发布吗？",()=>{
                Request.asyncBody(BasePath+"/history/historyNotice/deploy",{
                    model:{
                        id:obj.data.id
                    }
                }).then(res=>{
                    Feng.success("发布成功")
                    refresh()
                })
            })
        }
        if(obj.event == "read"){
            Feng.loadWindow(obj.data.title+"：预览",BasePath+"/history/historyNotice/readPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
        if(obj.event == "edit"){
            Feng.loadWindow("编辑公告："+obj.data.title,BasePath+"/history/historyNotice/editPage?id="+obj.data.id+"&mongoId="+obj.data.mongoId)
        }
        if(obj.event == "delete"){
            Feng.confirm("级联删除相关数据，确定吗？",()=>{
                Request.async(BasePath+"/history/historyNotice/delete",{
                    id:obj.data.id,
                    mongoId:obj.data.mongoId
                }).then(res=>{
                    Feng.success("删除公告成功")
                    refresh()
                })
            })
        }
        if(obj.event == "readUser"){
            Request.async(BasePath+"/history/historyNotice/selectReadUser",{id:obj.data.id}).then(res=>{
                let content = template("readUser",{list:res})
                Feng.infoDetail("已读人员："+obj.data.title,content,null,null,"420px","550px")
            })
        }
    })

})
/**************************加载表格*****************************/
function loadTable(){
    let tabCols = [[
        {field: "title",title: "标题"},
        {field: "createUserName",title: "发布人",width: TABLE_COL_WIDTH.one_Cols(6)},
        {field: "typeName",title: "类型",sort:true,width:TABLE_COL_WIDTH.one_Cols(5),templet(d) {
            if(d.typeId == 1){
                return "<span class='layui-badge layui-bg-green'>日常</span>";
            }
            if(d.typeId == 2){
                return "<span class='layui-badge layui-bg-gray'>提议</span>";
            }
            if(d.typeId == 3){
                return "<span class='layui-badge layui-bg-cyan'>制度</span>";
            }
            if(d.typeId == 4){
                return "<span class='layui-badge layui-bg-orange'>重要</span>";
            }
            if(d.typeId == 5){
                return "<span class='layui-badge'>加急</span>";
            }
        }},
        {field: "createTimeStr",title: "创建时间",sort: true,width: TABLE_COL_WIDTH.date},
        {field: "deployTimeStr",title: "发布时间",sort:true,toolbar:"#statusTool",width: TABLE_COL_WIDTH.date},
        {field:"id",title:"附件查看",width:TABLE_COL_WIDTH.one_Cols(4),templet(d){
            return "<label class='layui-btn layui-btn-sm layui-btn-normal' onclick='showEnclosure("+d.id+",\""+d.title+"\")'>查看附件</label>"
        }},
        {fixed: "right",title: "操作",align: "center",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(3)}
    ]]
    tableRender = Initlay.initTable("#noticeTable",BasePath+"/history/historyNotice/loadTable",tabCols,"#toolbar",param)
}
/** 刷新 */
function refresh(){
    Initlay.reloadTable(tableRender,param)
}

/** 查看附件 */
function showEnclosure(id,title){
    Request.async(BasePath+"/history/historyNotice/getFileListByWeekReportId",{id:id}).then(res=>{
        let content = template("fileList",{list:res})
        Feng.infoDetail("附件列表",content,()=>{

            /** 附件类型替换文本 */
            $.each($(".fileType"),(i,o)=>{
                $(o).text(UploadFile.getTypeText($(o).text()))
            })
        },null,"450px","500px")
    })
}