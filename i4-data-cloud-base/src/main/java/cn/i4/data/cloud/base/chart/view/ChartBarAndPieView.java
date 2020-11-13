package cn.i4.data.cloud.base.chart.view;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 柱状图+饼图的组合视图
 * @author wangjc
 * @title: ChartBarAndPieView
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1618:41
 */
public class ChartBarAndPieView implements Serializable{

    private static final long serialVersionUID = 2750343486604622476L;

    /**
     * 报表名称
     */
    private String title;
    /**
     * 柱部分
     */
    private Map<String,Object> bar;
    /**
     * 饼
     */
    private Map<String,Object> pie;

    /**
     * 构造器
     * @param title：名称
     * @param keyName：主数据对应的name
     * @param keyValue：主数据对应的value
     * @param list：SQL主数据
     * @param pieLength：饼图的长度设置，超出部分按”其他“处理
     */
    public ChartBarAndPieView(String title,String keyName,String keyValue,List<Map<String,Object>> list,Integer pieLength) {
        this.title = title;

        /** bar的xdata,series */
        List<Object> barXdata = new ArrayList<>();
        List<Object> barSeries = new ArrayList<>();
        /** pie的legend，series */
        List<Object> pieLegend = new ArrayList<>();
        List<Map<String,Object>> pieSeries = new ArrayList<>();

        /** 格式化赋值 */
        for(Map<String,Object> map:list){
            barXdata.add(map.get(keyName));
            barSeries.add(map.get(keyValue));

            pieLegend.add(map.get(keyName));
            pieSeries.add(new HashMap<String, Object>(){{
                put("name",map.get(keyName));
                put("value",map.get(keyValue));
            }});
        }
        /** 饼图长度的设置，不可大于 */
        if(pieLength != null && pieLegend.size() > pieLength){
            List<Object> pieLegend0 = CollUtil.sub(pieLegend,0,pieLength);//list切片处理，左闭右开，避免了越界等问题
            List<Map<String,Object>> pieSeries0 = CollUtil.sub(pieSeries,0,pieLength);

            /** 其他部分的求和 */
            Integer other = 0;
            for(int i=pieLength;i<pieLegend.size();i++){
                other += Convert.toInt(pieSeries.get(i).get("value"));
            }

            Integer finalOther = other;//final处理
            pieLegend0.add("其他");
            pieSeries0.add(new HashMap<String, Object>(){{
                put("name","其他");
                put("value", finalOther);
            }});
            this.pie = new HashMap<String, Object>(){{
                put("legend",pieLegend0);
                put("series",pieSeries0);
            }};
        }else{
            this.pie = new HashMap<String, Object>(){{
                put("legend",pieLegend);
                put("series",pieSeries);
            }};
        }
        this.bar = new HashMap<String, Object>(){{
            put("xdata",barXdata);
            put("series",barSeries);
        }};
    }

    /**
     * 构造器
     * @param title：名称
     * @param keyName：主数据对应的name
     * @param keyValue：主数据对应的value
     * @param list：SQL主数据
     * @param pieLength：饼图的长度设置，超出部分按”其他“处理
     * @param barLength：柱状的长度设置，超出部分按”其他“处理
     */
    public ChartBarAndPieView(String title,String keyName,String keyValue,List<Map<String,Object>> list,Integer pieLength,Integer barLength) {
        this.title = title;

        /** bar的xdata,series */
        List<Object> barXdata = new ArrayList<>();
        List<Object> barSeries = new ArrayList<>();
        /** pie的legend，series */
        List<Object> pieLegend = new ArrayList<>();
        List<Map<String,Object>> pieSeries = new ArrayList<>();

        /** 格式化赋值 */
        for(Map<String,Object> map:list){
            barXdata.add(map.get(keyName));
            barSeries.add(map.get(keyValue));

            pieLegend.add(map.get(keyName));
            pieSeries.add(new HashMap<String, Object>(){{
                put("name",map.get(keyName));
                put("value",map.get(keyValue));
            }});
        }
        /** 饼图长度的设置，不可大于 */
        if(pieLength != null && pieLegend.size() > pieLength){
            List<Object> pieLegend0 = CollUtil.sub(pieLegend,0,pieLength);//list切片处理，左闭右开，避免了越界等问题
            List<Map<String,Object>> pieSeries0 = CollUtil.sub(pieSeries,0,pieLength);

            /** 其他部分的求和 */
            Integer other = 0;
            for(int i=pieLength;i<pieLegend.size();i++){
                other += Convert.toInt(pieSeries.get(i).get("value"));
            }

            Integer finalOther = other;//final处理
            pieLegend0.add("其他");
            pieSeries0.add(new HashMap<String, Object>(){{
                put("name","其他");
                put("value", finalOther);
            }});
            this.pie = new HashMap<String, Object>(){{
                put("legend",pieLegend0);
                put("series",pieSeries0);
            }};
        }else{
            this.pie = new HashMap<String, Object>(){{
                put("legend",pieLegend);
                put("series",pieSeries);
            }};
        }

        /** 柱状长度的设置，不可大于 */
        if(barLength != null && barXdata.size() > barLength){
            List<Object> barXdata0 = CollUtil.sub(barXdata,0,barLength);//list切片处理，左闭右开，避免了越界等问题
            List<Object> barSeries0 = CollUtil.sub(barSeries,0,barLength);

            /** 其他部分的求和 */
            Integer other = 0;
            for(int i=barLength;i<barSeries.size();i++){
                other += Convert.toInt(barSeries.get(i));
            }

            barXdata0.add("其他");
            barSeries0.add(other);
            this.bar = new HashMap<String, Object>(){{
                put("xdata",barXdata0);
                put("series",barSeries0);
            }};
        }else{
            this.bar = new HashMap<String, Object>(){{
                put("xdata",barXdata);
                put("series",barSeries);
            }};
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getBar() {
        return bar;
    }

    public void setBar(Map<String, Object> bar) {
        this.bar = bar;
    }

    public Map<String, Object> getPie() {
        return pie;
    }

    public void setPie(Map<String, Object> pie) {
        this.pie = pie;
    }
}
