package com.mycompany.reflection.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Main main = new Main();
        Class<?> cl = Main.class;
        Method[] methods = cl.getDeclaredMethods();
        for (Method m: methods){
            if (m.isAnnotationPresent(Test.class)){
             Test t = m.getAnnotation(Test.class);
             m.invoke(main, t.a(),t.b());
            }
        }

    }

    @Test(a = 2, b = 3)
    public void test1(int a, int b) {
        System.out.println("a = " + a + ", b = " + b);
    }

    @Test(a = 10, b = 20)
    public void test2(int a, int b) {
        System.out.println("a = " + a + ", b = " + b);
    }
}
