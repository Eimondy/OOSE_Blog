package com.ablog.dao;

import com.ablog.dto.AttachmentDto;
import com.ablog.model.AttachmentDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AttachmentDao {

    /**
     * 添加单个附件文件
     * @param attachmentDomain
     */
    void addAttAch(AttachmentDomain attachmentDomain);

    /**
     * 获取所有的附件信息
     * @return
     */
    List<AttachmentDto> getAtts();

    /**
     * 获取附件总数
     * @return
     */
    Long getAttAchCount();

    /**
     * 通过ID获取附件信息
     * @param id
     * @return
     */
    AttachmentDto getAttAchById(@Param("id") Integer id);

    /**
     * 通过ID删除附件信息
     * @param id
     */
    void deleteAttAch(@Param("id") Integer id);
}
