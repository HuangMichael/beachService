package com.linkbit.beidou.domain.workOrder;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "v_work_order_report_bill")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 工单报修完成率
 * */
public class VworkOrderReportBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String orderLineNo;
    private String orderDesc;
    private String eqName;
    private String locName;
    private String eqClass;
    private String nodeTime;
    private String nodeState;
}
