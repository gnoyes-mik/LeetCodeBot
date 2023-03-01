package com.decode.leetcodebot.leetcode.repository;

import com.decode.leetcodebot.leetcode.model.Difficulty;
import com.decode.leetcodebot.leetcode.model.Problem;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProblemMemoryRepository implements ProblemRepository<Problem> {

    private final Map<Long, Problem> map = new HashMap<>();

    public Boolean saveAll(List<Problem> problems) {
        for (Problem problem : problems) {
            save(problem);
        }
        return true;
    }

    public Boolean save(Problem problem) {
        map.put(problem.getId(), problem);
        return true;
    }

    public Optional<Problem> findById(Long id) {
        return Optional.of(map.get(id));
    }

    public List<Problem> findAll() {
        return new ArrayList<>(map.values());
    }

    public List<Problem> findAllBySolved(boolean solved) {
        return map.values().stream()
                .filter(p -> p.getSolved() == solved)
                .collect(Collectors.toList());
    }

    public List<Problem> findByDifficult(Difficulty difficulty) {
        return findAll().stream()
                .filter(p -> p.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    public List<Problem> findByDifficultAndSolved(Difficulty difficulty, boolean solved) {
        return findAll().stream()
                .filter(p -> p.getDifficulty() == difficulty)
                .filter(p -> p.getSolved() == solved)
                .collect(Collectors.toList());
    }

    public void bulkSolvedUpdate(List<Problem> problems) {
        problems.stream()
                .filter(Objects::nonNull)
                .filter(p -> Objects.nonNull(p.getId()))
                .forEach(p -> {
                    Problem problem = map.get(p.getId());
                    problem.solve();
                });
    }
}
