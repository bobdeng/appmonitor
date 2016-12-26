package io.github.bobdeng.appmonitor.utils;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zhiguodeng on 2016/12/9.
 * 一个多线程写、单线程读的Buffer
 */
public interface ObjectBuffer<T> {

    List<T> readBufferData();
    void putBufferData(T data);
    Stream<T> stream();
}
