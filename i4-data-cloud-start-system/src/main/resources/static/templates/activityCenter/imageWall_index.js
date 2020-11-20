let layer,flow

layui.use(["layer","flow"],()=>{

    layer = layui.layer
    flow = layui.flow

    /** 流加载分页 */
    Initlay.loadFlowAuto("#imageDiv",obj=>{
        let r = BaseAjax.getData(BasePath+"/activityCenter/imageWall/selectImageGroup",{
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
        return {
            content: template("imageContent",{list:r.result.records}),
            page: r.result.total/15 +1
        }
    })


})
/********************* 照片组详情 *********************/
function photoGroup(activityId,activityTitle){
    Feng.loadWindow(activityTitle+"--》照片墙",BasePath+"/activityCenter/imageWall/detailPage?activityId="+activityId+"&activityTitle="+activityTitle)
}