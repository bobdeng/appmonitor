package io.github.bobdeng.restmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Data
@Builder
@AllArgsConstructor
public class MonitorBase {
    private String methodName;
    private String className;
    private long useTime;

    public String getKey(){
        return String.format("%s-%s",className,methodName);
    }
}
