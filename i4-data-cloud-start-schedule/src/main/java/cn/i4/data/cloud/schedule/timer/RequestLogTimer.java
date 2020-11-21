package cn.i4.data.cloud.schedule.timer;

import cn.i4.data.cloud.schedule.core.RequestLogCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 请求日志的定时执行
 * @author wangjc
 * @title: RequestLogTimer
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/20-18:52
 */
@Component
public class RequestLogTimer {

    @Autowired
    private RequestLogCore core;

    /**
     * 定时执行：每天上午10点，下午16点，晚上23点
     */
    @Scheduled(cron = "0 0 12,16,23 * * ?")
    public void schedule(){
        core.asyncRequestLog();
        core.asyncRequestLimit();
        core.asyncRequestPermission();
    }
}
