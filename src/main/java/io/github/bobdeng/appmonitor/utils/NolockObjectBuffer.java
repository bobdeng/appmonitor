package io.github.bobdeng.appmonitor.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Created by zhiguodeng on 2016/12/9.
 */
public class NolockObjectBuffer<T> implements ObjectBuffer<T> {

    private T[] buffer;
    private int remMask;
    private long readPointer=0;
    private AtomicLong writePointer=new AtomicLong(0);
    public NolockObjectBuffer(int bufferSizeTimes,Class<T> clz) {
        buffer = (T[])Array.newInstance(clz,1<<bufferSizeTimes);
        remMask = buffer.length-1;
    }

    @Override
    public List readBufferData() {
        long start=readPointer;
        long end=writePointer.get();
        while(end-start>buffer.length){
            start=end-buffer.length;
        }
        List<T> result=new ArrayList<T>((int)(end-start));
        for(long i=start;i<end;i++){
            result.add(buffer[getBufferPos(i)]);
        }
        readPointer=end;
        return result;
    }
    private int getBufferPos(long pointer){
        return (int)pointer&remMask;
    }

    @Override
    public void putBufferData(T data) {
        buffer[getBufferPos(writePointer.getAndIncrement())]=data;
    }

    @Override
    public Stream<T> stream() {
        long start=readPointer;
        long end=writePointer.get();
        List<T> result=new ArrayList<T>((int)(end-start));
        for(long i=start;i<end;i++){
            result.add(buffer[getBufferPos(i)]);
        }
        return result.stream();
    }
}
