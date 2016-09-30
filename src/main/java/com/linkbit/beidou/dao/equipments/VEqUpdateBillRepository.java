package com.linkbit.beidou.dao.equipments;

import com.linkbit.beidou.domain.equipments.VEqUpdateBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 设备更新申请单视图数据接口
 *
 * @Date 2016年9月23日10:46:47
 **/
public interface VEqUpdateBillRepository extends PagingAndSortingRepository<VEqUpdateBill, Long>, CrudRepository<VEqUpdateBill, Long> {


    /**
     * @param eqName   设备名称
     * @param pageable 分页
     * @return 根据设备名称模糊查询
     */
    Page<VEqUpdateBill> findByEqNameContaining(String eqName, Pageable pageable);


    /**
     * @param id 根据id查询
     * @return
     */
    VEqUpdateBill findById(Long id);


    /**
     * @return查询所有的id
     */
    @Query("select id from VEqUpdateBill")
    List<Long> findAllIds();
}
