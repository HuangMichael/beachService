package com.linkbit.beidou.domain.menu;


import lombok.*;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 菜单
 */
@Entity
@Table(name = "T_MENU")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 20, unique = true, nullable = false)
    private String menuName;
    @Column(length = 20, unique = false, nullable = false)
    private String menuDesc;
    @Column(length = 50, unique = false, nullable = true)
    private String iconClass;
    @Column(length = 50, unique = false, nullable = true)
    private String url;
    @Column(scale = 1000, nullable = true)
    private long sortNo;
    @Column(nullable = false, length = 1)
    private String status;
}

