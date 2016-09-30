package com.linkbit.beidou.dao.workOrder;

import com.linkbit.beidou.domain.workOrder.VworkOrderNumFinish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/7/24.
 */
public interface VworkOrderNumFinishRepository extends CrudRepository<VworkOrderNumFinish, Long> {


    List<VworkOrderNumFinish> findAll();

}
