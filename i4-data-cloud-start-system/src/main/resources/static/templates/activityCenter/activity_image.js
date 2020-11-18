let layer,element,flow
let imageList = []


layui.use(["layer","element","flow"],()=>{

    layer = layui.layer
    element = layui.element
    flow = layui.flow

    Request.async(BasePath+"",{
        activityId:activityId,
        current:0,
        size:15
    }).then(res=>{

        imageList.push(res.records)

        $("#imageDiv").append(template("imageContent",{list:res.records}))

        /** 流加载分页 */
        Initlay.loadFlowAuto("#imageDiv",obj=>{
            debugger
        })

    })

})
