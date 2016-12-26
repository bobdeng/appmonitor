package io.github.bobdeng.appmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorItem {
    private String className;
    private String methodName;
    private List<MonitorDataPerMinute> lastMonitorData;

    public void addNewData(long useTime, long nowTime) {
        long minute=(nowTime/60000 % 60)/5;
        MonitorDataPerMinute dataPerMinute=createIfNotExist(minute);
        dataPerMinute.putNewData(useTime);
    }
    private MonitorDataPerMinute createIfNotExist(long minute){
        if(lastMonitorData==null) lastMonitorData=new ArrayList<>();
        if(lastMonitorData.size()==0){
            return newMonitorDataPerMinute(minute);
        }
        else{
            MonitorDataPerMinute dataPerMinute=lastMonitorData.get(lastMonitorData.size()-1);
            if(dataPerMinute.getMinute() == minute){
                return dataPerMinute;
            }else{
                return newMonitorDataPerMinute(minute);
            }
        }
    }

    private MonitorDataPerMinute newMonitorDataPerMinute(long minute) {
        MonitorDataPerMinute dataPerMinute=MonitorDataPerMinute.builder()
                .minute(minute)
                .min(Long.MAX_VALUE)
                .build();
        lastMonitorData.add(dataPerMinute);
        if(lastMonitorData.size()>60){
            lastMonitorData.remove(0);
        }
        return dataPerMinute;
    }

}
