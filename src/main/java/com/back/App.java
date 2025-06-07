package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

import static com.back.AppContext.scanner;
import static com.back.AppContext.systemController;

public class App {
    private final Scanner sc = scanner;
    private final WiseSayingController controller = AppContext.wiseSayingController;
    private final SystemController systemController = AppContext.systemController;

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);
            String actionName = rq.getActionName();

            switch (actionName) {
                case "등록" -> controller.register();
                case "목록" -> controller.list(rq);
                case "삭제" -> controller.delete(rq);
                case "수정" -> controller.modify(rq);
                case "종료" -> {
                    systemController.exit();
                    return;
                }
                default -> System.out.println("지원하지 않는 명령입니다.");
            }
        }
    }
}
