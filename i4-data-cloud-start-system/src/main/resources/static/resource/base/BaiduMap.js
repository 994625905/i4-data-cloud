/**
 * 百度地图API的封装
 * @author wangjc
 */
var BaiduMap = {

    init:(elemId,inputId,searchResultPanelId,address = "")=>{

        /** 初始化地图,设置城市和地图级别。暂时先写死吧 */
        let map = new BMap.Map(elemId);
        map.centerAndZoom("深圳",12);
        map.enableScrollWheelZoom();//滚轮放大缩小

        let ac = new BMap.Autocomplete({ // 建立一个自动完成的对象
            input : inputId,
            location : map
        });
        if(!BaseUtil.isEmpty(address)){
            ac.setInputValue(address)
        }

        /** 鼠标放在下拉列表上的事件 */
        ac.addEventListener("onhighlight", function(e) {
            let str = "";
            let _value = e.fromitem.value;
            let value = "";
            if (e.fromitem.index > -1) {
                value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            }
            str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

            value = "";
            if (e.toitem.index > -1) {
                _value = e.toitem.value;
                value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            }
            str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
            document.getElementById(searchResultPanelId).innerHTML = str;
        });

        /** 鼠标点击下拉列表后的事件 */
        let myValue;
        ac.addEventListener("onconfirm", function(e) {
            let _value = e.item.value;
            myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            document.getElementById(searchResultPanelId).innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

            /** 清除地图上所有覆盖物 */
            map.clearOverlays();
            function myFun(){
                let pp = local.getResults().getPoi(0).point;//获取第一个智能搜索的结果
                map.centerAndZoom(pp, 18);
                map.addOverlay(new BMap.Marker(pp));    //添加标注
            }
            let local = new BMap.LocalSearch(map, { //智能搜索
                onSearchComplete: myFun
            });
            local.search(myValue);
        });

        return map;
    }

}