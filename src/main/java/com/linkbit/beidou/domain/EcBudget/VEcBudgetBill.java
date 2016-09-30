package com.linkbit.beidou.domain.EcBudget;

import lombok.*;

import javax.persistence.*;

/**
 * 易耗品采购申请单视图
 *
 * @author
 * @create 2016-09-09 11:03
 **/
@Entity
@Table(name = "V_EC_BUDGET_BILL")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VEcBudgetBill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;  //id

    private String applyDate;// 申购申请日期
    private long amount;
    private String applicant;
    private String auditDate;
    private String  auditor;
    private String confirmReason;
    private String ecname;
    private String epermited;
    private String fixAdvice;
    private String  leaderAdvice;
    private String updateReason;
    private String locName;
    private String  location;

}
