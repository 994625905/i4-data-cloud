/**
 * 图文操作
 * @type {{}}
 */
var RichText= {

    /**
     * 导入图文
     * @param elem
     * @param callback
     */
    import(elem,callback){
        $(elem).click(()=>{
            Feng.loadWindow("图文选择导入",BasePath+"/materialMsg/richText/select",LAYOUT_SIZE.BASE_HEIGHT(),LAYOUT_SIZE.BASE_WIDTH(),null,()=>{
                Request.async(BasePath+"/materialMsg/richText/getRichTextSelectTemp").then(res=>{
                    /** 给个回调函数吧，为了拓展 */
                    if(callback){
                        callback(res)
                    }
                })
            })
        })
    }

}