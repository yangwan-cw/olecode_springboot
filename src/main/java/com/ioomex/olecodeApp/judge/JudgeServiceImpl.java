package com.ioomex.olecodeApp.judge;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import com.ioomex.olecodeApp.common.ErrorCode;
import com.ioomex.olecodeApp.enums.JudgeInfoMessageEnum;
import com.ioomex.olecodeApp.enums.QuestionSubmitStatusEnum;
import com.ioomex.olecodeApp.exception.BusinessException;
import com.ioomex.olecodeApp.judge.codesandbox.CodeSandBox;
import com.ioomex.olecodeApp.judge.codesandbox.factory.CodeSandBoxFactory;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeReQuest;
import com.ioomex.olecodeApp.judge.codesandbox.model.ExecuteCodeResponse;
import com.ioomex.olecodeApp.judge.codesandbox.proxy.CodeSandBoxProxy;
import com.ioomex.olecodeApp.judge.model.ExecuteResult;
import com.ioomex.olecodeApp.model.dto.problem.JudgeCase;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.JudgeInfo;
import com.ioomex.olecodeApp.model.entity.Question;
import com.ioomex.olecodeApp.model.entity.QuestionSubmit;
import com.ioomex.olecodeApp.service.QuestionService;
import com.ioomex.olecodeApp.service.QuestionSubmitService;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JudgeServiceImpl
 *
 * @author sutton
 * @since 2024-07-18 13:05
 */
@Slf4j
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionSubmitService questionSubmitService;


    @Resource
    private QuestionService questionService;


    @Value("${codesandbox.type}")
    private String type;

    @Override
    public ExecuteResult judge(Integer param) {
        // 根据传入的问题id，去获取到对应的题目，语言，代码等
        QuestionSubmit questionSubmit = questionSubmitService.getById(param);
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);

        // 校验系统级数据是否存在
        checkDataFinding(questionSubmit, question);

        questionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean updateStatus = questionSubmitService.updateById(questionSubmit);
        checkUpdate(updateStatus);


        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();

        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());


        // 根据类型获取代码机
        CodeSandBox codeSandBox = getCodeSandBox();
        // 构建请求
        ExecuteCodeReQuest build = ExecuteCodeReQuest.builder().code(code).language(language).inputList(inputList).build();
        // 获取执行结果
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCodeMachine(build);

        // 获取输出用例，
        List<String> outputList = executeCodeResponse.getOutputList();

        // TODO: 需要优化
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;

        // 判断代码机执行的是否和预期输出数量相等
        if (outputList.size() != judgeCaseList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            return null;
        }


        // 判断代码机返回的
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCaseData = judgeCaseList.get(i);
            if (!judgeCaseData.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                return null;
            }
        }

        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();

        String judgeConfig = question.getJudgeConfig();
        JudgeInfo judgeInfoBean = JSONUtil.toBean(judgeConfig, JudgeInfo.class);
        Long memoryLimit = judgeInfoBean.getMemory();
        Long timeLimit = judgeInfoBean.getTime();

        if (memory > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            return null;
        }

        if (time > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            return null;
        }

        return null;
    }

    /**
     * 校验是否更新成功
     *
     * @param updateStatus 更新的状态
     */
    private static void checkUpdate(boolean updateStatus) {
        if (!updateStatus) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目提交状态失败");
        }
    }


    /**
     * 判断系统级别数据是否存在
     *
     * @param questionSubmit 用户提交的数据
     * @param question       问题
     */
    private static void checkDataFinding(QuestionSubmit questionSubmit, Question question) {
        if (ObjUtil.isEmpty(questionSubmit)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目提交不存在");
        }

        if (ObjUtil.isEmpty(question)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目提交状态不正确");
        }
    }


    // 获取代码机
    private @NotNull CodeSandBox getCodeSandBox() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        return codeSandBox;
    }
}