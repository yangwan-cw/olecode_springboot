package com.ioomex.olecodeApp.judge.codesandbox.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import co.elastic.clients.elasticsearch.nodes.Http;
import com.ioomex.olecodeApp.common.ErrorCode;
import com.ioomex.olecodeApp.enums.JudgeInfoMessageEnum;
import com.ioomex.olecodeApp.enums.QuestionSubmitStatusEnum;
import com.ioomex.olecodeApp.exception.BusinessException;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandbox;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeRequest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.JudgeInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
@Slf4j
public class RemoteCodeSandbox implements CodeSandbox {
    private final static String URL = "http://localhost:9000/api/code/execute";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("远程代码沙箱");
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String body = HttpUtil.createPost(URL)
          .body(json)
          .execute()
          .body();

        if (StrUtil.isEmpty(body)) {
            throw new BusinessException(ErrorCode.REMOTE_ERROR, "远程服务异常");
        }
        ExecuteCodeResponse bean = JSONUtil.toBean(body, ExecuteCodeResponse.class);

        return bean;
    }
}
