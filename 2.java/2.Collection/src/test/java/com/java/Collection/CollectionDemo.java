package com.java.Collection;

import com.basic.comon.util.GsonUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: railgun
 * 2021/4/5 14:13
 * PS:集合
 */
public class CollectionDemo {

    /**
     * railgun
     * 2021/4/5 14:15
     * PS:java 常用集合
     */
    @Test
    public void test1() {
        // 画出 java 常用集合架构图
        System.out.println("Collection：List、Set、Queue");
        System.out.println("List：ArrayList、LinkedList、Vector");
        System.out.println("Set：HashSet、TreeSet");
        System.out.println("Queue：Deque");
        System.out.println("上述的里面会有复杂的 Abstract 在里面");
        System.out.println("Map：HashMap、Hashtable、TreeMap、WeakHashMap");
        System.out.println("上述会夹杂复杂的 Abstract 在里面");
    }

    /**
     * railgun
     * 2021/4/5 14:15
     * PS:Collection 和 Collections 区别
     */
    @Test
    public void test2() {
        System.out.println("Collection 是 java 集合接口，提供了对集合对象进行基本操作的通用接口方法。Collection 的意义是为各种具体的集合实现提供最大化的统一操作方式");
        System.out.println("Collections 是集合工具类，提供了一系列操作对象的静态方法，例如：排序、搜索、修改等操作");
    }

    /**
     * railgun
     * 2021/4/5 21:41
     * PS:List、Set、Map 区别
     */
    @Test
    public void test3() {
        System.out.println("List 有序、可重复。");
        System.out.println("Set 无序、不可重复。");
        System.out.println("List、Set 是属于 Collection 那一分支的，和 Map 是不同的分支。");
        System.out.println("Map 元素不可重复。");
    }

    /**
     * railgun
     * 2021/4/5 21:42
     * PS:HashMap、Hashtable 区别
     */
    @Test
    public void test4() {
        System.out.println("HashMap 去掉了 Hashtable 的 contains 方法，但是加上了 containsKey 和 containsValue 方法。");
        System.out.println("HashMap 非线程安全，Hashtable 线程安全。");
        System.out.println("HashMap 允许空键空值，Hashtable 不允许空键空值");
        System.out.println("Hashtable 都是加的 synchronized 锁，运行效率很低");
        Map hashMap = new HashMap();
        hashMap.put(null, null);
        Map hashtable = new Hashtable<>();
        // 如果放入 null 值，编译通过，但是执行报错
        hashtable.put("railgun", "railgun");
    }

    /**
     * railgun
     * 2021/4/5 21:53
     * PS:如何决定使用 HashMap 还是 TreeMap
     */
    @Test
    public void test5() {
        System.out.println("HashMap：适合在 Map 中插入、删除、和定位元素");
        System.out.println("TreeMap：适合以自然顺序，或者是自定义顺序去遍历键");
        System.out.println("HashMap 会比 TreeMap 快一点，哈希表和树的数据结构使然，哈希表的速度会比树结构快，因为哈希值定位快。多使用 HashMap，在有需要排序的时候使用 TreeMap");
        System.out.println("HashMap 非线程安全，TreeMap 非线程安全");
        System.out.println("HashMap 无序，TreeMap 有序");
    }

    /**
     * railgun
     * 2021/4/5 22:12
     * PS:简述 TreeMap
     */
    @Test
    public void test6() {
        System.out.println("是有顺序的");
        System.out.println("通过比较器Comparator和Comparable，实现排序");
        System.out.println("通过红黑树数据结构将定位时间复杂度缩减到 O(logN)");
    }

    /**
     * railgun
     * 2021/4/5 22:57
     * PS:简介 HashMap
     */
    @Test
    public void test7() {
        System.out.println("Map 集合接口的一种具体实现。根据键的 hashCode 确定存储位置，访问速度快。非线程安全的，使用 Collections.synchronizedMap 可以实现线程安全，或者使用 ConcurrentHashMap 实现线程安全。");
        System.out.println("数据结构：数组 + 链表 + 红黑树。");
        System.out.println("通过阈值和负载因子去控制扩容逻辑。");
        System.out.println("确定哈希桶数组数组索引位置：取 key 的 hashCode 值、高位运算、取模运算。");
        System.out.println("put 操作：索引位置为空新增、索引位置有值添加链表节点（超过8改成红黑树）、插入之后的长度大于阈值进行扩容");
        System.out.println("扩容操作：扩容大小为两倍扩容一次、依据负载因子计算出阈值");
        System.out.println("线程安全性，何时会发生死循环？：");
        System.out.println("JDK1.8 与 JDK1.7 性能对比：分散的 hash 值，性能优化在15%左右，不分散的 hash 值，优化程度很高，O(N) 和 O(logN) 的区别");
        Map hashMap = new HashMap(10, 0.75f);
        hashMap = Collections.synchronizedMap(hashMap);
        hashMap.put(1, "railgun");
        GsonUtil.objectSoutJson(hashMap);
    }

}
