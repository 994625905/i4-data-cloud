package cn.i4.data.cloud.system.web.action.activityCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageView;
import cn.i4.data.cloud.core.service.IPartyActivityImageService;
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
 * 活动中心--照片墙
 * @author wangjc
 * @title: ImageWallController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/18-10:30
 */
@RestController
@RequestMapping(value = "/activityCenter/imageWall")
public class ImageWallController extends WebBaseController {

    private static final String MODULE_NAME = "活动中心--照片墙";
    private static final String KEY_PREFIX = "/activityCenter/imageWall";
    @Autowired
    private IPartyActivityImageService iPartyActivityImageService;

    /**
     * 加载详情表格，点赞数和阅读数从redis中获取
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadDetailTable")
    @RequestLog(module = MODULE_NAME,content = "/加载详情表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载详情表格",key = KEY_PREFIX+"/loadDetailTable")
    @RequestPermission(value = "activityCenter:imageWall/loadDetailTable")
    public ActionResult<IPage<PartyActivityImageView>> loadDetailTable(PartyActivityImageDto dto, HttpServletRequest request){
        Integer userId = this.getUser(request).getId();
        IPage<PartyActivityImageView> page = iPartyActivityImageService.selectPage(dto);

        /** 获取点赞数，阅读数，当前用户是否点赞 */
        for(PartyActivityImageView view:page.getRecords()){

            Integer likeCount = (Integer) redisService.hget(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_COUNT,view.getId().toString());
            Integer readCount = (Integer) redisService.hget(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_READ_COUNT,view.getId().toString());
            Set<Integer> userLike = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_USER,userId.toString());

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
     * 上传照片，
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadImage")
    @RequestLog(module = MODULE_NAME,content = "/上传照片",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--上传照片",key = KEY_PREFIX+"/uploadImage")
    @RequestPermission(value = "activityCenter:imageWall/uploadImage")
    public ActionResult<List<PartyActivityImageView>> uploadImage(@RequestBody PartyActivityImageDto dto, HttpServletRequest request){

        UserModel user = this.getUser(request);
        dto.setUserId(user.getId());

        try {
            List<PartyActivityImageView> list = iPartyActivityImageService.uploadImage(dto);
            /** 获取点赞数，阅读数，当前用户是否点赞 */
            for(PartyActivityImageView view:list){
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
    @RequestPermission(value = "activityCenter:imageWall/deleteImage")
    public ActionResult<Boolean> deleteImage(PartyActivityImageDto dto,HttpServletRequest request){

        /** 删除该图片的redis缓存 */
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_COUNT,dto.getId().toString());
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_READ_COUNT,dto.getId().toString());
        /** 删除MySQL */
        try {
            Boolean delete = iPartyActivityImageService.deleteById(dto);
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
    @RequestPermission(value = "activityCenter:imageWall/read")
    public ActionResult<Boolean> read(@RequestBody PartyActivityImageDto dto, HttpServletRequest request){
        PartyActivityImageReadModel readModel = dto.getReadModel();
        readModel.setCreateTime(System.currentTimeMillis()/1000L);
        readModel.setCreateUserId(this.getUser(request).getId());

        /** 添加到阅读set中 */
        redisService.setAddSetMap(RedisConstant.SET_KEY.ACTIVITY_IMAGE_READ, JSONObject.toJSONString(readModel));
        /** 阅读数量+1 */
        redisService.hincr(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_READ_COUNT,readModel.getImageId().toString(),1);
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
    @RequestPermission(value = "activityCenter:imageWall/like")
    public ActionResult<Boolean> like(@RequestBody PartyActivityImageDto dto, HttpServletRequest request){
        PartyActivityImageLikeModel likeModel = dto.getLikeModel();
        likeModel.setCreateTime(System.currentTimeMillis()/1000L);
        likeModel.setCreateUserId(this.getUser(request).getId());
        likeModel.setType(1);

        /** 添加到点赞set中 */
        redisService.setAddSetMap(RedisConstant.SET_KEY.ACTIVITY_IMAGE_LIKE, JSONObject.toJSONString(likeModel));
        /** 照片点赞数量+1 */
        redisService.hincr(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_COUNT,likeModel.getImageId().toString(),1);
        /** 个人点赞列表添加 */
        Set<Integer> userLike = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString());
        if(userLike == null){
            userLike = new HashSet<>();
        }
        userLike.add(likeModel.getImageId());
        redisService.hset(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString(),userLike);
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
    @RequestPermission(value = "activityCenter:imageWall/cancelLike")
    public ActionResult<Boolean> cancelLike(@RequestBody PartyActivityImageDto dto, HttpServletRequest request){
        PartyActivityImageLikeModel likeModel = dto.getLikeModel();
        likeModel.setCreateTime(System.currentTimeMillis()/1000L);
        likeModel.setCreateUserId(this.getUser(request).getId());
        likeModel.setType(-1);

        /** 添加到点赞的set中 */
        redisService.setAddSetMap(RedisConstant.SET_KEY.ACTIVITY_IMAGE_LIKE, JSONObject.toJSONString(likeModel));
        /** 照片点赞数量-1 */
        redisService.hincr(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_COUNT,likeModel.getImageId().toString(),-1);
        /** 个人点赞列表移除 */
        Set<Integer> userLike = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString());
        userLike.remove(likeModel.getImageId());
        redisService.hset(RedisConstant.HASH_KEY.ACTIVITY_IMAGE_LIKE_USER,likeModel.getCreateUserId().toString(),userLike);
        return ActionResult.ok(true);
    }

}
