package com.newly.common.boot.operationLog.holder;


import com.newly.common.boot.operationLog.dto.OperationLogDTO;

/**
 * Created by bingo on 2020/4/23 下午3:010
 */
public class OperationLogHolder {

  private static final ThreadLocal<OperationLogDTO> operationLogDTOThreadLocal = new ThreadLocal<OperationLogDTO>(){
    @Override
    protected OperationLogDTO initialValue() {
      return new OperationLogDTO();
    }
  };

  public static OperationLogDTO getOperationLog() {
    return operationLogDTOThreadLocal.get();
  }

  public static void setOperationLog(final OperationLogDTO operationLogDTO) {
    operationLogDTOThreadLocal.set(operationLogDTO);
  }

  public static void clear() {
    operationLogDTOThreadLocal.remove();
  }
}
