package com.decode.leetcodebot.leetcode.service;

import com.decode.leetcodebot.leetcode.component.LeetCodeApiClient;
import com.decode.leetcodebot.leetcode.model.AllProblemsResponse;
import com.decode.leetcodebot.leetcode.model.Problem;
import com.decode.leetcodebot.leetcode.repository.ProblemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeetCodeService {

    private final LeetCodeApiClient leetCodeApiClient;
    private final ProblemRepository<Problem> repository;

    public void saveAllProblem() {
        log.info("Fetch all problems from the LeetCode");
        AllProblemsResponse allProblemsResponse = fetchAllProblems();
        List<Problem> problems = allProblemsResponse.getStat_status_pairs().stream()
                .filter(AllProblemsResponse.Problem::isFree)
                .map(p -> Problem.builder()
                        .id(p.getProblemId())
                        .title(p.getProblemTitle())
                        .pathKey(p.getProblemTitleSlug())
                        .difficulty(p.getDifficulty())
                        .build())
                .collect(Collectors.toList());
        repository.saveAll(problems);
    }

    private AllProblemsResponse fetchAllProblems() {
        String response = leetCodeApiClient.fetchAllProblems();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, AllProblemsResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
