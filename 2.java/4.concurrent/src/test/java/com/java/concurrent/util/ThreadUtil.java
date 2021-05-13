package com.java.concurrent.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.java.concurrent.priormaxpool.EagerThreadPoolExecutor;
import com.java.concurrent.priormaxpool.TaskQueue;

import java.util.concurrent.*;

/**
 * @Author: railgun
 * 2021/5/12 14:07
 * PS: 线程工具类
 **/
public class ThreadUtil {

    /**
     * railgun
     * 2021/5/12 14:09
     * PS: 工具类禁用外部 new 方式创建对象
     **/
    private ThreadUtil() {
    }

    /**
     * 2021/5/12 14:12 @railgun 长期驻留的线程数目
     **/
    private static final int CORE_POOL_SIZE = 2;

    /**
     * 2021/5/12 14:12 @railgun 能够创建的最大线程数，任务数量超出该值时，执行线程池中设定好的饱和策略
     **/
    private static final int MAXIMUM_POOL_SIZE = 6;

    /**
     * 2021/5/12 14:13 @railgun keepAliveTime，TimeUnit unit【两个一起指定了额外的线程能够闲置多久】
     * 非核心线程的控制；
     * 调用 allowCoreThreadTimeOut(true) 方法后，核心线程也会受到控制
     **/
    private static final long KEEP_ALIVE_TIME = 1;

    /**
     * 2021/5/12 14:14 @railgun workQueue 必须是 BlockingQueue
     * ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue
     **/
    private static final ArrayBlockingQueue<Runnable> QUEUE = new ArrayBlockingQueue<>(4);

    /**
     * 2021/5/12 14:14 @railgun threadFactory，定义了线程任务的名称
     **/
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();

    /**
     * 2021/5/12 15:13 @railgun 线程池饱和策略
     **/
    private static final RejectedExecutionHandler DEFAULT_HANDLER = new ThreadPoolExecutor.AbortPolicy();

    /**
     * railgun
     * 2021/5/12 14:18
     * PS: 创建默认的自定义线程池【2、4、4】
     **/
    public static ThreadPoolExecutor getDefaultThreadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, QUEUE, THREAD_FACTORY, DEFAULT_HANDLER);
        System.out.println("");
        return threadPool;
    }

    /**
     * railgun
     * 2021/5/13 16:31
     * PS: 创建一个允许核心线程被回收的线程池
     **/
    public static ThreadPoolExecutor getThreadPoolAllowCoreThreadTimeOut() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, QUEUE, THREAD_FACTORY, DEFAULT_HANDLER);
        threadPool.allowCoreThreadTimeOut(true);
        return threadPool;
    }

    /**
     * railgun
     * 2021/5/12 21:04
     * PS: 创建优先 maxPool 的线程池
     **/
    public static ThreadPoolExecutor getPriorMaxPoolThreadPool() {
        TaskQueue<Runnable> queue = new TaskQueue<>(4);
        EagerThreadPoolExecutor priorMaxPoolThreadPool = new EagerThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue, THREAD_FACTORY, DEFAULT_HANDLER);
        queue.setExecutor(priorMaxPoolThreadPool);
        System.out.println("");
        return priorMaxPoolThreadPool;
    }

    /**
     * railgun
     * 2021/5/12 22:12
     * PS: 创建一个能够获取子线程异常的线程池【通过重写 uncaughtException 方法】
     **/
    public static ThreadPoolExecutor getCatchChildrenThreadExceptionThreadPoolByOverrideUncaughtException() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setUncaughtExceptionHandler(new CustomClassToGetException()).build();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, QUEUE, threadFactory, DEFAULT_HANDLER);
        System.out.println("");
        return threadPool;
    }

    /**
     * @Author: railgun
     * 2021/5/12 22:06
     * PS: 自定义线程池异常接收类，并且重写 uncaughtException 方法
     **/
    private static class CustomClassToGetException implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("【uncaughtException】threadId = " + t.getId() + ", threadName = " + t.getName() + ", ex = " + e.getMessage());
        }
    }

    /**
     * railgun
     * 2021/5/12 22:12
     * PS: 创建一个能够获取子线程异常的线程池【通过重写 afterExecute 方法】
     **/
    public static ThreadPoolExecutor getCatchChildrenThreadExceptionThreadPoolByOverrideAfterExecute() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutorOverrideAfterExecute(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, QUEUE, THREAD_FACTORY, DEFAULT_HANDLER);
        System.out.println("");
        return threadPool;
    }

    /**
     * railgun
     * 2021/5/13 11:16
     * PS: 自定义线程池重写 afterExecute 方法
     **/
    private static class ThreadPoolExecutorOverrideAfterExecute extends ThreadPoolExecutor {

        /**
         * railgun
         * 2021/5/13 11:15
         * PS: 线程池构造方法
         **/
        public ThreadPoolExecutorOverrideAfterExecute(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ArrayBlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        /**
         * railgun
         * 2021/5/13 11:15
         * PS: 重写 afterExecute 方法获取异常
         **/
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            Thread thread = Thread.currentThread();
            System.out.println("【afterExecute】threadId = " + thread.getId() + ", threadName = " + thread.getName() + ", ex = " + t.getMessage());
        }

    }

}
