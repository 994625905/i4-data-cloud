package cn.i4.data.cloud.system.web.action.weekReport;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.model.UserTemplateModel;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.UserTemplateView;
import cn.i4.data.cloud.core.entity.view.WeekreportView;
import cn.i4.data.cloud.core.service.IWeekreportService;
import cn.i4.data.cloud.mongo.core.entity.MongoWeekReport;
import cn.i4.data.cloud.mongo.core.service.MongoWeekReportService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 周报事务--周报提交的控制层
 * @author wangjc
 * @title: WeekReportApplyController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/6-9:51
 */
@RestController
@RequestMapping(value = "/weekReport/weekReportApply")
public class WeekReportApplyController extends WebBaseController {

    private static final String KEY_PREFIX = "/processEngine/modelMsg";
    private static final String MODULE_NAME = "周报事务--周报提交";

    @Autowired
    private IWeekreportService iWeekreportService;
    @Autowired
    private MongoWeekReportService mongoWeekReportService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<IPage<WeekreportView>> loadTable(WeekreportDto dto, HttpServletRequest request){
        dto.setUserId(getUser(request).getId());

        IPage<WeekreportView> page = iWeekreportService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    public ActionResult<Boolean> delete(WeekreportDto dto,HttpServletRequest request){
        boolean remove = iWeekreportService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除失败");
    }

    /**
     * 设置标题模板
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/setTitle")
    @RequestLog(module = MODULE_NAME,content = "设置标题模板",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--设置标题模板",key = KEY_PREFIX+"/setTitle")
    public ActionResult<Boolean> setTitle(WeekreportDto dto,HttpServletRequest request){
        Integer userId = this.getUser(request).getId();
        Boolean res = true;

        UserTemplateModel template = iUserTemplateService.selectByUserId(userId);
        if(template == null){
            template = new UserTemplateModel();
            template.setUserId(userId);
            template.setWeekreportTitle(dto.getTitleTemplate());
            res = iUserTemplateService.save(template);
        }else{
            template.setWeekreportTitle(dto.getTitleTemplate());
            res = iUserTemplateService.modify(template);
        }
        if(res){
            return ActionResult.ok(true);
        }
        return ActionResult.error("设置标题模板失败");
    }

    /**
     * 新增
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    public ActionResult<Boolean> insert(WeekreportDto dto,HttpServletRequest request){

        /** 富文本的存储 */
        MongoWeekReport mongoWeekReport = new MongoWeekReport();
        mongoWeekReport.setContent(dto.getContent());
        mongoWeekReport.setMdContent(dto.getMdContent());
        String mongoId = mongoWeekReportService.insertOne(mongoWeekReport);
        if(StringUtil.isNullOrEmpty(mongoId)){
            return ActionResult.error("富文本的存储失败");
        }

        /** MySQL的存储 */
        WeekreportModel model = dto.getModel();
        model.setMongoId(mongoId);
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setUserId(this.getUser(request).getId());
        Boolean save = iWeekreportService.save(model);
        if(save){
            return ActionResult.ok(true);
        }
        return ActionResult.error("新增失败");
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    public ActionResult<Boolean> update(WeekreportDto dto){

        /** 富文本的修改 */
        MongoWeekReport mongoWeekReport = new MongoWeekReport();
        mongoWeekReport.setContent(dto.getContent());
        mongoWeekReport.setMdContent(dto.getMdContent());
        mongoWeekReport.setMongoId(new ObjectId(dto.getMongoId()));
        mongoWeekReportService.updateOne(mongoWeekReport);

        /** MySQL的修改 */
        WeekreportModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);
        Boolean modify = iWeekreportService.modify(model);
        if(modify){
            return ActionResult.ok(modify);
        }
        return ActionResult.error("修改失败");
    }

}
