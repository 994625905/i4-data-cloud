package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 素材中心的视图页面
 * @author wangjc
 * @title: MaterialMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2010:09
 */
@RequestMapping(value = "/materialMsg")
@Controller
public class MaterialMsgController extends WebBaseController {

    /**
     * 加载文件查看首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/fileFind/index")
    public ModelAndView fileFindIndex(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_index", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 文件上传页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/fileFind/uploadPage")
    public ModelAndView fileFindUploadPage(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_upload", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载图片选择页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/imageSelect/index")
    public ModelAndView imageSelectIndex(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_selectImage", request);
        view.addObject("param",dto);
        return view;
    }

}
