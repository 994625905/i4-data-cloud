package cn.i4.data.cloud.auth.microservice.impl;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.service.ISystemConstantService;
import cn.i4.data.cloud.auth.microservice.FileMicroservice;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件的微服务降级处理
 * @author wangjc
 * @title: FileMicroserviceImpl
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1314:52
 */
@Component
public class FileMicroserviceImpl implements FileMicroservice {

    private static final Logger logger = LoggerFactory.getLogger(FileMicroserviceImpl.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private ISystemConstantService iSystemConstantService;

    @Override
    public ActionResult<Map<String, Object>> upload(MultipartFile file) {
        logger.error("upload服务降级处理");

        String json = redisService.get(RedisConstant.KEY.SYSTEM_CONSTANT, String.class);
        Map<String,Object> map;
        if(json == null){
            map = iSystemConstantService.getSystemConstant();
            redisService.set(RedisConstant.KEY.SYSTEM_CONSTANT, JSONObject.toJSONString(map),RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
        }else{
            map = JSONObject.parseObject(json,Map.class);
        }

        return ActionResult.ok(new HashMap<String, Object>(){{
            put("fileName",map.get("errorImage_name"));
            put("fileUrl",map.get("errorImage"));
            put("fileSuffix","jpg");
            put("fileSize","30");
        }});
    }

    @Override
    public ActionResult<Boolean> delete(String fileUrl) {
        return ActionResult.ok(false);
    }
}
