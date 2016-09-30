package com.linkbit.beidou.dao.line;


import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.line.Station;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 车站信息查询接口
 */
public interface StationRepository extends CrudRepository<Station, Long> {
    /**
     * 查询所有车站
     */
    List<Station> findAll();

    /**
     * 根据状态查询车站
     */
    List<Station> findByStatus(String status);

    /**
     * 根据id查询
     */
    Station findById(long id);

    /**
     * 根据线路和使用状态查寻车站
     */

    List<Station> findByLineAndStatus(Line line, String status);


    /**
     * 根据状态查询车站
     */
    @Query("update Station s set s.status =0 where s.id =:id")
    void update(@Param("id") Long id);


    /**
     * 根据id 状态查询车站
     */
    Station findByIdAndStatus(Long id, String status);

    /**
     * @param description 站名
     * @param pageable    可分页
     * @return 根据站名模糊查询
     */
    Page<Station> findByDescriptionContains(String description, Pageable pageable);

}
