package com.java.jvm;

import com.alibaba.fastjson.JSON;
import com.basic.comon.util.GsonUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        String classNameUser = "com.java.jvm.service.impl.UserServiceImpl";
        // 获取接口实现类实例化对象
        Object objectUserService = getObjectByClassName(classNameUser);
        // 数据库取出数据
        Object dataSelf = reflectGetData(objectUserService, "findByForeignId", "proid", new Object());
        // 组织对方需要的数据
        String classNamePeopleEntity = "com.java.jvm.entity.People";
        Object dataOther = GsonUtil.jsonToObject(GsonUtil.objectToJson(dataSelf), getClassByName(classNamePeopleEntity));
        // 特殊主键等赋值
        reflectAssignment(dataSelf, dataOther, "userId", "peopleId");


        // 数据库保存数据
        String classNamePeople = "com.java.jvm.service.impl.PeopleServiceImpl";
        // 实例化 people 接口实现类
        Object objectPeopleService = getObjectByClassName(classNamePeople);
        reflectSaveData(objectPeopleService, dataOther, "saveOrUpdate");
    }

    /**
     * railgun
     * 2021/8/5 23:33
     * PS: 根据全类名获取 class
     **/
    public static Class<?> getClassByName(String className) {
        Class<?> result = null;
        try {
            result = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * railgun
     * 2021/8/5 16:08
     * PS: 通过全类名获取对象
     **/
    public static Object getObjectByClassName(String className) {
        Object object = null;
        try {
            Class<?> clz = Class.forName(className);
            object = clz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * railgun
     * 2021/8/5 16:25
     * PS: 反射方法获取数据
     **/
    public static Object reflectGetData(Object object, String methodName, String key, Object value) {
        Object result = null;
        try {
            Method method = object.getClass().getMethod(methodName, String.class, Object.class);
            result = method.invoke(object, key, value);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * railgun
     * 2021/8/5 17:08
     * PS: 反射方法保存对象
     **/
    public static void reflectSaveData(Object objectPeopleService, Object object, String methodName) {
        try {
            Method method = objectPeopleService.getClass().getMethod(methodName, Object.class);
            method.invoke(objectPeopleService, object);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * railgun
     * 2021/8/5 17:12
     * PS: 反射赋值
     **/
    public static void reflectAssignment(Object dataSelf, Object dataOther, String nameSelf, String nameOther) {
        try {
            // 取出属性值
            Field fieldSelf = dataSelf.getClass().getDeclaredField(nameSelf);
            fieldSelf.setAccessible(true);
            Object value = fieldSelf.get(dataSelf);

            // 设置属性值
            Field fieldOther = dataOther.getClass().getDeclaredField(nameOther);
            fieldOther.setAccessible(true);
            fieldOther.set(dataOther, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static class GcListenDemo {
        @Override
        public void finalize() {
            System.out.println("GcListenDemo finalize 被回收，回收方法执行");
        }
    }

    /**
     * railgun
     * 2021/5/15 16:12
     * PS: 垃圾回收触发方法
     * 1、主程序需阻塞，否则 GC 线程会因为主线程退出而结束不再执行垃圾回收操作
     * 2、禁止重写 finalize 方法，会导致整个 GC 时间延长，而导致更加频繁的 FGC 更严重的造成 OOM
     **/
    @SneakyThrows
    @org.junit.jupiter.api.Test
    public void test1() {
        GcListenDemo gcListenDemo = new GcListenDemo();
        gcListenDemo = null;
        System.gc();

        // 加阻塞。因为 GC 是守护线程，主线程结束之后，GC 线程也就结束了，不一定来得及进行垃圾回收
        System.in.read();
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        // 【key、value】【uuid、文件名】
        Map<String, String> map = new HashMap<>();
        String uuid = "454564654", filename = "星期一.pdf";
        map.put(uuid, filename);
        String uuid2 = "5648789", filename2 = "回收利用确认书.pdf";
        map.put(uuid2, filename2);
        String uuid3 = "686876868", filename3 = "一百块钱换个锁.pdf";
        map.put(uuid3, filename3);
        String json = JSON.toJSONString(map);
        System.out.println(json);

        Map<String, String> mapJson = (Map<String, String>) JSON.parse(json);
        System.out.println("dfdfd");
    }

}
