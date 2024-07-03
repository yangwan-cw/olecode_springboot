package com.ioomex.olecodeApp.service;

import com.ioomex.olecodeApp.model.entity.SysUser;

import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子点赞服务测试
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@SpringBootTest
class PostThumbServiceTest {

    @Resource
    private PostThumbService postThumbService;

    private static final SysUser LOGIN_SYS_USER = new SysUser();

    @BeforeAll
    static void setUp() {
        LOGIN_SYS_USER.setId(1L);
    }

    @Test
    void doPostThumb() {
        int i = postThumbService.doPostThumb(1L, LOGIN_SYS_USER);
        Assertions.assertTrue(i >= 0);
    }
}