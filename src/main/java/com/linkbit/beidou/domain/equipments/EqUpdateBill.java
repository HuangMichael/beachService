package com.linkbit.beidou.domain.equipments;

import com.linkbit.beidou.domain.locations.Vlocations;
import lombok.*;

import javax.persistence.*;

/**
 * 采购申请单
 **/
@Entity
@Table(name = "T_EQ_UPDATE_BILL")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EqUpdateBill {

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

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipments equipments; //位置


    @Column(length = 1)
    private String dateType; //数据分类 1为采购  2为设备更新

}
