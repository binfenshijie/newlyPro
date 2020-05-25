package com.newly.common.boot.operationLog;


import com.newly.common.boot.operationLog.dto.OperationLogDTO;
import com.newly.common.boot.operationLog.holder.OperationLogHolder;

/**
 * Created by bingo on 2020/4/23 下午3:28
 */
public class OperationLogBuilder {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private OperationLogDTO dto = OperationLogHolder.getOperationLog();

    public Builder operator(String operator) {
      dto.setOperator(operator);
      return this;
    }

    public Builder ip(String ip) {
      dto.setIp(ip);
      return this;
    }

    public Builder appCode(String appCode) {
      dto.setAppCode(appCode);
      return this;
    }

    public Builder menuCode(String menuCode) {
      dto.setActModule(menuCode);
      return this;
    }

    public Builder deptId(String userOrgId) {
      dto.setDeptId(userOrgId);
      return this;
    }


    public Builder result(Integer result) {
      dto.setResult(result);
      return this;
    }

    public Builder content(String actionDetail) {
      dto.setContent(actionDetail);
      return this;
    }

    public void build() {
      OperationLogHolder.setOperationLog(dto);
    }
  }
}
