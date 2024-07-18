package com.ioomex.olecodeApp.judge.strategy;

import com.ioomex.olecodeApp.model.dto.problem.JudgeCase;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.JudgeInfo;
import com.ioomex.olecodeApp.model.entity.Question;

import com.ioomex.olecodeApp.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
