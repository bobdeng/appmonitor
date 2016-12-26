package io.github.bobdeng.restmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDataPerMinute {
    private long minute;
    private long max;
    private long min;
    private long total;
    private int times;

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
