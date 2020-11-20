var layer,form;//声明组件

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form
    form.render()

    /** 刷新缓存 */
    $("#refreshCache").click(()=>{
        Request.async(BasePath+"/systemMsg/constantMsg/refresh").then(res=>{
            if(res){
                Feng.success("成功刷新Redis缓存系统常量")
            }
        })
    })

    /** 登录背景页 */
    $("#authLoginImage").click(()=>{
        UploadFile.imageSelect("选择登录背景页","#authLoginImage")
    })
    $(".authLoginImage").click(()=>{
        uploadConstantImage("authLoginImage")
    })

    /** 网站logo */
    $("#webLogoImage").click(()=>{
        UploadFile.imageSelect("选择网站logo","#webLogoImage",164,46,1,200)
    })
    $(".webLogoImage").click(()=>{
        uploadConstantImage("webLogoImage")
    })


    /** 默认头像 */
    $("#userHeadImage").click(()=>{
        UploadFile.imageSelect("选择默认头像","#userHeadImage",100,100,1,200)
    })
    $(".userHeadImage").click(()=>{
        uploadConstantImage("userHeadImage")
    })

    /** 默认密码 */
    $(".defaultPassword").click(()=>{
        uploadConstantText("defaultPassword")
    })



    /** MySQL图标 */
    $("#mysqlImage").click(()=>{
        UploadFile.imageSelect("选择MySQL图标","#mysqlImage",300,300,1,200)
    })
    $(".mysqlImage").click(()=>{
        uploadConstantImage("mysqlImage")
    })

    /** Oracle图标 */
    $("#oracleImage").click(()=>{
        UploadFile.imageSelect("选择Oracle图标","#oracleImage",300,300,1,200)
    })
    $(".oracleImage").click(()=>{
        uploadConstantImage("oracleImage")
    })

    /** 文件上传的降级服务 */
    $("#errorImage").click(()=>{
        UploadFile.imageSelect("选择文件上传降级的返回","#errorImage")
    })
    $(".errorImage").click(()=>{
        uploadConstantImage("errorImage")
    })

    /** 文档的图标 */
    $("#wordCover").click(()=>{
        UploadFile.imageSelect("选择文档的默认封面","#wordCover",300,300,1,200)
    })
    $(".wordCover").click(()=>{
        uploadConstantImage("wordCover")
    })

    /** 视频的图标 */
    $("#videoCover").click(()=>{
        UploadFile.imageSelect("选择视频的默认封面","#videoCover",300,300,1,200)
    })
    $(".videoCover").click(()=>{
        uploadConstantImage("videoCover")
    })

    /** 语音的图标 */
    $("#audioCover").click(()=>{
        UploadFile.imageSelect("选择语音的默认封面","#audioCover",100,100,1,200)
    })
    $(".audioCover").click(()=>{
        uploadConstantImage("audioCover")
    })

    /** 其他的图标 */
    $("#otherCover").click(()=>{
        UploadFile.imageSelect("选择其他类型的默认封面","#otherCover",300,300,1,200)
    })
    $(".otherCover").click(()=>{
        uploadConstantImage("otherCover")
    })

    /** 图文草稿的封面 */
    $("#richTextCover").click(()=>{
        UploadFile.imageSelect("选择图文草稿的默认封面","#richTextCover",300,200,1,1024)
    })
    $(".richTextCover").click(()=>{
        uploadConstantImage("richTextCover")
    })

    /** 下午茶默认图片 */
    $("#afternoonTeaImage").click(()=>{
        UploadFile.imageSelect("选择下午茶的默认图片","#afternoonTeaImage",300,300,1,1024)
    })
    $(".afternoonTeaImage").click(()=>{
        uploadConstantImage("afternoonTeaImage")
    })

    /** 图片丢失的默认图片 */
    $("#loseImage").click(()=>{
        UploadFile.imageSelect("图片丢失的默认图片","#loseImage",300,300,1,1024)
    })
    $(".loseImage").click(()=>{
        uploadConstantImage("loseImage")
    })

    /** 活动行的默认封面 */
    $("#activityCoverImage").click(()=>{
        UploadFile.imageSelect("活动行的默认封面","#activityCoverImage",300,200,1,1024)
    })
    $(".activityCoverImage").click(()=>{
        uploadConstantImage("activityCoverImage")
    })

    /** 相册的默认封面 */
    $("#photoGroupImage").click(()=>{
        UploadFile.imageSelect("相册的默认封面","#photoGroupImage",300,200,1,1024)
    })
    $(".photoGroupImage").click(()=>{
        uploadConstantImage("photoGroupImage")
    })

    /** 百度地图API */
    $(".baiduMapApi").click(()=>{
        uploadConstantText("baiduMapApi")
    })

})
/************************************文本修改***********************************/
function uploadConstantText(input){
    Request.async(BasePath+"/systemMsg/constantMsg/update",{
        value:$("input[name='"+input+"']").val(),
        id:$("input[name='"+input+"_id']").val()
    }).then(res=>{
        Feng.success("保存成功")
    })
}
/************************************图片修改***********************************/
function uploadConstantImage(image){
    Request.async(BasePath+"/systemMsg/constantMsg/update",{
        value:$("#"+image).attr("src"),
        id:$("input[name='"+image+"_id']").val()
    }).then(res=>{
        Feng.success("保存成功")
    })
}
/************************************查看key***********************************/
function findConstantKey(key){
    Feng.msg(key)
}