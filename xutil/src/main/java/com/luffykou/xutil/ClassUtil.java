package com.luffykou.xutil;

import java.lang.reflect.Constructor;
import java.util.Collection;

public class ClassUtil {

    private ClassUtil() {
    }

    /**
     * 根据类获取对象：不再必须一个无参构造
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T newInstance(Class<T> clazz) throws Exception {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            Class[] cls = c.getParameterTypes();
            if (cls.length == 0) {
                c.setAccessible(true);
                return (T) c.newInstance();
            } else {
                Object[] objects = new Object[cls.length];
                for (int i = 0; i < cls.length; i++) {
                    objects[i] = getDefaultPrimitiveValue(cls[i]);
                }
                c.setAccessible(true);
                return (T) c.newInstance(objects);
            }
        }
        return null;
    }

    private static Object getDefaultPrimitiveValue(Class clazz) {
        if (clazz.isPrimitive()) {
            return clazz == boolean.class ? false : 0;
        }
        return null;
    }

    public static boolean isCollection(Class clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    public static boolean isArray(Class clazz) {
        return clazz.isArray();
    }
}
