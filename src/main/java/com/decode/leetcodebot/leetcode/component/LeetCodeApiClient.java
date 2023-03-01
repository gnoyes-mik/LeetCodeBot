package com.decode.leetcodebot.leetcode.component;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "leetCodeApiClient", url = "https://leetcode.com/")
public interface LeetCodeApiClient {
    String ALL_PROBLEMS = "api/problems/all";

    @GetMapping(value = ALL_PROBLEMS)
    String fetchAllProblems();
    // TODO : Response Header인 content type이 'text/html' 형식이라 application/json 형식으로 받아지지 않음.
}
