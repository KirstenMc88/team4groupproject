package com.napier.team4groupproject;

import java.lang.reflect.Field;

public class Utilities {

    public static Object getPrivateField(Class<?> clazz, Object instance, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPrivateField(Class<?> clazz, Object instance, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            if (value == null){
                if (!field.getType().isPrimitive()){
                    field.set(instance, value);
                } else {
                    throw new IllegalArgumentException("Field " + fieldName + " cannot be set to null as it is a primitive type.");
                }
            } else {
                Class<?> argType = value.getClass();

                if (field.getType() == int.class && value instanceof Integer) {
                    field.setInt(instance, (int) value);
                } else if (field.getType().isAssignableFrom(argType)) {
                    field.set(instance, value);
                } else {
                    throw new IllegalArgumentException("Field " + fieldName + " is not of type " + argType);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
