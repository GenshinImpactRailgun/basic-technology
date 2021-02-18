package com.basic.comon.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author: railgun
 * 2021/2/2 10:24
 * PS:gson 工具类
 */
public class GsonUtil {

    /**
     * @Author: railgun
     * 2021/2/2 10:32
     * PS:静态内部类获取 gson 对象
     */
    private static class StaticInside {
        private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * railgun
     * 2021/2/2 10:45
     * PS:禁用用户主动创建实例
     */
    private GsonUtil() {
    }

    /**
     * railgun
     * 2021/2/2 10:58
     * PS:对象转换成 json
     */
    public static String objectToJson(Object object) {
        return StaticInside.gson.toJson(object);
    }

    /**
     * railgun
     * 2021/2/18 15:48
     * PS:控制台中将对象打印成 json
     */
    public static void objectSoutJson(Object object) {
        System.out.println(StaticInside.gson.toJson(object));
    }

    /**
     * railgun
     * 2021/2/2 10:58
     * PS:json 转换成对象
     */
    public static <T> T jsonToObject(String json, Class<T> objClass) {
        return StaticInside.gson.fromJson(json, objClass);
    }

    /**
     * PS:json 转换成指定泛型的 list
     *
     * @param json  json
     * @param clazz 泛型
     * @Author: only my railgun10:25 2021/2/2
     * @return: java.util.List<T>
     */
    public static <T> List<T> jsonToList(String json, Class clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = StaticInside.gson.fromJson(json, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
