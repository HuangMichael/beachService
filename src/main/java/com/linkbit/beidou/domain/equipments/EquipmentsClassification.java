package com.linkbit.beidou.domain.equipments;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 设备分类信息
 */
@Entity
@Table(name = "T_EQUIPMENTS_CLASSIFICATION")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentsClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //Fields
    @Column(length = 20)
    private String classId;
    @Column(length = 20)
    private String description;
    @Column(length = 1)
    private String hasChild;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    EquipmentsClassification parent;

    //一个分类有多个子分类
    @JsonBackReference("classificationList")
    @OneToMany(targetEntity = EquipmentsClassification.class, cascade = CascadeType.ALL, mappedBy = "parent")
    List<EquipmentsClassification> classificationList = new ArrayList<EquipmentsClassification>();

    @Column(length = 1)
    private Long level;

    @Column(length = 4)
    private Long limitHours; //时限单位小时

    @Column(length = 20)
    private Long sortNo;
    @JsonBackReference("unitSet")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "t_unit_class", joinColumns = {
            @JoinColumn(name = "unit_id", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "class_id",
                    nullable = false, updatable = true)})
    private List<OutsourcingUnit> unitSet;


    @Column(length = 1)
    private String classType; //设备分类类别 站区段区


    @Column(length = 1)
    private String status; //设备分类状态


}

