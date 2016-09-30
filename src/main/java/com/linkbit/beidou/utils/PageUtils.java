package com.linkbit.beidou.utils;/**
 * Created by Administrator on 2016/9/2.
 */

import com.linkbit.beidou.domain.app.MyPage;
import org.omg.CORBA.Object;
import org.springframework.data.domain.Page;

/**
 * 分页对象转换类
 *
 * @author
 * @create 2016-09-02 15:06
 **/
public class PageUtils {

    /**
     * @param page
     * @return 将JPA的分页对象转换为自定义的分页对象
     */
    public static MyPage transform(Page<Object> page) {
        MyPage myPage = new MyPage();
        if(page!=null){
            myPage.setCurrent(1);
            myPage.setRowCount(page.getTotalElements());//设置总条数
            myPage.setRows(page.getContent());
            myPage.setTotal(page.getTotalPages());
        }
        return myPage;
    }
}
