/*************************************封装请求的promise @com.wangjc***********************************/
var Request = {

    /**
     * 异步请求
     * @param url
     * @param param
     * @returns {Promise<unknown>}
     */
    async:function(url,param){
        return new Promise((success,fail)=>{
            BaseAjax.getDataAsync(url,param,res=>{
                if(res.code == 200){
                    return success(res.result)
                }else{
                    Feng.error(res.message || "系统服务器繁忙，请稍后再试")
                    return fail || null;
                }
            })
        })
    },
    /**
     * 异步请求：设置参数requestBody
     * @param url
     * @param param
     * @returns {Promise<unknown>}
     */
    asyncBody:function(url,param){
        return new Promise((success,fail)=>{
            BaseAjax.getDataAsync_Map(url,param,res=>{
                if(res.code == 200){
                    return success(res.result)
                }else{
                    Feng.error(res.message || "系统服务器繁忙，请稍后再试")
                    return fail || null;
                }
            })
        })
    },
    /**
     * 跨域请求，携带token
     * @param url
     * @param param
     */
    asyncCross(url,param){
        return new Promise((success,fail)=>{
            $.ajax({
                async: false,
                cache:false,//关闭缓存
                url:url+"?callback=?",
                data:param?param:null,
                method:"post",
                dataType:"jsonp",
                xhrFields : {
                    withCredentials : true
                },
                header:{
                    "content-type":"application/json",
                    "Authorization":$.cookie("token")
                },
                beforeSend:function(){
                    index = Feng.loading("数据请求中。。");
                },
                success:function(res){
                    Feng.close(index);
                    if(res.code == 200){
                        return success(res.result)
                    }else{
                        Feng.error(res.message || "系统服务器繁忙，请稍后再试")
                        return fail || null;
                    }
                },
                error: function (err) {
                    Feng.close(index);
                    Feng.close("请求超时")
                }
            });
        })
    },

}