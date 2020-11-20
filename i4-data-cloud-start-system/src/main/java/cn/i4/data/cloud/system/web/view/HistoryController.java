package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.HistoryImageDto;
import cn.i4.data.cloud.system.web.WebBaseController;
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

}
