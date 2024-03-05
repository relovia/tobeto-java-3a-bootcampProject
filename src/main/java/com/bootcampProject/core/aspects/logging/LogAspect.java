package com.bootcampProject.core.aspects.logging;

import com.bootcampProject.core.crosscuttingconcerns.logging.LogParameter;
import com.bootcampProject.core.crosscuttingconcerns.logging.LoggerServiceBase;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {
    private LoggerServiceBase loggerServiceBase;
    private HttpServletRequest httpServletRequest;

    public LogAspect(LoggerServiceBase loggerServiceBase, HttpServletRequest httpServletRequest) {
        this.loggerServiceBase = loggerServiceBase;
        this.httpServletRequest = httpServletRequest;
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)"
    )
    public void springBeanPointcut() {
    }

    @Around("springBeanPointcut() && @annotation(Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        List<LogParameter> logParameterList = new ArrayList<>();
        logParameterList.add(new LogParameter(result));

        String username = getUsername();

        loggerServiceBase.log(methodName, logParameterList, username);
        return result;
    }

    public String getUsername() {
        if (httpServletRequest.getUserPrincipal() != null) {
            return httpServletRequest.getUserPrincipal().getName();
        }
        return "There is no such user...";
    }
}
