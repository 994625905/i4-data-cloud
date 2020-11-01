var layer,form,table,element,laydate
var param = {
    type:1,
    startDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(-20),"start"),
    endDate:BaseDate.dateStrToTimeStamp(BaseDate.rangeDate(0),"end")
}
var tableRender_image,tableRender_audio,tableRender_video,tableRender_doc,tableRender_other

layui.use(["layer","form","table","element","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    table = layui.table
    element = layui.element
    laydate = layui.laydate

    /** 初始化图片表格 */
    loadImageTable();

    /** tab选项卡切换 */
    element.on("tab(type)",function(){
        param.type = this.getAttribute("type")
        debugger
        if(param.type == 1){
            loadImageTable()
        }
        if(param.type == 2){
            loadAudioTable()
        }
        if(param.type == 3){
            loadVideoTable()
        }
        if(param.type == 4){
            loadDocTable()
        }
        if(param.type == 5){
            loadOtherTable()
        }
    })

    /** 初始化日期 */
    Initlay.rangeLayDate("#date",value=>{
        param.startDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[0],"start")
        param.endDate = BaseDate.dateStrToTimeStamp(value.split(" - ")[1],"end")
        refresh()
    },BaseDate.rangeDate(-20)+" - "+BaseDate.rangeDate(0))

    /** table排序 */
    table.on("sort(fileTable_image)",obj=>{
        Initlay.sortTable(tableRender_image,obj,param)
    });
    table.on("sort(fileTable_audio)",obj=>{
        Initlay.sortTable(tableRender_audio,obj,param)
    });
    table.on("sort(fileTable_video)",obj=>{
        Initlay.sortTable(tableRender_video,obj,param)
    });
    table.on("sort(fileTable_doc)",obj=>{
        Initlay.sortTable(tableRender_doc,obj,param)
    });
    table.on("sort(fileTable_other)",obj=>{
        Initlay.sortTable(tableRender_other,obj,param)
    });

    /** table工具栏 */
    table.on("toolbar(fileTable_image)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender_image,param)
        }
        if(obj.event == "upload"){
            UploadFile.openPage(param.type)
        }
    })
    table.on("toolbar(fileTable_audio)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender_audio,param)
        }
        if(obj.event == "upload"){
            UploadFile.openPage(param.type)
        }
    })
    table.on("toolbar(fileTable_video)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender_video,param)
        }
        if(obj.event == "upload"){
            UploadFile.openPage(param.type)
        }
    })
    table.on("toolbar(fileTable_doc)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender_doc,param)
        }
        if(obj.event == "upload"){
            UploadFile.openPage(param.type)
        }
    })
    table.on("toolbar(fileTable_other)",obj=>{
        if(obj.event == "refresh"){
            Initlay.reloadTable(tableRender_other,param)
        }
        if(obj.event == "upload"){
            UploadFile.openPage(param.type)
        }
    })

    /** table操作列 */
    table.on("tool(fileTable_image)",obj=>{
        toolOperate(obj)
    })
    table.on("tool(fileTable_audio)",obj=>{
        toolOperate(obj)
    })
    table.on("tool(fileTable_video)",obj=>{
        toolOperate(obj)
    })
    table.on("tool(fileTable_doc)",obj=>{
        toolOperate(obj)
    })
    table.on("tool(fileTable_other)",obj=>{
        toolOperate(obj)
    })

})
/*************************加载封面************************/
function showImage(name,id,url){
    if(param.type == 1){
        Initlay.photo([{
            alt:name,
            pid:id,
            src:url
        }])
    }else{
        BaseUtil.openBlank(url,name)
    }
}
/*************************加载图片表格************************/
function loadImageTable(){
    var tabCols = [[
        {field:"url",title:"图片",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"suffix",title:"后缀类型",width: TABLE_COL_WIDTH.normal},
        {field:"size",title:"大小（单位KB）",sort:true,templet(d){ return d.size+" KB" },width: TABLE_COL_WIDTH.date},
        {field:"suffix",title:"分辨率（宽*高）",width: TABLE_COL_WIDTH.normal,templet(d){
            if(d.width && d.height){
                return d.width+" * "+d.height
            }
            return "分辨率未知"
        }},
        {field:"description",title:"描述信息"},
        {field:"createTimeStr",title:"上传时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"realname",title:"上传用户"},
        {field:"status",title:"状态",align:"center",toolbar: "#statusTool",width:TABLE_COL_WIDTH.tool(1)},
        {fixed:"right",title:"操作",align:"center",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender_image = Initlay.initTable("#fileTable_image",BasePath+"/materialMsg/fileFind/loadTable",tabCols,"#toolbar",param)
}
/*************************加载音频表格************************/
function loadAudioTable(){
    var tabCols = [[
        {field:"cover",title:"音频(封面)",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"suffix",title:"后缀类型",width: TABLE_COL_WIDTH.normal},
        {field:"size",title:"大小（单位KB）",sort:true,templet(d){ return d.size+" KB" },width: TABLE_COL_WIDTH.date},
        {field:"description",title:"描述信息"},
        {field:"createTimeStr",title:"上传时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"realname",title:"上传用户"},
        {field:"status",title:"状态",align:"center",toolbar: "#statusTool",width:TABLE_COL_WIDTH.tool(1)},
        {fixed:"right",title:"操作",align:"center",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender_audio = Initlay.initTable("#fileTable_audio",BasePath+"/materialMsg/fileFind/loadTable",tabCols,"#toolbar",param)
}
/*************************加载音频表格************************/
function loadVideoTable(){
    var tabCols = [[
        {field:"cover",title:"视频(封面)",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"suffix",title:"后缀类型",width: TABLE_COL_WIDTH.date},
        {field:"size",title:"大小（单位KB）",sort:true,templet(d){ return d.size+" KB" },width: TABLE_COL_WIDTH.date},
        {field:"description",title:"描述信息"},
        {field:"createTimeStr",title:"上传时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"realname",title:"上传用户"},
        {field:"status",title:"状态",align:"center",toolbar: "#statusTool",width:TABLE_COL_WIDTH.tool(1)},
        {fixed:"right",title:"操作",align:"center",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender_video = Initlay.initTable("#fileTable_video",BasePath+"/materialMsg/fileFind/loadTable",tabCols,"#toolbar",param)
}
/*************************加载文档表格************************/
function loadDocTable(){
    var tabCols = [[
        {field:"cover",title:"文档(封面)",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"suffix",title:"后缀类型",width: TABLE_COL_WIDTH.normal},
        {field:"size",title:"大小（单位KB）",sort:true,templet(d){ return d.size+" KB" },width: TABLE_COL_WIDTH.date},
        {field:"description",title:"描述信息"},
        {field:"createTimeStr",title:"上传时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"realname",title:"上传用户"},
        {field:"status",title:"状态",align:"center",toolbar: "#statusTool",width:TABLE_COL_WIDTH.tool(1)},
        {fixed:"right",title:"操作",align:"center",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender_doc = Initlay.initTable("#fileTable_doc",BasePath+"/materialMsg/fileFind/loadTable",tabCols,"#toolbar",param)
}
/*************************加载其他表格************************/
function loadOtherTable(){
    var tabCols = [[
        {field:"cover",title:"封面(封面)",align:"center",templet:"#imageUrl"},
        {field:"name",title:"名称"},
        {field:"suffix",title:"后缀类型",width: TABLE_COL_WIDTH.normal},
        {field:"size",title:"大小（单位KB）",sort:true,templet(d){ return d.size+" KB" },width: TABLE_COL_WIDTH.date},
        {field:"description",title:"描述信息"},
        {field:"createTimeStr",title:"上传时间",sort:true,width: TABLE_COL_WIDTH.date},
        {field:"realname",title:"上传用户"},
        {field:"status",title:"状态",align:"center",toolbar: "#statusTool",width:TABLE_COL_WIDTH.tool(1)},
        {fixed:"right",title:"操作",align:"center",toolbar:"#operate",width:TABLE_COL_WIDTH.tool(2)}
    ]]
    tableRender_other = Initlay.initTable("#fileTable_other",BasePath+"/materialMsg/fileFind/loadTable",tabCols,"#toolbar",param)
}
/*************************刷新************************/
function refresh(){
    if(param.type == 1){
        Initlay.reloadTable(tableRender_image,param)
    }
    if(param.type == 2){
        Initlay.reloadTable(tableRender_audio,param)
    }
    if(param.type == 3){
        Initlay.reloadTable(tableRender_video,param)
    }
    if(param.type == 4){
        Initlay.reloadTable(tableRender_doc,param)
    }
    if(param.type == 5){
        Initlay.reloadTable(tableRender_other,param)
    }
}
/*************************列操作************************/
function toolOperate(obj){
    if(obj.event == "freeze"){
        Feng.confirm("确定激活"+obj.data.name+"吗？",()=>{
            Request.async(BasePath+"/materialMsg/fileFind/changeStatus",{
                status:1,
                id:obj.data.id
            }).then(res=>{
                Feng.success("修改成功");
                refresh()
            })
        })
    }
    if(obj.event == "active"){
        Feng.confirm("确定冻结"+obj.data.name+"吗？",()=>{
            Request.async(BasePath+"/materialMsg/fileFind/changeStatus",{
                status:0,
                id:obj.data.id
            }).then(res=>{
                Feng.success("修改成功");
                refresh()
            })
        })
    }
    if(obj.event == "delete"){
        Feng.confirm("确定删除"+obj.data.name+"吗？",()=>{
            Request.async(BasePath+"/materialMsg/fileFind/delete",{
                id:obj.data.id,
                fileUrl:obj.data.url
            }).then(res=>{
                Feng.success("删除成功");
                refresh()
            })
        })
    }
    if(obj.event == "copy"){
        BaseUtil.clickCopy(obj.data.url)
    }
}