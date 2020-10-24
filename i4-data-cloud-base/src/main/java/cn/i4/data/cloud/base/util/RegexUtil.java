package cn.i4.data.cloud.base.util;

import java.util.regex.Pattern;

/**
 * 正则表达式的验证项
 * @author wangjc
 * @title: RegularUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1618:41
 */
public class RegexUtil {

    /** 验证手机号码的正则 */
    private static final Pattern IS_MOBILE = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");

    /**
     * 验证手机号码
     * @param mobile
     * @return
     */
    public static final Boolean IS_MOBILE(String mobile){
        return IS_MOBILE.matcher(mobile).matches();
    }

}
