package io.github.bobdeng.restmonitor.bean;

import io.github.bobdeng.restmonitor.dto.MonitorItem;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
public interface Monitor {
    Object newInvoke(ProceedingJoinPoint proceedingJoinPoint)throws Throwable;

    void runLoop();

    List<MonitorItem> getMonitorItems();
}
