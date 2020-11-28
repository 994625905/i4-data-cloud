package cn.i4.data.cloud.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程池,设置监控,用多线程来跑定时任务
 * @author wangjc
 * @title: ExecutorConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/19-17:46
 */
@Configuration
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Value("${i4.data.cloud.schedule.executor.core_pool_size}")
    private Integer corePoolSize;

    @Value("${i4.data.cloud.schedule.executor.max_pool_size}")
    private Integer maxPoolSize;

    @Value("${i4.data.cloud.schedule.executor.queue_size}")
    private Integer QueueSize;

    @Value("${i4.data.cloud.schedule.executor.thread_name_prefix}")
    private String threadNamePrefix;

    @Bean(value = "i4DataCloudScheduleAsyncServiceExecutor")
    public Executor i4DataCloudScheduleAsyncServiceExecutor(){
        logger.info("start i4DataCloudScheduleAsyncServiceExecutor");

        ThreadPoolTaskExecutor executor = new I4DataCloudScheduleThreadPoolTaskExecutor();

        // 配置核心线程数
        executor.setCorePoolSize(this.corePoolSize);

        // 配置最大线程数
        executor.setMaxPoolSize(this.maxPoolSize);

        // 配置队列大小
        executor.setQueueCapacity(this.QueueSize);

        //配置线程池中的线程名称前缀
        executor.setThreadNamePrefix(this.threadNamePrefix);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        executor.initialize();
        return executor;
    }

}
