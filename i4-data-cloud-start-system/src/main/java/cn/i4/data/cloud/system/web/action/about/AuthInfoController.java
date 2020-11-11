package cn.i4.data.cloud.system.web.action.about;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.SystemConstantDto;
import cn.i4.data.cloud.mongo.core.entity.MongoSystemConstant;
import cn.i4.data.cloud.mongo.core.service.MongoSystemConstantService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjc
 * @title: AuthInfoController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/5-15:57
 */
@RestController
@RequestMapping(value = "/about/authInfo")
public class AuthInfoController extends WebBaseController {

    private static final String MODULE_NAME = "关于平台--认证信息";
    private static final String KEY_PREFIX = "/about/authInfo";

    @Autowired
    private MongoSystemConstantService mongoSystemConstantService;

    /**
     * 修改
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "about:authInfo/update")
    public ActionResult<Boolean> update(SystemConstantDto dto){

        /** 修改MongoDB */
        MongoSystemConstant mongoSystemConstant = new MongoSystemConstant();
        mongoSystemConstant.setMongoId(new ObjectId(dto.getMongoId()));
        mongoSystemConstant.setContent(dto.getContent());
        mongoSystemConstantService.updateOne(mongoSystemConstant);

        return ActionResult.ok(true);
    }


}
