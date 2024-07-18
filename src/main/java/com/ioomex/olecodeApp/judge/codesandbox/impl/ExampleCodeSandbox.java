package com.ioomex.olecodeApp.judge.codesandbox.impl;

import com.ioomex.olecodeApp.enums.JudgeInfoMessageEnum;
import com.ioomex.olecodeApp.enums.QuestionSubmitStatusEnum;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandBox;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeReQuest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.JudgeInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * ExampleCodeSandbox
 *
 * @author sutton
 * @since 2024-07-18 00:03
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandBox {
    /**
     * 代码机
     *
     * @param param 需要执行的代码
     * @return 返回执行的代码执行结果
     */
    @Override
    public ExecuteCodeResponse executeCodeMachine(ExecuteCodeReQuest param) {
        List<String> inputList = param.getInputList();
        String code = param.getCode();
        String language = param.getLanguage();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}