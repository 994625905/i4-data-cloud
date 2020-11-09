package cn.i4.data.cloud.system.web.action.materialMsg;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.core.entity.model.FileModel;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.service.IFileService;
import cn.i4.data.cloud.system.microservice.FileMicroservice;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 素材中心--文件查看的控制层
 * @author wangjc
 * @title: FileFindController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/139:15
 */
@RequestMapping(value = "/materialMsg/fileFind")
@RestController
public class FileFindController extends WebBaseController {

    private static final String MODULE_NAME = "素材中心--文件查看";
    private static final String KEY_PREFIX = "/materialMsg/fileFind";
    private static final Logger logger = LoggerFactory.getLogger(FileFindController.class);

    @Autowired
    private IFileService iFileService;
    @Autowired
    private FileMicroservice fileMicroservice;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<IPage<FileView>> loadTable(FileDto dto,HttpServletRequest request){
        dto.setUserId(getUser(request).getId());
        IPage<FileView> page = iFileService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 文件保存
     * @param dto
     * @return
     */
    @PostMapping(value = "/save")
    @RequestLog(module = MODULE_NAME,content = "文件保存",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--文件保存",key = KEY_PREFIX+"/save")
    public ActionResult<Boolean> save(@RequestBody FileDto dto,HttpServletRequest request){

        FileModel model = dto.getModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(getUser(request).getId());
        model.setStatus(SystemConstant.FILE.ACTIVE);
        boolean save = iFileService.save(model);
        if(save){
            return ActionResult.ok(save);
        }else{
            return ActionResult.error("文件保存失败");
        }
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    @RequestLog(module = MODULE_NAME,content = "文件上传",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--文件上传",key = KEY_PREFIX+"/upload")
    public ActionResult<Map<String,Object>> upload(MultipartFile file){

        ActionResult<Map<String, Object>> result = fileMicroservice.upload(file);
        return result;
    }

    /**
     * 文件删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "文件删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--文件删除",key = KEY_PREFIX+"/delete")
    public ActionResult<Boolean> delete(FileDto dto){

        ActionResult<Boolean> delete = fileMicroservice.delete(dto.getFileUrl());
        if(delete.getResult()){
            boolean remove = iFileService.removeById(dto.getId());
            if(remove){
                return ActionResult.ok(remove);
            }
            return ActionResult.error("文件删除失败");
        }else{
            return ActionResult.error("文件服务器FastDFS数据删除失败");
        }
    }

    /**
     * 改变状态
     * @param dto
     * @return
     */
    @PostMapping(value = "/changeStatus")
    @RequestLog(module = MODULE_NAME,content = "改变状态",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--改变状态",key = KEY_PREFIX+"/changeStatus")
    public ActionResult<Boolean> changeStatus(FileDto dto){

        FileModel model = iFileService.getById(dto.getId());
        model.setStatus(dto.getStatus());
        boolean modify = iFileService.modify(model);
        if(modify){
            return ActionResult.ok(modify);
        }else{
            return ActionResult.error("改变状态失败");
        }
    }


    /**
     * markdown的图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadImageByMd",method = {RequestMethod.GET, RequestMethod.POST})
    @RequestLog(module = MODULE_NAME,content = "markdown的图片上传",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--markdown的图片上传",key = KEY_PREFIX+"/uploadImageByMd")
    public Object uploadImageByMd(@RequestParam(value = "editormd-image-file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            logger.error("markdown的图片上传出错，图片不存在");
            return MapUtil.of("error", 0);
        }
        /** 微服务上传 */
        ActionResult<Map<String, Object>> result = fileMicroservice.upload(file);
        if(result.getCode() != 200){
            logger.error("markdown的图片上传出错，微服务出错");
            return MapUtil.of("error", 0);
        }
        String fileUrl = Convert.toStr(result.getResult().get("fileUrl"));

        /** 入库存储 */
        FileModel model = new FileModel();
        model.setName(file.getOriginalFilename());
        model.setUrl(fileUrl);
        model.setSuffix(file.getContentType());
        model.setSize(Convert.toFloat(file.getSize()));
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(getUser(request).getId());
        model.setStatus(SystemConstant.FILE.ACTIVE);
        model.setCreateUserId(getUser(request).getId());

        /** 获取图片宽高，确认是图片 */
        BufferedImage image = ImageIO.read(file.getInputStream());
        if(image != null){
            model.setDescription("markdown的图片上传");
            model.setType(SystemConstant.FILE.TYPE.IMAGE);
            model.setWidth(image.getWidth());
            model.setHeight(image.getHeight());
        }

        boolean save = iFileService.save(model);
        if(save){
            return MapUtil.of(new Object[][]{{"success", 1}, {"message", "上传成功！"}, {"url", fileUrl}});
        }else{
            return MapUtil.of("error", 0);
        }
    }

    /**
     * 富文本编辑器的图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadImageByEditor",method = {RequestMethod.GET, RequestMethod.POST})
    @RequestLog(module = MODULE_NAME,content = "富文本编辑器的图片上传",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--富文本编辑器的图片上传",key = KEY_PREFIX+"/uploadImageByEditor")
    public Object uploadImageByEditor(MultipartFile file, HttpServletRequest request) throws IOException {

        if(file.isEmpty()){
            logger.error("富文本编辑器的图片上传出错，图片不存在");
            return null;
        }
        /** 微服务上传 */
        ActionResult<Map<String, Object>> result = fileMicroservice.upload(file);
        if(result.getCode() != 200){
            logger.error("富文本编辑器的图片上传出错，微服务出错");
            return null;
        }
        String fileUrl = Convert.toStr(result.getResult().get("fileUrl"));

        /** 入库存储 */
        FileModel model = new FileModel();
        model.setName(file.getOriginalFilename());
        model.setUrl(fileUrl);
        model.setSuffix(file.getContentType());
        model.setSize(Convert.toFloat(file.getSize()));
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(getUser(request).getId());
        model.setStatus(SystemConstant.FILE.ACTIVE);
        model.setCreateUserId(getUser(request).getId());

        /** 获取图片宽高，确认是图片 */
        BufferedImage image = ImageIO.read(file.getInputStream());
        if(image != null){
            model.setDescription("富文本编辑器的图片上传");
            model.setType(SystemConstant.FILE.TYPE.IMAGE);
            model.setWidth(image.getWidth());
            model.setHeight(image.getHeight());
        }

        boolean save = iFileService.save(model);
        if(save){
            return new HashMap<String, Object>() {{
                put("code", "000");
                put("item", new HashMap<String, Object>() {{
                    put("url", fileUrl);
                }});
            }};
        }else{
            return null;
        }
    }

}
