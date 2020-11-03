package cn.i4.data.cloud.system.web.action.materialMsg;

import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.service.IFileService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片选择的控制层
 * @author wangjc
 * @title: ImageSelectController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1417:27
 */
@RequestMapping(value = "/materialMsg/imageSelect")
@RestController
public class ImageSelectController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--图片选择";
    @Autowired
    private IFileService iFileService;

    /**
     * 根据条件加载图片
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadImageTable")
    @RequestLog(module = MODULE_NAME,content = "根据条件加载图片",type = RequestType.SELECT)
    public ActionResult<IPage<FileView>> loadImageTable(FileDto dto,HttpServletRequest request){
        dto.setUserId(getUser(request).getId());

        IPage<FileView> res = iFileService.loadImageTable(dto);
        return ActionResult.ok(res);
    }

    /**
     * 根据调解加载文件（除图片外）
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadFileTable")
    @RequestLog(module = MODULE_NAME,content = "根据条件加载图片",type = RequestType.SELECT)
    public ActionResult<IPage<FileView>> loadFileTable(FileDto dto,HttpServletRequest request){
        dto.setUserId(getUser(request).getId());

        IPage<FileView> res = iFileService.loadFileTable(dto);
        return ActionResult.ok(res);
    }

    /**
     * 设置临时存储的url
     * @return
     */
    @PostMapping(value = "/setImageSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "设置临时存储的url",type = RequestType.SELECT)
    public ActionResult<Boolean> setImageSelectTemp(String fileUrl, HttpServletRequest request){

        String authorization = CookieUtil.getCookieValue(request, "authorization");
        boolean res = redisService.hset(RedisConstant.HASH_KEY.SELECT_IMAGE_TEMP, authorization, fileUrl, RedisConstant.TIMEOUT.SELECT_IMAGE_TEMP);
        return ActionResult.ok(res);
    }

    /**
     * 获取临时存储的url，并删除缓存
     * @return
     */
    @PostMapping(value = "/getImageSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "获取临时存储的url，并删除缓存",type = RequestType.SELECT)
    public ActionResult<String> getImageSelectTemp(HttpServletRequest request){

        String authorization = CookieUtil.getCookieValue(request, "authorization");
        String fileUrl = (String) redisService.hget(RedisConstant.HASH_KEY.SELECT_IMAGE_TEMP, authorization);
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SELECT_IMAGE_TEMP, authorization);
        return ActionResult.ok(fileUrl);
    }

}
