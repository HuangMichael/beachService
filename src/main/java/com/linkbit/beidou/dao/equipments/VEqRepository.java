package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.Vequipments;
import org.omg.CORBA.Object;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备信息查询接口
 */
public interface VEqRepository extends PagingAndSortingRepository<Vequipments, Long>, JpaSpecificationExecutor<Vequipments> {


    /**
     * @param eqName   设备名称
     * @param pageable 根据设备名称查询
     * @return
     */
    Page<Vequipments> findByEqNameContains(String eqName, Pageable pageable);


    @Query(nativeQuery = true, value = " select * from v_equipments where 1=1  and eq_code like :eqCode")
    List<Vequipments> search(@Param("eqCode") String eqCode);


    Page<Vequipments> findAll(Pageable pageable);

    /**
     * @param eqName   设备名称
     * @param locName  设备位置
     * @param eqClass  设备分类
     * @param pageable 分页属性
     * @return
     */
    @Query
    Page<Vequipments> findByEqNameContainsAndLocNameContainsAndEqClassContains(String eqName, String locName, String eqClass, Pageable pageable);

    /**
     * @param eqName
     * @param locName
     * @param eqClass
     * @return
     */
    // List<Vequipments> findByEqNameContainsAndLocNameContainsAndEqClassContains(String eqName, String locName, String eqClass,Pageable pageable);
    @Query(nativeQuery = true, value = "select v.* from V_equipments v where 1=1 and v.eq_Name like :eqName and v.loc_Name like :locName and v.eq_Class like :eqClass limit :pageIndex, :pageCount")
    List<Object> myQuery(@Param("eqName") String eqName, @Param("locName") String locName, @Param("eqClass") String eqClass, @Param("pageIndex") int pageIndex, @Param("pageCount") int pageCount);


    @Query(nativeQuery = true, value = "select count(v.*) from V_equipments v where 1=1 and v.eq_Name like %:eqName% and v.loc_Name like %:locName% and v.eq_Class like %:eqClass% limit :pageIndex, :pageCount")
    Long myQueryCount(@Param("eqName") String eqName, @Param("locName") String locName, @Param("eqClass") String eqClass, @Param("pageIndex") int pageIndex, @Param("pageCount") int pageCount);

}
