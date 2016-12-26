package io.github.bobdeng.appmonitor.bean.impl;

import io.github.bobdeng.appmonitor.bean.TimeUtils;
import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Component
public class TimeUtilsImpl implements TimeUtils {
    @Override
    public long now() {
        return Clock.systemUTC().millis();
    }
}
