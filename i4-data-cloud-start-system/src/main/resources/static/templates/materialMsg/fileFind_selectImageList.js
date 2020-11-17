var layer,laypage;//声明组件

layui.use(["layer","laypage"],()=>{

    layer = layui.layer
    laypage = layui.laypage

    /** 开启加载 */
    Request.async(BasePath+"/materialMsg/imageSelect/loadImageTable",{
        fileSize:size,
        current:0,
        size:15
    }).then(res=>{
        $("#imageTable").html(template("imageCol",{list:res.records}))

        /** 点击事件 */
        $(".imageDiv").click(function(){
            $(this).find(".icon-xuanze").toggleClass("select-show")
            $(this).toggleClass("bg-layui")
        })

        /** 选中提交 */
        $("#selectSubmit").click(()=>{
            let selectList = []
            $.each($(".imageDiv .icon-xuanze.select-show"),(i,o)=>{
                selectList.push({
                    url:$(o).attr("data-url"),
                    name:$(o).attr("data-name"),
                    suffix:$(o).attr("data-suffix"),
                    type:$(o).attr("data-type"),
                    size:$(o).attr("data-size"),
                    width:$(o).attr("data-width"),
                    height:$(o).attr("data-height")
                })
            })
            if(selectList.length < 1){
                Feng.msg("请选择图片在提交")
                return false
            }
            Request.asyncBody(BasePath+"/materialMsg/imageSelect/setListSelectTemp",{
                fileList:selectList
            }).then(res=>{
                parent.layer.closeAll()
            })
        })

        Initlay.loadPage("pageDom",res.total,obj=>{

            Request.async(BasePath+"/materialMsg/imageSelect/loadImageTable",{
                fileSize:size,
                current:obj.curr,
                size:obj.limit
            }).then(res=>{
                $("#imageTable").html(template("imageCol",{list:res.records}))

                /** 点击事件 */
                $(".imageDiv").click(function(){
                    $(this).find(".icon-xuanze").toggleClass("select-show")
                    $(this).toggleClass("bg-layui")
                })
            })
        })
    })

    /** 上传图片 */
    $("#batchUploadImage").click(()=>{
        UploadFile.batchPage(size)
    })

})
function refresh(){
    location = location
}