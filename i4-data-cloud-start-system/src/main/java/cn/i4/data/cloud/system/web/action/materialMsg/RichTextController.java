package cn.i4.data.cloud.system.web.action.materialMsg;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.RichTextDto;
import cn.i4.data.cloud.core.entity.view.RichTextView;
import cn.i4.data.cloud.core.service.IRichTextService;
import cn.i4.data.cloud.mongodb.service.MongoRichTextService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 素材中心--图文草稿的控制层
 * @author wangjc
 * @title: RichTextController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/4-15:03
 */
@RequestMapping(value = "/materialMsg/richText")
@RestController
public class RichTextController extends WebBaseController {

    private static final String MODULE_NAME = "素材中心--图文草稿的控制层";
    private static final String KEY_PREFIX = "/materialMsg/richText";
    @Autowired
    private IRichTextService iRichTextService;
    @Autowired
    private MongoRichTextService mongoRichTextService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<IPage<RichTextView>> loadTable(RichTextDto dto){

        IPage<RichTextView> page = iRichTextService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除，级联删除MongoDB
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    public ActionResult<Boolean> delete(RichTextDto dto){

        /** 删除MongoDB */
        Long delete = mongoRichTextService.deleteOne(dto.getMongoId());
        if(delete < 1){
            return ActionResult.error("删除MongoDB富文本内容失败");
        }
        /** 删除MySQL */
        boolean remove = iRichTextService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除数据失败");
    }

}
