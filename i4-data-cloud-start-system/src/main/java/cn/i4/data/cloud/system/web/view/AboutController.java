package cn.i4.data.cloud.system.web.view;

import cn.hutool.core.convert.Convert;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.mongo.core.entity.MongoSystemConstant;
import cn.i4.data.cloud.mongo.core.service.MongoSystemConstantService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 关于平台的模型视图页面
 * @author wangjc
 * @title: AboutController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/5-15:49
 */
@Controller
@RequestMapping(value = "/about")
public class AboutController extends WebBaseController {

    @Autowired
    private MongoSystemConstantService mongoSystemConstantService;

    /**
     * 加载认证信息的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/authInfo/index")
    @RequestPermission(value = "about:authInfo/index")
    public ModelAndView authInfoIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/about/authInfo_index", request);

        String mongoId = Convert.toStr(this.getSystemConstant().get("authInfo"));
        MongoSystemConstant constant = mongoSystemConstantService.selectByMongoId(mongoId);

        view.addObject("content",constant == null?"":constant.getContent());
        return view;
    }
}
