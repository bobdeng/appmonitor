package io.github.bobdeng;

import io.github.bobdeng.restmonitor.dto.MonitorItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import static org.junit.Assert.*;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@RunWith(JUnit4.class)
public class TestMonitorItem {
    @Test
    public void testNewMonitorData(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR,10);
        cal.set(Calendar.MINUTE,0);
        MonitorItem item=new MonitorItem();
        item.addNewData(100,cal.getTimeInMillis());
        item.addNewData(300,cal.getTimeInMillis());
        item.addNewData(500,cal.getTimeInMillis());
        item.addNewData(300,cal.getTimeInMillis());
        assertEquals(item.getLastMonitorData().size(),1);
        assertEquals(item.getLastMonitorData().get(0).getMax(),500);
        assertEquals(item.getLastMonitorData().get(0).getMin(),100);
        assertEquals(item.getLastMonitorData().get(0).getTimes(),4);
        assertEquals(item.getLastMonitorData().get(0).getTotal(),1200);
        assertEquals(item.getLastMonitorData().get(0).getAverage(),300);
        assertEquals(item.getLastMonitorData().get(0).getMinute(),0l);
        cal.set(Calendar.MINUTE,1);
        item.addNewData(100,cal.getTimeInMillis());
        assertEquals(item.getLastMonitorData().size(),2);
        assertEquals(item.getLastMonitorData().get(1).getMinute(),1l);
    }
    @Test
    public void testNewMonitorDataNoMore60(){
        Calendar cal=Calendar.getInstance();
        MonitorItem item=new MonitorItem();
        for(int i=0;i<1000;i++){
            cal.add(Calendar.MINUTE,1);
            item.addNewData(100,cal.getTimeInMillis());
            item.addNewData(300,cal.getTimeInMillis());
            item.addNewData(500,cal.getTimeInMillis());
            assertTrue(item.getLastMonitorData().size()<=60);
        }
    }
}
