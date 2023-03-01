package com.decode.leetcodebot.leetcode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * LeetCodeService API (https://leetcode.com/api/problems/all/) 응답 규격<br>
 * <pre>
 *  {
 *     "user_name": "",
 *     "num_solved": 0,
 *     "num_total": 2577,
 *     "ac_easy": 0,
 *     "ac_medium": 0,
 *     "ac_hard": 0,
 *     "stat_status_pairs": [
 *         {
 *             "stat": {
 *                 "question_id": 2714,
 *                 "question__article__live": null,
 *                 "question__article__slug": null,
 *                 "question__article__has_video_solution": null,
 *                 "question__title": "Left and Right Sum Differences",
 *                 "question__title_slug": "left-and-right-sum-differences",
 *                 "question__hide": false,
 *                 "total_acs": 21781,
 *                 "total_submitted": 24495,
 *                 "frontend_question_id": 2574,
 *                 "is_new_question": false
 *             }
 *         }
 *     ]
 *  }
 *  </pre>
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllProblemsResponse {
    private Integer num_total;
    private List<Problem> stat_status_pairs;


    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Problem {
        private ProblemDetail stat;
        private Difficulty difficulty;
        private Boolean paid_only;

        public Long getProblemId() {
            return this.stat.question_id;
        }

        public String getProblemTitle() {
            return this.stat.question__title;
        }

        public String getProblemTitleSlug() {
            return this.stat.question__title_slug;
        }

        public Integer getDifficulty() {
            return this.difficulty.level;
        }

        public boolean isFree() {
            return !this.paid_only;
        }
    }

    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ProblemDetail {
        private Long question_id;
        private String question__title;
        private String question__title_slug;
    }

    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Difficulty {
        private Integer level;
    }
}
