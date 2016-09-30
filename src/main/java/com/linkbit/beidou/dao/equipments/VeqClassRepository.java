package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.VeqClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备分类视图信息查询接口
 */
public interface VeqClassRepository extends CrudRepository<VeqClass, Long> {
    /**
     * 查询所有设备分类视图信息
     */
    List<VeqClass> findAll();

    /**
     * 根据id查询设备分类视图信息
     */
    VeqClass findById(long id);


}
