package cn.i4.data.cloud.schedule.core;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import cn.i4.data.cloud.core.service.IPartyActivityImageLikeService;
import cn.i4.data.cloud.core.service.IPartyActivityImageReadService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 活动照片的业务处理
 * @author wangjc
 * @title: ActivityImageCore
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/19-16:54
 */
@Component
public class ActivityImageCore {

    private static final Logger logger = LoggerFactory.getLogger(ActivityImageCore.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private IPartyActivityImageLikeService iPartyActivityImageLikeService;
    @Autowired
    private IPartyActivityImageReadService iPartyActivityImageReadService;

    /**
     * 同步点赞数据
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncLike(){
        Set<Object> likeSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.ACTIVITY_IMAGE_LIKE);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.ACTIVITY_IMAGE_LIKE);

        /** 数据入库 */
        Integer insert = iPartyActivityImageLikeService.asyncByRedis(likeSet);
        logger.info("活动图片点赞数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }

    /**
     * 同步阅读数据
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncRead(){
        Set<Object> readSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.ACTIVITY_IMAGE_READ);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.ACTIVITY_IMAGE_READ);

        /** 数据入库 */
        Integer insert = iPartyActivityImageReadService.asyncByRedis(readSet);
        logger.info("活动图片阅读数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }

}
