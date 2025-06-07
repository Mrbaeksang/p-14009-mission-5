package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록 명령 테스트")
     void t1() {
        String out = AppTestRunner.run("""
        등록
        죽음은 피할 수 없다.
        에픽테토스
        종료
    """);

        assertThat(out).contains("1번 명언이 등록되었습니다.");
    }





}
