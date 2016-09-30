package com.linkbit.beidou.dao.outsourcingUnit;


import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 外委单位信息查询接口
 */
public interface OutsourcingUnitRepository extends CrudRepository<OutsourcingUnit, Long> {
    /**
     * 查询所有外委单位信息
     */
    List<OutsourcingUnit> findAll();


    /**
     * @param unitCode
     * @return 根据单位编号查询
     */
    List<OutsourcingUnit> findByUnitNo(String unitCode);



    /**
     * @return 根据单位编号查询
     */
    OutsourcingUnit save(OutsourcingUnit outsourcingUnit);


    /**
     * 根据状态查询外委单位
     */
    List<OutsourcingUnit> findByStatus(String status);

    /**
     * 根据id查询
     */
    OutsourcingUnit findById(long id);

    /**
     * @param eqClassId 设备分类ID
     * @return 根据设备分类查询对应的外委单位信息 id 描述
     */
    @Query(nativeQuery = true, value = "SELECT u.id, u.description, u.linkman, u.telephone  FROM t_outsourcing_unit u WHERE u.id  IN (SELECT uc.unit_id  FROM t_unit_class uc WHERE uc.class_id = :eqClassId) AND u.status = '1'  AND u.description <> '无'")
    List<Object> findUnitListByEqClassIdEq(@Param("eqClassId") Long eqClassId);


    /**
     * @param idList id 集合
     * @return 根据设备分类查询非该分类对应的外委单位信息 id 描述
     */
    @Query(nativeQuery = true, value = "SELECT u.id, u.description, u.linkman, u.telephone  FROM t_outsourcing_unit u WHERE u.id NOT IN (:idList) AND u.status = '1'  AND u.description <> '无'")
    List<Object> findUnitListByEqClassIdNotEq(@Param("idList") List<Long> idList);
}
