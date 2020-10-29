package cn.i4.data.cloud.autocode.core;

import ch.qos.logback.core.db.dialect.DBUtil;
import cn.hutool.core.date.DateUtil;
import cn.i4.data.cloud.autocode.entity.TableDto;
import cn.i4.data.cloud.autocode.entity.TemplateVal;
import cn.i4.data.cloud.autocode.util.DataBaseUtil;
import cn.i4.data.cloud.autocode.util.FreemarkerUtil;
import cn.i4.data.cloud.base.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据库处理的核心业务
 * @author wangjc
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-14:23
 */
@Component
public class DataBaseCore {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseCore.class);
    private static FreemarkerUtil freemarkerUtil = new FreemarkerUtil();

    public void buildCode(String driverClass,String dataSourceUrl,String user,String password,String author,String packagePrefix,String localDir,List<String> tableList){
        Map<String, TableDto> map = DataBaseUtil.showTablesAndFColumns(driverClass, dataSourceUrl, user, password,tableList);

        for(Map.Entry<String,TableDto> entry : map.entrySet()){
            logger.info(entry.getValue().getTableName());

            TemplateVal val = new TemplateVal();
            val.setModuleName(packagePrefix);
            val.setModelName(entry.getValue().getBuzName());
            val.setTable_Comment(entry.getValue().getTableComment());
            val.setAuthor(author);
            val.setCrateDate(DateUtil.format(new Date()	, "yyyy-MM-dd HH:mm:ss"));
            val.setTable_name(entry.getValue().getTableName());
            val.setTableDto(entry.getValue());

            buildModel(val,localDir);
            buildView(val,localDir);
            buildDto(val,localDir);
            buildMapper(val,localDir);
            buildMapperXml(val,localDir);
            buildIService(val,localDir);
            buildServiceImpl(val,localDir);
        }
    }

    /**
     *模型构造
     * @param val
     */
    private void buildModel(TemplateVal val,String localDir){
        Map<String,Object> root = new HashMap<>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");
        freemarkerUtil.fprint("Model.ftl", root, val.getModelName()+"Model.java" , "entity/model",localDir);
    }

    /**
     * 视图构造
     * @param val
     */
    static void buildView(TemplateVal val,String localDir) {
        Map<String,Object> root = new HashMap<>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");

        freemarkerUtil.fprint("View.ftl", root, val.getModelName() + "View.java", "entity/view",localDir);
    }

    /**
     * Dto构造
     * @param val
     */
    static void buildDto(TemplateVal val,String localDir) {
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");

        freemarkerUtil.fprint("Dto.ftl", root, val.getModelName() + "Dto.java", "entity/dto",localDir);
    }

    /**
     * Mapper构造
     * @param val
     */
    static void buildMapper(TemplateVal val,String localDir) {
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");

//		freemarkerUtil.print("Mapper.ftl", root);
        freemarkerUtil.fprint("Mapper.ftl", root, val.getModelName() + "Mapper.java", "mapper",localDir);
    }


    /**
     * Mapper构造
     * @param val
     */
    static void buildMapperXml(TemplateVal val,String localDir) {
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");
        root.put("c_status", "#{c_status}");
        root.put("c_startTime", "#{c_startTime}");
        root.put("c_endTime", "#{c_endTime}");
        //freemarkerUtil.print("MapperXml.ftl", root);
        freemarkerUtil.fprint("MapperXml.ftl", root, val.getModelName() + "Mapper.xml", "xml",localDir);
    }


    /**
     * IService构造
     * @param val
     */
    static void buildIService(TemplateVal val,String localDir) {
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");

//		freemarkerUtil.print("IService.ftl", root);
        freemarkerUtil.fprint("IService.ftl", root, "I"+val.getModelName()+"Service.java", "service",localDir);
    }

    /**
     * Service Impl构造
     * @param val
     */
    static void buildServiceImpl(TemplateVal val,String localDir) {
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("ModuleName", val.getModuleName());
        root.put("ModelName", val.getModelName());
        root.put("table_Comment", val.getTable_Comment());
        root.put("author", val.getAuthor());
        root.put("crateDate", val.getCrateDate());
        root.put("table_name", val.getTable_name());
        TableDto tablesDto = val.getTableDto();
        root.put("fields", tablesDto);
        root.put("serialVersionUID", System.currentTimeMillis() +"L");

        freemarkerUtil.fprint("ServiceImpl.ftl", root, val.getModelName()+"ServiceImpl.java", "service/impl",localDir);
    }




}
