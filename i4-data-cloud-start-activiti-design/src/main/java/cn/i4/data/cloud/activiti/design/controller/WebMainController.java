package cn.i4.data.cloud.activiti.design.controller;

import cn.i4.data.cloud.activiti.design.constant.ActivitiConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjc
 * @title: WebMainController
 * @projectName i4-data-cloud-start-activiti-design
 * @description: TODO
 * @date 2020/7/3111:27
 */
@Controller
@RequestMapping("/user")
public class WebMainController {

    /**
     * 创建模型,也是新增
     */
    @RequestMapping("create.do")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

            RepositoryService repositoryService = processEngine.getRepositoryService();

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put(ActivitiConstant.NODE_INFO.NAMESPACE, ActivitiConstant.NODE_INFO.NAMESPACE_VALUE);
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ActivitiConstant.NODE_INFO.NAME, ActivitiConstant.NODE_INFO.NAME_VALUE);
            modelObjectNode.put(ActivitiConstant.NODE_INFO.REVISION, ActivitiConstant.NODE_INFO.REVISION_VALUE);
            modelObjectNode.put(ActivitiConstant.NODE_INFO.DESCRIPTION, ActivitiConstant.NODE_INFO.DESCRIPTION_VALUE);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(ActivitiConstant.NODE_INFO.NAME_VALUE);
            modelData.setKey(ActivitiConstant.NODE_INFO.KEY);

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes(ActivitiConstant.NODE_INFO.ENCODING));

            //根据模型来部署流程
            response.sendRedirect(request.getContextPath() + "/activiti/modeler.html?modelId=" + modelData.getId());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建模型失败：");
        }
    }

}
