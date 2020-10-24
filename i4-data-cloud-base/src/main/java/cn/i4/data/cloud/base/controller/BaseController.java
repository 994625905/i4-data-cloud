package cn.i4.data.cloud.base.controller;

import org.springframework.web.servlet.ModelAndView;

/**
 * Controller的基类
 * @author wangjc
 * @title: BaseController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:32
 */
public class BaseController {

    /**
     * 获取模型视图
     * @param path
     * @return
     */
    public ModelAndView getModelAndView(String path){
        return new ModelAndView(path);
    }
}
