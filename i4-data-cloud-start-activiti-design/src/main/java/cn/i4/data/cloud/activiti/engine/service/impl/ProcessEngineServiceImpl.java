package cn.i4.data.cloud.activiti.engine.service.impl;

import cn.i4.data.cloud.activiti.engine.service.ProcessEngineService;
import cn.i4.data.cloud.base.exception.CommonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjc
 * @title: ProcessEngineServiceImpl
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-10:32
 */
@Service
public class ProcessEngineServiceImpl implements ProcessEngineService {

    @Autowired
    private RepositoryService repositoryService ;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    /**
     * 部署流程模板，返回部署单元，
     * @param modelId
     * @return
     * @throws Exception
     */
    @Override
    public Deployment deployProcessModelById(String modelId) throws Exception {
        Model model = this.repositoryService.getModel(modelId);

        ObjectNode modeNode = (ObjectNode) new ObjectMapper().readTree(this.repositoryService.getModelEditorSource(model.getId()));
        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modeNode);
        byte[] bpmnByte = new BpmnXMLConverter().convertToXML(bpmnModel);

        Deployment deployment = this.repositoryService.createDeployment().name(model.getName()).addString(model.getName()+".bpmn20.xml", new String(bpmnByte,"UTF-8")).deploy();
        return deployment;
    }

    /**
     * 删除流程模板
     * @param modelId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean deleteProcessModelById(String modelId) throws Exception {
        this.repositoryService.deleteModel(modelId);
        return true;
    }

    /**
     * 删除流程定义
     * @param deploymentId
     * @param cascade：是否级联删除（默认否）：流程定义，流程实例以及各种历史实例…
     * @return
     */
    @Override
    public Boolean deleteProcessDeploymentById(String deploymentId, Boolean cascade) throws Exception {
        if(cascade == null || !cascade){
            this.repositoryService.deleteDeployment(deploymentId);
        }else{
            this.repositoryService.deleteDeployment(deploymentId,true);
        }
        return true;
    }

    /**
     * 启动一个流程实例
     * @param processDefId：流程定义Id
     * @param variable：流程变量
     * @return
     */
    @Override
    public ProcessInstance startProcessInstanceByProcessDefId(String processDefId, Map<String, Object> variable) throws Exception {
        if(variable == null){
            return this.runtimeService.startProcessInstanceById(processDefId);
        }
        return this.runtimeService.startProcessInstanceById(processDefId,variable);
    }

    /**
     * 根据流程实例获取到当前对应的待办任务
     * @param processInstanceId
     * @return
     */
    @Override
    public Task getTaskByProcessInstanceId(String processInstanceId) throws Exception {
        return this.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public Task getTaskByProcessInstanceIdUserId(String processInstanceId, Integer userId) throws Exception {
        return this.taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId.toString()).singleResult();
    }

    @Override
    public List<Task> getListTaskByProcessInstanceId(String processInstanceId) throws Exception {
        return this.taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }

    /**
     * 办理开始节点
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean completeStart(String processInstanceId) throws Exception {
        Task task = this.getTaskByProcessInstanceId(processInstanceId);
        this.taskService.complete(task.getId());
        return true;
    }

    /**
     * 不建议办理任务设置流程变量
     * @param processInstanceId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean completeTaskById(String processInstanceId,Integer userId) throws Exception {
        Task task = this.getTaskByProcessInstanceIdUserId(processInstanceId, userId);
        this.taskService.complete(task.getId());
        return true;
    }

    /**
     * 获取图片流程的变量（当前走到哪一环节）
     * @param processInstanceId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getImageVariable(String processInstanceId,Integer userId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        // 1、获取当前任务
        Task task = this.getTaskByProcessInstanceIdUserId(processInstanceId, userId);

        // 2、根据任务查询定义对象
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();

        // 3、根据任务查询坐标
        ProcessDefinitionEntity pdEntity = (ProcessDefinitionEntity) this.repositoryService.getProcessDefinition(processDefinition.getId());
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        // 获取当前节点
        ActivityImpl activityImpl = pdEntity.findActivity(processInstance.getActivityId());
        // 获取所有节点
        List<ActivityImpl> activityList = pdEntity.getActivities();
        for (ActivityImpl activity : activityList) {
            if(task.getTaskDefinitionKey().equals(activity.getId())){//id匹配
                activityImpl = activity;
            };
        }
        if(activityImpl != null){
            map.put("x", activityImpl.getX());
            map.put("y", activityImpl.getY());
            map.put("height", activityImpl.getHeight());
            map.put("width", activityImpl.getWidth());
        }
        return map;
    }

    /**
     * 根据流程定义的id获取流程图输入流
     * @param processDefId
     * @return
     * @throws Exception
     */
    @Override
    public InputStream viewImage(String processDefId) throws Exception {
        return this.repositoryService.getProcessDiagram(processDefId);
    }

    /**
     * 获取图片流程的输入流
     * @param deploymentId
     * @param imageName
     * @return
     * @throws Exception
     */
    @Override
    public InputStream viewImage(String deploymentId, String imageName) throws Exception {
        return this.repositoryService.getResourceAsStream(deploymentId,imageName);
    }

    /**
     * 拒绝
     * @param processInstanceId
     * @throws Exception
     */
    @Override
    public void refuse(String processInstanceId) throws Exception {
        this.runtimeService.suspendProcessInstanceById(processInstanceId);
    }

    /**
     * 驳回到上一节点
     * @param processInstanceId
     * @param userId
     * @throws Exception
     */
    @Override
    public void rollBackUpper(String processInstanceId, Integer userId) throws Exception {
        // 1、获取当前任务，流程实例，流程变量，流程定义，上一步活动
        Task task = this.getTaskByProcessInstanceIdUserId(processInstanceId, userId);
        ProcessInstance instance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        Map<String, Object> variables = instance.getProcessVariables();
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (this.repositoryService.getProcessDefinition(task.getProcessDefinitionId()));
        ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(task.getTaskDefinitionKey());

        // 2、清除当前活动的出口
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        List<PvmTransition> pvmTransitionList = currActivity.getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        // 3、建立新的出口
        List<PvmTransition> nextTransitionList = currActivity.getIncomingTransitions();
        List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
        for (PvmTransition nextTransition : nextTransitionList) {
            PvmActivity nextActivity = nextTransition.getSource();
            ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition).findActivity(nextActivity.getId());
            TransitionImpl newTransition = currActivity.createOutgoingTransition();
            newTransition.setDestination(nextActivityImpl);
            newTransitions.add(newTransition);
        }

        // 4、完成任务
        List<Task> tasks = this.taskService.createTaskQuery().processInstanceId(instance.getId()).taskDefinitionKey(task.getTaskDefinitionKey()).list();
        for (Task t : tasks) {
            this.taskService.complete(t.getId(), variables);
            this.historyService.deleteHistoricTaskInstance(t.getId());
        }

        // 5、恢复方向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
        return;
    }

}
