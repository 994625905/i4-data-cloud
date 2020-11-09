package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.core.entity.dto.RichTextDto;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import cn.i4.data.cloud.core.service.IRichTextService;
import cn.i4.data.cloud.mongo.core.entity.MongoRichText;
import cn.i4.data.cloud.mongo.core.service.MongoRichTextService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 素材中心的视图页面
 * @author wangjc
 * @title: MaterialMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2010:09
 */
@RequestMapping(value = "/materialMsg")
@Controller
public class MaterialMsgController extends WebBaseController {

    @Autowired
    private IRichTextService iRichTextService;
    @Autowired
    private MongoRichTextService mongoRichTextService;

    /**
     * 加载文件查看首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/fileFind/index")
    @RequestPermission(value = "materialMsg:fileFind/index")
    public ModelAndView fileFindIndex(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_index", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 文件上传页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/fileFind/uploadPage")
    @RequestPermission(value = "materialMsg:fileFind/uploadPage")
    public ModelAndView fileFindUploadPage(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_upload", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载图片选择页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/imageSelect/index")
    @RequestPermission(value = "materialMsg:imageSelect/index")
    public ModelAndView imageSelectIndex(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_selectImage", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载文件选择页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/imageSelect/file")
    @RequestPermission(value = "materialMsg:imageSelect/file")
    public ModelAndView imageSelectFile(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/fileFind_selectFile", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 图文草稿的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/richText/index")
    @RequestPermission(value = "materialMsg:richText/index")
    public ModelAndView richTextIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/richText_index", request);
        return view;
    }

    /**
     * 图文草稿的新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/richText/addPage")
    @RequestPermission(value = "materialMsg:richText/addPage")
    public ModelAndView richTextAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/richText_add", request);
        return view;
    }

    /**
     * 图文草稿的编辑页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/richText/editPage")
    @RequestPermission(value = "materialMsg:richText/editPage")
    public ModelAndView richTextEditPage(RichTextDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/richText_edit", request);

        RichTextModel model = iRichTextService.getById(dto.getId());
        MongoRichText richText = mongoRichTextService.selectByMongoId(model.getMongoId());

        view.addObject("model",model);
        view.addObject("richText",richText);
        return view;
    }

    /**
     * 图文草稿的预览页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/richText/readPage")
    @RequestPermission(value = "materialMsg:richText/readPage")
    public ModelAndView richTextEReadPage(RichTextDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/richText_read", request);

        RichTextModel model = iRichTextService.getById(dto.getId());
        MongoRichText richText = mongoRichTextService.selectByMongoId(model.getMongoId());

        view.addObject("model",model);
        view.addObject("richText",richText);
        return view;
    }

    /**
     * 加载图文选择页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/richText/select")
    @RequestPermission(value = "materialMsg:richText/select")
    public ModelAndView richTextSelect(FileDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/materialMsg/richText_select", request);
        return view;
    }


}
