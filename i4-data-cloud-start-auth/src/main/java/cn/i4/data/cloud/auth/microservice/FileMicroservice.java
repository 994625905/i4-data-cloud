package cn.i4.data.cloud.auth.microservice;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.auth.config.FeignSupportConfig;
import cn.i4.data.cloud.auth.microservice.impl.FileMicroserviceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件的微服务调用
 * @author wangjc
 * @title: FileMicroservice
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1314:49
 */
@FeignClient(value = "i4-data-cloud-start-file",fallback = FileMicroserviceImpl.class, configuration = FeignSupportConfig.class)
public interface FileMicroservice {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ActionResult<Map<String,Object>> upload(@RequestPart("file")MultipartFile file);

    /**
     * 删除
     * @param fileUrl
     * @return
     */
    @PostMapping(value = "/file/delete")
    ActionResult<Boolean> delete(@RequestParam String fileUrl);

}
