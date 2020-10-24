package cn.i4.data.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 基础的service接口,泛型对应实体为model
 * @author wangjc
 * @title: BaseService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:27
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 保存，只保存对象中有值 的字段
     * @param t
     * @return
     */
    boolean save(T t);

    /**
     * 修改，根据id只修改有值的字段，
     * @param t
     * @return
     */
    boolean modify(T t);

}
