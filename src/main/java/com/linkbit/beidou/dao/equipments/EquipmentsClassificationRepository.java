package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备分类信息查询接口
 */
public interface EquipmentsClassificationRepository extends CrudRepository<EquipmentsClassification, Long> {
    /**
     * 查询所有设备类别
     */
    List<EquipmentsClassification> findAll();
    /**
     * 根据id查询
     */
    EquipmentsClassification findById(long id);


    /**
     * 查询一级节点集合
     */
    @OrderBy("sortNo asc")
    List<EquipmentsClassification> findByParent(EquipmentsClassification equipmentsClassification);


    /**
     * 根据根节点id查询子节点
     */
    @Query("select l from  EquipmentsClassification  l   where  l.parent = :parentId order by l.sortNo")
    List<EquipmentsClassification> findNodeByParent(EquipmentsClassification parent);


    /**
     * 查询根节点
     */
    @Query("select l from  EquipmentsClassification  l   where  l.parent is null  order by l.sortNo")
    List<EquipmentsClassification> findNodeByParentId();


    /**
     * 查询叶子节点 按照编号排序
     */
    @Query("select l from  EquipmentsClassification  l   where  l.hasChild ='0' order by l.sortNo")
    List<EquipmentsClassification> findLeafNode();


    /**
     * 查询设备分类对应的维修单位
     */


}
