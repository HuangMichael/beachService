package com.linkbit.beidou.dao.menu;


import com.linkbit.beidou.domain.menu.Menu;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by huangbin on 2016/1/8 0008.
 */
public interface MenuRepository extends CrudRepository<Menu, Long> {
    /**
     * 查询所有菜单
     */
    @OrderBy("sortNo desc")
    List<Menu> findAll();

    /**
     * 根据状态查询所有菜单
     */
    @OrderBy("sortNo desc")
    List<Menu> findByStatus(String status);

    /**
     * 根据id查询
     */
    Menu findById(long id);


}
