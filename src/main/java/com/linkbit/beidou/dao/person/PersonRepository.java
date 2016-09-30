package com.linkbit.beidou.dao.person;


import com.linkbit.beidou.domain.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 人员信息查询接口
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
    /**
     * 查询所有人员
     */
    List<Person> findAll();

    /**
     * 根据状态查询人员
     */
    List<Person> findByStatus(String status);

    /**
     * 根据id查询
     */
    Person findById(long id);

    /**
     * @param personName 人员姓名
     * @param pageable   分页
     * @return 根据人员姓名模糊查询
     */
    Page<Person> findByPersonNameContains(String personName, Pageable pageable);
}
