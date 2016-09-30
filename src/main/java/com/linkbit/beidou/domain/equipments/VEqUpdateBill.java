package com.linkbit.beidou.domain.equipments;

import lombok.*;

import javax.persistence.*;

/**
 * 设备更新申请单视图
 **/
@Entity
@Table(name = "V_EQ_UPDATE_BILL")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VEqUpdateBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id

    @Column(length = 20)
    private String applyDate;// 申购日期

    @Column(length = 50)
    private String locName; //设备名称

    @Column(length = 50)
    private String eqName; //设备名称
    @Column(length = 50)
    private String eqCode; //设备编号
    @Column(length = 50)
    private String eqClass; //设备分类

    @Column(length = 50)
    private String applicant; //申请人

    @Column(length = 50)
    private String applyDep; //申请部门

    @Column(length = 50)
    private String purpose; //用途

    @Column(length = 10)
    private String approver; //批准人

    @Column(length = 10)
    private String handler; //经办人

    @Column(length = 10)
    private String receiver; //接收人

    @Column(length = 50)
    private String specifications; //规格

}
