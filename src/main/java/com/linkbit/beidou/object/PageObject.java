package com.linkbit.beidou.object;

import lombok.Data;

import java.util.List;

/**
 * Created by huangbin on 2016/5/18.
 */
@Data
public class PageObject {
    private Long current;
    private Long rowCount;
    private List<?> rows;
    private Long total;
}
