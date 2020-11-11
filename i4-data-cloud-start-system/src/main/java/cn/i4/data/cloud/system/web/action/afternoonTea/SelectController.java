package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
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

/**
 * 下午茶--选择记录
 * @author wangjc
 * @title: SelectController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/10-20:14
 */
@RestController
@RequestMapping(value = "/afternoonTea/select")
public class SelectController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--选择记录";
    private static final String KEY_PREFIX = "/afternoonTea/select";
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
    @RequestPermission(value = "afternoonTea:select/loadTable")
    public ActionResult<IPage<AfternoonTeaSelectView>> loadTable(AfternoonTeaSelectDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        IPage<AfternoonTeaSelectView> page = iAfternoonTeaSelectService.selectPage(dto);
        return ActionResult.ok(page);
    }
}
