package io.github.bobdeng.appmonitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDataPerMinute {
    @Getter
    @Setter
    private long max;
    @Getter
    @Setter
    private long min;
    private long total;
    @Getter
    @Setter
    private int times;
    @Getter
    @Setter
    private String time;

    public void putNewData(long useTime) {
        times++;
        total+=useTime;
        if(useTime>max) max=useTime;
        if(useTime<min) min=useTime;
    }

    public long getAverage() {
        if(times>0)
        {
            return total/times;
        }else{
            return 0;
        }
    }
}
