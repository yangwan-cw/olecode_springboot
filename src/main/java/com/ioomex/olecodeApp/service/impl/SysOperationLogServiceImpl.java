package com.ioomex.olecodeApp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ioomex.olecodeApp.model.entity.SysOperationLog;
import com.ioomex.olecodeApp.mapper.SysOperationLogMapper;
import com.ioomex.olecodeApp.service.SysOperationLogService;



@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements SysOperationLogService{

}
