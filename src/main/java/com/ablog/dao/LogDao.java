package com.ablog.dao;

import com.ablog.model.LogDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LogDao {

    /**
     * 添加日志
     * @param logDomain
     * @return
     */
    int addLog(LogDomain logDomain);

    /**
     * 获取日志
     * @return
     */
    List<LogDomain> getLogs();
}
