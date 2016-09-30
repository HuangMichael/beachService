package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.locations.Locations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备信息查询接口
 */
public interface EqRepository extends PagingAndSortingRepository<Equipments, Long>{

}
