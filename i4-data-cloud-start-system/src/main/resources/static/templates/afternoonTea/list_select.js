var layer,laypage;//声明组件

layui.use(["layer","laypage"],()=>{

    layer = layui.layer
    laypage = layui.laypage

    /** 开启加载 */
    Request.async(BasePath+"/afternoonTea/list/loadTable",{
        current:0,
        size:15
    }).then(res=>{
        $("#listTable").html(template("imageCol",{list:res.records}))

        /** 鼠标移入移出 */
        $(".imageDiv").on("mouseover mouseout",function () {
            $(this).toggleClass("colorClassName")
        });

        /** 点击选中，临时存储 */
        $(".imageDiv").click(function(){
            Request.async(BasePath+"/afternoonTea/list/setTeaSelectTemp",{
                image:$(this).find("img").attr("src"),
                id:$(this).find("img").attr("data-id"),
                name:$(this).find("img").attr("data-name"),
                price:$(this).find("img").attr("data-price")
            }).then(res=>{
                parent.layer.closeAll()
            })
        })

        Initlay.loadPage("pageDom",res.total,obj=>{
            Request.async(BasePath+"/afternoonTea/list/loadTable",{
                current:obj.curr,
                size:obj.limit
            }).then(res=>{
                $("#listTable").html(template("imageCol",{list:res.records}))

                /** 鼠标移入移出 */
                $(".imageDiv").on("mouseover mouseout",function () {
                    $(this).toggleClass("colorClassName")
                });

                /** 点击选中 */
                $(".imageDiv").find("img").click(function(){
                    Request.async(BasePath+"/afternoonTea/list/setTeaSelectTemp",{
                        image:$(this).find("img").attr("src"),
                        id:$(this).find("img").attr("data-id"),
                        name:$(this).find("img").attr("data-name"),
                        price:$(this).find("img").attr("data-price")
                    }).then(res=>{
                        parent.layer.closeAll()
                    })
                })
            })
        })
    })

})