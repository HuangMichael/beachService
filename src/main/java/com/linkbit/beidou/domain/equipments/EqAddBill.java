package com.linkbit.beidou.domain.equipments;

import com.linkbit.beidou.domain.locations.Locations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 设备新置申请单
 **/
@Entity
@Table(name = "T_EQ_ADD_BILL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EqAddBill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id

    @Column(length = 20)
    private String applyDate;// 申购日期

    @Column(length = 50)
    private String accessoryName; //配件名称

    @Column(length = 50)
    private String applicant; //申请人

    @Column(length = 50)
    private String applyDep; //申请部门

    @Column(length = 50)
    private String purpose; //用途

    @Column(length = 50)
    private String specifications; //规格

    @Column(length = 10)
    private Long amount; //采购数量

    @Column(length = 10)
    private String approver; //批准人

    @Column(length = 10)
    private String handler; //经办人

    @Column(length = 10)
    private String receiver; //接收人


    @Column(length = 20)
    private String eqCode; //经办人

    @Column(length = 50)
    private String eqName; //接收人

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Locations location; //位置


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eq_class_id", referencedColumnName = "id")
    private EquipmentsClassification eqClass; //位置


    @Column(length = 1)
    private String dateType; //数据分类 1为采购  2为设备更新


    //id, applicant, apply_date, apply_dep, approver, date_type, handler, purpose, receiver, specifications, eq_class, locName, eq_code, eq_name

}
