package com.decode.leetcodebot.leetcode.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Problem {
    private Long id;
    private String title;
    private String pathKey;
    private Difficulty difficulty;
    private Boolean solved;


    public Problem() {
        this.solved = false;
    }

    @Builder
    public Problem(Long id, String title, String pathKey, Integer difficulty) {
        this.id = id;
        this.title = title;
        this.pathKey = pathKey;
        this.difficulty = Difficulty.of(difficulty);
        this.solved = false;
    }

    public void solve() {
        this.solved = true;
    }
}
