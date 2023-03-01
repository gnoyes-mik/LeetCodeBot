package com.decode.leetcodebot.core;

import com.decode.leetcodebot.leetcode.model.Difficulty;
import com.decode.leetcodebot.leetcode.model.Problem;
import com.decode.leetcodebot.leetcode.repository.ProblemRepository;
import com.decode.leetcodebot.slack.comonent.DecodeWebHook;
import com.decode.leetcodebot.slack.model.Payload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemManagementService {

    @Value("${problem-manager.rate.easy:0}")
    private int easyCnt;
    @Value("${problem-manager.rate.medium:0}")
    private int mediumCnt;
    @Value("${problem-manager.rate.hard:0}")
    private int hardCnt;

    private final ProblemRepository<Problem> problemRepository;
    private final DecodeWebHook decodeWebHook;

    @PostConstruct
    private void init() {
        int totalProblem = easyCnt + mediumCnt + hardCnt;
        if (totalProblem < 0) {
            log.warn("Total problem count is negative! So it will be set default value(easy:1/medium:1/hard:0)");
            this.easyCnt = 1;
            this.mediumCnt = 1;
            this.hardCnt = 0;
            totalProblem = easyCnt + mediumCnt + hardCnt;
        }
    }


    public void postProblems() {
        List<Problem> problems = getProblems();
        String message = toText(problems);
        sendMessageToChannel(message);
    }

    private List<Problem> getProblems() {
        List<Problem> problems = new ArrayList<>();
        if (easyCnt > 0) {
            List<Problem> easyList = problemRepository.findByDifficultAndSolved(Difficulty.EASY, false);
            problems.addAll(randomDraw(easyList, easyCnt));
        }
        if (mediumCnt > 0) {
            List<Problem> mediumList = problemRepository.findByDifficultAndSolved(Difficulty.MEDIUM, false);
            problems.addAll(randomDraw(mediumList, mediumCnt));
        }
        if (hardCnt > 0) {
            List<Problem> hardList = problemRepository.findByDifficultAndSolved(Difficulty.HARD, false);
            problems.addAll(randomDraw(hardList, hardCnt));
        }

        problemRepository.bulkSolvedUpdate(problems);
        return problems;
    }

    private List<Problem> randomDraw(List<Problem> problems, int times) {
        List<Problem> result = new ArrayList<>();
        Random random = new Random();
        int bound = problems.size();
        Set<Integer> drew = new HashSet<>();

        for (int t = 0; t < times; t++) {
            int index = random.nextInt(bound);
            while (drew.contains(index)) {
                index = random.nextInt(bound);
            }
            drew.add(index);
            result.add(problems.get(index));
        }
        return result;
    }

    private String toText(List<Problem> problems) {
        String baseUrl = "https://leetcode.com/problems/";
        StringBuilder sb = new StringBuilder();
        sb.append("[테스트입니다]").append("\n");
        for (Problem problem : problems) {
            String line = String.format("[%s] %s (%s)", problem.getDifficulty(), problem.getTitle(), baseUrl + problem.getPathKey());
            sb.append(" - ").append(line).append("\n");
        }
        sb.append("!! 테스트중이므로 10초 마다 알고리즘이 추천됩니다 !!");
        return sb.toString();
    }

    private void sendMessageToChannel(String message) {
        Payload payload = new Payload(message);
        decodeWebHook.postAlgorithmProblems(payload);
    }

}
