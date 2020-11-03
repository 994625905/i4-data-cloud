var layer,form,upload;//声明组件

layui.use(["form","layer","upload"],function(){

    form = layui.form;
    layer = layui.layer;
    upload = layui.upload

    /** 渲染表单 */
    form.render();

    /** 上传图片 */
    UploadFile.image("#uploadImageBtn",res=>{
        $("#uploadImage").attr("src",res.fileUrl)
        $("input[name='url']").val(res.fileUrl)
        $("input[name='name']").val(res.fileName)
        $("input[name='suffix']").val(res.fileSuffix)
        $("input[name='size']").val((res.fileSize/1024).toFixed(2))//单位KB
        if(type == "1"){
            $("input[name='width']").val(res.width)
            $("input[name='height']").val(res.height)
        }
    },size,limit_width,limit_height,limit_flag)

    /** 上传文件 */
    UploadFile.file("#uploadFileBtn",res=>{
        $("input[name='url']").val(res.fileUrl)
        $("input[name='name']").val(res.fileName)
        $("input[name='suffix']").val(res.fileSuffix)
        $("input[name='size']").val((res.fileSize/1024).toFixed(2))//单位KB
    },type,size)

    /** 上传封面，封面的比例满足（1;1） */
    UploadFile.image("#uploadCoverBtn",res=>{
        $("#uploadCover").attr("src",res.fileUrl)
        $("input[name='cover']").val(res.fileUrl)
    },size,100,100,1)

    /** 提交项 */
    form.on("submit(save)",function(obj){
        var param = obj.field;
        Request.asyncBody(BasePath+"/materialMsg/fileFind/save",{model:param}).then(res=>{
            Feng.success("新增成功");
            parent.refresh();
            parent.layer.closeAll()
        })
        return false;
    })

});