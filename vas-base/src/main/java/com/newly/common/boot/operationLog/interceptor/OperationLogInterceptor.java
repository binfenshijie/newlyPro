package com.newly.common.boot.operationLog.interceptor;

import com.newly.common.boot.operationLog.OperationLogIgnore;
import com.newly.common.boot.operationLog.dto.OperationLogDTO;
import com.newly.common.boot.operationLog.holder.OperationLogHolder;
import com.newly.common.boot.operationLog.service.LogService;
import com.newly.common.utils.IpAddressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.function.Supplier;

/**
 * Created by bingo on 2020/4/23 下午3:37
 */
public class OperationLogInterceptor extends HandlerInterceptorAdapter {

  private final String applicationName;

  private final String applicationCode;

  @Autowired
  private LogService logService;

  private Logger logger = LoggerFactory.getLogger(OperationLogInterceptor.class);

  public OperationLogInterceptor(String applicationName, String applicationCode) {
    this.applicationName = applicationName;
    this.applicationCode = applicationCode;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (shouldLog(handler)) {
      OperationLogHolder.getOperationLog().setCreateTime(new Date());
      return super.preHandle(request, response, handler);
    } else {
      return true;
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    if (shouldLog(handler)) {
      OperationLogDTO operationLogDTO = OperationLogHolder.getOperationLog();
      String userPrincipal = null;
      if (null != request.getUserPrincipal()) {
        userPrincipal = request.getUserPrincipal().getName();
      }
      if (!StringUtils.isEmpty(userPrincipal) && userPrincipal.contains("&&")) {
        String[] principals = userPrincipal.split("&&");
        if (principals.length > 1) {
          operationLogDTO.setOperator(principals[0]);
          operationLogDTO.setDeptId(principals[2]);
        }
      }
      operationLogDTO.setServiceName(applicationName);
      operationLogDTO.setAppCode(applicationCode);
      operationLogDTO.setIp(IpAddressUtil.getRemoteIpAddress(request));
      if (null != operationLogDTO && operationLogDTO.valid()) {
        save(operationLogDTO, () -> {
          if (request.getQueryString() != null) {
            return request.getRequestURL().toString() + "?" + request.getQueryString();
          }
          return request.getRequestURL().toString();
        });
      }
    }
    super.afterCompletion(request, response, handler, ex);
    OperationLogHolder.clear();
  }

  private boolean shouldLog(Object handler) {
    if (!(handler instanceof HandlerMethod)) {
      return false;
    }
    HandlerMethod method = (HandlerMethod) handler;
    // 获取类上的注解
    OperationLogIgnore classAnnotation =
        method.getMethod().getDeclaringClass().getAnnotation(OperationLogIgnore.class);
    // 获取方法上的注解
    OperationLogIgnore methodAnnotation = method.getMethodAnnotation(OperationLogIgnore.class);

    return null == classAnnotation && null == methodAnnotation;
  }

  private void save(OperationLogDTO operationLogDTO, Supplier<String> supplier) {
    // 获取操作的执行时间
    operationLogDTO.setExecuteTime(
        System.currentTimeMillis() - operationLogDTO.getCreateTime().getTime());

    // 如果必填信息没有填写，则拦截的URL以便定位日志来源模块
    if (StringUtils.isEmpty(operationLogDTO.getActModule())
        && StringUtils.isEmpty(operationLogDTO.getContent())) {
      operationLogDTO.setContent(supplier.get());
    }

    operationLogDTO.setUrl(supplier.get());
    logService.saveOperationLog(operationLogDTO);
  }

  @Override
  public void afterConcurrentHandlingStarted(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    super.afterConcurrentHandlingStarted(request, response, handler);
  }
}
