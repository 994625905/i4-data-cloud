package cn.i4.data.cloud.autocode.web;

import ch.qos.logback.core.db.dialect.DBUtil;
import cn.i4.data.cloud.autocode.core.DataBaseCore;
import cn.i4.data.cloud.autocode.util.DataBaseUtil;
import cn.i4.data.cloud.base.controller.BaseController;
import cn.i4.data.cloud.base.result.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-14:11
 */
@RestController
@RequestMapping(value = "/autoCode")
public class AutoCodeController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(AutoCodeController.class);
    @Autowired
    private DataBaseCore dataBaseCore;

    /**
     * 根据数据源获取表
     * @param driverClass
     * @param dataSourceUrl
     * @param user
     * @param password
     * @return
     */
    @PostMapping(value = "/getTableListBySource")
    public ActionResult<List<String>> getTableListBySource(@RequestParam String driverClass, @RequestParam String dataSourceUrl,@RequestParam String user,@RequestParam String password){
        try {
            List<String> list = DataBaseUtil.tableList(driverClass,dataSourceUrl,user,password);
            return ActionResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return ActionResult.error("根据数据源获取表失败");
        }
    }

    /**
     * 生成代码
     * @param driverClass
     * @param dataSourceUrl
     * @param user
     * @param password
     * @param author
     * @param packagePrefix
     * @param localDir
     * @param tableList
     * @return
     */
    @PostMapping(value = "/create")
    public ActionResult<Boolean> create(@RequestParam String driverClass, @RequestParam String dataSourceUrl,@RequestParam String user,@RequestParam String password,
                                          @RequestParam String author,@RequestParam String packagePrefix,@RequestParam String localDir,@RequestParam List<String> tableList){
        try {
            dataBaseCore.buildCode(driverClass, dataSourceUrl, user, password, author, packagePrefix, localDir, tableList);
            return ActionResult.ok(true);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("生成代码失败");
        }
    }

}
