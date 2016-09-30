package com.linkbit.beidou.domain.locations;


import lombok.*;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/17 0023.
 * 设备位置信息
 */
@Entity
@Table(name = "T_LOCATIONS")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationsSel implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String description;//描述
    @Column(length = 1)
    private String status;//状态
}