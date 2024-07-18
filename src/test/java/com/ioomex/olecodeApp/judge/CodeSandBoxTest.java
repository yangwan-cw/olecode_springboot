package com.ioomex.olecodeApp.judge;

import com.ioomex.olecodeApp.enums.QuestionSubmitLanguageEnum;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandBox;
import com.ioomex.olecodeApp.judge.codesandbox.impl.ExampleCodeSandbox;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeReQuest;
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
        CodeSandBox codeSandBox = new ExampleCodeSandbox();
        String code = "init main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeReQuest build = ExecuteCodeReQuest.builder()
          .code(code)
          .language(language)
          .inputList(inputList)
          .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCodeMachine(build);
        Assertions.assertNotNull(executeCodeResponse);
    }
}