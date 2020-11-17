var layer,form,upload;//声明组件

layui.use(["form","layer","upload"],function(){

    form = layui.form;
    layer = layui.layer;
    upload = layui.upload

    /** 渲染表单 */
    form.render();

    /** 开始上传 */
    let imageListView = $('#imageList')
    let imageWidth,imageHeight
    let loadIndex
    let uploadListIns = upload.render({
        elem: '#uploadList',
        url: BasePath+"/materialMsg/fileFind/upload",
        accept:"images",
        acceptMime:"image/*",
        exts:"jpg|png|gif|bmp|jpeg",
        multiple: true,
        auto: false,
        bindAction: '#batchUpload',
        choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1024).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                imageListView.append(tr);
            });
        },
        before:function(obj){
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
        },
        done: function(res, index, upload){
            if(res.code == 200){
                /** 入库 */
                Request.asyncBody(BasePath+"/materialMsg/fileFind/save",{
                    model:{
                        url:res.result.fileUrl,
                        name:res.result.fileName,
                        suffix:res.result.fileSuffix,
                        size:(res.result.fileSize/1024).toFixed(2),
                        type:type,
                        width:imageWidth,
                        height:imageHeight,
                        description:$("input[name='description']").val()
                    }
                }).then(res=>{

                    let tr = imageListView.find('tr#upload-'+ index)
                    let tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作

                    let length = imageListView.find(".demo-delete").length
                    if(length == 0){
                        Feng.success("上传成功");
                        BaseUtil.setTimeout(()=>{
                            parent.refresh();
                            parent.layer.closeAll()
                        },1000)
                    }
                })
                Feng.close(loadIndex)
                return;
            }
            this.error(index, upload);
        },
        error: function(index, upload){
            let tr = imageListView.find('tr#upload-'+ index)
            let tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传按钮
        }
    });

});