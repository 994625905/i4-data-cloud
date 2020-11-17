package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.chart.view.ChartBarAndPieView;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;
import cn.i4.data.cloud.core.service.IAfternoonTeaSelectService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 下午茶--选择记录汇总
 * @author wangjc
 * @title: SelectController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/10-20:14
 */
@RestController
@RequestMapping(value = "/afternoonTea/selectLog")
public class SelectLogController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--选择记录汇总";
    private static final String KEY_PREFIX = "/afternoonTea/selectLog";
    @Autowired
    private IAfternoonTeaSelectService iAfternoonTeaSelectService;


    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "afternoonTea:selectLog/loadTable")
    public ActionResult<IPage<AfternoonTeaSelectView>> loadTable(AfternoonTeaSelectDto dto, HttpServletRequest request){
        IPage<AfternoonTeaSelectView> page = iAfternoonTeaSelectService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 加载报表按类型
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadChart")
    @RequestLog(module = MODULE_NAME,content = "加载报表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载报表",key = KEY_PREFIX+"/loadChart")
    @RequestPermission(value = "afternoonTea:selectLog/loadChart")
    public ActionResult<ChartBarAndPieView> loadChart(AfternoonTeaSelectDto dto, HttpServletRequest request){
        List<Map<String,Object>> list = iAfternoonTeaSelectService.loadChart(dto);
        ChartBarAndPieView res = new ChartBarAndPieView("点单统计图","name","count",list,15);
        return ActionResult.ok(res);
    }

    /**
     * 加载报表根据类型
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadChartByType")
    @RequestLog(module = MODULE_NAME,content = "加载报表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载报表",key = KEY_PREFIX+"/loadChartByType")
    @RequestPermission(value = "afternoonTea:selectLog/loadChartByType")
    public ActionResult<ChartBarAndPieView> loadChartByType(AfternoonTeaSelectDto dto, HttpServletRequest request){
        List<Map<String,Object>> list = iAfternoonTeaSelectService.loadChartByType(dto);
        ChartBarAndPieView res = new ChartBarAndPieView("点单统计图","type","count",list,15);
        return ActionResult.ok(res);
    }

}
