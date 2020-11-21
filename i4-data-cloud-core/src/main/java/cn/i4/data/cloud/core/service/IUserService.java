package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Set;

/**
* Service
* @author wangjc
* @date 2020-10-10 10:14:11
*/
public interface IUserService extends BaseService<UserModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserView> selectPage(UserDto dto);

    /**
     * 登录
     * @param loginName：登录名/手机/邮箱
     * @param password
     * @return
     */
    UserModel login(String loginName, String password);

    /**
     * 根据id查询详情
     * @param userId
     * @return
     */
    UserView selectById(Integer userId);

    /**
     * 获取除指定userId之外的user
     * @param userId
     * @return
     */
    List<UserModel> selectListNotUserId(Integer userId);

    /**
     * 加载活动未签到列表
     * @param activityId
     * @return
     */
    List<UserView> selectActivityNoSignList(Integer activityId);

    /**
     * 查询历史公告已读列表
     * @param id
     * @param userIdList
     * @return
     */
    List<UserView>  selectByHistoryNoticeId(Integer historyNoticeId, Set<Integer> userIdList);
}
