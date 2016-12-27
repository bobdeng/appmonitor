package io.github.bobdeng;

import io.github.bobdeng.appmonitor.dto.MonitorItem;
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
        cal.set(Calendar.YEAR,2000);
        cal.set(Calendar.MONTH,0);
        cal.set(Calendar.DAY_OF_MONTH,1);
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
        assertEquals(item.getLastMonitorData().get(0).getAverage(),300);
        assertEquals(item.getLastMonitorData().get(0).getTime(),"2000-01-01 10:00");
        cal.set(Calendar.MINUTE,4);
        item.addNewData(100,cal.getTimeInMillis());
        item.addNewData(100,cal.getTimeInMillis());
        assertEquals(item.getLastMonitorData().size(),1);
        cal.set(Calendar.MINUTE,5);
        item.addNewData(100,cal.getTimeInMillis());
        item.addNewData(100,cal.getTimeInMillis());
        assertEquals(item.getLastMonitorData().size(),2);
        assertEquals(item.getLastMonitorData().get(1).getTime(),"2000-01-01 10:05");
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
