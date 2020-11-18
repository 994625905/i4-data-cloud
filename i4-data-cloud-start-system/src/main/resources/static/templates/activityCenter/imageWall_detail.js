let layer,element,flow
let imageList = []
let likeInterval = 0;
let imageListSize = 0;

layui.use(["layer","element","flow"],()=>{

    layer = layui.layer
    element = layui.element
    flow = layui.flow

    /** 预览模式 */
    $("#imageRead").click(()=>{
        Initlay.photo(imageList,activityTitle,0,obj=>{
            Request.asyncBody(BasePath+"/activityCenter/imageWall/read",{readModel:{imageId:obj.pid}})
        })
    })

    /** 照片上传 */
    $("#imageUpload").click(()=>{
        UploadFile.listSelect(activityTitle+"照片上传",null,res=>{
            let p = {
                activityId:activityId,
                imageList:res
            }
            Request.asyncBody(BasePath+"/activityCenter/imageWall/uploadImage",p).then(res=>{
                Feng.success("上传成功")

                $("#imageDiv").append(template("imageContent",{list:res,size:imageList.length}))

                /** 绑定点赞，删除 */
                likeEvent(imageList.length)
                deleteImage(imageList.length)

                setImageList(res)
            })
        })
    })

    Request.async(BasePath+"/activityCenter/imageWall/loadDetailTable",{
        activityId:activityId,
        current:0,
        size:15
    }).then(res=>{

        $("#imageDiv").append(template("imageContent",{list:res.records,size:imageList.length}))

        /** 点赞，删除 */
        likeEvent(imageList.length)
        deleteImage(imageList.length)

        setImageList(res.records)

        /** 流加载分页 */
        Initlay.loadFlowAuto("#imageDiv",obj=>{

            let r = BaseAjax.getData(BasePath+"/activityCenter/imageWall/loadDetailTable",{
                activityId:activityId,
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
            imageListSize = imageList.length

            setImageList(r.result.records)
            return {
                content: template("imageContent",{list:r.result.records,size:imageListSize}),
                page: r.result.total/15 +1
            }
        },()=>{
            /** 点赞，删除 */
            likeEvent(imageListSize)
            deleteImage(imageListSize)
        })

    })
})
/** 格式化结果集，设置到imageList */
function setImageList(array,site){
    $.each(array,(i,o)=>{
        if(site){
            imageList.unshift({
                alt:o.name,
                pid:o.id,
                src:o.url
            })
        }else{
            imageList.push({
                alt:o.name,
                pid:o.id,
                src:o.url
            })
        }
    })
}
/** 图片的预览模式 */
function readImage(id,name,start){
    Initlay.photo(imageList,activityTitle,start,obj=>{
        Request.asyncBody(BasePath+"/activityCenter/imageWall/read",{readModel:{imageId:obj.pid}})
    })
}
/** 点赞事件，取赞，频率间隔 */
function likeEvent(size){
    $(".like"+size).click(function(e){

        if(BaseDate.currTime() - likeInterval < 2){
            Feng.error("请控制点赞频率！")
            return;
        }
        likeInterval = BaseDate.currTime()

        let likeModel = {
            imageId:$(this).attr("data-id")
        }
        if($(this).hasClass("icon-dianzan1")){

            $(this).parent().next().text(parseInt($(this).parent().next().text()) -1)
            $(this).removeClass("icon-dianzan1")
            $(this).addClass("icon-dianzan")

            Request.asyncBody(BasePath+"/activityCenter/imageWall/cancelLike",{
                likeModel:likeModel
            }).then(res=>{
                Feng.msg("您已取消点赞")
            })
        }else{

            $(this).parent().next().text(parseInt($(this).attr("data-count")) +1)
            $(this).removeClass("icon-dianzan")
            $(this).addClass("icon-dianzan1")
            Request.asyncBody(BasePath+"/activityCenter/imageWall/like",{
                likeModel:likeModel
            }).then(res=>{
                Feng.msg("点赞成功")
            })
        }
    })
}
/** 删除事件 */
function deleteImage(size){
    $(".deleteImage"+size).click(function(){
        let p = {
            id:$(this).attr("data-id")
        }
        let temp = $(this)
        Feng.confirm("将删除该图片的所有数据，确定吗",()=>{
            Request.async(BasePath+"/activityCenter/imageWall/deleteImage",p).then(res=>{
                Feng.success("删除成功")
                temp.parent().remove()
            })
        })
    })
}