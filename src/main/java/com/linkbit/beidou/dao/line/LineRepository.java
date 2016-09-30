package com.linkbit.beidou.dao.line;


import com.linkbit.beidou.domain.line.Line;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 线路信息查询接口
 */
public interface LineRepository extends CrudRepository<Line, Long> {
    /**
     * 查询所有线路
     */
    @Query("select l from Line l where l.description <> '-' order by l.id")
    List<Line> findAll();

    /**
     * 根据状态查询线路
     */
    List<Line> findByStatus(String status);

    /**
     * 根据id查询
     */
    Line findById(long id);


    /**
     * 根据类型查询 1为线路  2为段
     */
    List<Line> findByType(String type);


}
