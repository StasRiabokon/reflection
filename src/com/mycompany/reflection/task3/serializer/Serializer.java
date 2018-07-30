package com.mycompany.reflection.task3.serializer;

import com.mycompany.reflection.task3.annotations.Save;
import com.mycompany.reflection.task3.annotations.SaveTo;

import java.io.*;
import java.lang.reflect.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

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

    private static void deepSerialization(Field[] fields, PrintWriter writer, Object obj) throws IllegalAccessException, IOException {

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

    public static Object deserialize(Class<?> cl) {
        if (!cl.isAnnotationPresent(SaveTo.class)) {
            System.out.println("Class is not annotated with SaveTo!");
            return null;
        }
        Object object = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(cl.getAnnotation(SaveTo.class).path()))) {
            object = deepDeserialization(cl, getObject(cl), reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;

    }

    private static Object getObject(Class<?> cl) {

        Constructor constructor = null;
        Object obj = null;
        try {
            constructor = cl.getConstructor();
            obj = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;

    }

    private static Object deepDeserialization(Class<?> cl, Object object, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {

            StringTokenizer tokenizer = new StringTokenizer(line, "=");

            String nextToken = tokenizer.nextToken();
            String field = nextToken;
            String value;

            if (!tokenizer.hasMoreTokens()) {
                value = "";
            } else {
                nextToken = tokenizer.nextToken();
                value = nextToken.substring(0, nextToken.length() - 1);
            }

            try {
                fillObject(field, value, cl, object);

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return object;
    }

    private static Object fillObject(String field, String value, Class<?> cl, Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, ParseException {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            if (Modifier.isPrivate(m.getModifiers())) {
                m.setAccessible(true);
            }
            String fieldName = field.substring(0, 1).toUpperCase() + field.substring(1);
            if (m.getName().substring(3, m.getName().length()).equals(fieldName)) {
                m = object.getClass().getMethod("set" + fieldName, cl.getDeclaredField(field).getType());
                m.invoke(object, toObject(cl.getDeclaredField(field).getType(), value));
            }
        }

        return object;

    }

    private static Object toObject(Class clazz, String value) throws ParseException {
        if (Boolean.class == clazz || boolean.class == clazz) return Boolean.parseBoolean(value);
        if (Byte.class == clazz || byte.class == clazz) return Byte.parseByte(value);
        if (Short.class == clazz || short.class == clazz) return Short.parseShort(value);
        if (Integer.class == clazz || int.class == clazz) return Integer.parseInt(value);
        if (Long.class == clazz || long.class == clazz) return Long.parseLong(value);
        if (Float.class == clazz || float.class == clazz) return Float.parseFloat(value);
        if (Double.class == clazz || double.class == clazz) return Double.parseDouble(value);
        if (Date.class == clazz) {
            return new Date();//TODO: must return date value from the file
        }
        return value;
    }


}



