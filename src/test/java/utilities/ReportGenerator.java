package utilities;

import java.io.IOException;

public class ReportGenerator {

    public static void runAllureBatchFile() {
        String batFile = System.getProperty("user.dir") + "\\allure-report_run.bat";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c start " + batFile);
            process.waitFor(); // Optional wait
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
