package com.technoride.process.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JobFindAndKill implements Runnable
{
    private String processToKill;


    public JobFindAndKill(String processToKill) {
        this.processToKill = processToKill;
    }

    @Override
    public void run() {
        try {
            Process p = Runtime.getRuntime().exec
                    (System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(processToKill)) {
                    Process killProcess = Runtime.getRuntime()
                            .exec(System.getenv("windir") + "\\system32\\taskkill /F /IM " + processToKill);

                    BufferedReader killBufferedReader = new BufferedReader(new InputStreamReader(killProcess.getInputStream()));
                    Thread.sleep(1000);
                    String outputLine = killBufferedReader.readLine();
                    if (outputLine.contains("SUCCESS"))
                        System.out.println("Process Killed Successfully");
                    else
                        System.out.println("Process Not Killed Successfully");
                }
            }
        }
        catch (IOException ioe)
        {
              ioe.printStackTrace();
        }
        catch (InterruptedException in)
        {
            in.printStackTrace();
        }
    }
}
