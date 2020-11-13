/************************************初始化图表 @wangjc************************************/
var InitChart = {

    /**
     * 加载折线图，分组和单独通用
     * @param t：装载容器
     * @param data：ajax请求的数据
     * @param title:标题
     * @param show：是否只展示第一个,
     * @param dataShow：折线的数据是否显示（默认显示）,
     * @param scheduleSet：折线的数据是否显示（默认显示）,
     */
    line: function (t, data, title, show,dataShow,scheduleSet) {
        var disTitle = BaseUtil.isEmpty(title)?"":title;

        var legend = {};
        legend.data = data.legend;

        if (!BaseUtil.isEmpty(show) && show == true) {
            var selected = {};
            $.each(data.legend, function (i, o) {
                selected[o] = false;
            });
            selected[data.legend[0]] = true;//展示第一个
            legend.selected = selected;
        }

        /** 折线的数据可见显示 */
        var seriesDate = [];
        $.each(data.series,function(i,o){
            if(dataShow != false){
                o.itemStyle = { normal: {label : {show: true}}};
            }
            seriesDate.push(o);
        });

        var option = {
            title: {
                text: disTitle
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: legend,
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {},//保存图片
                    dataView: {readOnly: false},//数据展示
                    myShare: { //自定义按钮参数必须以my开头
                        show: true,
                        title: "分享图表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    },
                    mySchedule:{

                    }
                },
                right: 20
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                "axisLabel": {
                    interval: 'auto',
                    rotate: 30
                },
                data: data.xAxis
            },
            yAxis: {
                type: 'value',
                scale: 1
            },
            series: seriesDate
        };

        /** 定时任务的设置 */
        if(scheduleSet){
            option.toolbox.feature.mySchedule = {
                show: true,
                title: "定时分享",
                icon:"image://"+BasePath+"/resource/image/schedule.png",
                onclick: function () {

                }
            }
        }

        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option, {notMerge: true});
        $(window).resize(t.resize);
    },

    /**
     * 加载折线图，对比折线
     * @param t
     * @param data：两条线
     * @param title
     */
    lineContrast:function(t, data, title,show){
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        var legend = {};
        var legendDate = [];
        $.each(data[0].legend,function(i,o){
            legendDate.push(o);
            legendDate.push(data[1].legend[i]);
        });
        // legend.data = [data[0].legend,data[1].legend];
        legend.data;
        /** 折线的数据可见显示 */
        var seriesDate = [];
        $.each(data[0].series,function(i,o){
            // o.itemStyle = { normal: {label : {show: true}}};//转折处数据显示
            o.symbol = "none";//去掉圆点
            o.xAxisIndex = 1;//刻度对齐
            o.label = {
                normal: {
                    show: true,
                    position: 'top',
                    formatter: '{c}'
                }
            };
            seriesDate.push(o);
        });
        $.each(data[1].series,function(i,o){
            // o.itemStyle = { normal: {label : {show: true}}};//转折处数据显示
            o.symbol = "none";//去掉圆点
            o.xAxisIndex = 1;
            o.label = {
                normal: {
                    show: true,
                        position: 'top',
                        formatter: '{c}'
                }
            };
            seriesDate.push(o);
        });
        /**只展示第一个*/
        if (!BaseUtil.isEmpty(show) && show == true) {
            var selected = {};
            $.each(data[0].legend, function (i, o) {
                selected[o] = false;
            });
            $.each(data[1].legend, function (i, o) {
                selected[o] = false;
            });
            selected[data[0].legend[0]] = true;//展示第一个
            selected[data[1].legend[0]] = true;//展示第一个
            legend.selected = selected;
        }

        var option = {
            title: {
                text: disTitle
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: legend,
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {},//保存图片
                    dataView: {readOnly: false},//数据展示
                    myShare: { //自定义按钮参数必须以my开头
                        show: true,
                        title: "分享图表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    }
                },
                right: 20
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    interval: 'auto',
                    // rotate: 30,X轴密度
                },
                data: data[0].xAxis
            },{
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    interval: 'auto',
                    // rotate: 30
                },
                data: data[1].xAxis
            }],
            yAxis: {
                type: 'value',
                scale: true
            },
            series: seriesDate
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option, {notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载折线图，可缩放，拉伸
     * @param t
     * @param data
     * @param title
     */
    lineZoom:function(t, data, title){
        var disTitle = BaseUtil.isEmpty(title)?"":title;

        var legend = {};
        legend.data = data.legend;

        /** 折线的数据可见显示 */
        var seriesDate = [];
        $.each(data.series,function(i,o){
            // o.itemStyle = { normal: {label : {show: true}}};
            o.symbolSize = 8;
            o.hoverAnimation = false;
            seriesDate.push(o);
        });

        var option = {
            title: {
                text: disTitle
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: legend,
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {},//保存图片
                    dataView: {readOnly: false},//数据展示
                    myShare: { //自定义按钮参数必须以my开头
                        show: true,
                        title: "分享图表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    },
                    restore: {}
                },
                right: 20
            },
            dataZoom: [{
                show: true
            },{
                type: 'inside',
                realtime: true,
                start: 0,
                end: 100,
                xAxisIndex: [0]
            }
            ],
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    interval: 'auto',
                    // rotate: 30,//密度
                    onZero: true
                },
                data: data.xAxis
            },
            yAxis: {
                type: 'value',
                scale: 1
            },
            series: seriesDate
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option, {notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载折线图，可缩放，拉伸（Y轴倒放）
     * @param t
     * @param data
     * @param title
     */
    lineZoomUpend:function(t, data, title,show,showMax){
        var disTitle = BaseUtil.isEmpty(title)?"":title;

        var legend = {};
        legend.data = data.legend;

        /** 只展示第一个 */
        if (!BaseUtil.isEmpty(show) && show == true) {
            var selected = {};
            $.each(data.legend, function (i, o) {
                selected[o] = false;
            });
            selected[data.legend[0]] = true;//展示第一个
            legend.selected = selected;
        }

        /** 折线的数据可见显示 */
        var seriesDate = [];
        $.each(data.series,function(i,o){
            // o.itemStyle = { normal: {label : {show: true}}};
            o.symbolSize = 8;
            o.hoverAnimation = false;
            if(showMax){
                o.markLine = {
                    data : [
                        {type : 'max', name: '最大值'}
                    ]
                };
            }
            seriesDate.push(o);
        });

        var option = {
            title: {
                text: disTitle
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: legend,
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {},//保存图片
                    dataView: {readOnly: false},//数据展示
                    myShare: { //自定义按钮参数必须以my开头
                        show: true,
                        title: "分享图表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    },
                    restore: {}
                },
                right: 20
            },
            dataZoom: [{
                show: true
            },{
                type: 'inside',
                realtime: true,
                start: 0,
                end: 100,
                xAxisIndex: [0]
            }
            ],
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    interval: 'auto',
                    // rotate: 30,//密度
                    onZero: true
                },
                data: data.xAxis
            },
            yAxis: {
                name: "排名",
                nameLocation: 'middle',
                nameGap: 42,
                type: "value",
                scale: 1,
                inverse: 1,
                minInterval: 1
            },
            series: seriesDate
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option, {notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载柱状图，单独柱
     * @param t：选择容器
     * @param data：数据
     * @param title：标题
     */
    bar: function (t, data, title) {
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        var option = {
            title: {
                text: disTitle
            },
            color: ['#000'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false},
                    myShare: {
                        show: true,
                        title: "分享报表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    }
                },
                right: 20
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '1%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: data.xAxis,
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        interval: 0,
                        rotate: 30
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: data.name,
                    type: 'bar',
                    barWidth: '60%',
                    barMaxWidth: '60',
                    data: data.data,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    }
                }
            ]
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option,{notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载柱状图，单独柱（静态数据渲染）
     * @param t：选择容器
     * @param name：柱名称
     * @param list：集合数据
     * @param keyName：
     * @param keyCount：
     * @param title：标题
     * @param otherKey：其他需要补充的key
     */
    barStatic:function(t, name,list,keyName,keyCount,title,otherKey){
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        var data = [];
        var xAxis = [];
        $.each(list,function(i,o){
            xAxis.push(o[keyName]);

            var m ={name:o[keyName],value:o[keyCount]};
            m[otherKey] = o[otherKey];
            data.push(m);
        });
        var option = {
            title: {
                text: disTitle
            },
            color: ['#000'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false},
                    myShare: {
                        show: true,
                        title: "分享报表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    }
                },
                right: 20
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '1%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: xAxis,
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        interval: 0,
                        rotate: 30
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: name,
                    type: 'bar',
                    barWidth: '60%',
                    barMaxWidth: '60',
                    data: data,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    }
                }
            ]
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option,{notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载柱状图，组合柱
     * @param t
     * @param data
     * @param title
     */
    barGroup:function(t,data, title){
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        /** 构造series */
        var disSeries = [];
        $.each(data.data,function(i,o){
            var obj = {
                            name: data.legend[i],
                            type: 'bar',
                            barGap:0,
                            data: o,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            }
                        };
            disSeries.push(obj);
        });
        var option = {
            title: {
                text: disTitle
            },
            // color: ['#000'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:data.legend
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false},
                    myShare: {
                        show: true,
                        title: "分享报表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    }
                },
                right: 20
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: data.xAxis
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: disSeries
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option,{notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载组合柱状图饼图
     * @param t
     * @param data
     * @param title
     */
    barAndPie:function(t,data,title){
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        var option = {
            title: {
                text: disTitle
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} <br/>{a} : {c}"
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false}
                },
                right: 20
            },
            grid: {
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : data.bar.xdata,
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        interval: 0,
                        rotate: 30
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name: data.title,
                    type: 'bar',
                    barWidth: '60%',
                    barMaxWidth: '60',
                    data: data.bar.series,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: ['#000000']
                        }
                    }
                },
                {
                    name: data.title,
                    type: 'pie',
                    radius : '45%',
                    center: ['85%', '40%'],
                    data: data.pie.series,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },
                    tooltip: {
                        formatter: "{b} <br/>{a} : {c} ({d}%)"
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'outside',
                            formatter: '{b} {d}%'
                        }
                    }
                }
            ]
        };
        t.setOption(option);
        $(window).resize(t.resize);
    },
    /**
     * 上下堆叠柱
     * @param t
     * @param data
     * @param title
     */
    stackBar:function(t, data,title){
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        var disSeries = [];
        $.each(data.legend,function(i,o){
            var series = {
                name: o,
                type: 'bar',
                stack: '总量',
                data: data.data[i]
            };
            disSeries.push(series);
        });
        var option = {
            title: {
                text: disTitle
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false}
                },
                myShare: {
                    show: true,
                    title: "分享报表",
                    icon:"image://"+BasePath+"/resource/image/share.png",
                    onclick: function () {
                        shareEvent();
                    }
                },
                right: 20
            },
            legend: {
                data: data.legend//上下堆叠类型
            },
            xAxis: [
                {
                    type: 'category',
                    data: data.xdata,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLabel: {
                        interval: 0,
                        rotate: 30
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: disSeries
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option, true);
        $(window).resize(t.resize);
    },
    /**
     * 加载饼图
     * @param t：选择容器
     * @param data：数据集
     * @param title：标题
     * @param showLegend：是否显示
     */
    pie:function(t,data,title,showLegend){
        var disTitle = BaseUtil.isEmpty(title)?"":title;
        var option = {
            title: {
                text: disTitle
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false},
                    myShare: {
                        show: true,
                        title: "分享报表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    }
                },
                right: 20
            },
            legend: {
                type: 'scroll',
                show: BaseUtil.isEmpty(showLegend)? true: showLegend,
                data: data.legend,
                left: 'right',
                top: '20%',
                bottom: '10%',
                orient: 'vertical',
                formatter: function (name) {
                    return echarts.format.truncateText(name, 100, '14px Microsoft Yahei', '…');
                },
                tooltip: {
                    show: true
                }
            },
            series : [
                {
                    name: data.name,
                    type: 'pie',
                    radius : '60%',
                    minAngle: 3,
                    data: data.series,
                    avoidLabelOverlap: true,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },
                    label: {
                        normal: {
                            formatter: '{b} {d}%'
                        }
                    }
                }
            ]
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,disTitle);
        };
        t.setOption(option,{notMerge: true});
        $(window).resize(t.resize);
    },
    /**
     * 加载心跳日期图
     * @param t 选择容器
     * @param data 数据集
     * @param year 年
     * @param title 标题
     */
    heartDate(t,data,year,title,subtitle){
        var cellSize = 30; // 格子大小
        var color = ['#fff', '#f4e925', '#47F462', '#F45E56'];
        var effectColor = ['#fff', '#f4e925', '#7DF46D', '#F48D89'];
        var colorLevel = [5000, 10000, 50000, 100000];

        title = title?title:(year+"数据")
        subtitle = subtitle?subtitle:""
        data = data.sort(function (a, b) {
            return b[1] - a[1];
        });
        var maxValue = data[0][1]; // 最大值

        var option = {
            backgroundColor: '#404a59',

            title: {
                top: 30,
                text: title,
                subtext:subtitle,
                left: 'center',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip : {
                trigger: 'item',
                default: true,
                formatter: function (params) {
                    var total = params.marker + '安装总数：' + params.data[1];
                    return [params.data[0].replace(/-/g, '/'), total].join('<br/>')
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    dataView: {readOnly: false},
                    myShare: {
                        show: true,
                        title: "分享报表",
                        icon:"image://"+BasePath+"/resource/image/share.png",
                        onclick: function () {
                            shareEvent();
                        }
                    }
                },
                right: 20
            },
            legend: {
                top: '30',
                left: '100',
                data:['安装总数', 'Top 10'],
                textStyle: {
                    color: '#fff'
                }
            },
            calendar: {
                top: 100,
                left: 'center',
                range: [year+'-01-01', year+'-12-31'],
                cellSize: cellSize,
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#000',
                        width: 4,
                        type: 'solid'
                    }
                },
                yearLabel: {
                    show: !1,
                    formatter: '{start}  年',
                    textStyle: {
                        color: '#fff'
                    }
                },
                monthLabel: {
                    nameMap: 'cn'
                },
                itemStyle: {
                    normal: {
                        color: '#323c48',
                        borderWidth: 1,
                        borderColor: '#111'
                    }
                }
            },
            series : [
                {
                    name: '安装总数',
                    type: 'scatter',
                    coordinateSystem: 'calendar',
                    data: data,
                    symbolSize: function (val) {
                        if (maxValue == 0) return 0;
                        else
                            return Math.floor(val[1] * (cellSize/maxValue));
                    },
                    itemStyle: {
                        normal: {
                            color: function (params) {
                                var thisColor = color[0];
                                $.each(colorLevel, function (i, v) {
                                    if (params.data[1] > v) {
                                        if (i==colorLevel.length-1)
                                            thisColor = effectColor[i];
                                        return true
                                    } else {
                                        if (i>0)
                                            thisColor = color[i];
                                        return false
                                    }
                                });
                                return thisColor;
                            },
                        }
                    }
                },
                {
                    name: 'Top 10',
                    type: 'effectScatter',
                    coordinateSystem: 'calendar',
                    data: data.slice(0, 10),
                    symbolSize: function (val) {
                        if (maxValue == 0) return 0;
                        else
                            return Math.floor(val[1] * (cellSize/maxValue));
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    itemStyle: {
                        normal: {
                            color: function (params) {
                                var thisColor = effectColor[0];
                                $.each(colorLevel, function (i, v) {
                                    if (params.data[1] > v) {
                                        if (i==colorLevel.length-1)
                                            thisColor = effectColor[i];
                                        return true
                                    } else {
                                        if (i>0)
                                            thisColor = effectColor[i];
                                        return false
                                    }
                                });
                                return thisColor;
                            },
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                }
            ]
        };
        /** 分享事件 */
        var shareEvent = function(){
            BaseShare.chart(option,title);
        };
        t.setOption(option, {notMerge: true});
    }

};