package cn.i4.data.cloud.schedule.timer;

import cn.i4.data.cloud.schedule.core.HistoryImageCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 历史照片的定时任务
 * @author wangjc
 * @title: HistoryImageTimer
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/20-11:04
 */
@Component
public class HistoryImageTimer {

    @Autowired
    private HistoryImageCore core;

    /**
     * 定时执行：每天凌晨01点30执行
     */
    @Scheduled(cron = "0 30 01 ? * *")
    public void schedule(){
        core.asyncLike();
        core.asyncRead();
    }


}
