package com.linkbit.beidou.dao.budget;

import com.linkbit.beidou.domain.EcBudget.EcBudgetBill;
import com.linkbit.beidou.domain.budget.BudgetBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 采购申请单
 *
 **/
public interface EcBudgetBillRepository extends PagingAndSortingRepository<EcBudgetBill, Long>, CrudRepository<EcBudgetBill, Long> {


    /**
     * @param pageable
     * @return
     */
    Page<EcBudgetBill> findAll(Pageable pageable);

    /**
     * @return
     */
    List<EcBudgetBill> findAll();


    /**
     * @param id 根据id查询
     * @return
     */
    EcBudgetBill findById(Long id);



    /**
     * @return查询所有的id
     */
    @Query("select id from EcBudgetBill")
    List<Long> findAllIds();
}
