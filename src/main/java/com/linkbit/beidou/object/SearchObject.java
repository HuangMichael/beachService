package com.linkbit.beidou.object;

import lombok.*;

import java.util.Map;

/**
 * 查询条件模型
 *
 * @author
 * @create 2016-09-06 10:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchObject {
    public int  pageIndex;
    public int  pageCount;
}
