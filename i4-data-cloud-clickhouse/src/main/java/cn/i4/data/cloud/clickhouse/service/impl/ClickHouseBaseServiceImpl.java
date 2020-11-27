package cn.i4.data.cloud.clickhouse.service.impl;

import cn.i4.data.cloud.clickhouse.dao.ClickHouseBaseDao;
import cn.i4.data.cloud.clickhouse.entity.ClickHouseBaseEntity;
import cn.i4.data.cloud.clickhouse.service.ClickHouseBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service的实现类，泛型约束注入dao，禁止在service里面写SQL，统一规范，全部交给dao层来做
 * @author wangjc
 * @title: ClickHouseBaseServiceImpl
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2217:37
 */
public class ClickHouseBaseServiceImpl<T extends ClickHouseBaseEntity,D extends ClickHouseBaseDao<T>> implements ClickHouseBaseService<T> {

    @Autowired
    protected D baseDao;

    @Override
    public ClickHouseBaseDao<T> getBaseDao() {
        return this.baseDao;
    }

    @Override
    public int batchInsert(List<T> data) {
        return this.baseDao.batchInsert(data);
    }

    @Override
    public int insert(T entity) {
        return this.baseDao.batchInsert(new ArrayList<T>(){{
            add(entity);
        }});
    }

    @Override
    public int updateById(T entity) {
        return this.baseDao.updateById(entity);
    }

    @Override
    public int deleteById(Object id) {
        return this.baseDao.deleteById(id);
    }

    @Override
    public List<T> selectAllOrderBy(String orderByFieldAndIsAsc) {
        return this.baseDao.selectAllOrderBy(orderByFieldAndIsAsc);
    }

    @Override
    public List<T> selectList(T entity, String orderByFieldIsAsc) {
        return this.baseDao.selectList(entity,orderByFieldIsAsc);
    }

    @Override
    public Map<String,Object> selectPage(int start, int size, T entity, String orderByFieldAndIsAsc) {
        List<T> list = this.baseDao.selectPage(start, size, entity, orderByFieldAndIsAsc);
        int total = this.baseDao.selectCount(entity);
        return new HashMap<String, Object>(){{
            put("rows",list);
            put("total",total);
        }};
    }

}
