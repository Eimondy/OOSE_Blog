package com.ablog.service.attachment;

import com.github.pagehelper.PageInfo;
import com.ablog.dto.AttachmentDto;
import com.ablog.model.AttachmentDomain;

/**
 * 文件相关接口
 */
public interface AttachmentService {

    /**
     * 添加单个附件信息
     * @param attachmentDomain
     */
    void addAttAch(AttachmentDomain attachmentDomain);

    /**
     * 获取所有的附件信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AttachmentDto> getAtts(int pageNum, int pageSize);

    /**
     * 通过ID获取附件信息
     * @param id
     * @return
     */
    AttachmentDto getAttAchById(Integer id);

    /**
     * 通过ID删除附件信息
     * @param id
     */
    void deleteAttAch(Integer id);
}
