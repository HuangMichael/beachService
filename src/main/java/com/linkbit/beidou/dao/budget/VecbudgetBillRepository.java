package com.linkbit.beidou.dao.budget;

import com.linkbit.beidou.domain.EcBudget.VEcBudgetBill;
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
public interface VecbudgetBillRepository extends PagingAndSortingRepository<VEcBudgetBill, Long> {


    /**
     * @param pageable
     * @return
     */
    Page<VEcBudgetBill> findAll(Pageable pageable);


    /**
     * @param ecname   根据易耗品名称查询
     * @param pageable 设置可分页
     * @return
     */
    Page<VEcBudgetBill> findByEcnameContains(String ecname, Pageable pageable);

    /**
     * @return
     */
    List<VEcBudgetBill> findAll();
}
