package com.linkbit.beidou.dao.equipments;

import com.linkbit.beidou.domain.equipments.EqAddBill;
import com.linkbit.beidou.domain.equipments.EqUpdateBill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 设备新置申请单数据接口
 *
 * @Date 2016年9月28日13:49:43
 **/
public interface EqAddBillRepository extends PagingAndSortingRepository<EqAddBill, Long>, CrudRepository<EqAddBill, Long> {

    /**
     * @param id 根据id查询
     * @return
     */
    EqAddBill findById(Long id);


    /**
     * @return查询所有的id
     */
    @Query("select e.id from EqAddBill e")
    List<Long> findAllIds();

}
