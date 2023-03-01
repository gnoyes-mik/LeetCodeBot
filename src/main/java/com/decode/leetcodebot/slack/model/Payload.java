package com.decode.leetcodebot.slack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Payload implements Serializable {
    private static final long serialVersionUID = -4498168998405018053L;
    private String text;
}
