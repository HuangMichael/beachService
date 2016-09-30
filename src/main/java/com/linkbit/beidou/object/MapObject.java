package com.linkbit.beidou.object;

import lombok.*;

/**
 * Created by huangbin on 2016/4/18.
 * 返回对象
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MapObject {
    private Long eqClassId;  //返回结果
    private String idList;//返回描述
}
