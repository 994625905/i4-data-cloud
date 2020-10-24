package cn.i4.data.cloud.base.util;

/**
 * 字符串校验判断
 * @author wangjc
 * @title: StringUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:46
 */
public class StringUtil {

    /**
     * 判断两个对象的值转换为字符串后是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqual(Object a, Object b) {
        return a != null && b != null && a.toString().equals(b.toString());
    }

    /**
     * 判断字符串是否为NULL或空字符串
     *
     * @param str            字符串
     * @param trim           字符串是否调用trim方法
     * @param includeNullStr null字符串是否算空
     * @return 判断结果
     */
    public static boolean isNullOrEmpty(Object str, Boolean trim, boolean includeNullStr) {
        if (trim) {
            if (includeNullStr) {
                return str == null || str.toString().trim().equals("") || str.toString().trim().equals("null");
            } else {
                return str == null || str.toString().trim().equals("");
            }
        } else {
            if (includeNullStr) {
                return str == null || str.toString().equals("") || str.toString().equals("null");
            } else {
                return str == null || str.toString().equals("");
            }
        }
    }

    /**
     * 判断字符串是否为NULL或空字符串<br>
     * null字符串算空
     *
     * @param str  字符串
     * @param trim 字符串是否调用trim方法
     * @return 判断结果
     */
    public static boolean isNullOrEmpty(Object str, Boolean trim) {
        return isNullOrEmpty(str, trim, true);
    }

    /**
     * 判断字符串是否为NULL或空字符串<br>
     * 字符串调用trim方法<br>
     * null字符串算空
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNullOrEmpty(Object str) {
        return isNullOrEmpty(str, true, true);
    }

}
