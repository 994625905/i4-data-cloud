var layer,laypage;//声明组件

layui.use(["layer","laypage"],()=>{

    layer = layui.layer
    laypage = layui.laypage

    /** 开启加载 */
    BaseAjax.getDataAsync()
    Request.async(BasePath+"/materialMsg/richText/loadTable",{
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
            Request.async(BasePath+"/materialMsg/richText/setRichTextSelectTemp",{mongoId:$(this).find("img").attr("data")}).then(res=>{
                parent.layer.closeAll()
            })
        })

        Initlay.loadPage("pageDom",res.total,obj=>{

            Request.async(BasePath+"/materialMsg/imageSelect/loadFileTable",{
                type:type,
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
                    Request.async(BasePath+"/materialMsg/richText/setRichTextSelectTemp",{mongoId:$(this).attr("data")}).then(res=>{
                        parent.layer.closeAll()
                    })
                })
            })
        })
    })
})
function refresh(){
    location = location
}