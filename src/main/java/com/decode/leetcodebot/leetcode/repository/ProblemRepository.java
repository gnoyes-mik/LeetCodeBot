package com.decode.leetcodebot.leetcode.repository;

import com.decode.leetcodebot.leetcode.model.Difficulty;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository<Problem> {

    Boolean save(Problem problem);

    Boolean saveAll(List<Problem> problems);

    Optional<Problem> findById(Long id);

    List<Problem> findAll();


    // ---------------------------------------------------------------------- //
    List<Problem> findAllBySolved(boolean solved);
    List<Problem> findByDifficult(Difficulty difficulty);
    List<Problem> findByDifficultAndSolved(Difficulty difficulty, boolean solved);

    void bulkSolvedUpdate(List<Problem> problems);
}
