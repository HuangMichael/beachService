package com.linkbit.beidou.domain.line;

import lombok.*;

import javax.annotation.Resource;
import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 线路信息
 */
@Resource
@Entity
@Table(name = "T_STATION")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Station implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Fields
    @Column(length = 20, unique = true, nullable = false)
    private String stationNo;
    @Column(length = 200)
    private String description;
    @Column(length = 1)
    private String status;
    private Long sortNo; //排序
   /* @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    Line line; //上级位置*/

    @Column(length = 1)
    private String type;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    Line line;

}