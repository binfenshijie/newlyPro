package com.newly.common.boot.operationLog.dto;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bingo on 2020/4/23 下午3:09
 */
public class OperationLogDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 操作人
   */
  private String operator;

  /**
   * 操作ip
   */
  private String ip;

  /**
   * 操作人部门id
   */
  private String deptId;

  /**
   * 应用编码
   */
  private String appCode;

  /**
   * 应用内部代号
   */
  private String serviceName;

  /**
   * 操作模块
   */
  private String actModule;

  /**
   * url
   */
  private String url;

  /**
   * 操作结果 String 1：成功 String 0：失败'
   */
  private Integer result;

  /**
   * 操作内容
   */
  private String content;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 执行时间
   */
  private Long executeTime;


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getAppCode() {
    return appCode;
  }

  public void setAppCode(String appCode) {
    this.appCode = appCode;
  }

  public String getActModule() {
    return actModule;
  }

  public void setActModule(String actModule) {
    this.actModule = actModule;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getResult() {
    return result;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Long getExecuteTime() {
    return executeTime;
  }

  public void setExecuteTime(Long executeTime) {
    this.executeTime = executeTime;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public boolean valid() {
    if (this.actModule == null || this.appCode == null
            || this.result == null || StringUtils.isEmpty(this.content) ) {
      return false;
    }
    return true;
  }
}
