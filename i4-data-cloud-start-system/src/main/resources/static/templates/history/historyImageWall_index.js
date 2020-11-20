let layer,element,flow
let Size = 0;

layui.use(["layer","element","flow"],()=>{

    layer = layui.layer
    element = layui.element
    flow = layui.flow

    /** 新增事件 */
    $("#insertGroup").click(()=>{
        Feng.infoDetail("新增相册",template("insertContent",{}),null,index=>{
            let name = $("input[name='groupName']").val()
            if(BaseUtil.isEmpty(name)){
                return Feng.error("相册名称不能为空")
            }

            let describeInfo = $("input[name='groupDescribeInfo']").val()
            if(BaseUtil.isEmpty(describeInfo)){
                return Feng.error("相册描述不能为空")
            }
            Request.asyncBody(BasePath+"/history/historyImageWall/insert",{
                model:{
                    name:name,
                    describeInfo:describeInfo
                }
            }).then(res=>{
                Feng.close(index)
                Feng.success("新增成功")

                Size += 1;
                $("#groupDiv").prepend(template("groupContent",{list:[res],size:Size}))

                /** 绑定删除和修改 */
                deleteEvent(Size)
                updateEvent(Size)
            })
        },"500px","250px")
    })

    /** 流加载分页 */
    Initlay.loadFlowAuto("#groupDiv",obj=>{
        let r = BaseAjax.getData(BasePath+"/history/historyImageWall/loadTable",{
            current:obj,
            size:15
        })
        if(r.code != 200){
            Feng.error(r.message)
            return {
                content:r.message,
                page:obj
            }
        }
        Size += r.result.records.length
        return {
            content: template("groupContent",{list:r.result.records,size:Size}),
            page: r.result.total/15 +1
        }
    },()=>{
        /** 绑定删除和修改 */
        deleteEvent(Size)
        updateEvent(Size)
    })

})
/** 点击进入相册 */
function photoGroup(groupId,groupName){
    Feng.loadWindow(groupName+"：相册",BasePath+"/history/historyImageWall/detailPage?groupId="+groupId+"&groupName="+groupName)
}
/** 绑定修改事件 */
function updateEvent(size){
    $(".update"+size).click(function(){
        debugger
        let temp = $(this)
        let content = template("updateContent",{name:$(this).attr("data-name"),describeInfo:$(this).attr("data-describeInfo")})
        Feng.infoDetail("相册修改",content,null,index=>{

            let name = $("input[name='groupName']").val()
            if(BaseUtil.isEmpty(name)){
                return Feng.error("相册名称不能为空")
            }

            let describeInfo = $("input[name='groupDescribeInfo']").val()
            if(BaseUtil.isEmpty(describeInfo)){
                return Feng.error("相册描述不能为空")
            }
            Request.asyncBody(BasePath+"/history/historyImageWall/update",{
                model:{
                    id:temp.attr("data-id"),
                    name:name,
                    describeInfo:describeInfo
                }
            }).then(res=>{
                Feng.close(index)
                Feng.success("修改成功")
                temp.parent().find(".groupName").text(name)
                temp.parent().find(".groupDescribeInfo").text(describeInfo)
            })
        },"500px","250px")
    })
}
/** 绑定删除事件 */
function deleteEvent(size){
    $(".delete"+size).click(function(){
        let temp = $(this)
        Feng.confirm("将级联删除相册所有内容，确定吗？",()=>{
            Request.async(BasePath+"/history/historyImageWall/delete",{
                id:temp.attr("data-id")
            }).then(res=>{
                Feng.success("删除成功")
                temp.parent().remove()
            })
        })
    })
}