package com.technoride.process;


import com.technoride.process.task.JobFindAndKill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args)
    {

            if (args.length != 1) {
                System.err.println("Invalid number of command line option");
                return;
            }
            else
            {
              String option =args[0];
              String [] keyValue = option.split("=");

              if (keyValue.length !=2)
              {
                  System.err.println("Invalid command format");
                  return;
              }
              else
                  if (!keyValue[0].equals("--process-name"))
                  {
                      System.err.println("Invalid command");
                  }
                  else
                  {
                     String processToKill = keyValue[1];
                     if (!processToKill.endsWith(".exe") && !processToKill.endsWith(".EXE"))
                     {
                         System.err.println("Not a valid process name");
                         return;
                     }
                     else
                     {
                         ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                         JobFindAndKill jobFindAndKill = new JobFindAndKill(processToKill);
                         scheduledExecutorService.scheduleAtFixedRate(jobFindAndKill,0,5,TimeUnit.MINUTES);

                     }
                  }
            }
    }
}
