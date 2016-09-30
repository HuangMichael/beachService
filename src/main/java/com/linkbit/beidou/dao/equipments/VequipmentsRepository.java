package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.equipments.VeqClass;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.locations.Locations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备信息查询接口
 */
public interface VequipmentsRepository extends CrudRepository<Vequipments, Long> {


    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */
    List<Vequipments> findByLocationStartingWith(String location);
    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */
    List<Vequipments> findByLocationStartingWithOrderByIdDesc(String location);

}
