package com.ioomex.olecodeApp.judge;

import com.ioomex.olecodeApp.judge.model.ExecuteResult;

/**
 * JudgeService
 *
 * @author sutton
 * @since 2024-07-18 13:04
 */
public interface JudgeService {

    ExecuteResult judge(Integer questionId);
}