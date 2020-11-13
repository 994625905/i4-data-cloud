package cn.i4.data.cloud.base.chart.view;

import cn.hutool.core.convert.Convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 折线图表视图层,根据分组求和的第二行来拆分
 * @author wangjc
 * @title: ChartLineByCapView
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1620:53
 */
public class ChartLineByCapView implements Serializable{

    private static final long serialVersionUID = -965920590163431527L;

    /**
     * 构造器
     * @param legendCol
     * @param xAxisCol
     * @param dataCol
     * @param data
     */
    public ChartLineByCapView(String legendCol,String xAxisCol,String dataCol,List<Map<String,Object>> data){
        this.legendCol = legendCol;
        this.xAxisCol = xAxisCol;
        this.dataCol = dataCol;
        this.data = data;

        //设置折线的条目
        List<String> res = new ArrayList<>();
        Boolean flag = true;
        for(Map<String,Object> map:data){

            for(String l:res){
                if(l.equals(Convert.toStr(map.get(this.legendCol)))){
                    flag = false;
                }
            }
            if(flag){
                res.add(map.get(this.legendCol).toString());
            }
            flag = true;
        }
        this.legend = res.toArray(new String[res.size()]);

        //X轴坐标参数
        List<String> res2 = new ArrayList<>();
        Boolean flag2 = true;
        for(Map<String,Object> map:data){

            for(String o:res2){
                if(o.equals(Convert.toStr(map.get(this.xAxisCol)))){
                    flag2 = false;
                }
            }
            if(flag2){
                res2.add(map.get(this.xAxisCol).toString());
            }
            flag2 = true;
        }
        this.xAxis = res2;
    }
    /**
     * 折线的条目
     */
    private String[] legend;

    /**
     * X轴坐标参数
     */
    private List<String> xAxis;

    /**
     * 做X轴数据的条目
     */
    private String xAxisCol;

    /**
     * 类型的列名
     */
    private String legendCol;

    /**
     * 数据列
     */
    private String dataCol;

    /**
     * 传过来的数据，一般是SQL的结果集
     */
    private List<Map<String,Object>> data;

    /**
     * 核心加载数据
     */
    private List<Map<String,Object>> series;

    /**
     * 其他数据
     */
    private Object others;

    public String[] getLegend() {
        return legend;
    }

    public void setLegend(String[] legend) {
        this.legend = legend;
    }

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public String getxAxisCol() {
        return xAxisCol;
    }

    public void setxAxisCol(String xAxisCol) {
        this.xAxisCol = xAxisCol;
    }

    public String getLegendCol() {
        return legendCol;
    }

    public void setLegendCol(String legendCol) {
        this.legendCol = legendCol;
    }

    public String getDataCol() {
        return dataCol;
    }

    public void setDataCol(String dataCol) {
        this.dataCol = dataCol;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getSeries() {
        List<Map<String,Object>> res = new ArrayList<>();

        for(String l:this.legend ){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("type","line");
            dataMap.put("name",l);

            List<Object> datalist = this.getDatalist(l.toString(),this.data);
            dataMap.put("data",datalist);

            dataMap.put("smooth",true);//去掉有棱角，加上变柔和

            res.add(dataMap);
        }
        this.series = res;
        return series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }

    /**
     * 从data数据集中筛选
     * @param name
     * @return
     */
    private List<Object> getDatalist(String name,List<Map<String,Object>> list){
        List<Object> res = new ArrayList<>();
        for(Map<String,Object> map:list){
            if(name.equals(map.get(this.legendCol))){
                res.add(map.get(this.dataCol));
            }
        }
        return res;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }
}
