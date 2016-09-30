package com.linkbit.beidou.domain.line;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 线路信息
 */
@Resource
@Entity
@Table(name = "T_LINE")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Line implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Fields
    @Column(length = 20, unique = true, nullable = false)
    private String lineNo;
    @Column(length = 200)
    private String description;
    @Column(length = 1)
    private String status;
    private Long sortNo; //排序
    @JsonBackReference("stationList")
    @OneToMany(mappedBy = "line")
    List<Station> stationList = new ArrayList<Station>();
    @Column(length = 1)
    private String type;//(1为线 2为段)
}