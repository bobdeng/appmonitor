package io.github.bobdeng.appmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorItem {
    public static final int ONE_MINUTE = 60000;
    public static final int ONE_HOURE = 60;
    public static final int MONITOR_PERIOD = 5;
    private String className;
    private String methodName;
    private List<MonitorDataPerMinute> lastMonitorData;
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm");
    public void addNewData(long useTime, long nowTime) {
        long minute=(nowTime/ ONE_MINUTE % ONE_HOURE)/ MONITOR_PERIOD;
        MonitorDataPerMinute dataPerMinute=createIfNotExist(minute,nowTime);
        dataPerMinute.putNewData(useTime);
    }
    private MonitorDataPerMinute createIfNotExist(long minute,long nowTime){
        if(lastMonitorData==null) lastMonitorData=new ArrayList<>();
        if(lastMonitorData.size()==0){
            return newMonitorDataPerMinute(minute,nowTime);
        }
        else{
            MonitorDataPerMinute dataPerMinute=lastMonitorData.get(lastMonitorData.size()-1);
            if(dataPerMinute.getMinute() == minute){
                return dataPerMinute;
            }else{
                return newMonitorDataPerMinute(minute,nowTime);
            }
        }
    }

    private MonitorDataPerMinute newMonitorDataPerMinute(long minute,long nowTime) {
        MonitorDataPerMinute dataPerMinute=MonitorDataPerMinute.builder()
                .minute(minute)
                .min(Long.MAX_VALUE)
                .time(dateFormat.format(new Date(nowTime)))
                .build();
        lastMonitorData.add(dataPerMinute);
        if(lastMonitorData.size()>(ONE_HOURE/MONITOR_PERIOD)){
            lastMonitorData.remove(0);
        }
        return dataPerMinute;
    }

}
