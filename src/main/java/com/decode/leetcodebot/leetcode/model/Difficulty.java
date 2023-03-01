package com.decode.leetcodebot.leetcode.model;

import java.util.Arrays;

public enum Difficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private int level;

    Difficulty(int level) {
        this.level = level;
    }

    public static Difficulty of(int level) {
        return Arrays.stream(Difficulty.values())
                .filter(difficulty -> difficulty.level == level)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("레벨 %s은 존재하지 않습니다", level)));
    }
}
