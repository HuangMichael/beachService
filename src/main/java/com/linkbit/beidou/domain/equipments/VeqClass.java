package com.linkbit.beidou.domain.equipments;


import lombok.*;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 设备分类视图信息
 */
@Entity
@Table(name = "V_EQCLASS")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VeqClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 20)
    private String classId;
    @Column(length = 50)
    private String cpName;
    @Column(length = 50)
    private String cName;
}

