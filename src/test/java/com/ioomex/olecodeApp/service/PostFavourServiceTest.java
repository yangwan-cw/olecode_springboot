package com.ioomex.olecodeApp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ioomex.olecodeApp.model.entity.Post;
import com.ioomex.olecodeApp.model.entity.SysUser;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子收藏服务测试
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@SpringBootTest
class PostFavourServiceTest {

    @Resource
    private PostFavourService postFavourService;

    private static final SysUser LOGIN_SYS_USER = new SysUser();

    @BeforeAll
    static void setUp() {
        LOGIN_SYS_USER.setId(1L);
    }

    @Test
    void doPostFavour() {
        int i = postFavourService.doPostFavour(1L, LOGIN_SYS_USER);
        Assertions.assertTrue(i >= 0);
    }

    @Test
    void listFavourPostByPage() {
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        postQueryWrapper.eq("id", 1L);
        postFavourService.listFavourPostByPage(Page.of(0, 1), postQueryWrapper, LOGIN_SYS_USER.getId());
    }
}
