package io.github.bobdeng;

import io.github.bobdeng.restmonitor.bean.Monitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Aspect
@Component
public class MonitorAop {
    @Autowired
    Monitor monitor;

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Around("execution(* io.github.bobdeng.restmonitor.controler.*.*(..))")
    public Object runMonitor(ProceedingJoinPoint joinPoint){
        try {
            return monitor.newInvoke(joinPoint);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
