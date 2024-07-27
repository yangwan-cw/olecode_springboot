package com.ioomex.olecodeApp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ioomex.olecodeApp.common.BaseResponse;
import com.ioomex.olecodeApp.common.ErrorCode;
import com.ioomex.olecodeApp.common.ResultUtils;
import com.ioomex.olecodeApp.constant.CommonConstant;
import com.ioomex.olecodeApp.exception.BusinessException;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.QuestionSubmitAddRequest;
import com.ioomex.olecodeApp.model.dto.problem.problemSubmit.QuestionSubmitQueryRequest;
import com.ioomex.olecodeApp.model.entity.QuestionSubmit;
import com.ioomex.olecodeApp.model.entity.SysUser;
import com.ioomex.olecodeApp.model.vo.QuestionSubmitVO;
import com.ioomex.olecodeApp.service.QuestionSubmitService;
import com.ioomex.olecodeApp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/question_submit")
@Slf4j
@Api(tags = "问题提交管理")
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 提交题目
     */
    @PostMapping("/data")
    @ApiOperation(value = "提交题目", notes = "提交题目")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        final SysUser loginUser = userService.getLoginUser(request);

        submitThrottling(loginUser);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    private void submitThrottling(SysUser loginUser) {
        // 限流： 你的提交过于频繁，请稍候重试。
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        String key = "submit_rate_limit:" + loginUser.getId();
        if (valueOps.get(key) != null) {
            throw new BusinessException(ErrorCode.RATE_LIMIT_ERROR, "你的提交过于频繁，请稍候重试");
        }
        valueOps.set(key, "1", 10, TimeUnit.SECONDS);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    @ApiOperation(value = "分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）", notes = "分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
          questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final SysUser loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }


}
