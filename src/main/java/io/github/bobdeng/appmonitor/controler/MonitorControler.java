package io.github.bobdeng.appmonitor.controler;

import io.github.bobdeng.appmonitor.bean.Monitor;
import io.github.bobdeng.appmonitor.dto.MonitorItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@RestController
public class MonitorControler {
    @Autowired
    Monitor monitor;
    @RequestMapping(value = "/monitor/list",method = RequestMethod.GET)
    public List<MonitorItem> allItems(@RequestParam(required = false) String className
            , @RequestParam(required = false) String methodName){
        List<MonitorItem> result=monitor.getMonitorItems();
        return  result.stream()
                    .filter(monitor1 -> fitCondition(monitor1,className,methodName))
                    .collect(Collectors.toList());
    }

    private boolean fitCondition(MonitorItem item,String className, String methodName) {
        if(className !=null){
            if(!item.getClassName().contains(className)){
                return false;
            }
        }
        if(methodName!=null){
            if(!item.getMethodName().contains(methodName)){
                return false;
            }
        }
        return true;
    }

}
