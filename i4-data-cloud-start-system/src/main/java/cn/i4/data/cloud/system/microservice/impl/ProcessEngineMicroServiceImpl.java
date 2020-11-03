package cn.i4.data.cloud.system.microservice.impl;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.system.microservice.ProcessEngineMicroService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

/**
 * 流程引擎的微服务调用的降级处理
 * @author wangjc
 * @title: ProcessEngineMicroServiceImpl
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-17:39
 */
@Component
public class ProcessEngineMicroServiceImpl implements ProcessEngineMicroService {
    @Override
    public ActionResult<String> deployProcessModelById(String modelId) {
        return ActionResult.error("降级处理：部署流程模板失败");
    }

    @Override
    public ActionResult<Boolean> deleteProcessModelById(String modelId) {
        return ActionResult.error("降级处理：删除流程模板失败");
    }

    @Override
    public ActionResult<Boolean> deleteProcessDeploymentById(String deploymentId, Boolean cascade) {
        return ActionResult.error("降级处理：删除流程定义失败");
    }

    @Override
    public ActionResult<String> startProcessInstanceByProcessDefId(String processDefId, Map<String, Object> variable) {
        return ActionResult.error("降级处理：启动流程实例失败");
    }

    @Override
    public ActionResult<String> getTaskByProcessInstanceId(String processInstanceId) {
        return ActionResult.error("降级处理：根据流程实例获取到当前对应的待办任务");
    }

    @Override
    public ActionResult<String> getTaskByProcessInstanceIdUserId(String processInstanceId, Integer userId) {
        return ActionResult.error("降级处理：根据流程实例和接收人Id获取到当前对应的待办任务失败");
    }

    @Override
    public ActionResult<Boolean> completeStart(String processInstanceId) {
        return ActionResult.error("降级处理：办理开始节点失败");
    }

    @Override
    public ActionResult<Boolean> completeTaskById(String processInstanceId, Integer userId) {
        return ActionResult.error("降级处理：办理任务失败");
    }

    @Override
    public ActionResult<Map<String, Object>> getImageVariable(String processInstanceId, Integer userId) {
        return ActionResult.error("降级处理：获取图片流程的变量坐标失败");
    }

    @Override
    public ActionResult<byte[]> viewImage(String processDefId) {
        return ActionResult.error("降级处理：根据流程定义的id获取流程图输入流");
    }

    @Override
    public ActionResult<byte[]> viewImageByDeploymentId(String deploymentId, String imageName) {
        return ActionResult.error("降级处理：获取图片流程的输入流失败");
    }

    @Override
    public ActionResult<Boolean> refuse(String processInstanceId) {
        return ActionResult.error("降级处理：拒绝审批失败");
    }

    @Override
    public ActionResult<Boolean> rollBackUpper(String processInstanceId, Integer userId) {
        return ActionResult.error("降级处理：拒绝审批失败");
    }
}
