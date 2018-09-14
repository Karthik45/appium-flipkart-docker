package utils;

import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ADBLogGeneration {

    private String adbOutput;

    public void deviceLog() throws Exception {
        if (SystemUtils.IS_OS_LINUX) {
            String adbLog = "adb logcat";

            Process p = Runtime.getRuntime().exec(adbLog);
//            p.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            adbOutput = "";

            while ((line = buf.readLine()) != null) {
                adbOutput += line + "\n";
            }
        }
    }

    public void generateADBLog() throws Exception {

        File file = new File(System.getProperty("user.dir") + "/logs/adbLog.log");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] b = adbOutput.getBytes();
        fileOutputStream.write(b);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
