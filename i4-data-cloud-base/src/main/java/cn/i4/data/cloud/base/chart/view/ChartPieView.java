package cn.i4.data.cloud.base.chart.view;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjc
 * @title: ChartPieView
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1621:02
 */
public class ChartPieView implements Serializable {

    private static final long serialVersionUID = -5355962066637527747L;

    /**
     * 构造方法,数据为map
     * @param legend
     * @param key
     * @param data
     * @param name
     */
    public ChartPieView(String[] legend,String[] key,Map<String,Object> data,String name){
        this.legend = legend;
        this.key = key;
        this.name = name;

        //设置series
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i=0;i<legend.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("name",legend[i]);
            map.put("value",data.get(key[i]));
            res.add(map);
        }
        this.series = res;
    }

    /**
     * 构造方法,数据为list
     * @param keyName
     * @param keyCount
     * @param data
     * @param name
     */
    public ChartPieView(String keyName,String keyCount,List<Map<String,Object>> data,String name){
        this.name = name;
        this.data = data;

        List<String> legendList = new ArrayList<>();
        List<Map<String,Object>> res = new ArrayList<>();

        if(data != null && data.size()>0){

            for(Map<String,Object> map:data){

                legendList.add(Convert.toStr(map.get(keyName)));

                Map<String,Object> m = new HashMap<>();
                m.put("name",Convert.toStr(map.get(keyName)));
                m.put("value", Convert.toStr(map.get(keyCount)));
                res.add(m);
            }

        }

        String[] legend = new String[legendList.size()];
        legendList.toArray(legend);//集合转数组

        this.legend = legend;
        this.series = res;
    }

    /**
     * 构造方法,数据为list,限制展示条数，超出为其他
     * @param keyName
     * @param keyCount
     * @param data
     * @param name
     * @param pieLength
     */
    public ChartPieView(String keyName,String keyCount,List<Map<String,Object>> data,String name,Integer pieLength){
        this.name = name;
        this.data = data;

        List<String> legendList = new ArrayList<>();
        List<Map<String,Object>> res = new ArrayList<>();

        if(data != null && data.size()>0){

            /** 超过长度的设置 */
            List<Map<String,Object>> list = CollUtil.sub(data,0,pieLength);//list切片处理，左闭右开，避免了越界等问题
            Integer other = 0;
            for(int i=pieLength;i<data.size();i++ ){
                other += Convert.toInt(data.get(i).get(keyCount));
            }
            Integer finalOther = other;
            list.add(new HashMap<String, Object>(){{
                put(keyName,"其他");
                put(keyCount, finalOther);
            }});

            for(Map<String,Object> map:list){

                legendList.add(Convert.toStr(map.get(keyName)));

                Map<String,Object> m = new HashMap<>();
                m.put("name",Convert.toStr(map.get(keyName)));
                m.put("value", Convert.toStr(map.get(keyCount)));
                res.add(m);
            }
        }

        String[] legend = new String[legendList.size()];
        legendList.toArray(legend);//集合转数组

        this.legend = legend;
        this.series = res;
    }
    /**
     * 饼图名称
     */
    private String name;

    /**
     *扇形的分类目
     */
    private String[] legend;

    /**
     * 分类目对应数据的key
     */
    private String[] key;

    /**
     * 核心数据集，一般是SQL返回结果集
     */
    private List<Map<String,Object>> series;

    /**
     * 原始数据
     */
    private List<Map<String,Object>> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLegend() {
        return legend;
    }

    public void setLegend(String[] legend) {
        this.legend = legend;
    }

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }

    public List<Map<String, Object>> getSeries() {
        return series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
