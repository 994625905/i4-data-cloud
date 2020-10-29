package cn.i4.data.cloud.system.microservice.impl;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.system.microservice.AutoCodeMicroService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动代码上传的降级处理
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-15:27
 */
@Component
public class AutoCodeMicroServiceImpl implements AutoCodeMicroService {

    @Override
    public ActionResult<List<String>> getTableListBySource(String driverClass, String dataSourceUrl, String user, String password) {
        List<String> list = new ArrayList<String>(){{
            add("获取数据表集合失败");
        }};
        return ActionResult.ok(list);
    }

    @Override
    public ActionResult<Boolean> create(String driverClass, String dataSourceUrl, String user, String password, String author, String packagePrefix, String localDir, List<String> tableList) {
        return ActionResult.error("自动生成代码失败");
    }
}
