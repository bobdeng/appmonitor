package io.github.bobdeng.appmonitor.bean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhiguodeng on 2016/12/30.
 */
@Aspect
@Component
public class MonitorAop {
    @Autowired
    Monitor monitor;
    @Around("@annotation(io.github.bobdeng.appmonitor.annotation.MonitorMethod)")
    public Object whenMethodCall(ProceedingJoinPoint joinPoint){
        try {
            return monitor.newInvoke(joinPoint);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
