let layer,form

layui.use(["layer","form"],()=>{

    layer = layui.layer
    form = layui.form

    $("#endTime").val(BaseDate.timeStampToDate(modelEndTime))

})