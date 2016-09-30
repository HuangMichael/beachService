package com.linkbit.beidou.domain.person;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 人员信息
 */
@Entity
@Table(name = "T_PERSON")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 20)
    private String personNo;
    @Column(length = 20)
    private String personName;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    private String telephone;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(length = 1)
    private String status;
    private Long sortNo;
}