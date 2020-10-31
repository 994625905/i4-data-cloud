package cn.i4.data.cloud.activiti.engine.service;

import cn.i4.data.cloud.base.exception.CommonException;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 流程引擎的service
 * @author wangjc
 * @title: ActivitiEngineService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-10:27
 */
public interface ProcessEngineService {

    /**
     * 部署流程模板，返回部署单元
     * @param modelId
     * @return
     */
    public Deployment deployProcessModelById(String modelId) throws Exception;

    /**
     * 删除流程模板
     * @param modelId
     * @return
     */
    public Boolean deleteProcessModelById(String modelId) throws Exception;

    /**
     * 删除流程定义
     * @param deploymentId
     * @param cascade：是否级联删除（默认否）：流程定义，流程实例以及各种历史实例…
     * @return
     */
    public Boolean deleteProcessDeploymentById(String deploymentId,Boolean cascade) throws Exception;

    /**
     * 启动一个流程实例
     * @param processDefId：流程定义Id
     * @param variable：流程变量
     * @return
     */
    public ProcessInstance startProcessInstanceByProcessDefId(String processDefId, Map<String,Object> variable) throws Exception;

    /**
     * 根据流程实例获取到当前对应的待办任务（单人审批的节点）
     * @param processInstanceId
     * @return
     */
    public Task getTaskByProcessInstanceId(String processInstanceId) throws Exception ;

    /**
     * 根据流程实例和接收人Id获取到当前对应的待办任务
     * @param processInstanceId
     * @param userId：接收人Id
     * @return
     * @throws Exception
     */
    public Task getTaskByProcessInstanceIdUserId(String processInstanceId,Integer userId) throws Exception;

    /**
     * 根据流程实例获取到当前待办任务集合（可能有多人审批的节点）
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    public List<Task> getListTaskByProcessInstanceId(String processInstanceId) throws Exception;

    /**
     * 办理任务节点
     * @param processInstanceId
     * @param userId
     * @return
     * @throws Exception
     */
    public Boolean completeTaskById(String processInstanceId,Integer userId) throws Exception;

    /**
     * 获取图片流程的变量（当前走到哪一环节）
     * @param processInstanceId
     * @param userId
     * @return
     * @throws Exception
     */
    public Map<String,Object> getImageVariable(String processInstanceId,Integer userId) throws Exception;

    /**
     * 根据流程定义的id获取流程图输入流
     * @param processDefId
     * @return
     * @throws Exception
     */
    public InputStream viewImage(String processDefId) throws Exception;

    /**
     * 获取图片流程的输入流
     * @param deploymentId
     * @param imageName
     * @return
     * @throws Exception
     */
    public InputStream viewImage(String deploymentId,String imageName) throws Exception;

    /**
     * 直接驳回，即拒绝办理，挂起当前流程实例，冻结状态,可以激活
     * @param processInstanceId
     */
    public void refuse(String processInstanceId) throws Exception;

    /**
     * 驳回到上一节点
     * @param processInstanceId
     * @param userId
     * @throws Exception
     */
    public void rollBackUpper(String processInstanceId,Integer userId) throws Exception;

}
