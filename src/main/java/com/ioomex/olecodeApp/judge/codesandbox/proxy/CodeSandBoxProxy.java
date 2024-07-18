package com.ioomex.olecodeApp.judge.codesandbox.proxy;

import com.ioomex.olecodeApp.judge.codesandbox.CodeSandBox;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeReQuest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * CodeSandBoxProxy
 *
 * @author sutton
 * @since 2024-07-18 12:26
 */
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox {

    private CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    /**
     * 代码机
     *
     * @param param 参数
     * @return 返回执行的代码执行结果
     */
    @Override
    public ExecuteCodeResponse executeCodeMachine(ExecuteCodeReQuest param) {
        log.info("前置通知");
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCodeMachine(param);
        log.info("后置通知");
        return executeCodeResponse;
    }
}