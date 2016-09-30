package com.linkbit.beidou.domain.app.resoure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linkbit.beidou.domain.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by HUANGBIN on 2016/4/15.
 * 资源信息
 */

@Entity
@Table(name = "T_RESOURCE")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100, nullable = false)
    private String resourceCode;//编号
    @Column(length = 200, nullable = false)
    private String resourceName;//资源名称
    @Column(length = 100, nullable = false)
    private String resourceUrl;//资源路径
    @Column(length = 100, nullable = false)
    private String description;//资源描述
    @Column(length = 100, nullable = true)
    private String iconClass;//资源描述
    @Column(length = 1)
    private Long resourceLevel;//资源级别

    @Column(length = 100, nullable = true)
    private String appName;//应用名称


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    Resource parent;

    @Column(length = 1)
    private String status;
    private Long sortNo; //排序
    @Column(length = 1)
    private boolean staticFlag;

    @JsonBackReference("roleList")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_role_resource",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")
            }
    )
    private List<Role> roleList;
}

