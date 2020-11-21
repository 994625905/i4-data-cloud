package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.HistoryImageDto;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeDto;
import cn.i4.data.cloud.core.service.IHistoryNoticeFileService;
import cn.i4.data.cloud.core.service.IHistoryNoticeService;
import cn.i4.data.cloud.core.service.IHistoryNoticeTypeService;
import cn.i4.data.cloud.mongo.core.service.MongoHistoryNoticeService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 点滴的模型视图页面
 * @author wangjc
 * @title: HistoryController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/19-19:09
 */
@Controller
@RequestMapping(value = "/history")
public class HistoryController extends WebBaseController {

    @Autowired
    private IHistoryNoticeService iHistoryNoticeService;
    @Autowired
    private IHistoryNoticeTypeService iHistoryNoticeTypeService;
    @Autowired
    private IHistoryNoticeFileService iHistoryNoticeFileService;
    @Autowired
    private MongoHistoryNoticeService mongoHistoryNoticeService;

    /**
     * 加载历史相册的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/historyImageWall/index")
    @RequestPermission(value = "history:historyImageWall/index")
    public ModelAndView historyImageWallIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/history/historyImageWall_index", request);
        return view;
    }

    /**
     * 加载历史相册的明细页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/historyImageWall/detailPage")
    @RequestPermission(value = "history:historyImageWall/detailPage")
    public ModelAndView historyImageWallDetailPage(HistoryImageDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/history/historyImageWall_detail", request);

        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载历史公告的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/historyNotice/index")
    @RequestPermission(value = "history:historyNotice/index")
    public ModelAndView historyNoticeIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/history/historyNotice_index", request);
        return view;
    }

    /**
     * 加载历史公告的新增页
     * @param request
     * @return
     */
    @RequestMapping(value = "/historyNotice/addPage")
    @RequestPermission(value = "history:historyNotice/addPage")
    public ModelAndView historyNoticeAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/history/historyNotice_add", request);
        view.addObject("typeList",iHistoryNoticeTypeService.list());
        return view;
    }

    /**
     * 加载历史公告的编辑页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/historyNotice/editPage")
    @RequestPermission(value = "history:historyNotice/editPage")
    public ModelAndView historyNoticeEditPage(HistoryNoticeDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/history/historyNotice_edit", request);

        view.addObject("typeList",iHistoryNoticeTypeService.list());
        view.addObject("fileList",iHistoryNoticeFileService.selectByNoticeId(dto.getId()));
        view.addObject("model",iHistoryNoticeService.getById(dto.getId()));
        view.addObject("mongo",mongoHistoryNoticeService.selectByMongoId(dto.getMongoId()));
        return view;
    }

    /**
     * 加载历史公告的预览页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/historyNotice/readPage")
    @RequestPermission(value = "history:historyNotice/readPage")
    public ModelAndView historyNoticeReadPage(HistoryNoticeDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/history/historyNotice_read", request);

        view.addObject("typeList",iHistoryNoticeTypeService.list());
        view.addObject("fileList",iHistoryNoticeFileService.selectByNoticeId(dto.getId()));
        view.addObject("model",iHistoryNoticeService.selectById(dto.getId()));
        view.addObject("mongo",mongoHistoryNoticeService.selectByMongoId(dto.getMongoId()));
        return view;
    }

}
