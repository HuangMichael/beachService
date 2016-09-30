package com.linkbit.beidou.dao.workOrder;

import com.linkbit.beidou.domain.workOrder.VworkOrderLineNumFixing;
import com.linkbit.beidou.domain.workOrder.VworkOrderLineNumReport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/7/24.
 */
public interface VworkOrderLineNumFixingRepository extends CrudRepository<VworkOrderLineNumFixing, Long> {
    List<VworkOrderLineNumFixing> findAll();

}
