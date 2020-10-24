package cn.i4.data.cloud.mongo.config;

import cn.hutool.db.nosql.mongo.MongoDS;
import cn.hutool.db.nosql.mongo.MongoFactory;
import cn.hutool.setting.GroupedMap;
import cn.hutool.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * MongoDB的配置
 * @author wangjc
 * @title: MongoConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3018:27
 */
public class MongoConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    /**
     * MongoDB实例连接列表
     */
    private static MongoDS DS;

    /** 初始化MongoDS */
    static {
        try {
            /** 配置文件的各个配置项 */
            Setting setting = new Setting(MongoDS.MONGO_CONFIG_PATH);
            GroupedMap groupsMap = setting.getGroupedMap();
            List<String> groups = new ArrayList<String>();
            Set<Map.Entry<String, LinkedHashMap<String, String>>> set = groupsMap.entrySet();
            for(Map.Entry<String,LinkedHashMap<String,String>> entry:set){
                if(entry.getKey() == null || entry.getKey() == ""){
                    continue;
                }
                String host = entry.getValue().get("host");
                if(host == null || host == ""){
                    continue;
                }
                logger.info("MongoDB初始化数据源[{}]信息",entry.getKey());
                logger.info("MongoDB初始化数据源host[{}]信息",host);
                groups.add(entry.getKey());
            }
            if(groups.size() > 0){
                DS = MongoFactory.getDS(groups);
            }else{
                DS = MongoFactory.getDS("master");
            }
        }catch (Exception e){
            logger.error("MongoDB初始化数据源失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取实例连接
     * @return
     */
    public static MongoDS getMongoDS(){
        return DS;
    }

}
