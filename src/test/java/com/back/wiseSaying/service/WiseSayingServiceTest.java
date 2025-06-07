package com.back.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WiseSayingServiceTest {

    WiseSayingService service;

    @BeforeEach
    void setUp() {
        AppContext.wiseSayingRepository.clear(); // 저장소 초기화
        service = new WiseSayingService();       // 매 테스트마다 새로 생성
    }

    @Test
    @DisplayName("명언 등록 시 id, 내용, 작가가 올바르게 저장된다")
    void t1() {
        WiseSaying wiseSaying = service.register("행동이 운명을 만든다", "존 듀이");

        assertNotNull(wiseSaying);
        assertEquals("행동이 운명을 만든다", wiseSaying.getContent());
        assertEquals("존 듀이", wiseSaying.getAuthor());
        assertTrue(wiseSaying.getId() > 0);
    }

    @Test
    @DisplayName("author 타입으로 검색하면 작가명에 keyword가 포함된 명언만 조회된다")
    void t2() {
        service.register("행동이 운명을 만든다", "존 듀이");
        service.register("과거에 집착하지 마라", "작자미상");

        List<WiseSaying> result = service.findForList("author", "작자", 1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthor()).isEqualTo("작자미상");
    }

    @Test
    @DisplayName("content 타입으로 검색하면 명언 내용에 keyword가 포함된 명언만 조회된다")
    void t3() {
        service.register("행동이 운명을 만든다", "존 듀이");
        service.register("과거에 집착하지 마라", "작자미상");

        List<WiseSaying> result = service.findForList("content", "행동", 1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getContent()).contains("행동");
    }

    @Test
    @DisplayName("작가명에만 '작자'가 포함된 경우만 필터링 된다")
    void t4() {
        service.register("작자는 운명을 바꾼다", "김삿갓");
        service.register("사랑은 모든 것을 이긴다", "작자미상");

        List<WiseSaying> result = service.findForList("author", "작자", 1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthor()).isEqualTo("작자미상");
    }

    @Test
    @DisplayName("앱 시작 시 명언이 없으면 샘플 10개를 자동 생성한다")
    void t5() {
        service.initSampleDataIfEmpty();

        int totalCount = AppContext.wiseSayingRepository.findAll().size();
        assertThat(totalCount).isEqualTo(10);
    }

    @Test
    @DisplayName("페이징: 페이지 1에는 최신순으로 5개의 명언이 출력된다")
    void t6() {
        service.initSampleDataIfEmpty();

        List<WiseSaying> result = service.findForList("", "", 1);

        assertThat(result).hasSize(5);
        assertThat(result.get(0).getContent()).isEqualTo("명언 10");
        assertThat(result.get(4).getContent()).isEqualTo("명언 6");
    }

}
