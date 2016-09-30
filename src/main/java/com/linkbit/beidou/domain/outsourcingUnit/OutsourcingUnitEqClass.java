package com.linkbit.beidou.domain.outsourcingUnit;

import lombok.*;

import javax.annotation.Resource;
import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 外委单位安全教育文档
 */
@Resource
@Entity
@Table(name = "T_OUTSOURCING_UNIT_SAFE_DOCS")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutsourcingUnitEqClass implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    //多对一关联外委单位信息
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    OutsourcingUnit unit;
}