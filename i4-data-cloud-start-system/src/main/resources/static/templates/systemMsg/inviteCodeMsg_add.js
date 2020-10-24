var layer,form,laydate,inviteRoleDiv
var param = {

}
layui.use(["layer","form","laydate"],()=>{

    layer = layui.layer
    form = layui.form
    laydate = layui.laydate

    /** 渲染日期组件 */
    laydate.render({
        elem:"#overTime",
        type:"datetime",
        range: false,
        theme:"#000000"
    })

    /** 渲染下拉多选 */
    inviteRoleDiv = InitSelect.selectByData("#inviteRoleDiv",(function(){
        var res = BaseAjax.getData(BasePath+"/systemMsg/inviteCode/getRole")
        if(res.code != 200){
            return Feng.error(res.message)
        }
        $.each(res.result,(i,o)=>{
            o.value = o.id
        })
        return res.result
    })())

    /** 提交项，当前页只允许提交一下 */
    form.on("submit(produce)",obj=>{
        var param = {
            model:{
                ...obj.field,
                overTime:BaseDate.dateStrToTimeStamp(obj.field.overTime)
            },
            roleList:getRole()
        }
        if(param.roleList.length < 1){
            return Feng.error("必须勾选携带的角色")
        }
        Request.asyncBody(BasePath+"/systemMsg/inviteCode/saveCode",param).then(res=>{
            /** 加载二维码 */
            var code = res.code
            var codeName = res.codeName
            var roleNames = (function(){
                var temp = ""
                $.each(res.roleList,(i,o)=>{
                    temp += o.name + " | "
                })
                return temp
            })()
            loadORCode(codeName,code,roleNames)

            /** 刷新二维码 */
            $(".refresh").show()
            $(".refresh").click(()=>{
                loadORCode(codeName,code,roleNames)
            })
        })
        return false
    })

})
function getRole(){
    var list = []
    $.each(inviteRoleDiv.getValue(),function(i,o){
        list.push(o.value)
    })
    return list
}
/***********************生成二维码***********************/
function loadORCode(codeName,code,roleNames){
    Request.async(BasePath+"/systemMsg/inviteCode/createQRCode",{code:code,roleNames:roleNames}).then(res=>{{
        var content = template("showQRCode",{encode:res,code:code,roleNames:roleNames,name:codeName})
        $("#content").html(content)
    }})
}