package cn.i4.data.cloud.base.chart.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 基础的柱状图，独柱
 * @author wangjc
 * @title: ChartBarBaseView
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1619:40
 */
public class ChartBarBaseView implements Serializable{

    private static final long serialVersionUID = 4025208062532910739L;

    /**
     * 柱：名称
     */
    private String name;

    /**
     * X轴坐标
     */
    private List<Object> xAxis;

    /**
     * 主数据
     */
    private List<Object> data;

    /**
     * 构造器，设置值
     * @param name
     * @param xKey
     * @param yKey
     * @param sqlData:sql结果集
     */
    public ChartBarBaseView(String name, String xKey, String yKey, List<Map<String,Object>> sqlData){
        this.name = name;

        //设置xAxis和data
        List<Object> xAxis = new ArrayList<>();
        List<Object> data = new ArrayList<>();
        for(Map<String,Object> map:sqlData){
            xAxis.add(map.get(xKey));
            data.add(map.get(yKey));
        }
        this.xAxis = xAxis;
        this.data = data;
    }

    /**
     * 构造器设置值，多列模式
     * @param xCol
     * @param xKey
     * @param sqlData
     */
    public ChartBarBaseView(String[] xCol, String[] xKey, Map<String,Object> sqlData){
        this.xAxis = Arrays.asList(xCol);

        List<Object> data = new ArrayList<>();
        for(String key:xKey){
            data.add(sqlData.get(key));
        }
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<Object> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
