package cn.i4.data.cloud.system.web.action.materialMsg;

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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
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
}
