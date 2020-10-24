package cn.i4.data.cloud.base.service.impl;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 基础的service实现，泛型M为mapper
 * @author wangjc
 * @title: BaseServiceImpl
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:28
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {

    @Override
    public boolean save(T entity) {
        return super.save(entity);
    }

    @Override
    public boolean modify(T t) {
        return super.updateById(t);
    }
}
