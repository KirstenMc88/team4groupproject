package com.napier.team4groupproject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The {@code Utilities} contains methods so that java unit tests and integration tests can be run
 * contains methods to access class attributes, field and methods that would normally not be exposed
 */

public class Utilities {

    /**
     * Get private field method
     *
     * <p>This method returns the value of a private field using reflection.</p>
     *
     * @param clazz the class in which the field is
     * @param instance the instance of the class from which the value is wanted, null if static
     * @param fieldName the name of the field
     * @return object containing the value of the field
     */
    public static Object getPrivateField(Class<?> clazz, Object instance, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set private field method
     *
     * <p>This method can set a private field to a different value using reflection.</p>
     *
     * @param clazz the class in which the field is
     * @param instance the instance of the class from which the value is wanted, null if static
     * @param fieldName the name of the field
     * @param value the value to which the field will be set
     *
     * @implNote This is a work in progress and might not work for all data types yet!
     */
    public static void setPrivateField(Class<?> clazz, Object instance, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            // check for null
            if (value == null){
                // check field is nullable
                if (!field.getType().isPrimitive()){
                    field.set(instance, value);
                } else {
                    throw new IllegalArgumentException("Field " + fieldName + " cannot be set to null as it is a primitive type.");
                }
            } else {
                // get type of value
                Class<?> argType = value.getClass();

                // handle assigning Integer value to int field
                if (field.getType() == int.class && value instanceof Integer) {
                    field.setInt(instance, (int) value);

                }
                // handle general assignable forms
                else if (field.getType().isAssignableFrom(argType)) {
                    field.set(instance, value);
                } else {
                    throw new IllegalArgumentException("Field " + fieldName + " is not of type " + argType);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Call private method
     *
     * <p>This method can call a private method.</p>
     *
     * @param clazz the class in which the field is
     * @param instance the instance of the class from which the value is wanted, null if static
     * @param methodName name of the method being called
     * @param argTypesReceived the classes of the different arguments to be passed to the method
     * @param args the arguments to be passed to the method that is being called
     *
     * @implNote This is a work in progress and might not work for all methods yet!
     */
    public static Object callPrivateMethod(Class<?> clazz, Object instance, String methodName, Class<?>[] argTypesReceived, Object ... args) throws InvocationTargetException {
        Class<?>[] argTypes = new Class[args.length];

        if(argTypesReceived == null){
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
        } else {
            argTypes = argTypesReceived;
        }

        try {
            Method method = clazz.getDeclaredMethod(methodName, argTypes);
            method.setAccessible(true);
            if (method.getReturnType().equals(Void.TYPE)) {
                method.invoke(instance, args);
                return null;
            } else{
                return method.invoke(instance, args);
            }
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
