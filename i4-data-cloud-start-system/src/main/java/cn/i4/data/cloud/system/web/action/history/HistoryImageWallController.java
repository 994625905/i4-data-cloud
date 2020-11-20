package cn.i4.data.cloud.system.web.action.history;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.HistoryImageDto;
import cn.i4.data.cloud.core.entity.dto.HistoryImageGroupDto;
import cn.i4.data.cloud.core.entity.model.*;
import cn.i4.data.cloud.core.entity.view.HistoryImageGroupView;
import cn.i4.data.cloud.core.entity.view.HistoryImageView;
import cn.i4.data.cloud.core.service.IHistoryImageGroupService;
import cn.i4.data.cloud.core.service.IHistoryImageService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 点滴--历史相册的控制层
 * @author wangjc
 * @title: HistoryImageWallController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/19-19:13
 */
@RequestMapping(value = "/history/historyImageWall")
@RestController
public class HistoryImageWallController extends WebBaseController {

    private static final String MODULE_NAME = "点滴--历史相册";
    private static final String KEY_PREFIX = "/history/historyImageWall";
    @Autowired
    private IHistoryImageGroupService iHistoryImageGroupService;
    @Autowired
    private IHistoryImageService iHistoryImageService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "history:historyImageWall/loadTable")
    public ActionResult<IPage<HistoryImageGroupView>> loadTable(HistoryImageGroupDto dto){
        IPage<HistoryImageGroupView> page = iHistoryImageGroupService.selectPage(dto);

        return ActionResult.ok(page);
    }

    /**
     * 新增
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "history:historyImageWall/insert")
    public ActionResult<HistoryImageGroupView> insert(@RequestBody HistoryImageGroupDto dto, HttpServletRequest request){
        HistoryImageGroupModel model = dto.getModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(this.getUser(request).getId());

        boolean save = iHistoryImageGroupService.save(model);
        if(save){
            HistoryImageGroupView groupView = iHistoryImageGroupService.selectByGroupId(model.getId());
            return ActionResult.ok(groupView);
        }
        return ActionResult.error("新增失败");
    }

    /**
     * 修改
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "history:historyImageWall/update")
    public ActionResult<Boolean> update(@RequestBody HistoryImageGroupDto dto, HttpServletRequest request){
        HistoryImageGroupModel model = dto.getModel();

        boolean modify = iHistoryImageGroupService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("修改失败");
    }

    /**
     * 删除，级联删除数据，包括redis
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "history:historyImageWall/delete")
    public ActionResult<Boolean> delete(HistoryImageGroupDto dto){
        try {
            /** 删除redis */
            List<HistoryImageModel> imageModelList = iHistoryImageService.selectByGroupId(dto.getId());
            for(HistoryImageModel image:imageModelList){
                redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_COUNT,image.getId().toString());
                redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.HISTORY_IMAGE_READ_COUNT,image.getId().toString());
            }
            /** 删除MySQL */
            Boolean delete = iHistoryImageGroupService.deleteById(dto);
            return ActionResult.ok(delete);
        } catch (CommonException e) {
            e.printStackTrace();
            return ActionResult.error("删除失败");
        }
    }

    /**
     * 加载相册照片列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadDetailTable")
    @RequestLog(module = MODULE_NAME,content = "加载相册照片列表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载相册照片列表",key = KEY_PREFIX+"/loadDetailTable")
    @RequestPermission(value = "history:historyImageWall/loadDetailTable")
    public ActionResult<IPage<HistoryImageView>> loadDetailTable(HistoryImageDto dto,HttpServletRequest request){
        Integer userId = this.getUser(request).getId();
        IPage<HistoryImageView> page = iHistoryImageService.selectPage(dto);

        /** 获取点赞数，阅读数，当前用户是否点赞 */
        for(HistoryImageView view:page.getRecords()){

            Integer likeCount = (Integer) redisService.hget(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_COUNT,view.getId().toString());
            Integer readCount = (Integer) redisService.hget(RedisConstant.HASH_KEY.HISTORY_IMAGE_READ_COUNT,view.getId().toString());
            Set<Integer> userLike = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_USER,userId.toString());

            view.setLikeCount(likeCount == null?0:likeCount);
            view.setReadCount(readCount == null?0:readCount);

            view.setIsLike(0);
            if(userLike != null && userLike.size() > 0){
                for(Integer imageId:userLike){
                    if(imageId.equals(view.getId())){
                        view.setIsLike(1);
                        break;
                    }
                }
            }
        }

        /** list排序，按照点赞数，上传时间 */
        Collections.sort(page.getRecords(), (o1, o2) -> {
            Integer likeCount1 = o1.getLikeCount();
            Integer likeCount2 = o2.getLikeCount();

            if(likeCount1.equals(likeCount2)){
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }else{
                return likeCount2.compareTo(likeCount1);
            }
        });
        return ActionResult.ok(page);
    }

    /**
     * 上传照片
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadImage")
    @RequestLog(module = MODULE_NAME,content = "上传照片",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--上传照片",key = KEY_PREFIX+"/uploadImage")
    @RequestPermission(value = "history:historyImageWall/uploadImage")
    public ActionResult<List<HistoryImageView>> uploadImage(@RequestBody HistoryImageDto dto,HttpServletRequest request){

        UserModel user = this.getUser(request);
        dto.setUserId(user.getId());

        try {
            List<HistoryImageView> list = iHistoryImageService.uploadImage(dto);
            /** 获取点赞数，阅读数，当前用户是否点赞 */
            for(HistoryImageView view:list){
                view.setLikeCount(0);
                view.setReadCount(0);
                view.setIsLike(0);
                view.setCreateUserName(user.getRealname());
            }
            return ActionResult.ok(list);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 删除图片
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/deleteImage")
    @RequestLog(module = MODULE_NAME,content = "/删除图片",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除图片",key = KEY_PREFIX+"/deleteImage")
    @RequestPermission(value = "history:historyImageWall/deleteImage")
    public ActionResult<Boolean> deleteImage(HistoryImageDto dto, HttpServletRequest request){

        /** 删除该图片的redis缓存 */
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_COUNT,dto.getId().toString());
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.HISTORY_IMAGE_READ_COUNT,dto.getId().toString());
        /** 删除MySQL */
        try {
            Boolean delete = iHistoryImageService.deleteById(dto);
            return ActionResult.ok(delete);
        } catch (CommonException e) {
            e.printStackTrace();
            return ActionResult.error("删除失败");
        }
    }

    /**
     * 阅读记录+1，添加到set中
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/read")
    @RequestLog(module = MODULE_NAME,content = "/阅读",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--阅读",key = KEY_PREFIX+"/read")
    @RequestPermission(value = "history:historyImageWall/read")
    public ActionResult<Boolean> read(@RequestBody HistoryImageDto dto, HttpServletRequest request){
        HistoryImageReadModel readModel = dto.getReadModel();
        readModel.setCreateTime(System.currentTimeMillis()/1000L);
        readModel.setCreateUserId(this.getUser(request).getId());

        /** 添加到阅读set中 */
        redisService.setAddSetMap(RedisConstant.SET_KEY.HISTORY_IMAGE_READ, JSONObject.toJSONString(readModel));
        /** 阅读数量+1 */
        redisService.hincr(RedisConstant.HASH_KEY.HISTORY_IMAGE_READ_COUNT,readModel.getImageId().toString(),1);
        return ActionResult.ok(true);
    }

    /**
     * 点赞记录+1，添加到set中，不能追加createTime，否则set无法去重
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/like")
    @RequestLog(module = MODULE_NAME,content = "/点赞",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--点赞",key = KEY_PREFIX+"/like")
    @RequestPermission(value = "history:historyImageWall/like")
    public ActionResult<Boolean> like(@RequestBody HistoryImageDto dto, HttpServletRequest request){
        HistoryImageLikeModel likeModel = dto.getLikeModel();
        likeModel.setCreateTime(System.currentTimeMillis()/1000L);
        likeModel.setCreateUserId(this.getUser(request).getId());
        likeModel.setType(1);

        /** 添加到点赞set中 */
        redisService.setAddSetMap(RedisConstant.SET_KEY.HISTORY_IMAGE_LIKE, JSONObject.toJSONString(likeModel));
        /** 照片点赞数量+1 */
        redisService.hincr(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_COUNT,likeModel.getImageId().toString(),1);
        /** 个人点赞列表添加 */
        Set<Integer> userLike = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString());
        if(userLike == null){
            userLike = new HashSet<>();
        }
        userLike.add(likeModel.getImageId());
        redisService.hset(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString(),userLike);
        return ActionResult.ok(true);
    }

    /**
     * 点赞取消-1，添加到取赞的set中
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/cancelLike")
    @RequestLog(module = MODULE_NAME,content = "/点赞取消",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--点赞取消",key = KEY_PREFIX+"/cancelLike")
    @RequestPermission(value = "history:historyImageWall/cancelLike")
    public ActionResult<Boolean> cancelLike(@RequestBody HistoryImageDto dto, HttpServletRequest request){
        HistoryImageLikeModel likeModel = dto.getLikeModel();
        likeModel.setCreateTime(System.currentTimeMillis()/1000L);
        likeModel.setCreateUserId(this.getUser(request).getId());
        likeModel.setType(-1);

        /** 添加到点赞的set中 */
        redisService.setAddSetMap(RedisConstant.SET_KEY.HISTORY_IMAGE_LIKE, JSONObject.toJSONString(likeModel));
        /** 照片点赞数量-1 */
        redisService.hincr(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_COUNT,likeModel.getImageId().toString(),-1);
        /** 个人点赞列表移除 */
        Set<Integer> userLike = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString());
        userLike.remove(likeModel.getImageId());
        redisService.hset(RedisConstant.HASH_KEY.HISTORY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString(),userLike);
        return ActionResult.ok(true);
    }


}
