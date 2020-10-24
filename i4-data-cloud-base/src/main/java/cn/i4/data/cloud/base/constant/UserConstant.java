package cn.i4.data.cloud.base.constant;

/**
 * 用户的常量类
 * @author wangjc
 * @title: UserConstant
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1011:31
 */
public class UserConstant {

    /**
     * 状态
     */
    public interface STATUS{
        Integer INIT = 0;//初始化
        Integer INTERNSHIP = 1;//实习
        Integer TRIAL = 2;//试用
        Integer NORMAL = 3;//转正成普通
        Integer HOLIDAY = 4;//休假
        Integer LEAVE = 5;//离职
        Integer OTHER = 6;//其他
    }

}
