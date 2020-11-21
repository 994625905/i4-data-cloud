const BasePath = "/i4-data-cloud-start-system";

const LAYOUT_SIZE = {
    /** 标准宽高 */
    BASE_WIDTH(){
        return $(window).width()*0.8+"px";
    },
    BASE_HEIGHT(){
        return $(window).height()-100+"px"
    },
    /** 小型宽高 */
    SM_WIDTH(){
        return $(window).width()*0.5+"px"
    },
    SM_HEIGHT(){
        return $(window).height()-400+"px"
    },
    /** 同级宽高 */
    LEVEL_WIDTH(){
        return $(window).width()+"px"
    },
    LEVEL_HEIGHT(){
        return $(window).height()+"px"
    }
}
/******************************table列宽度*********************************************/
const TABLE_COL_WIDTH = {
    one_Cols(num = 2){//值一个列多字
        return 25*num
    },
    text:320,//文本宽度
    date_no_time:120,//不带时间日期宽度
    date:170,//日期宽度
    normal:150,//普通宽度
    number_min:60,
    number_max:90,
    tool(num = 1){//操作列宽
        return 85*num
    },
}
/******************************layui全局配置第三方插件使用*****************/
layui.config({
    base: BasePath+'/resource/plugin/layui_third/' //可以使用绝对路径
}).extend({
    formSelects: 'formSelects-v4',//多选下拉框
    treeGrid:'treeGrid',//树级table
    treetable:"treetable"//树形table
});