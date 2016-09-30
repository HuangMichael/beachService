package com.linkbit.beidou.domain.EcBudget;

import com.linkbit.beidou.domain.locations.Vlocations;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 易耗品采购申请单
 **/
@Entity
@Table(name = "T_EC_BUDGET_BILL")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EcBudgetBill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id
    @Column(length = 20)
    private String applyDate;// 申请日期
    @Column(length = 20)
    private String applicant;// 填报人
    @Column(length = 20)
    private String ecname;// 填报人
    @Column(length = 20)
    private String amount;// 填报人
    @Column(length = 20)
    private String epermited;// 填报人
    @Column(length = 20)
    private String updateReason;// 填报人
    @Column(length = 20)
    private String auditDate;// 填报人
    @Column(length = 20)
    private String auditor;// 填报人
    @Column(length = 20)
    private String confirmReason;// 填报人
    @Column(length = 20)
    private String fixAdvice;// 填报人
    @Column(length = 20)
    private String leaderAdvice;// 填报人

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "vlocations_id", referencedColumnName = "id")
    private Vlocations vlocations; //位置

}
