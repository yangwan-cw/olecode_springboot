package com.ioomex.olecodeApp.judge.codesandbox.model;

import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ExecuteCodeResponse
 *
 * @author sutton
 * @since 2024-07-18 00:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    // 执行的状态
    private Integer status;

    // 执行的信息
    private JudgeInfo judgeInfo;

    // 输出用例
    private List<String> outputList;

    // 输出信息
    private String message;

}