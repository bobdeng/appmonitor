package io.github.bobdeng.appmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhiguodeng on 2016/12/7.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {
    private int code;
    private String result;
    private T data;

}
