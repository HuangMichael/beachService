package com.linkbit.beidou.domain.locations;


import lombok.*;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/17 0023.
 * 设备位置信息
 */
@Entity
@Table(name = "V_LOCATIONS")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vlocations implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 10)
    private String location; //位置描述
    @Column(length = 100)
    private String locName; //位置描述



}