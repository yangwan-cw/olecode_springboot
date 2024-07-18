package com.ioomex.olecodeApp.judge;

import com.ioomex.olecodeApp.enums.QuestionSubmitLanguageEnum;

import com.ioomex.olecodeApp.judge.codesandbox.CodeSandbox;
import com.ioomex.olecodeApp.judge.codesandbox.impl.ExampleCodeSandbox;

import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeRequest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CodeSandBoxTest {


    @Test
    void executeCode() {
        CodeSandbox codeSandBox = new ExampleCodeSandbox();
        String code = "init main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest build = ExecuteCodeRequest.builder()
          .code(code)
          .language(language)
          .inputList(inputList)
          .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(build);
        Assertions.assertNotNull(executeCodeResponse);
    }
}