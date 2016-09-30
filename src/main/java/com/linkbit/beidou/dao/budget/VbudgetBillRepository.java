package com.linkbit.beidou.dao.budget;

import com.linkbit.beidou.domain.budget.VbudgetBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 采购申请单
 *
 * @author
 * @create 2016-09-09 11:16
 **/
public interface VbudgetBillRepository extends PagingAndSortingRepository<VbudgetBill, Long> {


    /**
     * @param pageable
     * @return
     */
    Page<VbudgetBill> findAll(Pageable pageable);


    /**
     * @param accessoryName 易耗品名称 模糊查询
     * @param pageable      设置可分页
     * @return 返回易耗品名称模糊查询分页对象
     */
    Page<VbudgetBill> findByAccessoryNameContains(String accessoryName, Pageable pageable);

    /**
     * @return
     */
    List<VbudgetBill> findAll();
}
