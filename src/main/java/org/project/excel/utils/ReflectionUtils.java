package org.project.excel.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ReflectionUtils {
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> clazzInClasses : getAllClassesIncludingSuperClasses(clazz, true)) {
            fields.addAll(Arrays.asList(clazzInClasses.getDeclaredFields()));
        }
        return fields;
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Annotation> getAllAnnotations(Field field) {
        return Arrays.asList(field.getAnnotations());
    }

    public static Annotation getAnnotation(Field field, Class<? extends Annotation> target) {
        return field.getAnnotation(target);
    }

    public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation> target) {
        for (Class<?> clazzInClasses : getAllClassesIncludingSuperClasses(clazz, true)) {
            if (clazzInClasses.isAnnotationPresent(target)) {
                return clazzInClasses.getAnnotation(target);
            }
        }
        return null;
    }

    private static List<Class<?>> getAllClassesIncludingSuperClasses(Class<?> clazz, boolean fromSuper) {
        List<Class<?>> classes = new ArrayList<>();
        while (clazz != null) {
            classes.add(clazz);
            clazz = clazz.getSuperclass();
        }
        if (fromSuper) {
            Collections.reverse(classes);
        }
        return classes;
    }
}
