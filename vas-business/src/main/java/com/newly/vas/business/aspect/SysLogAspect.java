package com.newly.vas.business.aspect;

import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.login.bean.SysLogBO;
import com.newly.vas.business.user.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


@Aspect // 使用@Aspect注解声明一个切面
@Component
public class SysLogAspect {

    @Autowired
    private UserService userService;

    /**
     * 这里我们使用注解的形式 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method 切点表达式:
     * execution(...)
     */
    @Pointcut("@annotation(com.newly.vas.business.annotation.Log)")
    public void logPointCut() {

    }

    /**
     * 环绕通知 @Around ， 当然也可以使用 @Before (前置通知) @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String userName = (String) request.getSession().getAttribute(CommonConstants.USER_ACCOUNT);
        String sessionId=sra.getSessionId();
//        getIpAddress(request);
        saveLog(point, userName,sessionId);
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint,  String userName,String sessionId) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogBO sysLogBO = new SysLogBO();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysLogBO.setCreateDate(dateFormat.format(new Date()));
        Log sysLog = method.getAnnotation(Log.class);
        if (sysLog != null) {
            // 注解上的描述
            sysLogBO.setRemark(sysLog.value());
        }
        sysLogBO.setUserName(userName);
        // 请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        // 请求的参数
//        Object[] args = joinPoint.getArgs();
//        try {
//            List<String> list = new ArrayList<String>();
//            for (Object o : args) {
//                list.add(new Gson().toJson(o));
//            }
//            sysLogBO.setParams(list.toString());
//        } catch (Exception e) {
//        }
        sysLogBO.setSessionId(sessionId);
        userService.saveLog(sysLogBO);
    }

    private  String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
