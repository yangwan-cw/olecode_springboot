package com.ioomex.olecodeApp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.QuestionSubmitAddRequest;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.QuestionSubmitQueryRequest;
import com.ioomex.olecodeApp.model.entity.QuestionSubmit;
import com.ioomex.olecodeApp.model.entity.SysUser;
import com.ioomex.olecodeApp.model.vo.QuestionSubmitVO;

public interface QuestionSubmitService extends IService<QuestionSubmit>{

    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, SysUser loginUser);


    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, SysUser loginUser);


    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, SysUser loginUser);

}
