package com.linkbit.beidou.dao.locations;

import com.linkbit.beidou.domain.locations.Vlocations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 位置视图信息查询接口
 */
public interface VlocationsRepository extends CrudRepository<Vlocations, Long> {
    /**
     * @param location
     * @return 根据位置编码模糊查询位置视图信息
     */
    List<Vlocations> findByLocationStartingWith(String location);

    /**
     * @return 根据位置ID查询位置
     */
    Vlocations findById(Long id);

}
