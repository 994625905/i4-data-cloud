package cn.i4.data.cloud.system.microservice;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.system.microservice.impl.ProcessEngineMicroServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.util.Map;

/**
 * 流程引擎的微服务调用
 * @author wangjc
 * @title: ProcessEngineMicroService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-17:37
 */
@FeignClient(value = "i4-data-cloud-start-activiti-design",fallback = ProcessEngineMicroServiceImpl.class)
public interface ProcessEngineMicroService {

    /**
     * 部署流程模板，返回deploymentId
     * @param modelId
     * @return
     */
    @PostMapping(value = "/processEngine/deployProcessModelById")
    public ActionResult<String> deployProcessModelById(@RequestParam String modelId);

    /**
     * 删除流程模板
     * @param modelId
     * @return
     */
    @PostMapping(value = "/processEngine/deleteProcessModelById")
    public ActionResult<Boolean> deleteProcessModelById(@RequestParam String modelId);

    /**
     * 删除流程定义（已部署好的）
     * @param deploymentId
     * @param cascade：是否级联删除（默认否）：流程定义，流程实例以及各种历史实例…
     * @return
     */
    @PostMapping(value = "/processEngine/deleteProcessDeploymentById")
    public ActionResult<Boolean> deleteProcessDeploymentById(@RequestParam String deploymentId,@RequestParam Boolean cascade);

    /**
     * 启动一个流程实例
     * @param processDefId
     * @param variable
     * @return
     */
    @PostMapping(value = "/processEngine/startProcessInstanceByProcessDefId")
    public ActionResult<String> startProcessInstanceByProcessDefId(@RequestParam String processDefId, @RequestParam Map<String,Object> variable);

    /**
     * 根据流程实例获取到当前对应的待办任务（单人审批的节点）
     * @param processInstanceId
     * @return
     */
    @PostMapping(value = "/processEngine/getTaskByProcessInstanceId")
    public ActionResult<String> getTaskByProcessInstanceId(@RequestParam String processInstanceId);

    /**
     * 根据流程实例和接收人Id获取到当前对应的待办任务
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/processEngine/getTaskByProcessInstanceIdUserId")
    public ActionResult<String> getTaskByProcessInstanceIdUserId(@RequestParam String processInstanceId,@RequestParam Integer userId);

    /**
     * 办理任务节点
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/processEngine/completeTaskById")
    public ActionResult<Boolean> completeTaskById(@RequestParam String processInstanceId,@RequestParam Integer userId);

    /**
     * 获取图片流程的变量，坐标（当前走到哪一环节）
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/processEngine/getImageVariable")
    public ActionResult<Map<String,Object>> getImageVariable(@RequestParam String processInstanceId,@RequestParam Integer userId);

    /**
     * 根据流程定义的id获取流程图输入流
     * @param processDefId
     * @return
     */
    @PostMapping(value = "/processEngine/viewImage")
    public ActionResult<InputStream> viewImage(@RequestParam String processDefId);

    /**
     * 获取图片流程的输入流
     * @param deploymentId
     * @param imageName
     * @return
     */
    @PostMapping(value = "/processEngine/viewImageByDeploymentId")
    public ActionResult<InputStream> viewImageByDeploymentId(@RequestParam String deploymentId,@RequestParam String imageName);

    /**
     * 直接驳回，即拒绝办理，挂起当前流程实例，冻结状态,可以激活
     * @param processInstanceId
     * @return
     */
    @PostMapping(value = "/processEngine/refuse")
    public ActionResult<Boolean> refuse(@RequestParam String processInstanceId);

    /**
     * 驳回到上一节点
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/processEngine/rollBackUpper")
    public ActionResult<Boolean> rollBackUpper(@RequestParam String processInstanceId,@RequestParam Integer userId);


}
