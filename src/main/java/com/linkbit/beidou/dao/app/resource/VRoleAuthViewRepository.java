package com.linkbit.beidou.dao.app.resource;

import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.role.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/8/16.
 */
public interface VRoleAuthViewRepository extends CrudRepository<VRoleAuthView, Long> {


    /**
     * @param role
     * @return 根据角色查询角色权限预览视图
     */
    List<VRoleAuthView> findByRole(Role role);

    /**
     * @param resourceLevel
     * @return 根据角色和资源级别查询
     */
    @Query("select distinct v from VRoleAuthView v where v.role.id in :roleIdList and  v.resourceLevel =:resourceLevel ")
    List<VRoleAuthView> findByRoleListAndResourceLevel(@Param("roleIdList") List<Long> roleIdList, @Param("resourceLevel") Long resourceLevel);
    //@Param("roleIdStr") String roleIdStr,

    /**
     * @param resourceLevel
     * @param parentId
     * @return 根据角色和资源级别查询
     */
    @Query("select  distinct v  from VRoleAuthView v where 1=1 and v.role.id in :idList and v.resourceLevel =:resourceLevel and v.parentId =:parentId")
    List<VRoleAuthView> findByRoleListAndResourceLevelAndParentId(@Param(value = "idList") List<Long> idList, @Param("resourceLevel") Long resourceLevel, @Param("parentId") Long parentId);


    /**
     * 根据应用名称查询菜单
     */
    @Query("select  distinct v  from VRoleAuthView v where  v.role in :roleList and v.appName =:appName ")
    List<VRoleAuthView> findByRoleListAppName(@Param("roleList") List<Role> roleList, @Param("appName") String appName);
}
