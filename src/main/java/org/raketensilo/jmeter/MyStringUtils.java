package org.raketensilo.jmeter;

public class MyStringUtils {

    public static String useSystemLineSeparator(String str) {

        return str.
                replaceAll("\r\n", "\n").
                replaceAll("\n", System.getProperty("line.separator"));

    }

}
