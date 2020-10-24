package cn.i4.data.cloud.base.util;

import java.util.Random;

/**
 * 随机数的工具类
 * @author wangjc
 * @title: RandomUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1713:29
 */
public class RandomUtil {

    /**
     * 获取4位验证码
     * @return
     */
    public static final Integer getFourCode(){
        Random ne = new Random();//实例化一个random的对象ne
        return ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
    }

    /**
     * 获取6位随机码，字母+数字
     * @return
     */
    public static final String getSixCode(){
        return cn.hutool.core.util.RandomUtil.randomString(6);
    }
}
