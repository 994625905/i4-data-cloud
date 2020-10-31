package cn.i4.data.cloud.system.web.action.systemMsg;

import cn.hutool.core.codec.Base64;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.RandomUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeDepartmentModel;
import cn.i4.data.cloud.core.entity.model.InviteCodeModel;
import cn.i4.data.cloud.core.entity.model.InviteCodeRoleModel;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeView;
import cn.i4.data.cloud.core.service.IInviteCodeDepartmentService;
import cn.i4.data.cloud.core.service.IInviteCodeRoleService;
import cn.i4.data.cloud.core.service.IInviteCodeService;
import cn.i4.data.cloud.core.service.IRoleService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户邀请码的控制层
 * @author wangjc
 * @title: InviteCodeController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/199:05
 */
@RequestMapping(value = "/systemMsg/inviteCode")
@RestController
public class InviteCodeController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--菜单管理";
    @Autowired
    private IInviteCodeService iInviteCodeService;
    @Autowired
    private IInviteCodeRoleService iInviteCodeRoleService;
    @Autowired
    private IInviteCodeDepartmentService iInviteCodeDepartmentService;
    @Autowired
    private IRoleService iRoleService;
    @Value("${spring.servlet.multipart.location}")
    private String systemTempPath;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    public ActionResult<IPage<InviteCodeView>> loadTable(InviteCodeDto dto){

        IPage<InviteCodeView> res = iInviteCodeService.selectPage(dto);
        return ActionResult.ok(res);
    }

    /**
     * 删除根据ID，级联删除邀请权限数据
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestLog(module = MODULE_NAME,content = "删除根据ID，级联删除邀请权限数据",type = RequestType.DELETE)
    public ActionResult<Boolean> deleteById(InviteCodeDto dto){
        boolean remove = iInviteCodeService.removeById(dto.getId());
        if(!remove){
            return ActionResult.error("删除邀请码失败");
        }

        /** 删除redis */
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.INVITE_CODE,dto.getCode());

        /** 级联删除邀请权限 */
        remove = iInviteCodeRoleService.remove(new QueryWrapper<InviteCodeRoleModel>() {{
            eq("invite_code_id", dto.getId());
        }});
        if(!remove){
            return ActionResult.error("删除邀请权限失败");
        }

        /** 级联删除部门 */
        remove = iInviteCodeDepartmentService.remove(new QueryWrapper<InviteCodeDepartmentModel>() {{
            eq("invite_code_id", dto.getId());
        }});
        if(!remove){
            return ActionResult.error("删除邀请部门失败");
        }
        return ActionResult.ok(remove);
    }

    /**
     * 获取角色列表
     * @return
     */
    @PostMapping(value = "/getRole")
    @RequestLog(module = MODULE_NAME,content = "获取角色列表",type = RequestType.SELECT)
    public ActionResult<List<RoleModel>> getRole(){

        List<RoleModel> list = iRoleService.getBaseMapper().selectList(null);
        return ActionResult.ok(list);
    }

    /**
     * 保存邀请码
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/saveCode")
    @RequestLog(module = MODULE_NAME,content = "保存邀请码",type = RequestType.INSERT)
    public ActionResult<Map<String,Object>> saveCode(@RequestBody InviteCodeDto dto, HttpServletRequest request){

        /** 生成可用的邀请码（6位数），确保不冲突 */
        String code = null;
        while (true){
            code = RandomUtil.getSixCode();
            String redisValue = (String)redisService.hget(RedisConstant.HASH_KEY.INVITE_CODE, code);
            if(StringUtil.isNullOrEmpty(redisValue)){
                /** 加入redis缓存 */
                redisService.hset(RedisConstant.HASH_KEY.INVITE_CODE,code,"OK",dto.getModel().getOverTime() - System.currentTimeMillis()/1000L);
                break;
            }
        }
        InviteCodeModel model = dto.getModel();
        model.setCode(code);
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(this.getUser(request).getId());
        model.setEffectTime(model.getOverTime() - System.currentTimeMillis()/1000L);

        /** 入口保存 */
        boolean save = iInviteCodeService.save(model);
        if(!save){
            return ActionResult.error("生成邀请码失败！");
        }

        /** 保存携带的权限 */
        for(Integer roleId:dto.getRoleList()){
            InviteCodeRoleModel roleModel = new InviteCodeRoleModel();
            roleModel.setInviteCodeId(model.getId());
            roleModel.setRoleId(roleId);
            save = iInviteCodeRoleService.save(roleModel) && save;
        }
        if(!save){
            return ActionResult.error("携带角色权限配置失败！");
        }

        /** 保存携带的部门 */
        InviteCodeDepartmentModel departmentModel = new InviteCodeDepartmentModel();
        departmentModel.setDepartmentId(dto.getDepartmentId());
        departmentModel.setInviteCodeId(model.getId());
        save = iInviteCodeDepartmentService.save(departmentModel) && save;
        if(!save){
            return ActionResult.error("携带部门配置失败");
        }

        /** 返回邀请码和携带的角色 */
        return ActionResult.ok(new HashMap<String, Object>(){{
            put("codeName",model.getName());
            put("code",model.getCode());
            put("codeId",model.getId());
            put("departmentName",iInviteCodeDepartmentService.selectDeploymentByid(dto.getDepartmentId()).getName());
            put("roleList",iRoleService.selectRolesByIds(dto.getRoleList()));
        }});
    }

    /**
     * 生成二维码(流转base64)
     * @param code
     * @param roleNames
     * @return
     */
    @PostMapping(value = "/createQRCode")
    @RequestLog(module = MODULE_NAME,content = "生成二维码(流转base64)",type = RequestType.SELECT)
    public ActionResult<String> createQRCode(Integer id,String roleNames,String departmentName,HttpServletRequest request){

        InviteCodeModel model = iInviteCodeService.getById(id);

        ByteArrayOutputStream os = null;
        InputStream is = null;
        File logo = null;

        try {
            QrConfig config = new QrConfig(300, 300);
            // 设置边距，既二维码和背景之间的边距
            config.setMargin(3);
            // 设置背景色（灰色）
            if(model.getOverTime() < System.currentTimeMillis()/1000L){
                config.setBackColor(Color.GRAY.getRGB());
            }
            // 设置中心logo
            logo = this.getHeadImage(this.getUserInfo(request).getHeadimage());
            config.setImg(logo);

            BufferedImage image = QrCodeUtil.generate(model.getCode() + "，[部门："+departmentName+"]，[角色："+roleNames+"]", config);

            /** 转输入 */
            os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            is = new ByteArrayInputStream(os.toByteArray());

            /** 转base64 */
            byte[] data = new byte[is.available()];
            is.read(data);
            String encode = Base64.encode(data);
            return ActionResult.ok(encode);
        }catch (Exception e){
            e.printStackTrace();
            return ActionResult.error();
        }finally {
            /** 资源释放，清除临时文件 */
            try {
                os.close();
                is.close();
                if(logo != null){
                    logo.deleteOnExit();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据在线图片获取file
     * @param headImage
     * @return
     */
    private File getHeadImage(String headImage){
        File file = null;
        try {
            URL url = new URL(headImage);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(1000*5);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();

            /** IO流写入 */
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String path = this.systemTempPath + UUID.randomUUID().toString()+".png";
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            byte[] buf = new byte[bin.available()];
            while ((size = bin.read(buf)) != -1) {
                out.write(buf, 0, size);
            }
            // 关闭资源
            bin.close();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return file;
        }
    }

}
