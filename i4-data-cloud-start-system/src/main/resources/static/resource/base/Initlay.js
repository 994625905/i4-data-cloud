/*********************************封装layUI的基础组件 @wangjc*******************************/
var Initlay = {

    /**
     * 初始化table
     * @param elem：初始化容器
     * @param url：后台请求接口
     * @param cols：表头设置
     * @param toolbar：是否带toolbar栏
     * @param where：请求附加条件
     * @param totalRow：是否开启合计
     * @param callback：回调函数
     * @param limit：页码限制条数，默认15
     */
    initTable:function(elem,url,cols,toolbar,where,totalRow,callback,limit){
        var disWhere,disToolbar,disTotalRow,disLimit;
        if(BaseUtil.isEmpty(toolbar)){
            disToolbar = "d";//默认还是要带上按钮组，考虑到右边的分享，不带的话改为false
        }else{
            disToolbar = toolbar;
        }
        if(BaseUtil.isEmpty(where)){
            disWhere = {};
        }else{
            disWhere = where;
        }
        if(BaseUtil.isEmpty(totalRow)){
            disTotalRow = false;
        }else{
            disTotalRow = totalRow;
        }
        if(BaseUtil.isEmpty(limit)){
            disLimit = 15;
        }else{
            disLimit = limit;
        }

        var option = {
            elem:elem,
            id:elem.substr(1),//过滤掉#号
            url:url,
            page:true,
            loading:true,
            limit:disLimit,
            limits:[10,15,25,35,50],
            cellMinWidth:100,
            title:"",
            even:true,//隔行色
            // size:"sm",//表格尺寸
            autoSort:false,//禁用前端自动排序
            contentType: 'application/x-www-form-urlencoded',
            // contentType: 'application/json',
            cols:cols,
            method:"post",
            headers:{
                authorization:BaseCookie.getStr("authorization"),//设置请求头
            },
            totalRow:disTotalRow,//是否开启合计
            toolbar:disToolbar,
            defaultToolbar:[
                {
                    title:"刷新",
                    layEvent:"refresh",
                    icon:"layui-icon-refresh"
                },
                'filter', 'exports', 'print'
            ],
            where:disWhere,
            request:{
                pageName:"current",//分页参数名称
                limitName:"size",
            },
            parseData:function(res){//数据格式解析的回调，res为接口返回值
                if(res.code != 200){
                    Feng.error("表格数据加载有误，请检查后台接口是否正常")
                    return {
                        "code" : res.code,
                        "msg" : res.message
                    };
                }else{
                    return {
                        "code" : res.code == 200 ? 0 : 500,
                        "msg" : res.message,
                        "count" : res.result.total,
                        "data" : res.result.records
                    }
                }
            },
            done:function(res,curr,count){

                //如果异步请求数据方式，res即为你接口返回的信息
                //如果是直接赋值的方式，res即为{data:[],count:99} data为当前页数据。count为数据总长度
                console.log(res);

                if(!BaseUtil.isEmpty(callback)){
                    callback(res);
                }
                console.log(curr);//当前页码
                console.log(count);//数据总量
            }
        };
        return table.render(option);
    },
    /**
     * 重新加载table
     * @param tableRender
     * @param where
     */
    reloadTable:function(tableRender,where = {}){
        tableRender.reload({
            where:where,
            page:{
                curr:1//重新加载分页
            }
        });
    },
    /**
     * 排序table
     * @param tableRender
     * @param obj：排序列参数
     * @param where
     */
    sortTable:function(tableRender,obj,where = {}){
        where.field = obj.field;
        where.sort = obj.type;
        tableRender.reload({
            where:where,
            page:{
                curr:1//重新加载分页
            }
        });
    },
    /**
     * layUI的分页组件,默认展示全部特性
     * @param elemId:容器的id(不带#)
     * @param dateCount：数据总量
     * @param callback：分页回调函数
     * @param first：首次是否加载,(默认不加载)
     */
    loadPage:function(elemId,dateCount,callback,firstload){
        laypage.render({
            elem:elemId,
            count:dateCount,
            limit:15,
            limits:[15, 30, 45, 60, 90],
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function(obj,first){
                if(!BaseUtil.isEmpty(firstload) && firstload == true){
                    callback(obj);
                }else{
                    if(!first){
                        callback(obj);
                    }
                }
            }
        });
    },

    /**
     * 评分星型组件(默认开启半星)
     * @param elem，组件容器（dom选择）
     * @param rateLength：星个数
     * @param info：自定义内容（对象，星型对应内容）
     * @param readonly：是否只读
     * @param callback:回调函数
     * @param half:是否开启半星(默认开启)
     * @param theme：主题（默认黑色）
     * @param value：初始值，默认0
     */
    loadRate:function(elem,rateLength,info,readonly,callback,half,theme,value){
        var disHalf,disTheme,disValue;
        if(BaseUtil.isEmpty(theme)){
            disTheme = "#2F4056";
        }else{
            disTheme = theme;
        }
        if(BaseUtil.isEmpty(value)){
            disValue = 0;
        }else{
            disValue = value;
        }
        if(BaseUtil.isEmpty(half) && half != false){
            disHalf = true;
        }else{
            disHalf = half;
        }
        rate.render({
            elem:elem,
            half:disHalf,
            length:rateLength,
            text:true,
            setText:function(value){//自定义文本的回调
                this.span.text(info[value] || ( value + "星"));
            },
            readonly:readonly,
            theme:disTheme,
            value:disValue,
            choose:function(value){//此处的value是星值
                callback(value);
            }
        });
    },

    /**
     * 自动流加载
     * @param elem：组件容器
     * @param callback：回调函数(返回填充的HTML片段和最大分页数，分页值自己设置)
     * @param isLazyimg：图片是否设置懒加载，默认false，设置后必须使用lay-src
     */
    loadFlowAuto:function(elem,callback,isLazyimg){
        var data,disLazyImg;
        if(BaseUtil.isEmpty(isLazyimg)){
            disLazyImg = false;
        }else{
            disLazyImg = isLazyimg;
        }
        flow.load({
            elem:elem,
            isAuto:true,//默认为true
            isLazyimg:disLazyImg,
            done:function(page, next){
                data = callback(page);
                next(data.content,page < data.page);
            }
        });
    },

    /**
     * 手动流加载
     * @param elem：组件容器
     * @param callback：回调函数
     * @param isLazyimg：图片是否设置懒加载，默认false，设置后必须使用lay-src
     */
    loadFlowClick:function(elem,callback,isLazyimg){
        var data,disLazyImg;
        if(BaseUtil.isEmpty(isLazyimg)){
            disLazyImg = false;
        }else{
            disLazyImg = isLazyimg;
        }
        flow.load({
            elem:elem,
            isAuto:false,
            isLazyimg:disLazyImg,
            done:function(page, next){
                data = callback(page);
                next(data.content,page < data.page);
            }
        });
    },

    /**
     * 时间选择器的加载,默认黑色主题
     * @param elem 绑定元素
     * @param type 类型，默认常规 yyyy-mm-dd
     * @param callback_done：选择完毕的回调
     */
    loadLayDate:function(elem,callback_done,type,value){
        var date_left = elem.substring(1)+"_date_left"
        var date_right = elem.substring(1)+"_date_right"
        var disType,disValue;
        if(BaseUtil.isEmpty(type)){
            disType = "date";
        }else{
            disType = type;
        }
        if(BaseUtil.isEmpty(value)){
            disValue = BaseDate.rangeDate(-1);//默认显示昨天
        }else{
            disValue = value;
        }
        var option = {
            elem:elem,
            type:disType,
            value:disValue,
            max:BaseDate.rangeDate(0),//设置最大时间为当天
            range: false,
            theme:"#000000",
            done:function(value){
                if(value == BaseDate.rangeDate(0)){
                    Feng.tip("数据统计日期截止到"+value,elem,2);
                }
                if(!BaseUtil.isEmpty(callback_done)){
                    callback_done(value);
                }
            }
        };
        laydate.render(option);

        /** 新增日期左右移控件,同时绑定事件 */
        var html =  '<div class="layui-input-inline" style="margin-left: -4px;color: black">' +
                    '   <div class="layui-btn-group">' +
                    '       <button class="layui-btn-primary '+date_left+'" type="button" style="width: 30px;height:38px;text-align:center;font-size:14px;">' +
                    '           <strong><i class="layui-icon layui-icon-left"></i></strong>' +
                    '       </button>' +
                    '       <button class="layui-btn-primary '+date_right+'" type="button" style="width: 30px;height:38px;text-align:center;font-size:14px;">' +
                    '           <strong><i class="layui-icon layui-icon-right"></i></strong>' +
                    '       </button>' +
                    '   </div>' +
                    '</div>';
        $(elem).parent().after(html);

        /** 左移 */
        $("."+date_left).click(function(){
            var d;
            if(disType == "date"){
                d = BaseDate.rangeDateByOper($(elem).val(),-1)
            }
            if(disType == "year"){
                d = parseInt($(elem).val()) - 1 +"";
            }
            $(elem).val(d);
            if(!BaseUtil.isEmpty(callback_done)){
                callback_done(d);
            }
        });
        /** 右移 */
        $("."+date_right).click(function(){
            var d;
            if(disType == "date"){
                d = BaseDate.rangeDateByOper($(elem).val(),1);
            }
            if(disType == "year"){
                d = parseInt($(elem).val()) + 1 +"";
            }

            if(new Date(d) > new Date(BaseDate.rangeDate(0))){
                Feng.tip("时间不可超过当前",elem,2);
            }else{
                $(elem).val(d);
                if(!BaseUtil.isEmpty(callback_done)){
                    callback_done(d);
                }
            }
        });
    },
    /**
     * 日期范围联动框
     * @param elem
     * @param callback_done
     * @param dateRange：默认到昨天
     */
    rangeLayDate:function(elem,callback_done,dateRange){
        var date_left = elem.substring(1)+"_date_left"
        var date_right = elem.substring(1)+"_date_right"
        var disDateRange;
        if(BaseUtil.isEmpty(dateRange)){
            disDateRange = BaseDate.rangeDate(-1)+" - "+BaseDate.rangeDate(-1);
        }else{
            disDateRange = dateRange;
        }
        laydate.render({
            elem: elem,
            range: true,
            theme:"#000000",
            value: disDateRange,
            max:BaseDate.rangeDate(0),//设置最大时间为当天
            done: function (value) {
                var a = value.split(" - ")[1];
                if(a == BaseDate.rangeDate(0)){
                    Feng.tip("数据统计日期截止到"+a,elem,2);
                }
                if(!BaseUtil.isEmpty(callback_done)){
                    callback_done(value);
                }
                return
            }
        });

        /** 新增日期左右移控件,同时绑定事件 */
        var html =  '<div class="layui-input-inline" style="margin-left: -4px;color: black">' +
                    '   <div class="layui-btn-group">' +
                    '       <button class="layui-btn-primary '+date_left+'" type="button" style="width: 30px;height:38px;text-align:center;font-size:14px;">' +
                    '           <strong><i class="layui-icon layui-icon-left"></i></strong>' +
                    '       </button>' +
                    '       <button class="layui-btn-primary '+date_right+'" type="button" style="width: 30px;height:38px;text-align:center;font-size:14px;">' +
                    '           <strong><i class="layui-icon layui-icon-right"></i></strong>' +
                    '       </button>' +
                    '   </div>' +
                    '</div>';
        $(elem).parent().after(html);

        /** 左移 */
        $("."+date_left).click(function(){
            var dateArr = $(elem).val().split(" - ");
            dateArr[0] = BaseDate.rangeDateByOper(dateArr[0],-1);
            dateArr[1] = BaseDate.rangeDateByOper(dateArr[1],-1);

            $(elem).val(dateArr[0]+" - "+dateArr[1]);
            if(!BaseUtil.isEmpty(callback_done)){
                callback_done(dateArr[0]+" - "+dateArr[1]);
            }
            return
        });
        /** 右移 */
        $("."+date_right).click(function(){
            var dateArr = $(elem).val().split(" - ");
            dateArr[0] = BaseDate.rangeDateByOper(dateArr[0],1);
            dateArr[1] = BaseDate.rangeDateByOper(dateArr[1],1);

            if(new Date(dateArr[1]) > new Date(BaseDate.rangeDate(0))){
                Feng.tip("时间不可超过当前",elem,2);
            }else{
                $(elem).val(dateArr[0]+" - "+dateArr[1]);
                if(!BaseUtil.isEmpty(callback_done)){
                    callback_done(dateArr[0]+" - "+dateArr[1]);
                }
            }
            return
        });
    },
    /**
     * 日期范围联动框,绑定非input元素
     * @param elem
     * @param callback_done
     * @param dateRange：默认到昨天
     */
    rangeLayDateByOther(elem,dateRange,callback_done){
        laydate.render({
            elem: elem,
            range: true,
            theme:"#000000",
            value: dateRange,
            done: function (value) {
                if(!BaseUtil.isEmpty(callback_done)){
                    callback_done(value);
                }
                return
            }
        });
    },
    /**
     * 获取指定年月的最后一天
     * @param month：默认当前月
     * @param year：默认当前年
     */
    getEndDate:function(month,year){
        return laydate.getEndDate(month,year);
    },
    /**
     * 点击table行的监听，选中复选框，获取数据
     */
    clickTableRow:function(){
        $(document).on("click", ".layui-table-body table.layui-table tbody tr", function () {
            var index = $(this).attr('data-index');
            var tableBox = $(this).parents('.layui-table-box');
            //存在固定列
            if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length > 0) {
                tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
            } else {
                tableDiv = tableBox.find(".layui-table-body.layui-table-main");
            }
            var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
            if (checkCell.length > 0) {
                checkCell.click();
            }
        });
        //对td的单击事件进行拦截停止，防止事件冒泡再次触发上述的单击事件（Table的单击行事件不会拦截，依然有效）
        $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
            e.stopPropagation();
        });
    },

    /**
     * 相册弹框
     * @param title
     * @param start：开始位置
     * @param array：数组alt,pid,src,thumb
     */
    photo:function(array,title="查看图片",start = 0){
        layer.photos({
            photos: {
                title:title,
                id:"123",
                start:start,
                data:array
            },
            anim:5
        });
    }

};

/*****************************************修改layUI，尊重原生，非万不得已慎用 @wangjc*****************************************/
var ChangeLay = {

    /**
     * layui合并tbody中单元格的函数，此函数适用于合并列冻结的单元格
     * @param fieldName  要合并列的field属性值
     * @param index 要合并列的索引值 从1开始(如果要合并第一列，则index = 1;)
     */
    layuiRowspanByFreeze:function(fieldName,index){
        var tbodyNode = document.getElementsByClassName("layui-table-fixed-l")[index-1];
        var child = tbodyNode.getElementsByTagName("td");
        var childFilterArr = [];
        // 获取data-field属性为fieldName的td
        for(var i = 0; i < child.length; i++){
            if(child[i].getAttribute("data-field") == fieldName){
                childFilterArr.push(child[i]);
            }
        }
        // 获取td的个数和种类
        var childFilterTextObj = {};
        for(var i = 0; i < childFilterArr.length; i++){
            var childText = childFilterArr[i].textContent;
            if(childFilterTextObj[childText] == undefined){
                childFilterTextObj[childText] = 1;
            }else{
                var num = childFilterTextObj[childText];
                childFilterTextObj[childText] = num*1 + 1;
            }
        }
        // 给获取到的td设置合并单元格属性
        for(var key in childFilterTextObj){
            var tdNum = childFilterTextObj[key];
            var canRowspan = true;
            for(var i = 0; i < childFilterArr.length; i++){
                if(childFilterArr[i].getAttribute("data-content") == key){
                    if(canRowspan){
                        childFilterArr[i].setAttribute("rowspan",tdNum);
                        canRowspan = false;
                    }else{
                        childFilterArr[i].style.display = "none";
                    }
                }
            }
        }
    },

    /**
     * layui合并tbody中单元格的函数,此方式适用于没有列冻结的单元格合并
     * @param fieldName  要合并列的field属性值
     * @param index 表格的索引值 从1开始
     * @returns {boolean}
     */
    layuiRowspan:function(fieldName, index){
        var fixedNode = document.getElementsByClassName("layui-table-body")[index - 1];
        if (!fixedNode) {
            return false;
        }
        var child = fixedNode.getElementsByTagName("td");
        var childFilterArr = [];
        // 获取data-field属性为fieldName的td
        for (var i = 0; i < child.length; i++) {
            if (child[i].getAttribute("data-field") == fieldName) {
                childFilterArr.push(child[i]);
            }
        }
        // 获取td的个数和种类
        var childFilterTextObj = {};
        for (var i = 0; i < childFilterArr.length; i++) {
            var childText = childFilterArr[i].textContent;
            if (childFilterTextObj[childText] == undefined) {
                childFilterTextObj[childText] = 1;
            } else {
                var num = childFilterTextObj[childText];
                childFilterTextObj[childText] = num * 1 + 1;
            }
        }
        // 给获取到的td设置合并单元格属性
        for (var key in childFilterTextObj) {
            var tdNum = childFilterTextObj[key];
            var canRowSpan = true;
            for (var i = 0; i < childFilterArr.length; i++) {
                if (childFilterArr[i].textContent == key) {
                    if (canRowSpan) {
                        childFilterArr[i].setAttribute("rowspan", tdNum);
                        canRowSpan = false;
                    } else {
                        childFilterArr[i].style.display = "none";
                    }
                }
            }
        }
    },
    /**
     * layui合并tbody中单元格的函数,此方式适用于没有列冻结的单元格合并
     * @param tableId 
     * @param fieldName
     * @returns {boolean}
     */
    tableRowSpanNoFixedCol:function(tableId, fieldName) {
        if (!tableId && !fieldName) {
            console.log('tableId, fieldName为必填项');
            return false;
        }
        // 获取页面中全部的表格元素
        var allTableNode = document.getElementsByClassName("layui-table-view");

        // 获取lay-id属性为tableId的表格元素的
        var targetTableNode = null;
        if (allTableNode.length > 0) {
            for (var index = 0, length = allTableNode.length; index < length; index++) {
                // 通过lay-id属性过滤表格元素
                var tableLayId = allTableNode[index].getAttribute("lay-id");
                if (tableLayId === tableId) {
                    targetTableNode = allTableNode[index];
                    break;
                }
            }
        }
        if (!targetTableNode) {
            console.log('没有找到ID为：' + tableId + '的表格, 请升级您的layui版本');
            return false;
        }

        // 开始合并单元格操作
        var tBodyNode = targetTableNode.getElementsByClassName("layui-table-body")[0];

        var tdNodes = tBodyNode.getElementsByTagName("td");
        var childFilterArr = [];
        // 获取data-field属性为fieldName的td
        for (var i = 0; i < tdNodes.length; i++) {
            if (tdNodes[i].getAttribute("data-field") === fieldName) {
                childFilterArr.push(tdNodes[i]);
            }
        }

        // 获取td的个数和种类
        var childFilterTextObj = {};
        var childFilterArrLength = childFilterArr.length;
        for (var j = 0; j < childFilterArrLength; j++) {
            var childText = childFilterArr[j].textContent;
            if (childFilterTextObj[childText] === undefined) {
                childFilterTextObj[childText] = 1;
            } else {
                var num = childFilterTextObj[childText];
                childFilterTextObj[childText] = num * 1 + 1;
            }
        }
        // 给获取到的td设置合并单元格属性
        for (var key in childFilterTextObj) {
            var tdNum = childFilterTextObj[key];
            var canRowSpan = true;
            var needChangeBackGroundNodes = [];
            var addEventNode = null;
            for (var h = 0; h < childFilterArrLength; h++) {
                if (childFilterArr[h].textContent === key) {
                    needChangeBackGroundNodes.push(childFilterArr[h]);
                    if (canRowSpan) {
                        childFilterArr[h].setAttribute("rowspan", tdNum);
                        addEventNode = childFilterArr[h];
                        canRowSpan = false;
                    } else {
                        childFilterArr[h].style.display = "none";
                    }
                }
            }

            // 以下为单元格鼠标悬浮样式修改(使用闭包)
            (function (addEventNode, needChangeBackGroundNodes) {
                addEventNode.onmouseover = function () {
                    for (var index = 0, length = needChangeBackGroundNodes.length; index < length; index++) {
                        needChangeBackGroundNodes[index].parentNode.style.background = "#f2f2f2"; // 我这里的单元格鼠标滑过背景色为layui默认，你可以更改为你想要的颜色。
                    }
                };
                addEventNode.onmouseout = function () {
                    for (var index = 0, length = needChangeBackGroundNodes.length; index < length; index++) {
                        needChangeBackGroundNodes[index].parentNode.style.background = "";
                    }
                };
            })(addEventNode, needChangeBackGroundNodes);
        }
    }

};