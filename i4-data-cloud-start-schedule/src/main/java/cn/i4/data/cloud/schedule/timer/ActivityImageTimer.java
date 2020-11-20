package cn.i4.data.cloud.schedule.timer;

import cn.i4.data.cloud.schedule.core.ActivityImageCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 活动照片的定时任务处理
 * @author wangjc
 * @title: ActivityImageTimer
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/19-17:22
 */
@Component
public class ActivityImageTimer {

    @Autowired
    private ActivityImageCore core;

    /**
     * 定时执行：每天凌晨01点25执行
     */
    @Scheduled(cron = "0 25 01 ? * *")
    public void schedule(){
        core.asyncLike();
        core.asyncRead();
    }


}
