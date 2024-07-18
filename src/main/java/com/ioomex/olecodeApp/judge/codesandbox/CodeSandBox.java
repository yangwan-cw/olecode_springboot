package com.ioomex.olecodeApp.judge.codesandbox;

import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeReQuest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * CodeSandBox
 *
 * @author sutton
 * @since 2024-07-17 23:53
 */
public interface CodeSandBox {

    /**
     * 代码机
     *
     * @return 返回执行的代码执行结果
     */
    ExecuteCodeResponse executeCodeMachine(ExecuteCodeReQuest param);
}