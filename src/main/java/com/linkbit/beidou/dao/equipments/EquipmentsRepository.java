package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.equipments.VeqClass;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.locations.Locations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备信息查询接口
 */
public interface EquipmentsRepository extends CrudRepository<Equipments, Long> {
    /**
     * 查询所有设备类别
     */
    List<Equipments> findAll();


    /**
     * 查询所有设备类别
     */
    void delete(Equipments equipments);

    /**
     * 根据状态查询设备类别
     */
    List<Equipments> findByStatus(String status);

    /**
     * 根据id查询
     */
    Equipments findById(long id);


    /**
     * @param eqCode
     * @return 根据设备编号查询设备信息
     */
    List<Equipments> findByEqCode(String eqCode);

    /**
     * 根据位置信息查询设备
     */
    List<Equipments> findByLocations(Locations locations);


    /**
     * @param equipments 保存设备信息
     * @return
     */
    Equipments save(Equipments equipments);


    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */
    List<Equipments> findByLocationStartingWith(String location);


    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */
    List<Equipments> findByLocationStartingWithAndStatus(String location, String status);


    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */
    List<Equipments> findByLocationStartingWithOrderByIdDesc(String location);

    /**
     * @param eqCode 设备编号
     * @return 根据设备编号查询设备数量
     */
    @Query("select count(e) from Equipments e where e.eqCode =:eqCode")
    Long selectCountByEqcode(@Param("eqCode") String eqCode);

    /**
     * @param eid 设备id
     * @return 根据设备id查询正在维修的流程节点
     */
    @Query(nativeQuery = true, value = "SELECT  v.id, v.order_line_no,e.description, v.order_desc, v.report_time, v.s FROM  v_work_order_step v left JOIN t_equipments e ON v.equipments_id = e.id WHERE  v.equipments_id = :eid AND v.status = '0' ORDER BY v.report_time DESC")
    List<Object> findFixingStepByEid(@Param("eid") Long eid);


    /**
     * @param eid 设备id
     * @return 根据设备id查询维修过程的所有节点
     */
    @Query(nativeQuery = true, value = "SELECT  v0.report_time, v0.s, v0.status FROM v_work_order_step v0 WHERE v0.equipments_id = :eid ORDER BY v0.report_time  limit 4  ")
    List<Object> findFixStepsByEid(@Param("eid") Long eid);

    @Query(nativeQuery = true, value = "SELECT   date_format(v0.report_Time,'%Y-%m-%d %H:%i:%s'), v0.flow_desc,v0.order_Desc,v0.fix_Desc FROM v_work_order_step v0 WHERE v0.equipments_id = :eid ORDER BY v0.report_time")
    List<Object> findFixHistoryByEid(@Param("eid") Long eid);

    @Query(nativeQuery = true, value = "select v.order_line_no,v.equipments_id,v.order_desc,date_format(v.report_time, '%Y-%m-%d %H:%I:%s'),v.status,v.flow_desc from  v_work_order_step v where v.equipments_id = :eid order by v.report_time desc limit 1 ")
    List<Object> findLastFixHistoryByEid(@Param("eid") Long eid);


    @Query(nativeQuery = true, value = "SELECT  v0.order_line_no, v0.flow_desc, v0.status,date_format(v0.report_Time,'%Y-%m-%d %H:%i:%s') ,v0.order_Desc,max(v0.fix_Desc) as fix_Desc FROM v_work_order_step v0 WHERE v0.order_line_no = :orderLineNo ORDER BY v0.report_time desc limit 1")
    List<Object> findFixStepsByOrderLineNo(@Param("orderLineNo") String orderLineNo);


    @Query(nativeQuery = true, value = "SELECT h.node_desc AS flow_desc,date_format(h.node_time,'%Y-%m-%d %H:%i:%s') ,c.order_desc,c.fix_desc FROM t_work_order_history h LEFT JOIN t_work_order_report_cart c ON h.order_id = c.id WHERE c.order_Line_No =:orderLineNo ORDER BY h.node_time")
    List<Object> findAllFixStepsByOrderLineNo(@Param("orderLineNo") String orderLineNo);


    @Query(nativeQuery = true, value = "SELECT  v0.order_line_no, v0.flow_desc, v0.status,date_format(v0.report_Time,'%Y-%m-%d %H:%i:%s') ,v0.order_Desc,max(v0.fix_Desc) as fix_Desc FROM v_work_order_step v0 WHERE v0.equipments_id = :eid GROUP BY v0.order_line_no ORDER BY v0.report_time desc")
    List<Object> findAllFixStepsByEid(@Param("eid") Long eid);


    @Query(nativeQuery = true, value = "select v.order_line_no,v.fix_desc as aaa,date_format(v.report_time,'%Y-%m-%d %H:%i:%s') ,v.fix_desc,v.node_state  from v_work_order_last_status v where v.equipments_id =:eid")
    List<Object> findEndFixStepsByEid(@Param("eid") Long eid);

    /**
     * @param id
     * @return 根据位置过滤设备分类
     */
    @Query("select vc from VeqClass vc where vc.id in (select distinct  e.equipmentsClassification.id from Equipments e where e.locations.id =:id)")
    List<VeqClass> findEqClassesByLocationId(@Param("id") Long id);


    /**
     * @param lid
     * @param cid
     * @return 根据位置和设备分类过滤设备
     */
    @Query("select ve from Vequipments ve where ve.id in ( select e.id from Equipments e where e.locations.id =:lid and e.equipmentsClassification.id =:cid)")
    List<Vequipments> findEqByLocIdAndEqcId(@Param("lid") Long lid, @Param("cid") Long cid);

}
