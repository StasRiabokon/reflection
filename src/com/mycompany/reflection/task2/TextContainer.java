package com.mycompany.reflection.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "temp", name = "example.txt")
public class TextContainer {

    private String text = "some text";

    @Saver
    public void save(String path, String name) throws IOException {

        File file = new File(path + File.separator + name);

        file.getParentFile().mkdirs();
        file.createNewFile();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        }
    }


}
