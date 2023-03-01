package com.decode.leetcodebot.core.schedule;

import com.decode.leetcodebot.core.ProblemManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostProblemsToSlack {
    private final ProblemManagementService problemManagementService;

    @Scheduled(cron = "${slack.workspace.decode.leetcode.cron}")
    public void run() {
        problemManagementService.postProblems();
    }
}
