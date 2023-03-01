package com.decode.leetcodebot.leetcode.schedule;

import com.decode.leetcodebot.leetcode.service.LeetCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeetCodeProblemSaveTask {

    private final LeetCodeService leetCodeService;

    @PostConstruct
    public void init() {
        leetCodeService.saveAllProblem();
    }

    /*
    TODO : 주기적으로 LeetCode 에서 전체 문제 리스트를 가져올 필요가 있는경우 활성화
    public void run() {

    }
    */
}
