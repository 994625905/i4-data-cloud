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
        Feng.loadWindow("选择登录背景页",BasePath+"/materialMsg/imageSelect/index",null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#authLoginImage").attr("src",res)
                }
            })
        })
    })
    $(".authLoginImage").click(()=>{
        uploadConstantImage("authLoginImage")
    })

    /** 网站logo */
    $("#webLogoImage").click(()=>{
        var p = "width=164&height=46&limitProp=1"
        Feng.loadWindow("选择网站logo（限制条件164*46，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#webLogoImage").attr("src",res)
                }
            })
        })
    })
    $(".webLogoImage").click(()=>{
        uploadConstantImage("webLogoImage")
    })


    /** 默认头像 */
    $("#userHeadImage").click(()=>{
        var p = "width=100&height=100&limitProp=1"
        Feng.loadWindow("选择默认头像（限制条件100*100，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#userHeadImage").attr("src",res)
                }
            })
        })
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
        var p = "width=300&height=300&limitProp=1&fileSize=200"
        Feng.loadWindow("选择MySQL图标（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#mysqlImage").attr("src",res)
                }
            })
        })
    })
    $(".mysqlImage").click(()=>{
        uploadConstantImage("mysqlImage")
    })

    /** Oracle图标 */
    $("#oracleImage").click(()=>{
        var p = "width=300&height=300&limitProp=1&fileSize=200"
        Feng.loadWindow("选择Oracle图标（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#oracleImage").attr("src",res)
                }
            })
        })
    })
    $(".oracleImage").click(()=>{
        uploadConstantImage("oracleImage")
    })

    /** 文件上传的降级服务 */
    $("#errorImage").click(()=>{
        Feng.loadWindow("选择文件上传降级的返回",BasePath+"/materialMsg/imageSelect/index",null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#errorImage").attr("src",res)
                }
            })
        })
    })
    $(".errorImage").click(()=>{
        uploadConstantImage("errorImage")
    })

    /** 文档的图标 */
    $("#wordCover").click(()=>{
        var p = "width=300&height=300&limitProp=1&fileSize=200"
        Feng.loadWindow("选择文档的默认封面（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#wordCover").attr("src",res)
                }
            })
        })
    })
    $(".wordCover").click(()=>{
        uploadConstantImage("wordCover")
    })

    /** 视频的图标 */
    $("#videoCover").click(()=>{
        var p = "width=300&height=300&limitProp=1&fileSize=200"
        Feng.loadWindow("选择视频的默认封面（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#videoCover").attr("src",res)
                }
            })
        })
    })
    $(".videoCover").click(()=>{
        uploadConstantImage("videoCover")
    })

    /** 语音的图标 */
    $("#audioCover").click(()=>{
        var p = "width=100&height=100&limitProp=1&fileSize=200"
        Feng.loadWindow("选择语音的默认封面（限制条件100*100，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#audioCover").attr("src",res)
                }
            })
        })
    })
    $(".audioCover").click(()=>{
        uploadConstantImage("audioCover")
    })

    /** 其他的图标 */
    $("#otherCover").click(()=>{
        var p = "width=300&height=300&limitProp=1&fileSize=200"
        Feng.loadWindow("选择其他类型的默认封面（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#otherCover").attr("src",res)
                }
            })
        })
    })
    $(".otherCover").click(()=>{
        uploadConstantImage("otherCover")
    })

    /** 图文草稿的封面 */
    $("#richTextCover").click(()=>{
        var p = "width=300&height=200&limitProp=1&fileSize=1024"
        Feng.loadWindow("选择图文草稿的默认封面（限制条件300*200，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#richTextCover").attr("src",res)
                }
            })
        })
    })
    $(".richTextCover").click(()=>{
        uploadConstantImage("richTextCover")
    })

    /** 下午茶默认图片 */
    $("#afternoonTeaImage").click(()=>{
        var p = "width=300&height=300&limitProp=1&fileSize=1024"
        Feng.loadWindow("选择下午茶的默认图片（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#afternoonTeaImage").attr("src",res)
                }
            })
        })
    })
    $(".afternoonTeaImage").click(()=>{
        uploadConstantImage("afternoonTeaImage")
    })

    /** 图片丢失的默认图片 */
    $("#loseImage").click(()=>{
        var p = "width=300&height=300&limitProp=1&fileSize=1024"
        Feng.loadWindow("图片丢失的默认图片（限制条件300*300，允许等比例，限制大小200KB）",BasePath+"/materialMsg/imageSelect/index?"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $("#loseImage").attr("src",res)
                }
            })
        })
    })
    $(".loseImage").click(()=>{
        uploadConstantImage("loseImage")
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