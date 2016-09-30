package com.linkbit.beidou.domain.workOrder;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "v_work_order_fix_bill")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 维修单视图实体类
 * */
public class VworkOrderFixBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String orderLineNo;
    private String orderDesc;
    private String eqName;
    private String locName;
    private String eqClass;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nodeTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadLine;
    private String nodeState;
    private String location;
}
