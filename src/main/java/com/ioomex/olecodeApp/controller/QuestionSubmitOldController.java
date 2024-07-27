package com.ioomex.olecodeApp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ioomex.olecodeApp.common.BaseResponse;
import com.ioomex.olecodeApp.common.ErrorCode;
import com.ioomex.olecodeApp.common.ResultUtils;
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
@RequestMapping("/old/question_submit")
@Slf4j
@Api(tags = "问题提交管理")
public class QuestionSubmitOldController {




}
