package cn.i4.data.cloud.base.constant;

/**
 * @author wangjc
 * @title: SystemConstant
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/129:13
 */
public class SystemConstant {

    public static final Integer STATUS_FREEZE = 0;//状态冻结
    public static final Integer STATUS_ACTIVE = 1;//状态激活

    /**
     * 菜单的常量
     */
    public interface MENU{
        Integer FREEZE = STATUS_FREEZE;
        Integer ACTIVE = STATUS_ACTIVE;
    }

    /**
     * 文件的常量
     */
    public interface FILE{
        Integer FREEZE = STATUS_FREEZE;
        Integer ACTIVE = STATUS_ACTIVE;

        /** 类型 */
        interface TYPE{
            Integer IMAGE = 1;//图片
            Integer AUDIO = 2;//音频
            Integer VIDEO = 3;//视频
            Integer DOC = 4;//文档
            Integer OTHER = 5;//其他
        }
    }

    /**
     * 流程控制的常量
     */
    public interface PROCESS{

        /** 流程状态：0审批中，1已通过，2未通过 */
        interface STATUS{
            Integer DOING = 0;
            Integer END = 1;
            Integer NOT = 2;
        }

        /** 流程审批：0拒绝，1放行，2驳回 */
        interface DEAL_TYPE{
            Integer REFUSE = 0;
            Integer PASS = 1;
            Integer REJECT = 2;
        }
    }

    /**
     * 考勤相关的常量
     */
    public interface ATTENDANCE{

        /** 核算状态 */
        Integer SETTLE_STATUS_INVALID = 0;//无效
        Integer SETTLE_STATUS_VALID = 1;//有效

        /** 修改状态 */
        Integer UPDATE_STATUS_AUTO = 0;//，自动统计
        Integer UPDATE_STATUS_MANUAL = 1;//人为改动

        /** 工作日类型 */
        Integer WORK_DATE_TYPE_NORMAL = 0;//正常上班
        Integer WORK_DATE_TYPE_WEEKEND = 1;//周末
        Integer WORK_DATE_TYPE_HOLIDAY = 2;//法定节假日

        /** 日历设置的类型 */
        Integer CALENDAR_TYPE_HOLIDAY = 2;//法定节假日
        Integer CALENDAR_TYPE_WORKDAY = 3;//法定工作日

        /** 打卡状态 */
        Integer ATTENDANCE_STATUS_NORMAL = 0;//正常
        Integer ATTENDANCE_STATUS_WITHOUT = -2;//未打卡
        Integer ATTENDANCE_STATUS_LATE = -1;//迟到
        Integer ATTENDANCE_STATUS_EARLY_LEAVE = 1;//早退

        /** 打卡时间段 */
        Integer ATTENDANCE_STAGE_GO = 0;//上班
        Integer ATTENDANCE_STAGE_LEAVE = 1;//下班
    }

}
