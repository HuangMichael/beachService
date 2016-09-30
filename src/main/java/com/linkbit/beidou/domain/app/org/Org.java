package com.linkbit.beidou.domain.app.org;

import lombok.*;

import javax.persistence.*;

/**
 * Created by HUANGBIN on 2016/4/15.
 * 组织  配置系统信息
 */

@Entity
@Table(name = "T_ORG")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Org {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String sysName; //系统名称
    @Column(length = 50)
    private String description;//表名称
    @Column(length = 50)
    private String logoUrl;//表名称
    @Column(length = 1)
    private String status;
    private Long sortNo; //排序
}
