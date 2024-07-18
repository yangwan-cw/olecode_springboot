package com.ioomex.olecodeApp.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ExecuteCodeReQuest
 *
 * @author sutton
 * @since 2024-07-17 23:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeReQuest {


    // 输入用例
    private List<String> inputList;

    // 代码
    private String code;

    // 语言
    private String language;


    // TODO: timeLimit 代码执行时间限制


}