var layer,table,form
var tableRender
var param = {

}
layui.use(["layer","table","form"],()=>{

    layer = layui.layer
    table = layui.table
    form = layui.form

    /** 初始化表格 */
    loadTable()

    /** 查询 */
    form.on("submit(search)",obj=>{
        param.loginName = obj.field.loginName
        param.departmentId = obj.field.departmentId
        param.gender = obj.field.gender
        refresh()
        return false;
    })

    /** table工具栏 */
    table.on("toolbar(userTable)",obj=>{
        if(obj.event == "add"){
            Feng.loadWindow("新增用户（详细信息用户初始化登陆会要求自行完善）",BasePath+"/systemMsg/userMsg/add",LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "refresh"){
            refresh()
        }
    })

    /** table操作列 */
    table.on("tool(userTable)",obj=>{
        if(obj.event == "change"){
            var contentInfo = template("changeStatus",{status:obj.data.status})
            Feng.infoDetail("修改"+obj.data.realname+"状态",contentInfo,()=>{
                form.render("select")
            },Index=>{
                Request.async(BasePath+"/systemMsg/userMsg/changeStatus",{
                    status:$("select[name='userStatus']").val(),
                    userId:obj.data.id
                }).then(res=>{
                    Feng.close(Index)
                    Feng.success("修改成功")
                    refresh()
                })
            })
        }
        if(obj.event == "detail"){
            Feng.loadWindow(obj.data.realname+"详情",BasePath+"/systemMsg/userMsg/detail?userId="+obj.data.id,LAYOUT_SIZE.SM_HEIGHT(),LAYOUT_SIZE.SM_WIDTH())
        }
        if(obj.event == "setRole"){
            Request.async(BasePath+"/systemMsg/userMsg/getUserRole",{userId:obj.data.id}).then(res=>{
                var contentInfo = template("userRole",{roleList:res.roleList,userRole:res.userRole})
                Feng.infoDetail(obj.data.realname+"-->设置角色",contentInfo,()=>{

                    /** 绑定权限标签的点击事件 */
                    $("label.role").click(function(){
                        $(this).toggleClass("search-button");
                    });

                },Index=>{
                    /** 获取选中的权限，提交 */
                    var roleList = [];
                    $.each($("label.role.search-button"),function(i,o){
                        roleList.push($(o).attr("data-id"))
                    })
                    Request.asyncBody(BasePath+"/systemMsg/userMsg/setUserRole",{userId:obj.data.id,roleList:roleList}).then(r=>{
                        Feng.success(obj.data.realname+"设置角色成功")
                        BaseUtil.setTimeout(()=>{
                            Feng.close(Index)
                            refresh()
                        },500)
                    })
                },LAYOUT_SIZE.SM_WIDTH(),LAYOUT_SIZE.SM_HEIGHT())
            })
        }
        if(obj.event == "delete"){
            Feng.confirm("确定删除"+obj.data.realname+"吗？",()=>{
                Request.async(BasePath+"/systemMsg/userMsg/deleteById",{
                    userId:obj.data.id
                }).then(res=>{
                    Feng.success("删除成功")
                    refresh()
                })
            })
        }
    })

})
/********************************加载表格******************************/
function loadTable(){
    var tabCols = [[
        {field:"headimage",title:"头像",align:"center",templet:"#imageUrl"},
        {field:"loginname",title:"登陆名称"},
        {field:"realname",title:"真实姓名"},
        {field:"gender",title:"性别",templet(d){return d.gender == "1"?"男":(d.gender == "2"?"女":"未知")}},
        {field:"phone",title:"联系电话"},
        {field:"email",title:"电子邮件"},
        {field:"departmentName",title:"所属部门",width:TABLE_COL_WIDTH.tool(2) ,templet(d){return "<label class='layui-btn layui-btn-sm layui-btn-primary' onclick='changeDepartment(\""+d.departmentName+"\","+d.id+")'>"+d.departmentName+"</label>"}},
        {field:'status',title: '状态',align:"center",toolbar:"#statusTool",width: TABLE_COL_WIDTH.tool(1)},
        {fixed:'right',title: '操作',align:"center",toolbar:"#tool",width:TABLE_COL_WIDTH.tool(3)}
    ]]
    tableRender = Initlay.initTable("#userTable",BasePath+"/systemMsg/userMsg/loadTable",tabCols,"#toolbar",param)
}
/*************************加载封面************************/
function showHeadImage(name,id,headimage){
    Initlay.photo([{
        alt:name,
        pid:id,
        src:headimage
    }])
}
function changeDepartment(departmentName,userId){
    Request.async(BasePath+"/systemMsg/departmentMsg/getList").then(res=>{
        Feng.infoDetail("设置部门",template("changeDepartment",{list:res,departmentName:departmentName}),null,index=>{
            Request.async(BasePath+"/systemMsg/userMsg/changeDepartment",{
                userId:userId,
                departmentId:$("select[name='department']").val()
            }).then(r=>{
                layer.close(index)
                Feng.success("设置部门成功")
                refresh()
            })
        },"420px","240px",5);
    })
}
/*************************刷新************************/
function refresh(){
    return Initlay.reloadTable(tableRender,param)
}