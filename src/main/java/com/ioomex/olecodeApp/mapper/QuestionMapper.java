package com.ioomex.olecodeApp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.olecodeApp.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangwan
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}