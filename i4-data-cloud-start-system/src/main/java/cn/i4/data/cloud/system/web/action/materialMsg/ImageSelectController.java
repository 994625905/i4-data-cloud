package cn.i4.data.cloud.system.web.action.materialMsg;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private static final String KEY_PREFIX = "/materialMsg/imageSelect";
    @Autowired
    private IFileService iFileService;

    /**
     * 根据条件加载图片
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadImageTable")
    @RequestLog(module = MODULE_NAME,content = "根据条件加载图片",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--根据条件加载图片",key = KEY_PREFIX+"/loadImageTable")
    @RequestPermission(value = "materialMsg:imageSelect/loadImageTable")
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
    @RequestLog(module = MODULE_NAME,content = "根据调解加载文件",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--根据调解加载文件",key = KEY_PREFIX+"/loadFileTable")
    @RequestPermission(value = "materialMsg:imageSelect/loadFileTable")
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
    @RequestLimit(name = MODULE_NAME+"--设置临时存储的url",key = KEY_PREFIX+"/setImageSelectTemp")
    @RequestPermission(value = "materialMsg:imageSelect/setImageSelectTemp")
    public ActionResult<Boolean> setImageSelectTemp(FileView file, HttpServletRequest request){
        String authorization = CookieUtil.getCookieValue(request, "authorization");
        boolean res = redisService.hset(RedisConstant.HASH_KEY.SELECT_IMAGE_TEMP, authorization, file, RedisConstant.TIMEOUT.SELECT_IMAGE_TEMP);
        return ActionResult.ok(res);
    }

    /**
     * 获取临时存储的url，并删除缓存
     * @return
     */
    @PostMapping(value = "/getImageSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "获取临时存储的url，并删除缓存",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--获取临时存储的url，并删除缓存",key = KEY_PREFIX+"/getImageSelectTemp")
    @RequestPermission(value = "materialMsg:imageSelect/getImageSelectTemp")
    public ActionResult<FileView> getImageSelectTemp(HttpServletRequest request){

        String authorization = CookieUtil.getCookieValue(request, "authorization");
        FileView file = (FileView) redisService.hget(RedisConstant.HASH_KEY.SELECT_IMAGE_TEMP, authorization);
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SELECT_IMAGE_TEMP, authorization);
        return ActionResult.ok(file);
    }

    /**
     * 设置临时存储的list
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/setListSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "设置临时存储的list",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--设置临时存储的list",key = KEY_PREFIX+"/setListSelectTemp")
    @RequestPermission(value = "materialMsg:imageSelect/setListSelectTemp")
    public ActionResult<Boolean> setListSelectTemp(@RequestBody FileDto dto, HttpServletRequest request){
        String authorization = CookieUtil.getCookieValue(request, "authorization");
        boolean res = redisService.hset(RedisConstant.HASH_KEY.SELECT_LIST_IMAGE_TEMP, authorization, dto.getFileList(), RedisConstant.TIMEOUT.SELECT_LIST_IMAGE_TEMP);
        return ActionResult.ok(res);
    }

    /**
     * 获取临时存储的list，并删除缓存
     * @return
     */
    @PostMapping(value = "/getListSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "获取临时存储的list，并删除缓存",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--获取临时存储的list，并删除缓存",key = KEY_PREFIX+"/getListSelectTemp")
    @RequestPermission(value = "materialMsg:imageSelect/getListSelectTemp")
    public ActionResult<List<FileView>> getListSelectTemp(HttpServletRequest request){

        String authorization = CookieUtil.getCookieValue(request, "authorization");
        List<FileView> list = (List<FileView>) redisService.hget(RedisConstant.HASH_KEY.SELECT_LIST_IMAGE_TEMP, authorization);
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SELECT_LIST_IMAGE_TEMP, authorization);
        return ActionResult.ok(list);
    }


}
