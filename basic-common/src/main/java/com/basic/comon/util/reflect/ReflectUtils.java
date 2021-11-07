package com.basic.comon.util.reflect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * @author railgun
 * 2021/11/7 16:00
 * 反射工具类
 */
@Slf4j
public class ReflectUtils {

    private ReflectUtils() {
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param object         : 子类对象
     * @param methodName     : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @return 父类中的方法对象
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz=clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected, default)
     *
     * @param object         : 子类对象
     * @param methodName     : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @param parameters     : 父类中的方法参数
     * @return 父类中方法的执行结果
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes,
                                      Object[] parameters) {
        // 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        // 抑制Java对方法进行检查,主要是针对私有方法而言
        method.setAccessible(true);
        try {
            if (null != method) {
                // 调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }

        return null;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @param value     : 将要设置的值
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {

        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);

        // 抑制Java对其的检查
        field.setAccessible(true);

        try {
            // 将 object 中 field 所代表的值 设置为 value
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @return : 父类中的属性值
     */
    public static Object getFieldValue(Object object, String fieldName) {

        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);

        // 抑制Java对其的检查
        field.setAccessible(true);

        try {
            // 获取 object 中 field 所代表的属性值
            return field.get(object);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
     * 【参数至多支持八个】
     **/
    public static Object getDataByMethod(Object object, String methodName, Integer parameterNumber, List<Class<?>> parameterTypes, List<Object> args) {
        Object result = null;
        try {
            Method method;
            switch (parameterNumber) {
                case 0:
                    method = object.getClass().getMethod(methodName);
                    result = method.invoke(object);
                    break;
                case 1:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0));
                    result = method.invoke(object, args.get(0));
                    break;
                case 2:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1));
                    result = method.invoke(object, args.get(0), args.get(1));
                    break;
                case 3:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1), parameterTypes.get(2));
                    result = method.invoke(object, args.get(0), args.get(1), args.get(2));
                    break;
                case 4:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1), parameterTypes.get(2), parameterTypes.get(3));
                    result = method.invoke(object, args.get(0), args.get(1), args.get(2), args.get(3));
                    break;
                case 5:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1), parameterTypes.get(2), parameterTypes.get(3), parameterTypes.get(4));
                    result = method.invoke(object, args.get(0), args.get(1), args.get(2), args.get(3), args.get(4));
                    break;
                case 6:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1), parameterTypes.get(2), parameterTypes.get(3), parameterTypes.get(4), parameterTypes.get(5));
                    result = method.invoke(object, args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5));
                    break;
                case 7:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1), parameterTypes.get(2), parameterTypes.get(3), parameterTypes.get(4), parameterTypes.get(5), parameterTypes.get(6));
                    result = method.invoke(object, args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6));
                    break;
                case 8:
                    method = object.getClass().getMethod(methodName, parameterTypes.get(0), parameterTypes.get(1), parameterTypes.get(2), parameterTypes.get(3), parameterTypes.get(4), parameterTypes.get(5), parameterTypes.get(6), parameterTypes.get(7));
                    result = method.invoke(object, args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6), args.get(7));
                    break;
                default:
                    break;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 2021/8/6 9:19 @railgun wrapper 查询方法名称
     **/
    private static final String QUERY_WRAPPER_METHOD_NAME = "list";


    /**
     * railgun
     * 2021/8/6 9:33
     * PS: 获取对象字段值
     **/
    public static Object getFieldData(Object object, String fieldName) {
        Object result = null;
        try {
            Field fieldSelf = object.getClass().getDeclaredField(fieldName);
            fieldSelf.setAccessible(true);
            result = fieldSelf.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * railgun
     * 2021/8/6 9:33
     * PS: 获取对象字段值【字符串类型】
     **/
    public static String getFieldDataString(Object object, String fieldName) {
        String result = "";
        try {
            Field fieldSelf = object.getClass().getDeclaredField(fieldName);
            boolean isAccessible = fieldSelf.isAccessible();
            fieldSelf.setAccessible(true);
            if (null != fieldSelf.get(object)) {
                result = fieldSelf.get(object).toString();
            }
            fieldSelf.setAccessible(isAccessible);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("无该字段：{}", fieldName);
        }
        return result;
    }

    /**
     * railgun
     * 2021/8/6 14:59
     * PS: 设置字段值
     **/
    public static void setFieldData(Object object, String fieldName, Object value) {
        try {
            Field fieldOther = object.getClass().getDeclaredField(fieldName);
            fieldOther.setAccessible(true);
            fieldOther.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
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

    /**
     * railgun
     * 2021/10/16 23:56
     * PS: 获取对象字段中指定注解注释的第一个字段
     * TODO 需要声明泛型方法，但是需要返回 String 类型返回值，别扭，之后用 Class.forName 改一下，如果没有其他更好的方法的话
     */
    public static String reflectGetAnnotationFirstFieldValue(Object object, Class annotationClass) {
        String result = "";
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            Annotation fieldAnnotation = declaredField.getAnnotation(annotationClass);
            if (null != fieldAnnotation) {
                try {
                    boolean isAccessible = declaredField.isAccessible();
                    result = declaredField.get(object).toString();
                    declaredField.setAccessible(isAccessible);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/10/28
     * 给类静态字段赋值
     */
    public static <T> void setStaticField(Class<T> clazz, String fieldName, Object object) {
        try {
            // 获取静态属性
            Field field = clazz.getDeclaredField(fieldName);
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            // 给静态属性赋值
            field.set(null, object);
            field.setAccessible(accessible);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * railgun
     * 2021/11/4
     * 获取字段上的数据库注释注解值
     */
    private static String getFieldCommentByAnnotationTypeAndMemberName(Field field, String commentAnnotationTypeName, String commentAnnotationMemberName) {
        String comment = "";
        Annotation[] annotations = field.getAnnotations();
        if (ArrayUtils.isNotEmpty(annotations)) {
            for (Annotation annotation : annotations) {
                if (StringUtils.equals(commentAnnotationTypeName, annotation.annotationType().getName())) {
                    comment = getAnnotationMemberValueByMemberName(annotation, commentAnnotationMemberName);
                    break;
                }
            }
        }
        return comment;
    }

    /**
     * 2021/11/5 railgun 注解实现类 map 值名称
     */
    private static final String MEMBER_VALUES = "memberValues";

    /**
     * railgun
     * 2021/11/4
     * 获取注解成员数据，依据成员名称
     */
    private static String getAnnotationMemberValueByMemberName(Annotation annotation, String memberName) {
        String memberValue = "";
        try {
            InvocationHandler h = Proxy.getInvocationHandler(annotation);
            // 获取 AnnotationInvocationHandler 的 memberValues 字段
            Field hField = h.getClass().getDeclaredField(MEMBER_VALUES);
            // 因为这个字段事 private final 修饰，所以要打开权限
            hField.setAccessible(true);
            // 获取 memberValues
            Map<String, String> memberValues = (Map<String, String>) hField.get(h);
            memberValue = MapUtils.getString(memberValues, memberName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return memberValue;
    }

}
