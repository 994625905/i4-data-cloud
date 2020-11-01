package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.VActReDeployProcdefDto;
import cn.i4.data.cloud.system.microservice.ProcessEngineMicroService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流程引擎的视图页面
 * @author wangjc
 * @title: ProcessEngineController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-15:29
 */
@RequestMapping(value = "/processEngine")
@Controller
public class ProcessEngineController extends WebBaseController {

    @Value("${i4.data.cloud.activiti-design-add}")
    private String designAdd;
    @Value("${i4.data.cloud.activiti-design-edit}")
    private String designEdit;

    @Autowired
    private ProcessEngineMicroService processEngineMicroService;

    /**
     * 加载流程模型管理的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/modelMsg/index")
    public ModelAndView modelMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/processEngine/modelMsg_index", request);
        view.addObject("designAdd",designAdd);
        view.addObject("designEdit",designEdit);
        return view;
    }

    /**
     * 加载流程部署管理的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/deployMsg/index")
    public ModelAndView deployMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/processEngine/deployMsg_index", request);
        return view;
    }

    /**
     * 加载部署流程的图片
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/deployMsg/processImage")
    public ModelAndView processImage(VActReDeployProcdefDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/processEngine/deployMsg_image", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载流程图的io
     * @param request
     * @param response
     */
    @RequestMapping(value = "/deployMsg/imageio")
    public void imageio(VActReDeployProcdefDto dto,HttpServletRequest request, HttpServletResponse response){
        ActionResult<byte[]> result = processEngineMicroService.viewImage(dto.getProcdefId());
        if(result.getCode() != 200){
            return;
        }
        /** 边读边写 */
        ByteArrayInputStream in = new ByteArrayInputStream(result.getResult());
        OutputStream os = null;
        try {
            int count = 0;
            os = response.getOutputStream();
            byte[] buffer = new byte[1024 * 8];
            while((count = in.read(buffer)) != -1){
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
                os.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
