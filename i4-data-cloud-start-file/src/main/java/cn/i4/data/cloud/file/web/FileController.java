package cn.i4.data.cloud.file.web;

import cn.hutool.core.convert.Convert;
import cn.i4.data.cloud.base.controller.BaseController;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.file.core.FileCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件服务器的控制层
 * @author wangjc
 * @title: FileController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1220:30
 */
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileCore fileCore;

    /**
     * 上传文件
     * 跨域（准备响应前的缓存持续的最大时间，单位秒）
     * @param file
     * @return
     */
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(maxAge = 60)
    public ActionResult<Map<String,Object>> upload(@RequestPart("file") MultipartFile file){
        if(file.isEmpty()){
            return ActionResult.error("上传文件不存在");
        }
        try {
            Long fileSize = file.getSize();
            if(fileSize/1024 > fileCore.getFdFsConfig().getMaxSize()){
                return ActionResult.error("文件大小超出限制100MB");
            }
            String fileName = file.getOriginalFilename();
            String fileSuffix = file.getContentType();
            String fileUrl = fileCore.uploadFile(file);
            return ActionResult.ok(new HashMap<String, Object>(){{
                put("fileName",fileName);
                put("fileUrl",fileUrl);
                put("fileSuffix",fileSuffix);
                put("fileSize",fileSize);
            }});
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 删除文件
     * @param fileUrl
     * @return
     */
    @PostMapping(value = "/delete")
    public ActionResult<Boolean> delete(@RequestParam String fileUrl){
        try {
            fileCore.deleteFile(fileUrl);
            return ActionResult.ok(true);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

}
