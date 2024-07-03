package com.ioomex.olecodeApp.service;

import com.ioomex.olecodeApp.model.entity.PostThumb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ioomex.olecodeApp.model.entity.SysUser;

/**
 * 帖子点赞服务
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @param loginSysUser
     * @return
     */
    int doPostThumb(long postId, SysUser loginSysUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);
}