package cn.i4.data.cloud.test.core;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wangjc
 * @title: TestCore
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/28-13:59
 */
@Component
public class TestCore {

    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Object value(){
        Integer res = 0;
        for(int i=0;i<10;i++){
            res += i;
        }
        return res;
    }

}
