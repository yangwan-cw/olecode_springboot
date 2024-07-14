package com.ioomex.olecodeApp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.olecodeApp.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

}




