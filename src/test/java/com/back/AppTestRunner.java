package com.back;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTestRunner {
    public static String run(String input) {
        // 입력 스트림 설정
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // 출력 스트림 캡처
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // App 실행
        new App().run();

        return out.toString();
    }
}
