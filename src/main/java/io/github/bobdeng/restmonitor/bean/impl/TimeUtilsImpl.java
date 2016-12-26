package io.github.bobdeng.restmonitor.bean.impl;

import io.github.bobdeng.restmonitor.bean.TimeUtils;
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
