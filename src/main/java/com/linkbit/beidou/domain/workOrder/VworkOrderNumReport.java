package com.linkbit.beidou.domain.workOrder;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "v_work_order_num_report")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 工单报修完成率
 * */
public class VworkOrderNumReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reportMonth;
    private Long reportNum;
}
