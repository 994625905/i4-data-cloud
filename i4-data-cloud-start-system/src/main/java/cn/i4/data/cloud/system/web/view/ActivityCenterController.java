package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;
import cn.i4.data.cloud.core.service.*;
import cn.i4.data.cloud.mongo.core.entity.MongoPartyActivity;
import cn.i4.data.cloud.mongo.core.service.MongoPartyActivityService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 活动中心的模型视图页面
 * @author wangjc
 * @title: ActivityCenterController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/14-14:21
 */
@Controller
@RequestMapping(value = "/activityCenter")
public class ActivityCenterController extends WebBaseController {

    @Autowired
    private IPartyActivityTypeService iPartyActivityTypeService;
    @Autowired
    private IPartyActivityService iPartyActivityService;
    @Autowired
    private IPartyActivityFileService iPartyActivityFileService;
    @Autowired
    private IPartyActivitySignService iPartyActivitySignService;
    @Autowired
    private MongoPartyActivityService mongoPartyActivityService;

    /**
     * 加载活动发布的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/activityDeploy/index")
    @RequestPermission(value = "activityCenter:activityDeploy/index")
    public ModelAndView activityDeployIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activityDeploy_index", request);

        view.addObject("typeList",iPartyActivityTypeService.list());
        return view;
    }

    /**
     * 加载活动发布的新增页
     * @param request
     * @return
     */
    @RequestMapping(value = "/activityDeploy/addPage")
    @RequestPermission(value = "activityCenter:activityDeploy/addPage")
    public ModelAndView activityDeployAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activityDeploy_add", request);

        view.addObject("userList",iUserService.list());
        view.addObject("typeList",iPartyActivityTypeService.list());
        return view;
    }

    /**
     * 加载活动发布的编辑页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/activityDeploy/editPage")
    @RequestPermission(value = "activityCenter:activityDeploy/editPage")
    public ModelAndView activityDeployEditPage(PartyActivityDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activityDeploy_edit", request);

        view.addObject("userList",iUserService.list());
        view.addObject("typeList",iPartyActivityTypeService.list());
        view.addObject("model",iPartyActivityService.getById(dto.getId()));
        view.addObject("fileList",iPartyActivityFileService.getListByActivityId(dto.getId()));
        view.addObject("mongo",mongoPartyActivityService.selectByMongoId(dto.getMongoId()));
        return view;
    }

    /**
     * 加载活动类型的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/activityType/index")
    @RequestPermission(value = "activityCenter:activityType/index")
    public ModelAndView activityTypeIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activityType_index", request);
        return view;
    }

    /**
     * 加载活动行的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/activity/index")
    @RequestPermission(value = "activityCenter:activity/index")
    public ModelAndView activityIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activity_index", request);
        view.addObject("typeList",iPartyActivityTypeService.list());
        return view;
    }

    /**
     * 加载活动行的签到页
     * @param request
     * @return
     */
    @RequestMapping(value = "/activity/signPage")
    @RequestPermission(value = "activityCenter:activity/signPage")
    public ModelAndView activitySignPage(PartyActivityDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activity_sign", request);
        Map<String,Object> sign = iPartyActivitySignService.signCount(dto.getId());

        view.addObject("userList",iUserService.list());
        view.addObject("model",iPartyActivityService.getById(dto.getId()));
        view.addObject("sign",sign);
        return view;
    }

    /**
     * 加载活动预览页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/activity/readPage")
    @RequestPermission(value = "activityCenter:activity/readPage")
    public ModelAndView activityReadPage(PartyActivityDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activity_read", request);

        MongoPartyActivity mongo = mongoPartyActivityService.selectByMongoId(dto.getMongoId());
        PartyActivityView model = iPartyActivityService.selectById(dto.getId());
        List<PartyActivityFileModel> fileList = iPartyActivityFileService.getListByActivityId(dto.getId());

        view.addObject("mongo",mongo);
        view.addObject("model",model);
        view.addObject("fileList",fileList);
        return view;
    }

    /**
     * 加载活动照片墙页面（图片要分页）
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/activity/imagePage")
    @RequestPermission(value = "activityCenter:activity/imagePage")
    public ModelAndView activityImagePage(PartyActivityDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/activity_image", request);
        view.addObject("activityId",dto.getId());
        return view;
    }

    /**
     * 加载照片墙的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/imageWall/index")
    @RequestPermission(value = "activityCenter:imageWall/index")
    public ModelAndView imageWallIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/imageWall_index", request);
        return view;
    }

    /**
     * 加载照片墙的详情页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/imageWall/detailPage")
    @RequestPermission(value = "activityCenter:imageWall/detailPage")
    public ModelAndView imageWallDetailPage(PartyActivityImageDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/activityCenter/imageWall_detail", request);

        view.addObject("activityId",dto.getActivityId());
        return view;
    }

}
