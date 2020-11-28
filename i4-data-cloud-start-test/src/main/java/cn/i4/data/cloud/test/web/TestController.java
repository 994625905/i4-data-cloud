package cn.i4.data.cloud.test.web;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.test.core.TestCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjc
 * @title: TestController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/27-16:59
 */
@RestController
@RequestMapping
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    public ThreadLocal<Map<String,Object>> tl = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected HashMap initialValue() {
            return new HashMap();
        }
    };

    @Autowired
    private TestCore testCore;

    public Map<String,Object> tMap = new HashMap<>();

    @RequestMapping(value = "get")
    public ActionResult<Object> get(){
        return ActionResult.ok(testCore.value());
    }

    @RequestMapping(value = "set")
    public ActionResult<Object> set(){
        tl.get().put("name","wangjc");
        tMap.put("name","tansongyun");
        return ActionResult.ok(tl.get().toString());
    }

    public static void main(String[] args) {



    }


}
