/*************************************基础Ajax请求 @com.wangjc***********************************/
var BaseAjax = {
	getDataAsync:function(ajax_url,param,success,contentType){//异步请求，回调函数一定要有
		var disParam,index;
		if(BaseUtil.isEmpty(param)){
			disParam = null;
		}else{
			disParam = param;
		}
		var ajaxOption={
			cache:false,//关闭缓存
			async:true,//默认为true
			url:ajax_url,
			data:disParam,
			method:"post",
			dataType:"json",

			beforeSend:function(XMLHttpRequest){
				index = Feng.loading("数据请求中。。");

				/** 设置请求头的token，从cookie中读取 */
				var authorization = BaseCookie.getStr("authorization");
				if(!BaseUtil.isEmpty(authorization)){
					XMLHttpRequest.setRequestHeader("authorization", authorization);
				}
			},
			success:function(resultData){
				Feng.close(index);
				success(resultData);
			},
			error: function (err) {
				Feng.close(index);
				Feng.close("请求超时，请检查接口是否正常")
			}
		}
		//之前封装遗漏的，导致很多地方编辑保存，传参不合法
		if(contentType){
			ajaxOption.contentType="application/json; charset=UTF-8";
		}
		$.ajax(ajaxOption);
	},
	getDataAsync_Map:function(ajax_url,param,success){
		return BaseAjax.getDataAsync(ajax_url,JSON.stringify(param),success,true);
	},
	getData_Map:function(ajax_url,param,loadFlag){
		return BaseAjax.getData(ajax_url,JSON.stringify(param),true,loadFlag);
	},
	getData:function(ajax_url,param,contentType,loadFlag){//同步加载,是否带loading
		var disParam,result,index;
		if(BaseUtil.isEmpty(param)){
			disParam = null;
		}else{
			disParam = param;
		}
		var ajaxOption={
			cache:false,//关闭缓存
			async:false,
			url:ajax_url,
			data:disParam,
			method:"post",
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
				/** 设置请求头的token，从cookie中读取 */
				var authorization = BaseCookie.getStr("authorization");
				if(!BaseUtil.isEmpty(authorization)){
					XMLHttpRequest.setRequestHeader("authorization", authorization);
				}
			},
			success:function(resultData){
				result = resultData;
			},
			error: function (err) {
				if(loadFlag == true){//默认不带loading加载
					Feng.close(index);
				}
				Feng.close("请求超时，请检查接口是否正常")
			}
		}
		//之前封装遗漏的，导致很多地方编辑保存，传参不合法
		if(contentType){
			ajaxOption.contentType="application/json; charset=UTF-8";
		}
		if(loadFlag == true){//默认不带loading加载
			ajaxOption.beforeSend=function(XMLHttpRequest){
				index = Feng.loading();

				/** 设置请求头的token，从cookie中读取 */
				var authorization = BaseCookie.getStr("authorization");
				if(!BaseUtil.isEmpty(authorization)){
					XMLHttpRequest.setRequestHeader("authorization", authorization);
				}
			};
			ajaxOption.success=function(resultData){
				Feng.close(index);
				result = resultData;
			};
		}
		$.ajax(ajaxOption);
		return result;
	}
}
/*************************************公共util函数 @com.wangjc*********************************/
var BaseUtil = {
	/**判断非空*/
	isEmpty:function(obj){//判断非空
		if(obj == "0"){
			return false;
		}
		if(typeof obj == "undefined" || obj == null || obj == "" || obj == "null" || obj == "undefined"){
			return true;
		}else{
			return false;
		}
	},

	/**替换字符串*/
	replaceAll:function(info,regax,replace){
		var t = eval("/"+regax+"/g");
		return info.replace(t,replace);
	},

	/**获取页面请求链接中包含的参数*/
	getPageParam:function(param){
		var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null){
			return unescape(r[2]);
		}else{
			return "";
		}
	},

	/** 获取form表单的输入的参数 */
	getParamByFormId:function(formId){
		var a, o, h, i, e;
		a = $("#"+formId).serializeArray();
		o = {};
		h = o.hasOwnProperty;
		for (i = 0; i < a.length; i++) {
			e = a[i];
			if (!h.call(o, e.name)) {
				o[e.name] = BaseUtil.trim(e.value);
			}
		}
		return o;
	},

	/** 去左右空格 */
	trim:function(s){
		return s.replace(/(^\s*)|(\s*$)/g, "");
	},

	/** 跳链接 */
	redirect:function(url){
		location.href = url;
	},

	/** 打开新标签 */
	openBlank(url,title = "爱思数据云平台"){
		var newWin = window.open(url, "_blank")
		newWin.document.title=title
	},

	/** 延时执行 */
	setTimeout:function(method,times){//延时函数
		setTimeout(method,times);
	},
	/** 点击复制 */
	clickCopy:function(copyInfo){
		var oInput = document.createElement('input');//虚拟一个对象
		oInput.value = copyInfo;
		document.body.appendChild(oInput);
		oInput.select(); // 选择对象
		document.execCommand("Copy"); // 执行浏览器复制命令
		oInput.className = 'oInput';
		oInput.style.display='none';
		Feng.success("已复制到剪切板。。");
	},
	/** 刷新父页面 */
	reloadParent:function(){
		parent.location.reload();
	}
}

/***************************************基础的校验规则 @com.wangjc***************************************************/
var BaseRegax = {
	/** 校验手机号 */
	isMobile:function(mobile){
		return /^[1][3,4,5,7,8,9][0-9]{9}$/.test(mobile);
	},

	/** 校验邮箱 */
	isEmail:function(email){
		return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(email);
	},

	/** 验证整数 */
	isInteger:function(obj){
		return typeof obj === 'number' && obj%1 === 0;
	},

	/** 验证数字 */
	isNum:function(num){
		var reNum=/^\d*$/;
		return(reNum.test(num));
	},

	/**非负整数验证*/
	regaxZInteger:function(value,type){//type：是否包括0
		if(BaseUtil.isEmpty(type)){
			if(/^\d+$/.test(value)){
				return true;
			}else{
				return false;
			}
		}else{
			if(/^\+?[1-9][0-9]*$/.test(value)){
				return true;
			}else{
				return false;
			}
		}
	},

	/**正小数验证*/
	regaxZFload:function(value){
		if(/^[0-9]+(.[0-9]{1,4})?$/.test(value)){
			return true;
		}else{
			return false;
		}
	},

	/** 是否含有中文验证 */
	haveCN:function(value){
		return /.*[\u4e00-\u9fa5]+.*$/.test(value)
	}
}
/*************************************cookie函数***********************************/
var BaseCookie = {

	/**
	 * 设置cookie值
	 * @param name
	 * @param value
	 * @param options：默认1天，根路径
	 */
	setStr(name,value,options = { expires: 1, path: '/' }){
		$.cookie(name,value,options)
	},

	/**
	 * 获取cookie值，前提是在base之前引入了jquery.cookie.js
	 * @param name
	 */
	getStr(name){
		return $.cookie(name)
	},

	/**
	 * 获取cookie值，数组,为空就给初始化值，
	 * @param name
	 */
	getArray(name){
		var res = $.cookie(name)
		if(BaseUtil.isEmpty(res)){
			return new Array()
		}
		return JSON.parse(res)
	},

	/**
	 * 获取cookie值，对象,为空就给初始化值，
	 * @param name
	 */
	getObj(name){
		var res = $.cookie(name)
		if(BaseUtil.isEmpty(res)){
			return new Object()
		}
		return JSON.parse(res)
	}
}
/*************************************日期工具**************************************/
var BaseDate = {

	/**
	 * 时间戳转字符串，精确到秒
	 * @param timestamp:时间戳
	 * @returns {*}
	 * @constructor
	 */
	timeStampToDate:function(timestamp){
		if(BaseUtil.isEmpty(timestamp)){
			return null;
		}else{
			var d = new Date(timestamp * 1000);
			return (d.getFullYear())+"-"+(d.getMonth()+1)+"-"+(d.getDate())+" "+
				( d.getHours().toString().length>1?d.getHours():"0"+d.getHours() )+":"+
				(d.getMinutes().toString().length>1?d.getMinutes():"0"+d.getMinutes())+":"+
				(d.getSeconds().toString().length>1?d.getSeconds():"0"+d.getSeconds());
		}
	},

	/**
	 * 字符串转为时间戳
	 * @param DateStr
	 * @returns {*}
	 * @constructor
	 */
	dateStrToTimeStamp:function(DateStr,type = ""){
		if(type == "start"){
			DateStr += " 00:00:00";
		}
		if(type == "end"){
			DateStr += " 23:59:59";
		}
		return new Date(DateStr).getTime()/1000;
	},

	/**
	 * 当前时间往前推几天，
	 * @param before：负数之前，正数之后
	 */
	rangeDate:function(before){
		var today = new Date();
		today.setTime(today.getTime()+(24*before)*60*60*1000);
		var y = today.getFullYear();
		var m = (today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1);// 获取当前月份的日期
		var d = today.getDate() < 10 ? "0" + today.getDate() : today.getDate();
		var pre = y + "-" + m + "-" + d;
		return pre;
	},

	/**
	 * 指定时间往前推几天，
	 * @param before：负数之前，正数之后
	 */
	rangeDateByOper:function(operDate,before){
		var today = new Date(operDate);
		today.setTime(today.getTime()+(24*before)*60*60*1000);
		var y = today.getFullYear();
		var m = (today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1);// 获取当前月份的日期
		var d = today.getDate() < 10 ? "0" + today.getDate() : today.getDate();
		var pre = y + "-" + m + "-" + d;
		return pre;
	},

	/**
	 * 为了迎合数据库yyyyMMdd的格式，此处主要服务于日期联动框，格式化
	 * @param date
	 */
	getDateByRangeLay:function(date){
		var arr = date.split(" - ");
		var res = {};
		res.startDate = BaseUtil.replaceAll(arr[0],"-","");
		res.endDate = BaseUtil.replaceAll(arr[1],"-","");
		return res;
	},

	/**
	 * 获取日期之间的天数
	 * @param startDate：计划开始时间
	 * @param endDate：计划结束时间
	 * @param dayLength：每隔几天，0-代表每天，1-代表日期间隔一天
	 */
	getRangeDatelist:function(startDate, endDate, dayLength) {
		var res = [];
		res.push(startDate);
		for (var i = 0 ;; i++) {
			var getDate = BaseDate.getTargetDate(startDate, dayLength);
			startDate = getDate;
			if (getDate <= endDate) {
				res.push(getDate);
			} else {
				break;
			}
		}
		return res;
	},

	/**
	 *
	 * @param date 开始时间
	 * @param dayLength 每隔几天，0-代表获取每天，1-代表日期间隔一天
	 * @returns {string}
	 */
	getTargetDate:function(date,dayLength) {
		dayLength = dayLength + 1;
		var tempDate = new Date(date);
		tempDate.setDate(tempDate.getDate() + dayLength);
		var year = tempDate.getFullYear();
		var month = tempDate.getMonth() + 1 < 10 ? "0" + (tempDate.getMonth() + 1) : tempDate.getMonth() + 1;
		var day = tempDate.getDate() < 10 ? "0" + tempDate.getDate() : tempDate.getDate();
		return year + "-" + month + "-" + day;
	},
	/**
	 * 获取两个日期之间天数差
	 * @param start
	 * @param end
	 * @returns {number}
	 */
	getDiff:function(start,end){
		var days = new Date(end).getTime() - new Date(start).getTime();
		var time = parseInt(days / (1000 * 60 * 60 * 24));
		return time;
	},
	/**
	 * 20181220 => 2018-12-20
	 * @param date
	 * @returns {string}
	 */
	d8ToD11(date){
		return [date.substring(0, 4), date.substring(4, 6), date.substring(6, 8)].join('-')
	},
	/**
	 * 获取当月的第一天
	 * @returns {string}
	 */
	getStartByCurrMonth(){
		return this.rangeDate(0).substr(0,8)+"01"
	},
	/**
	 * 根据时间戳来换算时间,默认精确到秒
	 * @param shorttime
	 * @returns {string}
	 */
	getTimeStr(shorttime){
		if(shorttime < 60){
			return shorttime+"秒";
		}else if(shorttime < 3600){
			return parseInt(shorttime/60)+"分"+shorttime%60+"秒";
		}else if(shorttime < 86400){
			return parseInt(shorttime/3600)+"小时"+parseInt(shorttime%3600/60)+"分"+parseInt(shorttime%3600%60)+"秒"
		}else{
			return parseInt(shorttime/86400)+"天"+ parseInt(shorttime%86400/3600)+"小时"+ parseInt(shorttime%86400%3600/60)+"分"+parseInt(shorttime%86400%3600%60)+"秒"
		}
	},
	/**
	 * 获取当前时间戳，精确到秒
	 */
	currTime(){
		return parseInt(new Date().getTime()/1000)
	},
	/**
	 * 获取当前年
	 */
	currYear(){
		return new Date().getFullYear()
	},
	/**
	 * 获取当前月
	 */
	currMonth(){
		return new Date().getMonth()+1
	},
	/**
	 * 获取当前月的第一天
	 */
	currMonthFirstDay(){
		var date = new Date();
		date.setDate(1);
		var month = parseInt(date.getMonth()+1);
		var day = date.getDate();
		if (month < 10) {
			month = '0' + month
		}
		if (day < 10) {
			day = '0' + day
		}
		return date.getFullYear() + '-' + month + '-' + day
	},
	/**
	 * 获取当前周次
	 */
	currWeekNum(){
		let day = new Date();//如果当年的第一天不是星期一，则该日所属周数为上一年的最后一周

		if(day.getDay() !== 1){
			day = day.getTime()-24*60*60*1000
			day = new Date(day);
		}
		day.setMonth(0);
		day.setDate(1);
		day.setHours(0);
		day.setMinutes(0);
		day.setSeconds(0);//到这里就得到该年的一月一日

		let today = new Date();

		//计算日期是一年中的第几天
		let rankDay = Math.ceil((today.getTime()-day.getTime())/(1000*24*60*60))
		let rankWeek = Math.ceil(rankDay/7)
		return  rankWeek < 10?("0"+rankWeek):rankWeek
	},
	/**
	 * 当前周一对应的日期
	 * @returns {string}
	 */
	currMonday(){
		var curr = new Date().getDay();
		return this.rangeDate(-(curr - 1))
	},
	/**
	 * 获取当前周五对应的日期
	 * @returns {string}
	 */
	currFriday(){
		var curr = new Date().getDay();
		return this.rangeDate( 5 - curr);
	},
	/**
	 * 根据时间获取周次
	 * @param dateStr
	 */
	getWeekByDate(dateStr){
		let temp = new Date(dateStr)
		switch (temp.getDay()){
			case 1:
				return "星期一"
			case 2:
				return "星期二"
			case 3:
				return "星期三"
			case 4:
				return "星期四"
			case 5:
				return "星期五"
			case 6:
				return "星期六"
			case 0:
				return "星期日"
		}
	}
};

