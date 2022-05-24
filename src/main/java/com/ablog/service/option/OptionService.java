/**
 * @author Bowen
 * @create 2022/5/24 19:12
 */
package com.ablog.service.option;

import com.ablog.model.OptionsDomain;
import java.util.List;
import java.util.Map;

/**
 * 网站选项相关Service接口
 */
public interface OptionService {

    /**
     * 获取全部网站配置
     * @return
     */
    List<OptionsDomain> getOptions();

    /**
     * 保存系统设置
     * @param querys
     */
    void saveOptions(Map<String,String> querys);

    /**
     * 更新网站配置
     * @param s
     * @param s1
     */
    void updateOptionByName(String name, String value);

    /**
     * 通过名称获取网站配置
     * @param site_record
     * @return
     */
    OptionsDomain getOptionByName(String site_record);
}
