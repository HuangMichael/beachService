package com.linkbit.beidou.domain.app;/**
 * Created by Administrator on 2016/9/2.
 */

import lombok.*;

import java.util.List;

/**
 * 自定义的分页对象
 *
 * @author
 * @create 2016-09-02 15:01
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {
    private int current;
    private Long rowCount;
    private List rows;
    private long total;
}
