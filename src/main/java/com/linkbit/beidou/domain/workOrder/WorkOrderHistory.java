package com.linkbit.beidou.domain.workOrder;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 工单历史信息
 * 记录工单从报修开始到完工所有生命周期节点信息s
 */
@Entity
@Table(name = "T_WORK_ORDER_HISTORY")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 维修单信息
 * */
public class WorkOrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //关联工单信息
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private WorkOrderReportCart workOrderReportCart;

    //节点时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nodeTime;

    //节点名称
    @Column(length = 50)
    private String nodeDesc;

    //状态标识
    @Column(length = 2)
    private String status; //节点状态
}
