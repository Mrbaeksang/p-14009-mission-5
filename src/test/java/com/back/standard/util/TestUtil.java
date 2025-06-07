package com.back.standard.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TestUtil {
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static PrintStream CURRENT_OUT;

    /**
     * 입력 문자열을 InputStream으로 변환하여 System.in에 주입할 때 사용
     */
    public static InputStream genInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    /**
     * System.out을 ByteArrayOutputStream으로 리다이렉팅하여 출력 캡처
     */
    public static ByteArrayOutputStream setOutToCapture() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CURRENT_OUT = new PrintStream(outputStream, true);
        System.setOut(CURRENT_OUT);
        return outputStream;
    }

    /**
     * System.out을 원래대로 복구
     */
    public static void clearOutCapture() {
        System.setOut(ORIGINAL_OUT);
        if (CURRENT_OUT != null) {
            CURRENT_OUT.close();
            CURRENT_OUT = null;
        }
    }
}
