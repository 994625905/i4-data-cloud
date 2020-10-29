let layer,form,transfer,element

layui.use(["layer","form","transfer","element"],()=>{

    layer = layui.layer
    form = layui.form
    transfer = layui.transfer
    element = layui.element

    /** 渲染穿梭框 */
    getTableListBySource()



    /** 验证项 */
    form.verify({
        defaultPackagePrefix:value=>{
            if(BaseUtil.isEmpty(value)){
                return "包前缀不允许为空！"
            }
            if(BaseRegax.haveCN(value)){
                return "包前缀不允许有中文"
            }
        }
    })

    /** 提交项 */
    form.on("submit(create)",obj=>{
        var tableList = transfer.getData("selectTable");
        if(tableList.length < 1){
            Feng.msg("请至少选中一张表格操作")
            return false
        }

        element.progress('processCreate', '0%');

        Request.asyncBody(BasePath+"/autoCode/dataSourceMsg/create",{
            ...obj.field,
            tableList:function(){
                var temp = []
                $.each(tableList,(i,o)=>{
                    temp.push(o.value)
                })
                return temp
            }(),
            driverClass:driverClass,
            dataSourceUrl:dataSourceUrl,
            user:user,
            password: password,
        }).then(res=>{
            /** 创建个假的进度条 */
            let n = 0;
            let timer = setInterval(function(){
                n = n + Math.random()*50|0;
                if(n>100){
                    n = 100;
                    clearInterval(timer);
                    Feng.success("已成功生成到指定目录中")
                }
                element.progress('processCreate', n+'%');
            }, Math.random()*500);
        })
        return false
    })

})
/****************************获取数据源中的表*****************************/
function getTableListBySource(){
    Request.async(BasePath+"/autoCode/dataSourceMsg/getTableListBySource",{
        driverClass:driverClass,
        dataSourceUrl:dataSourceUrl,
        user:user,
        password:password
    }).then(res=>{
        transfer.render({
            id:"selectTable",//定义唯一索引
            elem: '#tables',
            title: ['读取到的数据表', '选中要生成代码的表'],
            data: function(){
                var temp = []
                $.each(res,(i,o)=>{
                    if(BaseRegax.haveCN(o)){
                        temp.push({
                            value: o,title: o,disabled:true
                        })
                    }else{
                        temp.push({
                            value: o,title: o
                        })
                    }
                })
                return temp
            }(),
            showSearch: true,//搜索框
            width: 300,
            height: 450 //定义高度
        })
    })
}