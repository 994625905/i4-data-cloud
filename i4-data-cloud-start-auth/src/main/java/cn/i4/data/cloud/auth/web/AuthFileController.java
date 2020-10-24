package cn.i4.data.cloud.auth.web;

import cn.i4.data.cloud.auth.microservice.FileMicroservice;
import cn.i4.data.cloud.base.result.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author wangjc
 * @title: FileUploadController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1815:53
 */
@Controller
@RequestMapping(value = "/auth/file")
public class AuthFileController extends WebBaseController{

    private static final Logger logger = LoggerFactory.getLogger(AuthFileController.class);

    @Autowired
    private FileMicroservice fileMicroservice;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public ActionResult<Map<String,Object>> upload(MultipartFile file){
        try {
            ActionResult<Map<String, Object>> result = fileMicroservice.upload(file);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

}
