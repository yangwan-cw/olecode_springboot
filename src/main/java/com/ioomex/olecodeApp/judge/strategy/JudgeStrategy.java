package com.ioomex.olecodeApp.judge.strategy;


import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
