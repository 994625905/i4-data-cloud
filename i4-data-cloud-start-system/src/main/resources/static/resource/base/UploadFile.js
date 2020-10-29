/********************************上传文件的封装***********************************/
var UploadFile = {

    uploadUrl:BasePath+"/materialMsg/fileFind/upload",
    pageUrl:BasePath+"/materialMsg/fileFind/uploadPage",

    /**
     * 根据类型获取文本
     * @param type
     */
    getTypeText:function(type){
        if(type == "1"){
            return "图片"
        }
        if(type == "2"){
            return "音频"
        }
        if(type == "3"){
            return "视频"
        }
        if(type == "4"){
            return "文档"
        }
        if(type == "5"){
            return "其他"
        }
    },

    /**
     * 上传文件的页面（文件类型是图片，参数依次对应；文件类型是别的，宽高限制条件限制封面）
     * @param type
     * @param size
     * @param width
     * @param height
     * @param limit
     */
    openPage:function(type,size,width,height,limit){
        var url = this.pageUrl+"?type="+type;
        if(size){
            url += "&fileSize="+size;
        }
        if(width){
            url += "&width="+width;
        }
        if(height){
            url += "&height="+height;
        }
        if(limit){
            url += "&limitProp="+limit;
        }
        Feng.loadWindow(this.getTypeText(type)+"上传",url)
    },

    /**
     * 图片选择页面
     * @param title
     * @param imageElem
     * @param width
     * @param height
     * @param limit
     * @param size
     */
    imageSelect:function(title,imageElem,width,height,limit ,size){
        var p = "?type=1"
        if(width){
            p += "&width="+width;
        }if(height){
            p += "&height="+height;
        }if(limit){
            p += "&limitProp="+limit;
        }if(size){
            p += "&fileSize="+size;
        }
        Feng.loadWindow(title,BasePath+"/materialMsg/imageSelect/index"+p,null,null,null,()=>{
            Request.async(BasePath+"/materialMsg/imageSelect/getImageSelectTemp").then(res=>{
                if(!BaseUtil.isEmpty(res)){
                    $(imageElem).attr("src",res)
                }
            })
        })
    },

    /**
     * 上传图片(layui提供的默认文件类型为图片)
     * @param elem 点击的dom触发上传
     * @param callback 回调
     * @param size  是否限制大小，单位KB
     * @param width 是否限制宽
     * @param height 是否限制高
     * @param limit 是否允许等比例上传（必须设置了宽高限制，true），
     */
    image:function(elem,callback,size,width,height,limit){
        var loadIndex,imageWidth,imageHeight,limitFlag = true;
        var option = {
            elem:elem,
            url:this.uploadUrl,
            accept:"images",
            acceptMime:"image/*",
            exts:"jpg|png|gif|bmp|jpeg",
            done:function(res, index, upload){
                if(res.code == 200){
                    /** 即便尺寸比例校验失败，还是传到后台上传了，应该是layui的bug，后期考虑此种情况补偿删除fastDFS */
                    if(limitFlag){
                        if(callback){
                            callback({
                                ...res.result,
                                width:imageWidth,
                                height:imageHeight
                            })
                        }
                    }
                }else{
                    Feng.error(res.message || "上传接口异常");
                }
                Feng.close(loadIndex);
            },
            error:function(){
                Feng.error("上传失败，请检查文件服务器");
            }
        }
        //上传大小的限制，单位：KB
        if(!BaseUtil.isEmpty(size)){
            option.size = size;
        }
        //上传宽高限制
        if(!BaseUtil.isEmpty(width) && !BaseUtil.isEmpty(height)){
            option.before = function(obj){
                loadIndex = Feng.loading();
                obj.preview(function(index,file,result){
                    console.log("上传图片信息："+file);
                    var img = new Image();
                    img.src = result;
                    img.onload = function(){//前置初始化完成后，获取图片宽高
                        if(!BaseUtil.isEmpty(limit) && limit == "1"){//是否等比例上传，limit
                            limitFlag = (width*img.height == height*img.width);
                        }else{
                            limitFlag = (width == img.width && height == img.height);
                        }

                        /** 获取当前图片的宽高 */
                        imageWidth = img.width
                        imageHeight = img.height

                        if(limitFlag != true){
                            Feng.error("您选择的图片尺寸不符合");
                            Feng.close(loadIndex);
                            return limitFlag;
                        }
                    }
                    return limitFlag;
                });
                return limitFlag;
            }
        }else{
            option.before=function(obj){
                loadIndex = Feng.loading();
                obj.preview(function(index,file,result){
                    console.log("上传图片信息："+file);
                    var img = new Image();
                    img.src = result;
                    img.onload = function(){//前置初始化完成后，获取图片宽高
                        /** 获取当前图片的宽高 */
                        imageWidth = img.width
                        imageHeight = img.height
                    }
                });
            }
        }
        var res = upload.render(option);
        return res;
    },

    /**
     * 上传文件(layui提供的默认文件类型为图片)
     * @param elem 点击的dom触发上传
     * @param callback 回调
     * @param type 上传文件的类型（默认为file）
     * @param size  是否限制大小，单位KB
     */
    file:function(elem,callback,type = 5,size){
        var loadIndex;
        var option = {
            elem:elem,
            url:this.uploadUrl,
            accept:function(){
               if(type == 2){
                   return "audio"
               }
               if(type == 3){
                   return "video"
               }
               return "file"
            }(),
            acceptMime:function(){
                if(type == 2){
                    return "audio/*"
                }
                if(type == 3){
                    return "video/*"
                }
                return "file"
            }(),
            done:function(res, index, upload){
                if(res.code == 200){
                    if(callback){
                        callback(res.result)
                    }
                }else{
                    Feng.error("上传接口异常");
                }
                Feng.close(loadIndex);
            },
            error:function(){
                Feng.error("上传失败，请检查文件服务器");
            }
        }
        //上传大小的限制，单位：KB
        if(!BaseUtil.isEmpty(size)){
            option.size = size;
        }
        option.before=function(obj){
            console.log("before:"+obj);
            loadIndex = Feng.loading();
        }
        var res = upload.render(option);
        return res;
    }
}