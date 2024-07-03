package com.ioomex.olecodeApp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志表
 */
@ApiModel(description = "操作日志表")
@Data
@TableName(value = "sys_operation_log")
public class SysOperationLog {
    /**
     * id, 主键
     */
    @ApiModelProperty(value = "id, 主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 生成的唯一id
     */
    @TableField(value = "onlyId")
    @ApiModelProperty(value = "生成的唯一id")
    private String onlyid;

    /**
     * 操作日志
     */
    @TableField(value = "log")
    @ApiModelProperty(value = "操作日志")
    private String log;

    /**
     * api地址
     */
    @TableField(value = "api_address")
    @ApiModelProperty(value = "api地址")
    private String apiAddress;

    /**
     * 包路径
     */
    @TableField(value = "package_path")
    @ApiModelProperty(value = "包路径")
    private String packagePath;

    /**
     * 状态, 0: 异常, 1: 正常
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态, 0: 异常, 1: 正常")
    private Short status;

    /**
     * 请求参数json
     */
    @TableField(value = "param_json")
    @ApiModelProperty(value = "请求参数json")
    private String paramJson;

    /**
     * 响应结果json
     */
    @TableField(value = "result_json")
    @ApiModelProperty(value = "响应结果json")
    private String resultJson;

    /**
     * 错误信息(status=0时, 记录错误信息)
     */
    @TableField(value = "error_msg")
    @ApiModelProperty(value = "错误信息(status=0时, 记录错误信息)")
    private String errorMsg;

    /**
     * 耗时 (毫秒)
     */
    @TableField(value = "cost_time")
    @ApiModelProperty(value = "耗时 (毫秒)")
    private String costTime;

    /**
     * 操作ip地址
     */
    @TableField(value = "oper_ip")
    @ApiModelProperty(value = "操作ip地址")
    private String operIp;

    /**
     * 操作ip地址归属地
     */
    @TableField(value = "oper_ip_address")
    @ApiModelProperty(value = "操作ip地址归属地")
    private String operIpAddress;

    /**
     * 操作人用户名: 目前没有做 rdbc 暂时不需要
     */
    @TableField(value = "oper_user_name")
    @ApiModelProperty(value = "操作人用户名: 目前没有做 rdbc 暂时不需要")
    private String operUserName;

    /**
     * 操作时间
     */
    @TableField(value = "oper_time")
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operTime;
}