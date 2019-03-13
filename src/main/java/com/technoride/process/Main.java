package com.technoride.process;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {
        try {
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
                         Process p = Runtime.getRuntime().exec
                                 (System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");
                         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                         String line="";
                         while((line= bufferedReader.readLine()) != null )
                         {
                             if (line.contains(processToKill))
                             {
                                 Process killProcess = Runtime.getRuntime()
                                         .exec(System.getenv("windir")+"\\system32\\taskkill /F /IM "+processToKill);

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
                  }
            }

        }
        catch (IOException io)
        {

        }
        catch (InterruptedException in)
        {

        }

    }
}
