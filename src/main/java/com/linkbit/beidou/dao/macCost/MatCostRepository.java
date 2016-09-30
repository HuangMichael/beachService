package com.linkbit.beidou.dao.macCost;

import com.linkbit.beidou.domain.matCost.MatCost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 采购申请单
 **/
public interface MatCostRepository extends PagingAndSortingRepository<MatCost, Long>, CrudRepository<MatCost, Long> {


    /**
     * @param pageable
     * @return
     */
    Page<MatCost> findAll(Pageable pageable);

    /**
     * @return
     */
    List<MatCost> findAll();


    /**
     * @param id 根据id查询
     * @return
     */
    MatCost findById(Long id);


    /**
     * @return查询所有的id
     */
    @Query("select id from MatCost")
    List<Long> findAllIds();

    @Query("SELECT DISTINCT m.locName FROM  MatCost m ORDER BY m.locName DESC")
    List<String> findMyLocs();


    @Query("SELECT DISTINCT m.locName FROM  MatCost m ORDER BY m.locName DESC")
    List<String> findMyLines();


    List<MatCost> findByEcTypeContainsAndLocNameContainsAndEcNameContains(String ecType, String locName, String ecName);
}
