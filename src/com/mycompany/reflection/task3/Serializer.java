package com.mycompany.reflection.task3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Serializer {

    public static void serialize(Object obj) {
        Class<?> classObject = obj.getClass();
        if (classObject.isAnnotationPresent(SaveTo.class)) {
            Field[] fields = classObject.getDeclaredFields();
            SaveTo annotation = classObject.getAnnotation(SaveTo.class);
            try (PrintWriter writer = new PrintWriter(new File(annotation.path()))) {

                deepSerialization(fields, writer, obj);

            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void deepSerialization(Field[] fields, PrintWriter writer, Object obj) throws IllegalAccessException, IOException {
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }
                if (hasAnnotations(field)) {
                    writer.println(field.getName() + "=");
                    writer.println("{");
                    deepSerialization(field.getType().getDeclaredFields(), writer, field.get(obj));
                    writer.println("}");
                } else {
                    writer.println(field.getName() + "=" + field.get(obj) + ";");
                }
            }
        }
    }

    private static boolean hasAnnotations(Field field) {
        return Arrays.stream(field.getType().getDeclaredFields()).
                filter(f -> f.isAnnotationPresent(Save.class)).findAny().isPresent();
    }


}



