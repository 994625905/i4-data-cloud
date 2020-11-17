var layer,laypage;//声明组件

layui.use(["layer","laypage"],()=>{

    layer = layui.layer
    laypage = layui.laypage

    /** 开启加载 */
    Request.async(BasePath+"/materialMsg/imageSelect/loadImageTable",{
        width:limit_width,
        height:limit_height,
        limitProp:limit_flag,
        fileSize:size,
        current:0,
        size:15
    }).then(res=>{
        $("#imageTable").html(template("imageCol",{list:res.records}))

        /** 鼠标移入移出 */
        $(".imageDiv").on("mouseover mouseout",function () {
            $(this).toggleClass("colorClassName")
        });

        /** 点击选中，临时存储 */
        $(".imageDiv").click(function(){
            Request.async(BasePath+"/materialMsg/imageSelect/setImageSelectTemp",{
                url:$(this).find("img").attr("src"),
                name:$(this).find("img").attr("data-name"),
                suffix:$(this).find("img").attr("data-suffix"),
                type:$(this).find("img").attr("data-type"),
                size:$(this).find("img").attr("data-size"),
                width:$(this).find("img").attr("data-width"),
                height:$(this).find("img").attr("data-height")
            }).then(res=>{
                parent.layer.closeAll()
            })
        })

        Initlay.loadPage("pageDom",res.total,obj=>{

            Request.async(BasePath+"/materialMsg/imageSelect/loadImageTable",{
                width:limit_width,
                height:limit_height,
                limitProp:limit_flag,
                fileSize:size,
                current:obj.curr,
                size:obj.limit
            }).then(res=>{
                $("#imageTable").html(template("imageCol",{list:res.records}))

                /** 鼠标移入移出 */
                $(".imageDiv").on("mouseover mouseout",function () {
                    $(this).toggleClass("colorClassName")
                });

                /** 点击选中 */
                $(".imageDiv").find("img").click(function(){
                    Request.async(BasePath+"/materialMsg/imageSelect/setImageSelectTemp",{
                        url:$(this).attr("src"),
                        name:$(this).attr("data-name"),
                        suffix:$(this).attr("data-suffix"),
                        type:$(this).attr("data-type"),
                        size:$(this).attr("data-size"),
                        width:$(this).attr("data-width"),
                        height:$(this).attr("data-height")
                    }).then(res=>{
                        parent.layer.closeAll()
                    })
                })
            })
        })
    })

    /** 上传图片 */
    $("#uploadImage").click(()=>{
        UploadFile.openPage(1,size,limit_width,limit_height,limit_flag)
    })

})
function refresh(){
    location = location
}