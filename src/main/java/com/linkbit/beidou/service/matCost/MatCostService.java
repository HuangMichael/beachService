package com.linkbit.beidou.service.matCost;

import com.linkbit.beidou.dao.app.resource.ResourceRepository;
import com.linkbit.beidou.dao.macCost.MatCostRepository;
import com.linkbit.beidou.domain.matCost.MatCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin
 * 2016年9月29日10:03:31
 * <p/>
 * 物料消耗查询业务类
 */
@Service
public class MatCostService {

    @Autowired
    public ResourceRepository resourceRepository;

    @Autowired
    public MatCostRepository matCostRepository;


    /**
     * 查询所有数据资源
     */
    public Page<MatCost> findAll(Pageable pageable) {
        return matCostRepository.findAll(pageable);
    }


    /**
     * @return 返回有物资消耗单据的所有的位置
     */
    public List<String> findMyLocs() {
        return matCostRepository.findMyLocs();
    }

    // {"ecType":"物资","line":"10号线","locName":"10号线亮马桥办公区","ecName":""}


    /**
     * @param ecType  分类
     * @param line    线路
     * @param locName 位置名称
     * @param ecName  物资名称
     * @return
     */
    public List<MatCost> findByCondition(String ecType, String locName, String ecName) {
        return matCostRepository.findByEcTypeContainsAndLocNameContainsAndEcNameContains(ecType, locName, ecName);
    }
}
