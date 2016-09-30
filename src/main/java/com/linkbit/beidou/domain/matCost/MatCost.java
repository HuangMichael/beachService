package com.linkbit.beidou.domain.matCost;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by huangbin
 * 2016年9月29日10:05:49
 * 物料消耗信息视图
 */
@Entity
@Table(name = "V_MAT_COST")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatCost implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String locName; //位置描述
    @Column(length = 50)
    private String ecName; //位置描述
    @Column(length = 3)
    private Long amount; //位置描述
    @Column(length = 20)
    private String applyDate; //位置描述
    @Column(length = 10)
    private String ecType; //位置描述
}