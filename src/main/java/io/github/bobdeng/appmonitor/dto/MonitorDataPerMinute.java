package io.github.bobdeng.appmonitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private long minute;
    private long max;
    private long min;
    @JsonIgnore
    private long total;
    private int times;
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
