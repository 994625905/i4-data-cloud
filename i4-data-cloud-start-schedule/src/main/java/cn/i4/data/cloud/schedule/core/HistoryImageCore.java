package cn.i4.data.cloud.schedule.core;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.service.IHistoryImageLikeService;
import cn.i4.data.cloud.core.service.IHistoryImageReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 历史照片的业务处理
 * @author wangjc
 * @title: HistoryImageCore
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/20-11:00
 */
@Component
public class HistoryImageCore {

    private static final Logger logger = LoggerFactory.getLogger(HistoryImageCore.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private IHistoryImageLikeService iHistoryImageLikeService;
    @Autowired
    private IHistoryImageReadService iHistoryImageReadService;

    /**
     * 同步点赞数据
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncLike(){
        Set<Object> likeSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.HISTORY_IMAGE_LIKE);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.HISTORY_IMAGE_LIKE);

        /** 数据入库 */
        Integer insert = iHistoryImageLikeService.asyncByRedis(likeSet);
        logger.info("历史图片点赞数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }

    /**
     * 同步阅读数据
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncRead(){
        Set<Object> readSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.HISTORY_IMAGE_READ);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.HISTORY_IMAGE_READ);

        /** 数据入库 */
        Integer insert = iHistoryImageReadService.asyncByRedis(readSet);
        logger.info("历史图片阅读数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }

}
