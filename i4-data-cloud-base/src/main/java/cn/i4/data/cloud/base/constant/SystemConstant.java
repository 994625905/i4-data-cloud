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

}
