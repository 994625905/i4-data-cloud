package cn.i4.data.cloud.base.constant;

/**
 * redis常量类
 * @author wangjc
 * @title: SystemConstant
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3010:13
 */
public class RedisConstant {

    /**
     * redis表存储的key（string），不可随意变更
     */
    public interface KEY{
        String OPEN_API_PREFIX = "i4-data-cloud-open-api_";//对外开放的api

        String LOGIN_USER_PREFIX = "i4-data-cloud-login-user_";//登录的用户
        String LOGIN_USER_INFO_PREFIX = "i4-data-cloud-login-user_info_";//登录用户的info
        String LOGIN_USER_TEMPLATE_PREFIX = "i4-data-cloud-login-user_template_";//登录用户的模板设置
        String LOGIN_USER_ROLE_MENU_TREE_PREFIX = "i4-data-cloud-user-role-menu_";//登陆用户的权限菜单

        String SYSTEM_CONSTANT = "i4-data-cloud-system-constant";//系统常量
        String RABBITMQ_EXCHANGE = "i4-data-cloud-rabbitmq-exchange";//系统的rabbitMQ配置，交换机，不过期
        String RABBITMQ_QUEUE = "i4-data-cloud-rabbitmq-queue";//系统的rabbitMQ配置，队列，不过期
    }

    /**
     * redis表存储的key（hash），不可随意变更，（hash结构中的key）
     */
    public interface HASH_KEY{
        String AUTH_CODE = "i4-data-cloud-auth-code";//验证码（uuid）
        String AUTH_TOKEN = "i4-data-cloud-auth-token";//验证token（uuid）
        String SMS_CODE = "i4-data-cloud-auth-code";//短信验证码（uuid）
        String EMAIL_CODE = "i4-data-cloud-auth-code";//邮箱验证码（uuid）
        String INVITE_CODE = "i4-data-cloud-invite-code";//注册邀请码（OK）

        String SELECT_IMAGE_TEMP = "i4-data-cloud-select-image-temp";//选择图片的临时存储（authorization）
        String SELECT_LIST_IMAGE_TEMP = "i4-data-cloud-select-list-image-temp";//批量选择列表的临时存储（authorization）
        String SELECT_RICH_TEXT = "i4-data-cloud-select-rich-text-temp";//选择图文的临时存储（authorization）
        String SELECT_AFTERNOON_TEA = "i4-data-cloud-select-afternoon-tea-temp";//选择下午茶的临时存储（authorization）

        String ACTIVITY_IMAGE_LIKE_COUNT = "i4-data-cloud-activity-image-like-count";//活动照片墙的点赞数量（imageId-count），对应照片的点赞数量
        String ACTIVITY_IMAGE_LIKE_USER = "i4-data-cloud-activity-image-like-user";;//用户所点赞的照片（userId-Set<imageId>），对应点赞的照片列表
        String ACTIVITY_IMAGE_READ_COUNT = "i4-data-cloud-activity-image-read-count";//活动照片墙的阅读数量（imageId-Count）,对应照片的阅读数量

        String HISTORY_IMAGE_LIKE_COUNT = "i4-data-cloud-history-image-like-count";//历史照片墙的点赞数量（imageId-count），对应照片的点赞数量
        String HISTORY_IMAGE_LIKE_USER = "i4-data-cloud-history-image-like-user";;//用户所点赞的照片（userId-Set<imageId>），对应点赞的照片列表
        String HISTORY_IMAGE_READ_COUNT = "i4-data-cloud-history-image-read-count";//历史照片墙的阅读数量（imageId-Count）,对应照片的阅读数量
    }

    /**
     * redis表存储的key（set），不可随意变更，（set结构中的key）
     */
    public interface SET_KEY{
        String ACTIVITY_IMAGE_LIKE = "i4-data-cloud-activity-image-like";//活动照片墙的点赞（imageId-Set<like>）,默认不过期，定时任务跑（新增/）
        String ACTIVITY_IMAGE_READ = "i4-data-cloud-activity-image-read";//活动照片墙的阅读（imageId-Set<read>）,默认不过期，定时任务跑（新增）

        String HISTORY_IMAGE_LIKE = "i4-data-cloud-history-image-like";//历史照片墙的点赞（imageId-Set<like>）,默认不过期，定时任务跑（新增/）
        String HISTORY_IMAGE_READ = "i4-data-cloud-history-image-read";//历史照片墙的阅读（imageId-Set<read>）,默认不过期，定时任务跑（新增）
    }

    /**
     * redis表存储值的过期时间
     */
    public interface TIMEOUT{
        Long LOGIN_USER = 60 * 60 * 24 * 1L;//登录的用户，1天
        Long LOGIN_USER_INFO = 60 * 60 * 24 * 1L;//登录用户的info，1天
        Long LOGIN_USER_TEMPLATE_PREFIX = 60 * 60 * 24 * 1L;//登录用户的模板设置，1天
        Long LOGIN_USER_ROLE_MENU_TREE = 60 * 60 * 24 * 1L;//登录用户的模板，1天

        Long SYSTEM_CONSTANT = 60 * 60 * 24 * 1L;//系统常量，1天
        Long AUTH_CODE = 60L;//验证码（uuid），1分钟
        Long SMS_CODE = 60L;//短信验证码（uuid），1分钟
        Long EMAIL_CODE = 60L;//邮箱验证码（uuid），1分钟

        Long SELECT_IMAGE_TEMP = 60L;//选择图片的临时存储（authorization），1分钟
        Long SELECT_LIST_IMAGE_TEMP = 60L;//批量选择列表的临时存储（authorization）
        Long SELECT_RICH_TEXT = 60L;//选择图文的临时存储（authorization），1分钟
        Long SELECT_AFTERNOON_TEA = 60L;//选择下午茶的临时存储（authorization），1分钟
    }

}
