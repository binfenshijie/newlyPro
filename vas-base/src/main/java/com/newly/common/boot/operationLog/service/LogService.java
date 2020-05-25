package com.newly.common.boot.operationLog.service;


import com.newly.common.boot.operationLog.dto.OperationLogDTO;

/**
 * Created by bingo on 2020/4/23 下午3:07
 */
public interface LogService {

  /**
   * 日志拦截器interceptor执行完毕后执行保存日志动作
   * @param operationLogDTO 待写入的日志对象
   */
  void saveOperationLog(OperationLogDTO operationLogDTO);

}
