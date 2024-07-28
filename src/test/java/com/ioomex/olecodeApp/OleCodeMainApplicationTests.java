package com.ioomex.olecodeApp;

import com.ioomex.olecodeApp.enums.QuestionSubmitLanguageEnum;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandbox;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandboxFactory;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandboxProxy;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeRequest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 主类测试12
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@SpringBootTest
class OleCodeMainApplicationTests {


    @Value("${codesandbox.type}")
    private String type;

    @Test
    void contextLoads() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "public class Main {\n" +
          "    public static void main(String[] args) {\n" +
          "        int a = Integer.parseInt(args[0]);\n" +
          "        int b = Integer.parseInt(args[1]);\n" +
          "        System.out.println((a + b));\n" +
          "    }\n" +
          "}";

        // 测试 jenkins
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
          .code(code)
          .language(language)
          .inputList(inputList)
          .build();

        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
        System.out.println(executeCodeResponse);


    }

}
