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
public class OutsourcingUnitSafeDocs implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String docNo; //文档编号

    @Column(length = 100, unique = true, nullable = false)
    private String docUrl; //安全教育文档地址

    @Column(length = 1)
    private String status;  //使用状态


    //多对一关联外委单位信息
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    OutsourcingUnit unit;
}