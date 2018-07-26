package com.mycompany.reflection.task3;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Serializer {

    public static void serialize(Object obj, String path) throws IOException, IllegalAccessException {
        Class<?> cl = obj.getClass();

        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {

            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Save.class)) {
                    continue;
                }

                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }

                writer.write(field.getName());
                writer.write(":");

                if (field.getType() == Integer.class || field.getType() == int.class) {
                    writer.write(String.valueOf(field.getInt(obj)));
                }else  if (field.getType() == String.class) {
                    writer.write(String.valueOf(field.get(obj)));
                }else {
                    writer.write(String.valueOf(field.get(obj)));
                }


                writer.write(";");

            }


        }

    }
}
