package com.java.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.java.concurrent.util.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Author: railgun
 * 2021/4/15 17:36
 * PS:java 并发编程
 */
public class ConcurrentDemo {

    /**
     * railgun
     * 2021/5/11 10:56
     * PS: 启动线程三种方式
     **/
    @Test
    public void test1() {
        startThread1();
        startThread2();
        startThread3();
    }

    /**
     * railgun
     * 2021/5/11 10:57
     * PS: 继承 Thread 来启动一个线程
     **/
    public void startThread1() {
        $Thread thread = new $Thread();
        thread.start();
        thread.start();
    }

    private static class $Thread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：线程启动");
        }
    }

    /**
     * railgun
     * 2021/5/11 10:57
     * PS: 实现 Runnable 接口启动一个线程
     **/
    public void startThread2() {
        // 创建接口实现类对象
        $Runnable runnable = new $Runnable();
        // 作为 target 传入到 Thread 中
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static class $Runnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：线程启动");
        }
    }

    /**
     * railgun
     * 2021/5/11 10:57
     * PS: 实现 Callable 来启动线程
     **/
    public void startThread3() {
        // 创建接口实现类对象
        $Callable<String> callable = new $Callable<>();
        // 使用 FutureTask 包装
        FutureTask<String> futureTask = new FutureTask<>(callable);
        // 作为 target 传入 Thread 中
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class $Callable<T> implements Callable<T> {
        @Override
        public T call() {
            String result = Thread.currentThread().getName() + "：线程启动";
            System.out.println(result);
            return (T) (Thread.currentThread().getName() + "：执行完成的返回值");
        }
    }

    @Test
    public void test2() {
        createThreadPool1();
        createThreadPool2();
        createThreadPool3();
        createThreadPool4();
        createThreadPool5();
        createThreadPool6();
    }

    private static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    private void addSomethingToList() {
        System.out.println(Thread.currentThread().getName() + "：" + list.size());
        int[] nums = new int[]{2, 7, 9, 3, 1};
        list.add(rob(nums));
    }

    /**
     * railgun
     * 2021/5/11 21:21
     * PS: 打家劫舍
     **/
    private static int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[n];
    }

    /**
     * railgun
     * 2021/5/11 11:32
     * PS: 用来处理大量短时间工作流
     * 特点：
     * * 1、试图缓存线程并重用，当无缓存线程可用时，就会创建新的线程，如果线程闲置的时间超过60秒，则会被终止并移除缓存
     * * 2、长时间使用这种线程池不会损耗什么资源，其内部使用SynchronousQueue作为工作队列
     **/
    private static void createThreadPool1() {
        // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 以下为简单的使用方法
        // execute 为 ConcurrentDemo 的静态方法，没有返回值
        cachedThreadPool.execute(() -> {
            execute(0);
        });
        // submit 为 ConcurrentDemo 的静态方法，返回值为 String
        Future<String> future = cachedThreadPool.submit(ConcurrentDemo::submit);
        cachedThreadPool.shutdown();
        try {
            System.out.println("获取到 submit 执行的返回值为：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * railgun
     * 2021/5/11 11:36
     * PS: 重用指定数目的线程，使用无界的工作队列，任何时候最多有 nThreads 个工作线程是活动的
     * * 意味着，如果任务数量超过活动队列数目，将在工作队列中等待空闲线程出现，如果有工作线程退出，将会有新的工作线程被创建
     * * 以补足指定的线程数目nThreads
     **/
    private void createThreadPool2() {
        // new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    }

    /**
     * railgun
     * 2021/5/11 11:37
     * PS: 可以进行定制或者是周期性的工作调度，工作线程数目被限制为1，所以它保证了，所有任务都是被顺序执行的
     * * 最多只有一个任务处于活动状态，并且不允许使用者改动线程池实例，因此可以避免其改变线程数目
     **/
    private void createThreadPool3() {
        // new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * railgun
     * 2021/5/11 11:40
     * PS:可以进行定制或者是周期性的工作调度，工作线程数目被限制为 corePoolSize 个
     * * 最多只有corePoolSize个任务处于活动状态，并且不允许使用者改动线程池实例，因此可以避免其改变线程数目
     **/
    private void createThreadPool4() {
        // new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new DelayedWorkQueue())
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(12);
    }

    /**
     * railgun
     * 2021/5/11 11:42
     * PS: 利用ForkJoinPool算法，并行的处理任务，不保证处理顺序
     **/
    private void createThreadPool5() {
        // new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
    }

    /**
     * railgun
     * 2021/5/11 11:43
     * PS: 手动创建线程池
     **/
    private void createThreadPool6() {
        // 长期驻留的线程数目【newFixedThreadPool 是 nThreads，newCachedThreadPool 是 0】
        int corePoolSize = 10;
        // maximumPoolSize 当线程数不够时，能够创建的最大线程数【 newFixedThreadPool 是 nThreads，newCachedThreadPool 是 Integer.MAX_VALUE】
        int maximumPoolSize = 100;
        // keepAliveTime，TimeUnit unit【两个一起指定了额外的线程能够闲置多久】
        long keepAliveTime = 1;
        // workQueue 必须是 BlockingQueue
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(30);
        // threadFactory，定义了线程任务的名称
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();
        // 拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor customThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, threadFactory, rejectedExecutionHandler);
    }

    /**
     * railgun
     * 2021/5/12 20:56
     * PS: 线程池添加任务的顺序
     * core ——> queue ——> maxPool
     **/
    public static void threadPoolExecutorOrder() {
        ThreadPoolExecutor threadPool = ThreadUtil.getDefaultThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                execute(finalI);
            });
        }
        threadPool.allowCoreThreadTimeOut(true);
    }

    /**
     * railgun
     * 2021/5/12 20:56
     * PS: 优先 maxPool 的线程池
     * 自定义类继承工作队列，重写 offer 方法
     **/
    public static void threadPoolExecutorPriorMaxPool() {
        ThreadPoolExecutor threadPool = ThreadUtil.getPriorMaxPoolThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                execute(finalI);
            });
        }
        threadPool.allowCoreThreadTimeOut(true);
    }

    /**
     * railgun
     * 2021/5/12 22:13
     * PS: 获取到子线程的异常
     **/
    public static void getChildrenThreadException() {
        ThreadPoolExecutor threadPool = ThreadUtil.getCatchChildrenThreadExceptionThreadPool();
        threadPool.execute(() -> {
            System.out.println("我要产生异常了");
            System.out.println(1 / 0);
        });
    }

    /**
     * railgun
     * 2021/5/12 20:53
     * PS: 如何监听所有线程执行结束
     **/
    public static void listenAllThreadEnd() {
        ThreadPoolExecutor threadPool = ThreadUtil.getDefaultThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                execute(finalI);
            });
        }
        threadPool.allowCoreThreadTimeOut(true);
        //threadPool.shutdown();
    }

    private static CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

    public static void main(String[] args) {
        //createThreadPool1();
        //listenAllThreadEnd();
        //threadPoolExecutorPriorMaxPool();
        getChildrenThreadException();
    }

    /**
     * railgun
     * 2021/5/12 20:50
     * PS: 线程池中 execute 调用的内容
     * 时停 10 秒，没有返回值
     **/
    private static void execute(int i) {
        String msg = Thread.currentThread().getName();
        System.out.println(msg + "：开始执行【execute】【任务排序为：" + i + "】");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(msg + "：执行结束【execute】【任务排序为：" + i + "】");
    }

    /**
     * railgun
     * 2021/5/12 20:51
     * PS: 线程池中 submit 执行的方法
     * 时停 10 秒，返回值为线程名称
     **/
    private static String submit() {
        String msg = Thread.currentThread().getName();
        System.out.println(msg + "：开始执行【submit】");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(msg + "：执行结束【submit】");
        return msg;
    }

}
