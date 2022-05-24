package com.ablog.service.attach;

import com.github.pagehelper.PageInfo;
import com.ablog.dto.AttAchDto;
import com.ablog.model.AttAchDomain;

/**
 * 文件相关接口
 */
public interface AttAchService {

    /**
     * 添加单个附件信息
     * @param attAchDomain
     */
    void addAttAch(AttAchDomain attAchDomain);

    /**
     * 获取所有的附件信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AttAchDto> getAtts(int pageNum, int pageSize);

    /**
     * 通过ID获取附件信息
     * @param id
     * @return
     */
    AttAchDto getAttAchById(Integer id);

    /**
     * 通过ID删除附件信息
     * @param id
     */
    void deleteAttAch(Integer id);
}