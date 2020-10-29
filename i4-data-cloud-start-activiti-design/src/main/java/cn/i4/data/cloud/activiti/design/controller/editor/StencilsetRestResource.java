package cn.i4.data.cloud.activiti.design.controller.editor;

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;

@Controller
public class StencilsetRestResource {

    private static final Logger logger = LoggerFactory.getLogger(StencilsetRestResource.class);

    @RequestMapping(value = {"/activiti/editor/stencilset.do"}, method = {RequestMethod.GET}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getStencilset() {
        logger.info("StencilsetRestResource.getStencilset-----------");
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}