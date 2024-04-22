package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume("Andres L");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);

        System.out.println("\ninvoke toString");
        Method method1 = r.getClass().getDeclaredMethod("toString");
        method1.setAccessible(true);
        System.out.println(method1.invoke(r));
        Method method2 = r.getClass().getDeclaredMethods()[1];
        method2.setAccessible(true);
        System.out.println(method2.invoke(r));
    }
}
