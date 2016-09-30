package com.linkbit.beidou.domain.outsourcingUnit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import lombok.*;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 外委单位
 */
@Resource
@Entity
@Table(name = "T_OUTSOURCING_UNIT")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutsourcingUnit implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Fields
    @Column(length = 20, unique = true, nullable = false)
    private String unitNo; //编码

    @Column(length = 200)
    private String description;//外委单位名称

    @Column(length = 20)
    private String linkman;//责任人

    @Column(length = 20)
    private String telephone;//联系电话

    @Temporal(TemporalType.DATE)
    private Date beginDate; //合同开始日期

    @Temporal(TemporalType.DATE)
    private Date endDate;  //合同结束日期

    @Column(length = 1, columnDefinition = "default '1'")
    private String status;  //使用状态

    @Column(length = 1)
    private String workDays;  //工作制


    //一对多关联合同信息
    @JsonBackReference("contractList")
    @OneToMany(targetEntity = OutsourcingUnitContract.class, cascade = CascadeType.ALL, mappedBy = "unit")
    List<OutsourcingUnitContract> contractList = new ArrayList<OutsourcingUnitContract>();
    //一对多关联年度评价
    @JsonBackReference("evaluationList")
    @OneToMany(targetEntity = OutsourcingUnitEvaluation.class, cascade = CascadeType.ALL, mappedBy = "unit")
    List<OutsourcingUnitEvaluation> evaluationList = new ArrayList<OutsourcingUnitEvaluation>();
    //一对多关联安全教育文档信息
    @JsonBackReference("safeDocsList")
    @OneToMany(targetEntity = OutsourcingUnitSafeDocs.class, cascade = CascadeType.ALL, mappedBy = "unit")
    List<OutsourcingUnitSafeDocs> safeDocsList = new ArrayList<OutsourcingUnitSafeDocs>();

    @ManyToMany
    @JoinTable(name = "t_unit_class", joinColumns = {@JoinColumn(name = "unit_id")}, inverseJoinColumns = {@JoinColumn(name = "class_id")})
    private Set<EquipmentsClassification> eqClassList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "unitSet")
    private List<EquipmentsClassification> classificationSet;

}