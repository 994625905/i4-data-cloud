var BasePath = "/i4-data-cloud-start-auth";

var LAYOUT_SIZE = {
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