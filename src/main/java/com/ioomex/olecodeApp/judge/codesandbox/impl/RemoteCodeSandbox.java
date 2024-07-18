package com.ioomex.olecodeApp.judge.codesandbox.impl;

import com.ioomex.olecodeApp.judge.codesandbox.CodeSandBox;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeReQuest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * ExampleCodeSandbox
 *
 * @author sutton
 * @since 2024-07-18 00:03
 */
@Slf4j
public class RemoteCodeSandbox implements CodeSandBox {
    /**
     * 代码机
     *
     * @param param 需要执行的代码
     * @return 返回执行的代码执行结果
     */
    @Override
    public ExecuteCodeResponse executeCodeMachine(ExecuteCodeReQuest param) {
        log.info("远程代码机");
        return null;
    }
}