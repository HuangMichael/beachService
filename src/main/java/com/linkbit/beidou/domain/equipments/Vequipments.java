package com.linkbit.beidou.domain.equipments;


import lombok.*;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 设备信息
 */
@Entity
@Table(name = "V_EQUIPMENTS")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vequipments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;  //id
    @Column(length = 20, unique = true)
    private String eqCode; //设备编号
    @Column(length = 50)
    private String eqName; //设备描述
    @Column(length = 50)
    private String locName; //设备描述
    @Column(length = 20)
    private String eqClass; //设备分类
    @Column(length = 20)
    private String status; //设备状态
    @Column(length = 20)
    private String running; //运行状态
    @Column(length = 20)
    private String location; //设备位置编码



}
