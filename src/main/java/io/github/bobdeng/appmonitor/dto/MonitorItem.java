package io.github.bobdeng.appmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;
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
    public static final int FIVE_MINUTE = 5*60000;
    public static final int ONE_HOURE = 60;
    private String className;
    private String methodName;
    private List<MonitorDataPerMinute> lastMonitorData;
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm");
    public void addNewData(long useTime, long nowTime) {
        String timeWith5Minute= dateFormat.format(new Date(nowTime/ FIVE_MINUTE * FIVE_MINUTE));
        MonitorDataPerMinute dataPerMinute=createIfNotExist(timeWith5Minute);
        dataPerMinute.putNewData(useTime);
    }
    private MonitorDataPerMinute createIfNotExist(String nowTime){
        if(lastMonitorData==null) lastMonitorData=new ArrayList<>();
        if(lastMonitorData.size()==0){
            return newMonitorDataPerMinute(nowTime);
        }
        else{
            MonitorDataPerMinute dataPerMinute=lastMonitorData.get(lastMonitorData.size()-1);
            if(dataPerMinute.getTime().equals(nowTime)){
                return dataPerMinute;
            }else{
                return newMonitorDataPerMinute(nowTime);
            }
        }
    }

    private MonitorDataPerMinute newMonitorDataPerMinute(String nowTime) {
        MonitorDataPerMinute dataPerMinute=MonitorDataPerMinute.builder()
                .min(Long.MAX_VALUE)
                .time(nowTime)
                .build();
        lastMonitorData.add(dataPerMinute);
        if(lastMonitorData.size()>(ONE_HOURE/5)){
            lastMonitorData.remove(0);
        }
        return dataPerMinute;
    }

}
