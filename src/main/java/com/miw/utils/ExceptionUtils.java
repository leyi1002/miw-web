package com.miw.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by J lai on 2017/4/17 0017.
 */
public class ExceptionUtils {

    /**
     * 打印堆栈信息的日志
     * @param e
     * @return
     */
    public static String toString(Throwable e){
        if(e == null){ return "";}
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    /**
     * 打印堆栈信息的日志
     * @param e
     * @return
     */
    public static String toString(String messge , Throwable e){
        if(e == null){ return "";}
        return messge+"; nested exception is \n"+toString(e);
    }

}
