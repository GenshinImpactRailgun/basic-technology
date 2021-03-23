package com.java.basics.bio.fourth;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerPool {
    private ExecutorService executorService;

    public ServerPool(int max, int size) {
        executorService = new ThreadPoolExecutor(3, max, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(size));
    }
    
    /**
     * railgun
     * 2021/3/24 1:21
     * PS:提交任务给线程池
     */
    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
