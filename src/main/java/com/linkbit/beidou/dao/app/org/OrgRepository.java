package com.linkbit.beidou.dao.app.org;


import com.linkbit.beidou.domain.app.org.Org;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 查询系统信息
 */
public interface OrgRepository extends CrudRepository<Org, Long> {
    /**
     * 查询所有数据过滤器
     */
    List<Org> findAll();

    /**
     * 根据状态查询数据过滤器
     */
    List<Org> findByStatus(String status);

    /**
     * 根据id查询数据过滤器
     */
    Org findById(long id);

}
