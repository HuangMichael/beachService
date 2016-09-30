package com.linkbit.beidou.service.person;

import com.linkbit.beidou.dao.person.PersonRepository;
import com.linkbit.beidou.domain.person.Person;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.CommonStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员业务类
 */
@Service
public class PersonService extends BaseService {


    @Autowired
    PersonRepository personRepository;

    /**
     * @return 查询激活状态的人员
     */
    public List<Person> findActivePerson() {
        return personRepository.findByStatus(CommonStatusType.STATUS_YES);
    }


    /**
     * @return 查询所有人员
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * @param id
     * @return 根据人员id查询人员
     */
    public Person findById(Long id) {
        return personRepository.findById(id);
    }


    /**
     * @param person
     * @return 保存人员信息
     */
    public Person save(Person person) {
        return personRepository.save(person);
    }


    /**
     * @param person
     * @return 更新人员信息
     */
    public Person update(Person person) {
        return personRepository.save(person);
    }


    /**
     * @param id
     * @return 删除人员信息
     */
    public boolean delete(Long id) {
        personRepository.delete(id);
        return (personRepository.findById(id) == null);
    }


    /**
     * @param personName
     * @param pageable
     * @return 根据人员姓名模糊查询
     * @Date 2016年9月23日09:34:44
     */
    public Page<Person> findByPersonNameContains(String personName, Pageable pageable) {
        return personRepository.findByPersonNameContains(personName, pageable);
    }

}
