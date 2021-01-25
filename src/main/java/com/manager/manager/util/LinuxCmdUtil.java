package com.manager.manager.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author by jiaopeitao
 * @date on 2018/4/24.
 * @Description:
 */

public class LinuxCmdUtil {

    private static Logger log = LoggerFactory.getLogger(LinuxCmdUtil.class);

    public static List<String> exec(String command) {
        Process process = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        InputStreamReader errorInputStreamReader = null;
        BufferedReader errorBufferedReader = null;
        try {

            log.info("LinuxCmdUtil exec start command " + command);
            process = Runtime.getRuntime().exec(command);

            String line = null;

            inputStreamReader = new InputStreamReader (process.getInputStream ());
            bufferedReader = new BufferedReader (inputStreamReader);

            List<String> processList = new ArrayList<String>();
            while ((line = bufferedReader.readLine()) != null) {
                processList.add(line);
            }
            log.info("LinuxCmdUtil exec linux command {} return {}", command, processList);

            int exitVal = process.waitFor ();
            log.info("LinuxCmdUtil Process exitValue: " + exitVal);

            if(exitVal != 0) {
                errorInputStreamReader = new InputStreamReader (process.getErrorStream ());
                errorBufferedReader = new BufferedReader (errorInputStreamReader);
                List<String> errList = new ArrayList<String>();
                while ( (line = errorBufferedReader.readLine ()) != null) {
                    errList.add(line);
                }

                log.info("LinuxCmdUtil exec linux error stream {},  ", errList);
            }

            return processList;

        } catch (Exception e) {
            log.error("LinuxCmdUtil exec cmd catch exception", e);

        } finally {
            log.info("LinuxCmdUtil exec end command " + command);
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error("bufferedReader close error" , e);
                }
            }
            if(inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    log.error("inputStreamReader close error" , e);
                }
            }
            if(errorBufferedReader != null) {
                try {
                    errorBufferedReader.close();
                } catch (IOException e) {
                    log.error("errorBufferedReader close error" , e);
                }
            }
            if(errorInputStreamReader != null) {
                try {
                    errorInputStreamReader.close();
                } catch (IOException e) {
                    log.error("errorInputStreamReader close error" , e);
                }
            }
            if(process != null) {
                process.destroy();
            }
        }
        return null;
    }

}
