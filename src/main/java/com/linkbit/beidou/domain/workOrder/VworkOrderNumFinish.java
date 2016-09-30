package com.linkbit.beidou.domain.workOrder;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "v_work_order_num_finish")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 工单报修完成率
 * */
public class VworkOrderNumFinish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String finishMonth;
    private Long finishNum;
}
