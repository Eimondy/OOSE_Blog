package com.ablog.service.attachment.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ablog.constant.ErrorConstant;
import com.ablog.dao.AttachmentDao;
import com.ablog.dto.AttachmentDto;
import com.ablog.exception.BusinessException;
import com.ablog.model.AttachmentDomain;
import com.ablog.service.attachment.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件接口实现
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    @CacheEvict(value = {"attCaches", "attCache"}, allEntries = true, beforeInvocation = true)
    public void addAttAch(AttachmentDomain attachmentDomain) {
        if (null == attachmentDomain)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attachmentDao.addAttAch(attachmentDomain);
    }

    @Override
    @Cacheable(value = "attCaches", key = "'atts' + #p0")
    public PageInfo<AttachmentDto> getAtts(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttachmentDto> atts = attachmentDao.getAtts();
        PageInfo<AttachmentDto> pageInfo = new PageInfo<>(atts);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "attCaches", key = "'attAchByid' + #p0")
    public AttachmentDto getAttAchById(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return attachmentDao.getAttAchById(id);
    }

    @Override
    @CacheEvict(value = {"attCaches", "attCache"}, allEntries = true, beforeInvocation = true)
    public void deleteAttAch(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attachmentDao.deleteAttAch(id);
    }
}
