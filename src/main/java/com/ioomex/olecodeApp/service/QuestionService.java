package com.ioomex.olecodeApp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ioomex.olecodeApp.model.dto.problem.QuestionQueryRequest;
import com.ioomex.olecodeApp.model.entity.Question;
import com.ioomex.olecodeApp.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

public interface QuestionService extends IService<Question>{

    void validQuestion(Question question, boolean add);


    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);


    QuestionVO getQuestionVO(Question question, HttpServletRequest request);


    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

}
