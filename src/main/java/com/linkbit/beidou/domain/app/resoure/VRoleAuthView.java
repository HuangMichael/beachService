package com.linkbit.beidou.domain.app.resoure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linkbit.beidou.domain.role.Role;
import lombok.*;

import javax.persistence.*;

/**
 * Created by HUANGBIN on 2016/4/15.
 * 资源信息
 */

@Entity
@Table(name = "v_role_auth_view")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VRoleAuthView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 20, nullable = false)
    private String resourceCode;//编号
    @Column(length = 20, nullable = false)
    private String resourceName;//资源名称
    @Column(length = 50, nullable = false)
    private String resourceDesc;// 资源描述
    @Column(length = 2, nullable = false)
    private Long resourceLevel;//资源级别
    @Column(length = 20, nullable = true)
    private String iconClass;//菜单样式
    @Column(length = 20, nullable = false)
    private String resourceUrl;//资源路径

    @Column(length = 20, nullable = false)
    private String appName;//应用名称

    @JsonBackReference("role")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;  //所属位置
    private Long parentId;//上级id


}

