package cn.i4.data.cloud.system.web.action.autoCode;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.DesUtil;
import cn.i4.data.cloud.base.util.MD5Util;
import cn.i4.data.cloud.core.entity.dto.AutocodeDatasourceDto;
import cn.i4.data.cloud.core.entity.model.AutocodeDatasourceModel;
import cn.i4.data.cloud.core.entity.model.LogAutocodeModel;
import cn.i4.data.cloud.core.entity.model.LogAutocodeTableModel;
import cn.i4.data.cloud.core.service.IAutocodeDatasourceService;
import cn.i4.data.cloud.core.service.ILogAutocodeService;
import cn.i4.data.cloud.core.service.ILogAutocodeTableService;
import cn.i4.data.cloud.system.microservice.AutoCodeMicroService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 自动生成代码--数据源的控制层
 * @author wangjc
 * @title: DataSourceMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/26-20:45
 */
@RequestMapping(value = "/autoCode/dataSourceMsg")
@RestController
public class DataSourceMsgController extends WebBaseController {

    private static final String MODULE_NAME = "自动生成代码--数据源管理";
    private static final String KEY_PREFIX = "/autoCode/dataSourceMsg";
    @Autowired
    private IAutocodeDatasourceService iAutocodeDatasourceService;
    @Autowired
    private ILogAutocodeService iLogAutocodeService;
    @Autowired
    private ILogAutocodeTableService iLogAutocodeTableService;
    @Autowired
    private AutoCodeMicroService autoCodeMicroService;

    /**
     * 加载数据
     * @return
     */
    @PostMapping(value = "/loadData")
    @RequestLog(module = MODULE_NAME,content = "加载数据源列表",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"加载数据源列表",key = KEY_PREFIX+"/loadData")
    @RequestPermission(value = "autoCode:dataSourceMsg/loadData")
    public ActionResult<List<AutocodeDatasourceModel>> loadData(){

        List<AutocodeDatasourceModel> list = iAutocodeDatasourceService.list();
        return ActionResult.ok(list);
    }

    /**
     * 新增数据源
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增数据源",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"新增数据源",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "autoCode:dataSourceMsg/insert")
    public ActionResult<Boolean> insert(@RequestBody AutocodeDatasourceDto dto, HttpServletRequest request){
        AutocodeDatasourceModel model = dto.getModel();
        model.setUserId(getUser(request).getId());
        model.setAuthUser(DesUtil.enc(model.getAuthUser()));
        model.setAuthPassword(DesUtil.enc(model.getAuthPassword()));
        model.setCreateTime(System.currentTimeMillis()/1000L);

        boolean save = iAutocodeDatasourceService.save(model);
        if(!save){
            return ActionResult.error("新增数据源失败");
        }
        return ActionResult.ok(save);
    }

    /**
     * 修改数据源
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改数据源",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"修改数据源",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "autoCode:dataSourceMsg/update")
    public ActionResult<Boolean> update(@RequestBody AutocodeDatasourceDto dto){
        AutocodeDatasourceModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);

        boolean modify = iAutocodeDatasourceService.modify(model);
        if(!modify){
            return ActionResult.error("修改数据源失败");
        }
        return ActionResult.ok(modify);
    }

    /**
     * 删除数据源
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除数据源",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"删除数据源",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "autoCode:dataSourceMsg/delete")
    public ActionResult<Boolean> delete(AutocodeDatasourceDto dto){
        boolean remove = iAutocodeDatasourceService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除数据源失败");
    }

    /**
     * 获取数据表集合根据数据源
     * @param dto
     * @return
     */
    @PostMapping(value = "/getTableListBySource")
    @RequestLog(module = MODULE_NAME,content = "获取数据表集合根据数据源",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"获取数据表",key = KEY_PREFIX+"/getTableListBySource")
    @RequestPermission(value = "autoCode:dataSourceMsg/getTableListBySource")
    public ActionResult<List<String>> getTableListBySource(AutocodeDatasourceDto dto){
        ActionResult<List<String>> list = autoCodeMicroService.getTableListBySource(dto.getDriverClass(), dto.getDataSourceUrl(), DesUtil.dec(dto.getUser()), DesUtil.dec(dto.getPassword()));
        return list;
    }

    /**
     * 自动生成代码
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/create")
    @RequestLog(module = MODULE_NAME,content = "自动生成代码",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"自动生成代码",key = KEY_PREFIX+"/create")
    @RequestPermission(value = "autoCode:dataSourceMsg/create")
    public ActionResult<Boolean> create(@RequestBody AutocodeDatasourceDto dto,HttpServletRequest request){
        ActionResult<Boolean> result = autoCodeMicroService.create(dto.getDriverClass(), dto.getDataSourceUrl(), DesUtil.dec(dto.getUser()), DesUtil.dec(dto.getPassword()), dto.getDefaultAuthor(),
                                        dto.getDefaultPackagePrefix(), dto.getDefaultLocal(), dto.getTableList());
        if(result.getCode() != 200){
            return ActionResult.error("自动生成代码失败");
        }
        /** 生成日志 */
        LogAutocodeModel log = new LogAutocodeModel();
        log.setCreateAuthor(dto.getDefaultAuthor());
        log.setCreateLocal(dto.getDefaultLocal());
        log.setCreatePackagePrefix(dto.getDefaultPackagePrefix());
        log.setCreateResult(1);
        log.setCreateTime(System.currentTimeMillis()/1000L);
        log.setDatasourceId(dto.getId());
        log.setLoginName(this.getUser(request).getLoginname());
        log.setUserId(this.getUser(request).getId());

        boolean save = iLogAutocodeService.save(log);
        if(!save){
            return ActionResult.error("保存日志失败");
        }
        for(String name:dto.getTableList()){
            LogAutocodeTableModel tableModel = new LogAutocodeTableModel();
            tableModel.setAutocodeLogId(log.getId());
            tableModel.setCreateResult(1);
            tableModel.setCreateTime(System.currentTimeMillis()/1000L);
            tableModel.setLoginName(this.getUser(request).getLoginname());
            tableModel.setUserId(this.getUser(request).getId());
            tableModel.setTableName(name);
            save = iLogAutocodeTableService.save(tableModel) && save;
        }
        if(!save){
            return ActionResult.error("保存详情日志失败");
        }
        return ActionResult.ok(true);
    }

}
