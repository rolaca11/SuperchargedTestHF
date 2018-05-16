package io.supercharge.hf.entities;

import java.lang.reflect.Method;

public interface Entity {

    default <T> T getValue(String property) {
        Class<?> entityClass = getClass();

        Method getter = null;

        try {
            getter = InstanceUtils.getGetter(this, property);

            //noinspection unchecked
            return (T) getter.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default void setValue(String property, Object value) {
        Class<?> entityClass = getClass();

        Method setter = null;

        try {
            setter = InstanceUtils.getSetter(this, property);

            //noinspection unchecked
            setter.invoke(this, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
