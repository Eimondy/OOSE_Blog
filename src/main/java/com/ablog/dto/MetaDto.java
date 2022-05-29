/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/24 16:59
 **/
package com.ablog.dto;

import com.ablog.model.MetaDomain;

/**
 * 标签、分类列表
 */
public class MetaDto extends MetaDomain {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
