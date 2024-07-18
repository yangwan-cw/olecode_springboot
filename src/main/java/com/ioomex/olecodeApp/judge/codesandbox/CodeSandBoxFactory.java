package com.ioomex.olecodeApp.judge.codesandbox.factory;

import com.ioomex.olecodeApp.judge.codesandbox.CodeSandBox;
import com.ioomex.olecodeApp.judge.codesandbox.impl.ExampleCodeSandbox;
import com.ioomex.olecodeApp.judge.codesandbox.impl.RemoteCodeSandbox;
import com.ioomex.olecodeApp.judge.codesandbox.impl.ThirdPartyCodeSandbox;
import org.springframework.beans.factory.annotation.Value;

/**
 * CodeSandBoxFactory
 *
 * @author sutton
 * @since 2024-07-18 00:15
 */
public class CodeSandBoxFactory {

    @Value("${codesandbox.type}")
    private String type;

    /**
     * '
     * 静态工厂方法： 根据传递的类型返回对应的代码机
     *
     * @param type example,remote,thirdParty。默认执行：ExampleCodeSandbox
     * @return 返回代码机
     */
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }


    /**
     * '
     * 静态工厂方法： 根据传递的类型返回对应的代码机
     * 读取配置example,remote,thirdParty。默认执行：ExampleCodeSandbox
     *
     * @return 返回代码机
     */
    public CodeSandBox newInstance() {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }

}