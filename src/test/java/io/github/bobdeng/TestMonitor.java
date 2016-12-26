package io.github.bobdeng;

import io.github.bobdeng.restmonitor.bean.Monitor;
import io.github.bobdeng.restmonitor.bean.impl.MonitorImpl;
import io.github.bobdeng.restmonitor.dto.MonitorItem;
import io.github.bobdeng.restmonitor.bean.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by zhiguodeng on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("io.github.bobdeng")
@EnableAspectJAutoProxy
public class TestMonitor {
    @Autowired
    Monitor monitor;
    @Autowired
    TestAction testAction;
    @Autowired
    TestAop testAop;
    @Test
    public void testAop(){
        monitor = Mockito.mock(Monitor.class);
        testAop.setMonitor(monitor);
        testAction.doAction();
        try {
            verify(monitor,times(1)).newInvoke(any());
        } catch (Throwable throwable) {
            assertTrue(false);
        }
    }
    @Test
    public void testNewMission(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR,10);
        cal.set(Calendar.MINUTE,0);
        TimeUtils timeUtils=mock(TimeUtils.class);
        when(timeUtils.now()).thenReturn(cal.getTimeInMillis());
        ((MonitorImpl)monitor).setTimeUtils(timeUtils);
        testAop.setMonitor(monitor);
        testAction.doAction();
        testAction.doAction();
        testAction.doAction();
        testAction.doAction();
        monitor.runLoop();
        List<MonitorItem> itemList=monitor.getMonitorItems();
        assertEquals(itemList.size(),1);
        assertEquals(itemList.get(0).getLastMonitorData().get(0).getTimes(),4);

    }
}
