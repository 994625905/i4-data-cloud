package cn.i4.data.cloud.system.web.action.materialMsg;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.base.util.RichTextUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.core.entity.dto.RichTextDto;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import cn.i4.data.cloud.core.entity.view.RichTextView;
import cn.i4.data.cloud.core.service.IRichTextService;
import cn.i4.data.cloud.mongo.core.entity.MongoRichText;
import cn.i4.data.cloud.mongo.core.service.MongoRichTextService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ActionResult<IPage<RichTextView>> loadTable(RichTextDto dto,HttpServletRequest request){
        dto.setUserId(getUser(request).getId());
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

    /**
     * 新增
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    public ActionResult<Boolean> insert(@RequestBody RichTextDto dto, HttpServletRequest request){

        /** 新增MongoDB */
        MongoRichText richText = new MongoRichText();
        richText.setContent(dto.getContent());
        richText.setMdContent(dto.getMdContent());
        String mongoId = mongoRichTextService.insertOne(richText);

        /** 新增MySQL */
        if(StringUtil.isNullOrEmpty(mongoId)){
            return ActionResult.error("富文本存储失败");
        }
        RichTextModel model = dto.getModel();
        model.setMongoId(mongoId);
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setUserId(getUser(request).getId());
        model.setWordNumber(RichTextUtil.getWordNumber(dto.getContent()));
        if(StringUtil.isNullOrEmpty(model.getExplainNote())){
            model.setExplainNote(RichTextUtil.getExplain(dto.getContent()));
        }
        boolean save = iRichTextService.save(model);
        if(save){
            return ActionResult.ok(true);
        }
        return ActionResult.error("新增失败");
    }

    /**
     * 编辑
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "编辑",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--编辑",key = KEY_PREFIX+"/update")
    public ActionResult<Boolean> update(@RequestBody RichTextDto dto){

        /** 修改MongoDB */
        MongoRichText richText = new MongoRichText();
        richText.setMongoId(new ObjectId(dto.getMongoId()));
        richText.setContent(dto.getContent());
        richText.setMdContent(dto.getMdContent());
        mongoRichTextService.updateOne(richText);

        /** 修改MySQL */
        RichTextModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);
        model.setWordNumber(RichTextUtil.getWordNumber(dto.getContent()));
        if(StringUtil.isNullOrEmpty(model.getExplainNote())){
            model.setExplainNote(RichTextUtil.getExplain(dto.getContent()));
        }
        boolean modify = iRichTextService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("修改失败");
    }

    /**
     * 设置临时存储的url
     * @return
     */
    @PostMapping(value = "/setRichTextSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "设置临时存储的url",type = RequestType.SELECT)
    public ActionResult<Boolean> setRichTextSelectTemp(String mongoId, HttpServletRequest request){
        String authorization = CookieUtil.getCookieValue(request, "authorization");

        /** 查出图文消息 */
        MongoRichText richText = mongoRichTextService.selectByMongoId(mongoId);
        boolean res = redisService.hset(RedisConstant.HASH_KEY.SELECT_RICH_TEXT, authorization, richText, RedisConstant.TIMEOUT.SELECT_RICH_TEXT);
        return ActionResult.ok(res);
    }

    /**
     * 获取临时存储的url，并删除缓存
     * @return
     */
    @PostMapping(value = "/getRichTextSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "获取临时存储的url，并删除缓存",type = RequestType.SELECT)
    public ActionResult<MongoRichText> getRichTextSelectTemp(HttpServletRequest request){

        String authorization = CookieUtil.getCookieValue(request, "authorization");

        MongoRichText richText = (MongoRichText) redisService.hget(RedisConstant.HASH_KEY.SELECT_RICH_TEXT, authorization);
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SELECT_RICH_TEXT, authorization);
        return ActionResult.ok(richText);
    }


}
