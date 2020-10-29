package cn.i4.data.cloud.activiti.design.controller.editor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Controller
public class ModelSaveRestResource implements ModelDataJsonConstants {
    protected static final Logger logger = LoggerFactory.getLogger(ModelSaveRestResource.class);

    @Autowired
    private RepositoryService repositoryService ;
    @Autowired
    private ObjectMapper objectMapper ;

    @RequestMapping(value = {"/activiti/model/{modelId}/save.do"}, method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {
        try {
            Model model = this.repositoryService.getModel(modelId);
            logger.info("ModelSaveRestResource.saveModel----------");
            ObjectNode modelJson = (ObjectNode) this.objectMapper.readTree(model.getMetaInfo());

            modelJson.put("name", (String) values.getFirst("name"));
            modelJson.put("description", (String) values.getFirst("description"));
            model.setMetaInfo(modelJson.toString());
            model.setName((String) values.getFirst("name"));

            this.repositoryService.saveModel(model);

            this.repositoryService.addModelEditorSource(model.getId(), ((String) values.getFirst("json_xml")).getBytes("utf-8"));

            //保存到model表中
            //	      String modelName = model.getName();
            //	      saveModel(model);
            //根据模型来部署流程
            //      BpmnModel bpmn_model = new BpmnJsonConverter().convertToBpmnModel((ObjectNode)this.objectMapper.readTree(repositoryService.getModelEditorSource(model.getId())));
            //      byte[] bpmnbyte = new BpmnXMLConverter().convertToXML(bpmn_model);
            //      String processName = model.getName()+".bpmn20.xml";
            //      Deployment deploy = repositoryService.createDeployment().name(model.getName())
            //      					.addString(processName,new String(bpmnbyte,"UTF-8")).deploy();


            InputStream svgStream = new ByteArrayInputStream(((String) values.getFirst("svg_xml")).getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            transcoder.transcode(input, output);
            byte[] result = outStream.toByteArray();
            this.repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            logger.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }
    //  保存到model表
    //  @Transactional
    //  private void saveModel(Model model){
    //	  Connection connection = null;
    //	  try {
    //		connection = jdbcTemplate.getDataSource().getConnection();
    //		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from act_model");
    //		String sql = "insert into act_model(name,act_re_model_id,deploy) value('"+model.getName()+"','"+model.getId()+"','0')";
    //		jdbcTemplate.update(sql);
    //		connection.commit();//手动提交
    //	} catch (SQLException e) {
    //		e.printStackTrace();
    //	}
    //  }
}