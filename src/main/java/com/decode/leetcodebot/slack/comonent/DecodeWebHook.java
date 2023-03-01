package com.decode.leetcodebot.slack.comonent;

import com.decode.leetcodebot.slack.model.Payload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "decodeWebHook", url = "https://hooks.slack.com/services/")
public interface DecodeWebHook {

    @PostMapping(value = "${slack.workspace.decode.leetcode.key}", produces = MediaType.APPLICATION_JSON_VALUE)
    String postAlgorithmProblems(@RequestBody Payload text);
}
