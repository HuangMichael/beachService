package com.linkbit.beidou.dao.workOrder;

import com.linkbit.beidou.domain.workOrder.VworkOrderLineNumAbort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/7/24.
 */
public interface VworkOrderLineNumAbortRepository extends CrudRepository<VworkOrderLineNumAbort, Long> {
    List<VworkOrderLineNumAbort> findAll();

}
