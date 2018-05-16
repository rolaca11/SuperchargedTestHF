package io.supercharge.hf.entities;

import java.lang.reflect.Method;

public class InstanceUtils {

    public static Class<?> getParameterType(Entity entity, String property) {
        return getGetter(entity, property).getReturnType();
    }

    public static Method getGetter(Entity entity, String property) {
        try {
            return entity.getClass().getMethod("get" + property.substring(0, 1).toUpperCase() + property.substring(1));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getSetter(Entity entity, String property) {
        try {
            return entity.getClass().getMethod("set" + property.substring(0, 1).toUpperCase() + property.substring(1));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean equals(Object e1, Object e2) {
        if(e1 instanceof Entity && e2 instanceof Entity) {
            return e1.equals(e2);
        } else {
            return e1.toString().equals(e2.toString());
        }
    }
}
