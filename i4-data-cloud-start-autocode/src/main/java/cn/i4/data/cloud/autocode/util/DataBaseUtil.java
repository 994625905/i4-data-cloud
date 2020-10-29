package cn.i4.data.cloud.autocode.util;

import cn.i4.data.cloud.autocode.entity.CloumnEnums;
import cn.i4.data.cloud.autocode.entity.FieldDto;
import cn.i4.data.cloud.autocode.entity.TableDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库工具类
 * @author wangjc
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-17:15
 */
public class DataBaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseUtil.class);

    /**
     * 获取数据库连接
     * @param dataSourceUrl
     * @param user
     * @param password
     * @return
     */
    public static Connection getConnect(String driverClass, String dataSourceUrl, String user, String password){
        Connection conn = null;
        try {
            Class.forName(driverClass); // 加载驱动
            conn = DriverManager.getConnection(dataSourceUrl,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 显示数据表和字段
     * @param driverClass
     * @param dataSourceUrl
     * @param user
     * @param password
     */
    public static Map<String,TableDto> showTablesAndFColumns(String driverClass, String dataSourceUrl, String user, String password,List<String> tableList){
        /** 装载table的对象 */
        Map<String,TableDto> tableMap = new HashMap<>();
        try {
            Connection conn = getConnect(driverClass,dataSourceUrl,user,password);
            String dbName = getDBName(dataSourceUrl);

            logger.info("获取数据库[{}]表集合开始", dbName);
            Map<String, String> compareMap = tableMap(tableList);

            for(String name:tableList(driverClass,dataSourceUrl,user,password)){

                /** 只获取有效的数据表 */
                if(compareMap.get(name) == null){
                    continue;
                }

                /** 填充map */
                TableDto dto = new TableDto();
                dto.setTableName(name);
                tableMap.put(name,dto);

                logger.info("Tables : [{}]", name);
            }

            /** 开始分析数据表 */
            for (Map.Entry<String, TableDto> entry : tableMap.entrySet()) {

                /** 装载字段的集合 */
                List<FieldDto> columnList = new ArrayList<FieldDto>();

                String tableName = entry.getValue().getTableName();
                logger.info("数据库[{}]表[{}]分析开始.", dbName , tableName);
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet resultSet = meta.getColumns(dbName, null, tableName, "%");
                while (resultSet.next()) {
                    FieldDto dto = new FieldDto();
                    dto.setFieldName(resultSet.getString(4));
                    if(CloumnEnums.matchCloumnEnums(resultSet.getString("TYPE_NAME")) == null){
                        logger.error("数据库类型[{}]未在枚举类型匹配", resultSet.getString("TYPE_NAME"));
                    }
                    dto.setFiled(CloumnEnums.matchCloumnEnums(resultSet.getString("TYPE_NAME")));
                    columnList.add(dto);
                    logger.info("列[{}]，类型[{}]",resultSet.getString(4),resultSet.getString("TYPE_NAME"));
                }

                Statement stmt = conn.createStatement();

                /** 字段备注 */
                ResultSet rs = stmt.executeQuery("show full columns from " + entry.getValue().getTableName());
                Map<String , String> map = new HashMap<String , String>();
                while (rs.next()) {
                    map.put(rs.getString("Field"),  rs.getString("Comment") );
                    logger.info("Filed[{}]comment[{}]",rs.getString("Field"),rs.getString("Comment"));
                }
                for(FieldDto field : columnList){
                    field.setFieldComment(map.get(field.getFieldName()) + "");
                }
                entry.getValue().setFields(columnList);
                logger.info("数据库[{}]表[{}]分析结束.", dbName , tableName);

                /** 表备注 */
                ResultSet rest = stmt.executeQuery("SHOW CREATE TABLE " + entry.getValue().getTableName());
                if (rest != null && rest.next()) {
                    String createDDL = rest.getString(2);
                    String comment = parse(createDDL);
                    entry.getValue().setTableComment(comment);
                    logger.info("TABLE[{}] COMMENT[{}]", entry.getValue().getTableName() , comment);
                }
            }
        }catch (Exception e){
            logger.error("获取表结构数据异常.");
            e.printStackTrace();
        }
        return tableMap;
    }

    /**
     * 返回注释信息
     * @param all
     * @return
     */

    static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    /**
     * 获取数据表集合
     * @param driverClass
     * @param dataSourceUrl
     * @param user
     * @param password
     * @return
     */
    public static List<String> tableList(String driverClass, String dataSourceUrl, String user, String password){
        List<String> res = new ArrayList<>();
        try {
            Connection conn = getConnect(driverClass,dataSourceUrl,user,password);

            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()){
                res.add(rs.getString(3));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 生成表的集合转map
     * @param list
     * @return
     */
    private static Map<String,String> tableMap(List<String> list){
        Map<String,String> map = new HashMap<>();
        for(String table:list){
            map.put(table,table);
        }
        return map;
    }

    /**
     * 获取数据库名称
     * @param dataSourceUrl
     * @return
     */
    private static String getDBName(String dataSourceUrl){
        String[] s0 = dataSourceUrl.split("\\?");
        String[] s1 = s0[0].split("/");
        return s1[s1.length - 1];
    }
}
