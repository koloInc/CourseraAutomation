package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileUtils {

    public static void writeToTextFile(String fileName, String content, boolean append) {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
