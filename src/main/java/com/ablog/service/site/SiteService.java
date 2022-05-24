/**
 * @author Bowen
 * @create 2022/5/24 19:12
 */
package com.ablog.service.site;

import com.ablog.dto.StatisticsDto;
import com.ablog.model.CommentDomain;
import com.ablog.model.ContentDomain;

import java.util.List;

/**
 * 网站相关Service接口
 */
public interface SiteService {

    /**
     * 获取评论列表
     * @param limit
     * @return
     */
    List<CommentDomain> getComments(int limit);

    /**
     * 获取文章列表
     * @param limit
     * @return
     */
    List<ContentDomain> getNewArticles(int limit);

    /**
     * 获取后台统计数
     * @return
     */
    StatisticsDto getStatistics();
}
