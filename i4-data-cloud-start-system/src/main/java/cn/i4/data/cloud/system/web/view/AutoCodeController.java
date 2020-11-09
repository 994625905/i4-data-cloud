package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.AutocodeDatasourceDto;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeDto;
import cn.i4.data.cloud.core.entity.model.AutocodeDatasourceModel;
import cn.i4.data.cloud.core.service.IAutocodeDatasourceService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 自动代码生成的模型视图页面
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/26-20:08
 */
@RequestMapping(value = "/autoCode")
@Controller
public class AutoCodeController extends WebBaseController {

    @Autowired
    private IAutocodeDatasourceService iAutocodeDatasourceService;

    /**
     * 加载数据源管理模块的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataSourceMsg/index")
    @RequestPermission(value = "autoCode:dataSourceMsg/index")
    public ModelAndView dataSourceMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/autoCode/dataSource_index", request);
        return view;
    }

    /**
     * 添加数据源页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataSourceMsg/addPage")
    @RequestPermission(value = "autoCode:dataSourceMsg/addPage")
    public ModelAndView dataSourceMsgAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/autoCode/dataSource_add", request);
        return view;
    }

    /**
     * 读取数据源页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataSourceMsg/readPage")
    @RequestPermission(value = "autoCode:dataSourceMsg/readPage")
    public ModelAndView dataSourceMsgReadPage(AutocodeDatasourceDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/autoCode/dataSource_read", request);
        view.addObject("dataSource",iAutocodeDatasourceService.getById(dto.getId()));
        return view;
    }

    /**
     * 修改数据源页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataSourceMsg/editPage")
    @RequestPermission(value = "autoCode:dataSourceMsg/editPage")
    public ModelAndView dataSourceMsgEditPage(AutocodeDatasourceDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/autoCode/dataSource_edit", request);
        view.addObject("dataSource",iAutocodeDatasourceService.getById(dto.getId()));
        return view;
    }

    /**
     * 加载生成代码日志的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/codeLog/index")
    @RequestPermission(value = "autoCode:codeLog/index")
    public ModelAndView codeLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/autoCode/codeLog_index", request);
        return view;
    }

    /**
     * 生成代码日志的详情页
     * @param request
     * @return
     */
    @RequestMapping(value = "/codeLog/detailPage")
    @RequestPermission(value = "autoCode:codeLog/detailPage")
    public ModelAndView codeLogDetailPage(LogAutocodeDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/autoCode/codeLog_detail", request);
        view.addObject("param",dto);
        return view;
    }

}
