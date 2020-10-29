package cn.i4.data.cloud.system.microservice;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.system.microservice.impl.AutoCodeMicroServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 自动代码生成的微服务调用
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-15:25
 */
@FeignClient(value = "i4-data-cloud-start-autocode",fallback = AutoCodeMicroServiceImpl.class)
public interface AutoCodeMicroService {

    @PostMapping(value = "/autoCode/getTableListBySource")
    ActionResult<List<String>> getTableListBySource(@RequestParam String driverClass, @RequestParam String dataSourceUrl, @RequestParam String user, @RequestParam String password);

    @PostMapping(value = "/autoCode/create")
    ActionResult<Boolean> create(@RequestParam String driverClass, @RequestParam String dataSourceUrl, @RequestParam String user, @RequestParam String password,
                                   @RequestParam String author,@RequestParam String packagePrefix,@RequestParam String localDir,@RequestParam List<String> tableList);
}
