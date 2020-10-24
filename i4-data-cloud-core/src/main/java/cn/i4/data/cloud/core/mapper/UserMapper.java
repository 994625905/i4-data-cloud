package cn.i4.data.cloud.core.mapper;

import cn.i4.data.cloud.base.mapper.BaseIMapper;
import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.UserView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
* Mapper
* @author wangjc
* @date 2020-10-10 10:14:11
*/
public interface UserMapper extends BaseIMapper<UserModel> {

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
    UserModel login(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 根据id查询详情
     * @param userId
     * @return
     */
    UserView selectUserViewById(@Param("userId") Integer userId);
}
