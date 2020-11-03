package cn.i4.data.cloud.activiti.engine.web;

import cn.i4.data.cloud.activiti.engine.service.ProcessEngineService;
import cn.i4.data.cloud.base.controller.BaseController;
import cn.i4.data.cloud.base.result.ActionResult;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 流程引擎的业务处理
 * @author wangjc
 * @title: ProcessEngineController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-14:50
 */
@RestController
@RequestMapping(value = "/processEngine")
public class ProcessEngineController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessEngineController.class);
    @Autowired
    private ProcessEngineService processEngineService;

    /**
     * 部署流程模板，返回deploymentId
     * @param modelId
     * @return
     */
    @PostMapping(value = "/deployProcessModelById")
    public ActionResult<String> deployProcessModelById(@RequestParam String modelId){
        try {
            Deployment deployment = this.processEngineService.deployProcessModelById(modelId);
            return ActionResult.ok(deployment.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("部署流程模板失败");
        }
    }

    /**
     * 删除流程定义
     * @param modelId
     * @return
     */
    @PostMapping(value = "/deleteProcessModelById")
    public ActionResult<Boolean> deleteProcessModelById(@RequestParam String modelId){
        try {
            Boolean res = this.processEngineService.deleteProcessModelById(modelId);
            return ActionResult.ok(res);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("删除流程定义失败");
        }
    }

    /**
     * 删除流程定义（已部署好的）
     * @param deploymentId
     * @param cascade
     * @return
     */
    @PostMapping(value = "/deleteProcessDeploymentById")
    public ActionResult<Boolean> deleteProcessDeploymentById(@RequestParam String deploymentId,@RequestParam Boolean cascade){
        try {
            Boolean res = this.processEngineService.deleteProcessDeploymentById(deploymentId, cascade);
            return ActionResult.ok(res);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("删除流程定义失败");
        }
    }

    /**
     * 启动一个流程实例
     * @param processDefId
     * @param variable
     * @return
     */
    @PostMapping(value = "/startProcessInstanceByProcessDefId")
    public ActionResult<String> startProcessInstanceByProcessDefId(@RequestParam String processDefId, @RequestParam Map<String,Object> variable){
        try {
            ProcessInstance instance = this.processEngineService.startProcessInstanceByProcessDefId(processDefId, variable);
            return ActionResult.ok(instance.getId());
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("启动流程实例失败");
        }
    }

    /**
     * 根据流程实例获取到当前对应的待办任务（单人审批的节点）
     * @param processInstanceId
     * @return
     */
    @PostMapping(value = "/getTaskByProcessInstanceId")
    public ActionResult<String> getTaskByProcessInstanceId(@RequestParam String processInstanceId){
        try {
            Task task = this.processEngineService.getTaskByProcessInstanceId(processInstanceId);
            return ActionResult.ok(task.getId());
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("根据流程实例获取到当前对应的待办任务失败");
        }
    }

    /**
     * 根据流程实例和接收人Id获取到当前对应的待办任务
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/getTaskByProcessInstanceIdUserId")
    public ActionResult<String> getTaskByProcessInstanceIdUserId(@RequestParam String processInstanceId,@RequestParam Integer userId){
        try {
            Task task = this.processEngineService.getTaskByProcessInstanceIdUserId(processInstanceId, userId);
            return ActionResult.ok(task.getId());
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("根据流程实例和接收人Id获取到当前对应的待办任务失败");
        }
    }

    /**
     * 办理开始节点
     * @param processInstanceId
     * @return
     */
    @PostMapping(value = "/completeStart")
    public ActionResult<Boolean> completeStart(@RequestParam String processInstanceId){
        try {
            Boolean start = this.processEngineService.completeStart(processInstanceId);
            return ActionResult.ok(start);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("办理开始节点失败");
        }
    }

    /**
     * 办理任务节点
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/completeTaskById")
    public ActionResult<Boolean> completeTaskById(@RequestParam String processInstanceId,@RequestParam Integer userId){
        try {
            Boolean res = this.processEngineService.completeTaskById(processInstanceId, userId);
            return ActionResult.ok(res);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("办理任务节点失败");
        }
    }

    /**
     * 获取图片流程的变量，坐标（当前走到哪一环节）
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/getImageVariable")
    public ActionResult<Map<String,Object>> getImageVariable(@RequestParam String processInstanceId,@RequestParam Integer userId){
        try {
            Map<String, Object> variable = this.processEngineService.getImageVariable(processInstanceId, userId);
            return ActionResult.ok(variable);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("获取图片流程的变量失败");
        }
    }

    /**
     * 根据流程定义的id获取流程图输入流
     * @param processDefId
     * @return
     */
    @PostMapping(value = "/viewImage")
    public ActionResult<byte[]> viewImage(@RequestParam String processDefId){
        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            inputStream = this.processEngineService.viewImage(processDefId);

            /** 通过输出流将数据写到数组 */
            bos = new ByteArrayOutputStream();
            byte[] bt = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bt)) != -1){
                bos.write(bt,0,len);
            }

            byte[] res = bos.toByteArray();
            return ActionResult.ok(res);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("根据流程定义的id获取流程图输入流失败");
        }finally {
            try {
                inputStream.close();
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取图片流程的输入流
     * @param deploymentId
     * @param imageName
     * @return
     */
    @PostMapping(value = "/viewImageByDeploymentId")
    public ActionResult<byte[]> viewImageByDeploymentId(@RequestParam String deploymentId,@RequestParam String imageName){
        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            inputStream = this.processEngineService.viewImage(deploymentId, imageName);

            /** 通过输出流将数据写到数组 */
            bos = new ByteArrayOutputStream();
            byte[] bt = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bt)) != -1){
                bos.write(bt,0,len);
            }

            byte[] res = bos.toByteArray();
            return ActionResult.ok(res);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("获取图片流程的输入流失败");
        }finally {
            try {
                inputStream.close();
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 直接驳回，即拒绝办理，挂起当前流程实例，冻结状态,可以激活
     * @param processInstanceId
     * @return
     */
    @PostMapping(value = "/refuse")
    public ActionResult<Boolean> refuse(@RequestParam String processInstanceId){
        try {
            this.processEngineService.refuse(processInstanceId);
            return ActionResult.ok(true);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("驳回流程失败");
        }
    }

    /**
     * 驳回到上一节点
     * @param processInstanceId
     * @param userId
     * @return
     */
    @PostMapping(value = "/rollBackUpper")
    public ActionResult<Boolean> rollBackUpper(@RequestParam String processInstanceId,@RequestParam Integer userId){
        try {
            this.processEngineService.rollBackUpper(processInstanceId,userId);
            return ActionResult.ok(true);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("驳回到上一节点失败");
        }
    }

}
