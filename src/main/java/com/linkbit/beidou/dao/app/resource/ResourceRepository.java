package com.linkbit.beidou.dao.app.resource;


import com.linkbit.beidou.domain.app.resoure.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 数据资源查询接口
 */
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    /**
     * 查询所有数据资源
     */
    List<Resource> findAll();

    /**
     * 根据状态查询数据资源
     */
    List<Resource> findByStatus(String status);



    /**
     * 根据URL查询
     */
    List<Resource> findByResourceUrl(String resourceUrl);


    /**
     * 根据URL模糊查询
     */
    List<Resource> findByResourceUrlStartingWithAndStaticFlag(String resourceUrl,String  staticFlag);

    /**
     * 根据id查询数据资源
     */
    Resource findById(long id);

    /**
     * 保存数据资源
     */
    Resource save(Resource resource);


    @Override
    void delete(Resource resource);

    @Query("select count(r)>0 from Resource r where r.parent.id =:pid")
    Boolean hasChildren(@Param("pid") long pid);

    /**
     * 根据上级节点查询
     */
    List<Resource> findByParent(Resource resource);


    /**
     * 根据资源级别查询
     */
    List<Resource> findByResourceLevel(Long resourceLevel);


    /**
     * 根据资源级别查询
     */
    @OrderBy("parent ,sortNo asc")
    List<Resource> findByResourceLevelLessThan(Long resourceLevel);


    /**
     * 根据资源级别查询
     */
    List<Resource> findBystaticFlag(boolean staticOrNot);


    /**
     * @param idList
     * @return 按照id in查询
     */
    @Query("select r from Resource r where r.id in :idList")
    List<Resource> findResourceIdInIdList(@Param("idList") List<Long> idList);




}
