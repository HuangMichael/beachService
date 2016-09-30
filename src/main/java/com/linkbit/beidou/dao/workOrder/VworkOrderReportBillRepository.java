package com.linkbit.beidou.dao.workOrder;

import com.linkbit.beidou.domain.workOrder.VworkOrderReportBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/9/2.
 */
public interface VworkOrderReportBillRepository extends PagingAndSortingRepository<VworkOrderReportBill, Long>, JpaSpecificationExecutor<VworkOrderReportBill> {

    List<VworkOrderReportBill> findAll();

    @Query("SELECT count(r) from VworkOrderReportBill r  ")
    Long selectCount();


    //多字段模糊查询


    /**
     * @param orderDesc 报修描述
     * @param locName   位置名称
     * @return 模糊查询
     */
    Page<VworkOrderReportBill> findByOrderDescContainsOrLocNameContainsOrEqNameContains(String orderDesc, String locName,String eqName,Pageable pageable);


    /**
     * @param orderDesc 报修描述
     * @param pageable  分页
     * @return
     */
    Page<VworkOrderReportBill> findByOrderDesc(String orderDesc, Pageable pageable);


    Page<VworkOrderReportBill> findByLocNameContains(String locName, Pageable pageable);


}
