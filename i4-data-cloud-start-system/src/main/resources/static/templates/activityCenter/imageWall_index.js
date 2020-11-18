let layer

layui.use(["layer"],()=>{

    layer = layui.layer

})
/********************* 照片组详情 *********************/
function photoGroup(activityId,activityTitle){
    Feng.loadWindow(activityTitle+"--》照片墙",BasePath+"/activityCenter/imageWall/detailPage?activityId="+activityId+"&activityTitle="+activityTitle)
}