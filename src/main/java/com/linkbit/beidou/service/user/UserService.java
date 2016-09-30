package com.linkbit.beidou.service.user;

import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.dao.person.PersonRepository;
import com.linkbit.beidou.dao.user.UserRepository;
import com.linkbit.beidou.domain.locations.Vlocations;
import com.linkbit.beidou.domain.person.Person;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.CommonStatusType;
import com.linkbit.beidou.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin  on 2016/4/14.
 */

@Service
public class UserService extends BaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;

    /**
     * 根据状态查询用户
     */
    public List<User> findByStatus(String status) {
        return userRepository.findByStatus(status);
    }


    /**
     * 根据状态查询用户
     */
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * @return 查询所有的用户
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 对用户进行加密
     */
    public User save(User user) {
        String password = user.getPassword();
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.md5(password));
        }
        user.setStatus("1");
        return userRepository.save(user);
    }


    /**
     * @param userName 用户名
     * @param pageable 可分页
     * @return 用户名模糊查询
     * @Date 2016年9月23日09:05:15
     */
    public Page<User> findByUserNameContrains(String userName, Pageable pageable) {
        return userRepository.findByUserNameContains(userName, pageable);
    }


    /**
     * 对用户进行加密
     */
    public User createUser(User user) {
        user.setPassword("123456");
        String password = user.getPassword();
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.md5(password));
        }
        user.setStatus("1");
        return userRepository.save(user);
    }


    /**
     * 对用户进行加密
     */
    public User update(Long userId, Long personId, Long locationId, String status) {
        User user = userRepository.findById(userId);
        Person person = personRepository.findById(personId);
        Vlocations vlocations = vlocationsRepository.findById(locationId);
        user.setPerson(person);
        user.setVlocations(vlocations);
        user.setLocation(vlocations.getLocation());
        user.setStatus(status);
        return userRepository.save(user);
    }

    /**
     * 查询加密后的用户
     */

    public List<User> findByUserNameAndPasswordAndStatus(String userName, String password, String status) {

        return userRepository.findByUserNameAndPasswordAndStatus(userName, password, status);
    }


    /**
     * @param userName 用户名
     * @param status   状态
     * @return
     */
    public User findByUserNameAndStatus(String userName, String status) {
        List<User> userList = userRepository.findByUserNameAndStatus(userName, status);
        User user;
        if (!userList.isEmpty()) {
            user = userList.get(0);
        } else {
            user = null;
        }
        return user;
    }

    /**
     * 修改用户密码
     */
    public boolean changePwd(String userName, String password) {
        List<User> userList = userRepository.findByUserNameAndStatus(userName, CommonStatusType.STATUS_YES);
        if (!userList.isEmpty()) {
            User user = userList.get(0);
            user.setPassword(MD5Util.md5(password));
            user = userRepository.save(user);
            return user != null;
        } else {
            return false;
        }
    }

}
