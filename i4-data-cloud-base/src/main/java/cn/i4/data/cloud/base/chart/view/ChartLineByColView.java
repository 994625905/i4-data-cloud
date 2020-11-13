package cn.i4.data.cloud.base.chart.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 折线图表视图层,根据列来拆分
 * @author wangjc
 * @title: chartLineView
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1620:41
 */
public class ChartLineByColView implements Serializable {

    private static final long serialVersionUID = -165385779964732782L;

    /**
     * 构造方法，传入需要的参数
     * @param legend
     * @param legendKey
     * @param xAxisCol
     * @param data
     */
    public ChartLineByColView(String[] legend,String[] legendKey,String xAxisCol,List<Map<String,Object>> data){
        this.legend = legend;
        this.legendKey = legendKey;
        this.xAxisCol = xAxisCol;
        this.data = data;

        List<Object> xAxis = new ArrayList<>();
        for(Map<String,Object> map:data){
            xAxis.add(map.get(xAxisCol));
        }
        this.xAxis = xAxis;

        List<Map<String,Object>> series = new ArrayList<>();
        for(int i=0;i<legend.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("name",legend[i]);
            map.put("type","line");
            map.put("smooth",true);//去掉有棱角，加上变柔和
            List<Object> datalist = new ArrayList<>();
            for(Map<String,Object> dataMap:data){
                datalist.add(dataMap.get(legendKey[i]));
            }
            map.put("data",datalist);

            series.add(map);
        }
        this.series = series;

    }

    /**
     * 折线的条目
     */
    private String[] legend;

    /**
     * 条目对应数据的key
     */
    private String[] legendKey;

    /**
     * X轴坐标参数
     */
    private List<Object> xAxis;

    /**
     * 做X轴数据的条目
     */
    private String xAxisCol;

    /**
     * 传过来的数据，一般是SQL的结果集
     */
    private List<Map<String,Object>> data;

    /**
     * 核心加载数据
     */
    private List<Map<String,Object>> series;

    /**
     * 其他字段
     */
    private Object others;

    public String[] getLegend() {
        return legend;
    }

    public void setLegend(String[] legend) {
        this.legend = legend;
    }

    public String[] getLegendKey() {
        return legendKey;
    }

    public void setLegendKey(String[] legendKey) {
        this.legendKey = legendKey;
    }

    public List<Object> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<Object> xAxis) {
        this.xAxis = xAxis;
    }

    public String getxAxisCol() {
        return xAxisCol;
    }

    public void setxAxisCol(String xAxisCol) {
        this.xAxisCol = xAxisCol;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getSeries() {
        return series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }
}
