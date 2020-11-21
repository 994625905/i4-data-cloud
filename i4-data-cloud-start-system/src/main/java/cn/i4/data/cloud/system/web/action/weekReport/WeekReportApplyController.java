package cn.i4.data.cloud.system.web.action.weekReport;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.model.UserTemplateModel;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.UserTemplateView;
import cn.i4.data.cloud.core.entity.view.WeekreportFileView;
import cn.i4.data.cloud.core.entity.view.WeekreportView;
import cn.i4.data.cloud.core.service.IWeekreportFileService;
import cn.i4.data.cloud.core.service.IWeekreportService;
import cn.i4.data.cloud.mongo.core.entity.MongoWeekReport;
import cn.i4.data.cloud.mongo.core.service.MongoWeekReportService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private IWeekreportFileService iWeekreportFileService;
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
    @RequestPermission(value = "weekReport:weekReportApply/loadTable")
    public ActionResult<IPage<WeekreportView>> loadTable(WeekreportDto dto, HttpServletRequest request){
        dto.setUserId(getUser(request).getId());

        IPage<WeekreportView> page = iWeekreportService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除，同时删除MongoId
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "weekReport:weekReportApply/delete")
    public ActionResult<Boolean> delete(WeekreportDto dto,HttpServletRequest request){

        /** 删除Mongo */
        Long one = mongoWeekReportService.deleteOne(dto.getMongoId());
        if(one < 1){
            return ActionResult.error("删除mongo失败");
        }
        /** 删除MySQL */
        try {
            boolean delete = iWeekreportService.deleteById(dto.getId());
            return ActionResult.ok(delete);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
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
    @RequestPermission(value = "weekReport:weekReportApply/setTitle")
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
    @RequestPermission(value = "weekReport:weekReportApply/insert")
    public ActionResult<Boolean> insert(@RequestBody WeekreportDto dto, HttpServletRequest request){

        /** 富文本的存储 */
        MongoWeekReport mongoWeekReport = new MongoWeekReport();
        mongoWeekReport.setContent(dto.getContent());
        mongoWeekReport.setMdContent(dto.getMdContent());
        String mongoId = mongoWeekReportService.insertOne(mongoWeekReport);
        if(StringUtil.isNullOrEmpty(mongoId)){
            return ActionResult.error("富文本的存储失败");
        }

        /** MySQL的存储 */
        dto.setUserId(this.getUser(request).getId());
        dto.setMongoId(mongoId);
        try {
            Boolean insert = iWeekreportService.insert(dto);
            return ActionResult.ok(insert);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "weekReport:weekReportApply/update")
    public ActionResult<Boolean> update(@RequestBody WeekreportDto dto,HttpServletRequest request){

        /** 富文本的修改 */
        MongoWeekReport mongoWeekReport = new MongoWeekReport();
        mongoWeekReport.setContent(dto.getContent());
        mongoWeekReport.setMdContent(dto.getMdContent());
        mongoWeekReport.setMongoId(new ObjectId(dto.getMongoId()));
        mongoWeekReportService.updateOne(mongoWeekReport);

        /** MySQL的修改 */
        dto.setUserId(this.getUser(request).getId());
        try {
            Boolean update = iWeekreportService.update(dto);
            return ActionResult.ok(update);
        } catch (CommonException e) {
            return ActionResult.error("修改失败");
        }
    }

    /**
     * 查询附件列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getFileListByWeekReportId")
    @RequestLog(module = MODULE_NAME,content = "查询附件列表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--查询附件列表",key = KEY_PREFIX+"/getFileListByWeekReportId")
    @RequestPermission(value = "weekReport:weekReportApply/getFileListByWeekReportId")
    public ActionResult<List<WeekreportFileView>> getFileListByWeekReportId(WeekreportDto dto){
        List<WeekreportFileView> list = iWeekreportFileService.selectByWeekReportId(dto.getId());
        return ActionResult.ok(list);
    }

}
