package com.mycompany.reflection.task3;

import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {

        User user = new User(1, "Stas", "Ryabokon", new Date(), "Kyiv");
        Serializer.serialize(user, "temp/user.txt");

    }


}
