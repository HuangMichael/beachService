package com.linkbit.beidou.dao.equipments;

import com.linkbit.beidou.domain.equipments.VEqAddBill;
import com.linkbit.beidou.domain.equipments.VEqUpdateBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 设备申请单视图数据接口
 *
 * @Date 2016年9月23日10:46:47
 **/
public interface VEqAddBillRepository extends PagingAndSortingRepository<VEqAddBill, Long>, CrudRepository<VEqAddBill, Long> {


    /**
     * @param eqName   设备名称
     * @param pageable 分页
     * @return 根据设备名称模糊查询
     */
    Page<VEqAddBill> findByEqNameContaining(String eqName, Pageable pageable);


    /**
     * @param id 根据id查询
     * @return
     */
    VEqAddBill findById(Long id);


    /**
     * @return查询所有的id
     */
    @Query("select id from VEqAddBill")
    List<Long> findAllIds();
}
