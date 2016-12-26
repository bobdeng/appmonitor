package io.github.bobdeng.restmonitor.controler;

import io.github.bobdeng.restmonitor.bean.Monitor;
import io.github.bobdeng.restmonitor.dto.MonitorItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@RestController
public class MonitorControler {
    @Autowired
    Monitor monitor;
    @RequestMapping(value = "/monitor/list",method = RequestMethod.GET)
    public List<MonitorItem> allItems(){
        return monitor.getMonitorItems();
    }

}
