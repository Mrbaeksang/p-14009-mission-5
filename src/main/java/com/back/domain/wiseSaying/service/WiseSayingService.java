package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import com.back.AppContext;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.Comparator;
import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository = AppContext.wiseSayingRepository;


    public WiseSaying register(String content, String author) {
        return wiseSayingRepository.register(content, author);
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);
        if (wiseSaying != null) {
            wiseSayingRepository.delete(wiseSaying);
        }
    }

    public void modify(int id, String content, String author) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);
        if (wiseSaying != null) {
            wiseSaying.setContent(content);
            wiseSaying.setAuthor(author);
        }
    }

    public Page<WiseSaying> findForList(String keyWordType, String keyword, Pageable pageable) {
        List<WiseSaying> all = wiseSayingRepository.findAll();

        List<WiseSaying> filtered = all.stream()
                .filter(ws -> switch (keyWordType) {
                    case "author" -> ws.getAuthor().contains(keyword);
                    case "content" -> ws.getContent().contains(keyword);
                    default -> true;
                })
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        int totalCount = filtered.size();

        List<WiseSaying> paged = filtered.stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNo(), pageable.getPageSize(), paged);


    }

    public void initSampleDataIfEmpty() {
        if (wiseSayingRepository.findAll().size() < 10) {
            for (int i = 1 ; i <= 10; i++) {
                register("명언 " + i, "작자미상 " + i);
            }
        }
    }
}
