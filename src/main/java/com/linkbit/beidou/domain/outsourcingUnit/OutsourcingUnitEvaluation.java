package com.linkbit.beidou.domain.outsourcingUnit;

import lombok.*;

import javax.annotation.Resource;
import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 外委单位年度服务评价
 */
@Resource
@Entity
@Table(name = "T_OUTSOURCING_UNIT_EVALUATION")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutsourcingUnitEvaluation implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 4, unique = true, nullable = false)
    private String envNo; //年度

    @Column(length = 1000, unique = true, nullable = false)
    private String envContent; //年度评价内容

    @Column(length = 1)
    private String status;  //使用状态

    //多对一关联外委单位信息
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    OutsourcingUnit unit;

}