package com.linkbit.beidou.domain.outsourcingUnit;

import lombok.*;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 外委单位合同
 */
@Resource
@Entity
@Table(name = "T_OUTSOURCING_UNIT_CONTRACT")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutsourcingUnitContract implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String contractNo; //合同编号

    @Temporal(TemporalType.DATE)
    private Date beginDate; //合同开始日期

    @Temporal(TemporalType.DATE)
    private Date endDate;  //合同结束日期

    @Column(length = 100, unique = true, nullable = false)
    private String contractUrl; //合同文档路径文档地址

    @Column(length = 1)
    private String status;  //使用状态

    //多对一关联外委单位信息
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    OutsourcingUnit unit;

}