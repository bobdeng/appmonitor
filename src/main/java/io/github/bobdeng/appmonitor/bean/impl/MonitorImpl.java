package io.github.bobdeng.appmonitor.bean.impl;

import io.github.bobdeng.appmonitor.bean.Monitor;
import io.github.bobdeng.appmonitor.dto.MonitorBase;
import io.github.bobdeng.appmonitor.dto.MonitorItem;
import io.github.bobdeng.appmonitor.bean.TimeUtils;
import io.github.bobdeng.appmonitor.utils.NolockObjectBuffer;
import io.github.bobdeng.appmonitor.utils.ObjectBuffer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Component
public class MonitorImpl implements Monitor {
    Map<String,MonitorItem> items=new ConcurrentHashMap();
    ObjectBuffer<MonitorBase> objectBuffer;

    @Autowired
    TimeUtils timeUtils;

    public void setTimeUtils(TimeUtils timeUtils) {
        this.timeUtils = timeUtils;
    }

    @PostConstruct
    public void init(){
        objectBuffer=new NolockObjectBuffer<>(14,MonitorBase.class);
        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(this::runLoop,1,1, TimeUnit.SECONDS);
    }
    @Override
    public Object newInvoke(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
        long start=System.currentTimeMillis();
        try{
            return proceedingJoinPoint.proceed();
        }catch (Throwable e){
            throw  e;
        }finally {
            long usedTime=System.currentTimeMillis()-start;
            objectBuffer.putBufferData(MonitorBase.builder()
                .className(proceedingJoinPoint.getTarget().getClass().getName())
                .methodName(proceedingJoinPoint.getSignature().toString())
                .useTime(usedTime)
                .build());
        }

    }

    @Override
    public void runLoop() {
        try {

            objectBuffer.readBufferData().stream()
                    .collect(Collectors.groupingBy(data->data.getKey()))
                    .entrySet()
                    .stream()
                    .forEach(entry -> {
                        updateMonitorItem(entry.getKey(), entry.getValue());
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateMonitorItem(String key, List<MonitorBase> values) {
        MonitorItem item=items.computeIfAbsent(key, s -> MonitorItem.builder()
                .className(values.get(0).getClassName())
                .methodName(values.get(0).getMethodName())
                .build());
        values.stream()
                .forEach(value -> {
                    item.addNewData(value.getUseTime(),timeUtils.now());
                });
    }

    @Override
    public List<MonitorItem> getMonitorItems() {
        return items.values()
                .stream()
                .collect(Collectors.toList());
    }
}
