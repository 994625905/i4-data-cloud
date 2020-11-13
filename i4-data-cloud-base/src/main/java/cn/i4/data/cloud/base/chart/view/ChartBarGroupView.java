package cn.i4.data.cloud.base.chart.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 柱状图，组合柱
 * @author wangjc
 * @title: ChartBarGroupView
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1620:05
 */
public class ChartBarGroupView implements Serializable {
    private static final long serialVersionUID = -67798273070684854L;

    /**
     * 构造器，设值
     * @param legend
     * @param legendKey
     * @param xKey
     * @param data，一般为SQL结果集
     */
    public ChartBarGroupView(String[] legend, String[] legendKey, String xKey, List<Map<String,Object>> data){
        this.legend = legend;

        List<Object> xdata = new ArrayList<>();
        for(Map<String,Object> map:data){
            xdata.add(map.get(xKey));
        }
        this.xAxis = xdata;

        List<List<Object>> res = new ArrayList<>();
        for(String key:legendKey){
            List<Object> d = new ArrayList<>();
            for(Map<String,Object> map:data){
                d.add(map.get(key));
            }
            res.add(d);
        }
        this.data = res;
    }

    /**
     * 构造器，根据类型列来排版，对比chartLineByCap
     * @param legendCol
     * @param xAxisCol
     * @param dataCol
     * @param list
     */
    public ChartBarGroupView(String legendCol, String xAxisCol, String dataCol, List<Map<String,Object>> list){

        /** 设置legend，xdata */
        List<Object> xdata = new ArrayList<>();
        List<String> legend = new ArrayList<String>(){{
            for(Map<String,Object> map:list){
                if(!contains(map.get(legendCol).toString())){
                    add(map.get(legendCol).toString());
                }
                if(!xdata.contains(map.get(xAxisCol))){
                    xdata.add(map.get(xAxisCol));
                }
            }
        }};
        this.legend = legend.toArray(new String[legend.size()]);
        this.xAxis = xdata;

        /** 设置data */
        List<List<Object>> res = new ArrayList<>();
        for(String name:this.legend){
            List<Object> d = new ArrayList<>();
            for(Map<String,Object> map:list){
                if(name.equals(map.get(legendCol))){
                    d.add(map.get(dataCol));
                }
            }
            res.add(d);
        }
        this.data = res;

    }
    /**
     * 组合类型
     */
    private String[] legend;

    /**
     * X轴坐标
     */
    private List<Object> xAxis;

    /**
     * 主数据，外层按legend排序，内层按xAxis排序
     */
    private List<List<Object>> data;

    public String[] getLegend() {
        return legend;
    }

    public void setLegend(String[] legend) {
        this.legend = legend;
    }

    public List<Object> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<Object> xAxis) {
        this.xAxis = xAxis;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }
}
